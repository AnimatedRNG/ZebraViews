package com.zebraviews.reviews.preprocessor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.PreprocessorFetchThread;

public class AmazonPreprocessor extends Preprocessor{

	private final static String DATA_NAME = "Amazon";
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		Element description = null;
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
				if (description == null) 
					description = doc.select(".content .productdescriptionwrapper").first();
				if (description == null)
					description = doc.select(".aplus").first();
				if (description == null)
					return;
			}
			catch (IOException e) {
			}
		}
		if (!this.getPreprocessingData().containsKey("description") && description != null)
			this.getPreprocessingData().put("description", description.text());
		this.done();
	}

	@Override
	public String getPreprocessingDataName() {
		return AmazonPreprocessor.DATA_NAME;
	}
}