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

package com.zebraviews.reviews.preprocessor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.PreprocessorFetchThread;

public class AmazonPreprocessor extends Preprocessor{

	private final static String DATA_NAME = "Amazon";
	private static int attempts = 0;
	public boolean running;
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		this.running = true;
		Element description = null;
		Element productName = null;
		Element overallRating = null;
		Elements similarProducts = null;
		String similarProductsNames = new String();
		AmazonURL address = new AmazonURL(this.getFetchThread().
				getCompiler().getUPC());
		address.generateURL();
		String url = address.getURL();
		double overallRatingNum = 0.0F;
		if(!url.equals("No ASIN found"))
		{
			try
			{
				Document doc = Jsoup.connect(url).get();
				description = doc.select("#postBodyPS").first();
				productName = doc.select(".buying span[id=btAsinTitle]").first();
				overallRating = doc.select("span.asinreviewssummary.acr-popover").first();
				overallRatingNum = Double.parseDouble(overallRating.text().substring(0,3))*2; 
				if (description == null) 
					description = doc.select(".content .productdescriptionwrapper").first();
				if (description == null)
					description = doc.select(".aplus").first();
				similarProducts = doc.select("div.asindetails");
				if(!similarProducts.text().equals(""))
				{
					for(int i = 0; (i < similarProducts.size() && i < 4); i++)
						similarProductsNames+=similarProducts.get(i).text().substring(0, similarProducts.get(i).text().indexOf("stars")+5)+GooglePreprocessor.DELIMITER;
				}
				else
				{
					similarProducts = doc.select("div.shoveler-content");
					String[] stuff = similarProducts.first().text().split("\\d\\d\\p{Punct}\\d\\d\\p{Blank}");
					for(String e: stuff)
						similarProductsNames+=e.substring(0,e.indexOf("stars")-13)+Preprocessor.DELIMITER;
				}
			}
			catch (Exception e) {
				AmazonPreprocessor.attempts++;
				if (AmazonPreprocessor.attempts < 5)
				this.onPreExecute();
				this.done();
				return;
			}
		}
		if (!this.getPreprocessingData().containsKey("overallRating") && overallRating != null)
			this.getPreprocessingData().put("overallRating", "" + overallRatingNum);
		if (!this.getPreprocessingData().containsKey("description") && description != null)
			this.getPreprocessingData().put("description", description.text());
		if (!this.getPreprocessingData().containsKey("name") && productName != null)
			this.getPreprocessingData().put("name", productName.text());
		if (!this.getPreprocessingData().containsKey("similarProducts") && similarProductsNames!=null && !similarProductsNames.equals(""))
			this.getPreprocessingData().put("similarProducts", similarProductsNames);
		this.done();
		this.running = false;
	}

	@Override
	public String getPreprocessingDataName() {
		return AmazonPreprocessor.DATA_NAME;
	}
}