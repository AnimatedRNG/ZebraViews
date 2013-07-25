package com.animatedjuzz.zebraviews;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.os.AsyncTask;

import com.zebraviews.reviews.ReviewsCompiler;
import com.zebraviews.reviews.ReviewsData;
import com.zebraviews.reviews.preprocessor.PreprocessingData;

public class ReviewsManager extends AsyncTask<Void, ReviewsData, Void> {

	private ReviewsListener gui;
	private ReviewsCompiler compiler;
	private HashMap<String, PreprocessingData> preprocessedData;
	
	public ReviewsManager(ReviewsListener gui, String upc, InputStream 
			priorityListXML) {
		this.gui = gui;
		this.compiler = new ReviewsCompiler(upc, priorityListXML);
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
        for (ReviewsData r : progress)
        	this.gui.onReviewsDataDownloaded(r);
    }

	@Override
	protected void onPostExecute(Void result) {
		this.preprocessedData = this.compiler.retrievePreprocessingData();
		this.gui.onCompletion();
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
	
	public String getProductDescription() {
		return this.getPreprocessedData("Amazon").get("description");
	}
	
	public String getBestPrice() {
		List<String> prices =
				Arrays.asList(this.getPreprocessedData("Google").get("price").
				split("\\s+"));
		
		double bestPrice = Double.MAX_VALUE;
		
		for (String price : prices)
		{
			double priceVal = Double.parseDouble(price);
			if (priceVal < bestPrice)
				bestPrice = priceVal;
		}
		
		return bestPrice + "";
	}
	
	public PreprocessingData getPreprocessedData(String name) {
		return this.preprocessedData.get(name);
	}
}
