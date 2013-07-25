package com.zebraviews.reviews.preprocessor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.PreprocessorFetchThread;

public class AmazonPreprocessor extends Preprocessor{

	private final static String DATA_NAME = "Amazon";
	private static int attempts = 0;
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		Element description = null;
		Element productName = null;
		Element overallRating = null;
		AmazonURL address = new AmazonURL(this.getFetchThread().
				getCompiler().getUPC());
		address.generateURL();
		String url = address.getURL();
		if(!url.equals("No ASIN found"))
		{
			try
			{
				Document doc = Jsoup.connect(url).get();
				description = doc.select("#postBodyPS").first();
				productName = doc.select(".buying span[id=btAsinTitle]").first();
				overallRating = 
						doc.select(".reviews div.gry.txtnormal.acrrating").
						first();
				if (description == null) 
					description = doc.select(".content .productdescriptionwrapper").first();
				if (description == null)
					description = doc.select(".aplus").first();
			}
			catch (IOException e) {
				AmazonPreprocessor.attempts++;
				if (AmazonPreprocessor.attempts < 5)
					this.onPreExecute();
				this.done();
				return;
			}
		}
		double overallRatingNum = Double.parseDouble(overallRating.text().
				substring(0,3))*2;
		if (!this.getPreprocessingData().containsKey("description") && description != null)
			this.getPreprocessingData().put("description", description.text());
		if (!this.getPreprocessingData().containsKey("name") && productName != null)
			this.getPreprocessingData().put("name", productName.text());
		if (!this.getPreprocessingData().containsKey("overallRating")
				&& overallRating != null)
			this.getPreprocessingData().put("overallRating", "" + 
				overallRatingNum);
		this.done();
	}

	@Override
	public String getPreprocessingDataName() {
		return AmazonPreprocessor.DATA_NAME;
	}
}