//	This file is part of ZebraViews.
//
//	Copyright 2012 AnimatedJuzz <kazasrinivas3@gmail.com>
//
//	ZebraViews is free software: you can redistribute it and/or modify
//	under the terms of the GNU General Public License as published by
//	the Free Software Foundation, either version 3 of the License, or
//	(at your option) any later version.
//
//	ZebraViews is distributed in the hope that it will be useful,
//	but WITHOUT ANY WARRANTY; without even the implied warranty of
//	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//	GNU General Public License for more details.
//
//	You should have received a copy of the GNU General Public License
//	along with ZebraViews.  If not, see <http://www.gnu.org/licenses/>.

package com.animatedjuzz.zebraviews;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;

import com.zebraviews.reviews.ReviewsCompiler;
import com.zebraviews.reviews.ReviewsData;
import com.zebraviews.reviews.preprocessor.PreprocessingData;
import com.zebraviews.reviews.preprocessor.Preprocessor;

public class ReviewsManager extends AsyncTask<Void, ReviewsData, Void> {

	private boolean prexecuting;
	private ReviewsListener gui;
	private DescriptionListener description;
	private ReviewsCompiler compiler;
	private HashMap<String, PreprocessingData> preprocessedData;
	
	public ReviewsManager(ReviewsListener gui, DescriptionListener description,
			String upc, InputStream	priorityListXML) {
		this.gui = gui;
		this.description = description;
		this.compiler = new ReviewsCompiler(upc, priorityListXML);
		this.prexecuting = true;
	}
	
	@Override
    protected void onPreExecute() {
		this.compiler.activateAll();
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		do
		{
			try {
				if (!this.compiler.isPrexecuting())
					publishProgress(this.compiler.executePartially());
			} catch (InterruptedException e) {
				// Bad internet connection or some problem.
				// Can just skip the review
			}
		} while (!compiler.isComplete());
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(ReviewsData... progress) {
		if (this.prexecuting == true)
		{
			this.preprocessedData = this.compiler.retrievePreprocessingData();
			this.description.onPreExecuteComplete(this);
			this.prexecuting = false;
		}
		
        for (ReviewsData r : progress)
        	this.gui.onReviewsDataDownloaded(r, this);
    }

	@Override
	protected void onPostExecute(Void result) {
		this.gui.onCompletion(this);
	}
	
	public String getAllergens() {
		String labelAPI = this.getPreprocessedData("LabelAPI").
				get("allergens");
		
		if (labelAPI == null)
			return null;
		
		labelAPI.replaceAll("_", " ");
		return labelAPI;
	}
	
	// Preferring shortest name
	public String getProductName() {
		
		String labelAPI = this.getPreprocessedData("LabelAPI").get("title");
		if (labelAPI != null)
			return labelAPI;
		
		String amazon = this.getPreprocessedData("Amazon").get("name");
		String google = this.getPreprocessedData("Google").get("title");
		
		if (amazon == null)
			return google;
		else if (google == null)
			return amazon;
		else
			return (amazon.length() < google.length()) ? amazon : google;
	}
	
	public float getProductRating() {
		return Float.parseFloat(this.getPreprocessedData("Amazon").
				get("overallRating"));
	}
	
	public String getProductDescription() {
		return this.getPreprocessedData("Amazon").get("description");
	}
	
	// Currently uses cheap culling
	public String getBestPrice() {
		List<String> prices =
				Arrays.asList(this.getPreprocessedData("Google").get("prices").
				split("\\s+"));
		
		double bestPrice = Double.MAX_VALUE;
		double mean = this.getMean(prices);
		
		for (String price : prices)
		{
			double priceVal = Double.parseDouble(price);
			if (priceVal < bestPrice && (priceVal > mean/2 
					&& priceVal < mean*2))
				bestPrice = priceVal;
		}
		
		return bestPrice + "";
	}
	
	public String getSuggestions() {
		String labelAPI = 
				this.getPreprocessedData("LabelAPI").get("similarProducts");
		
		String amazon = 
				this.getPreprocessedData("Amazon").get("similarProducts");
		
		String suggestions = (amazon == null) ? labelAPI : amazon;
		if (suggestions == null)
			return null;
		
		suggestions = suggestions.replaceAll(Preprocessor.DELIMITER, "\n");
		suggestions = "\n" + suggestions;
		suggestions.trim();
		return suggestions;
	}
	
	public PreprocessingData getPreprocessedData(String name) {
		return this.preprocessedData.get(name);
	}
	
	private double getMean(List<String> data) {
		double mean = 0;
		
		for (String price : data)
		{
			double priceVal = Double.parseDouble(price);
			mean+=priceVal;
		}
		
		mean /= data.size();
		
		return mean;
	}
}
