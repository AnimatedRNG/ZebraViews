package com.zebraviews.reviews.preprocessor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.PreprocessorFetchThread;

public class AmazonPreprocessor extends Preprocessor{

	private final static String DATA_NAME = "Amazon";
	private PreprocessorFetchThread fetchThread;
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		Element description = null;
		AmazonURL address = new AmazonURL(this.fetchThread.
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
				System.out.println(description.text());
			}
			catch (IOException e) {
			}
		}
		this.getPreprocessingData().put("description", description.text());
	}

	@Override
	public String getPreprocessingDataName() {
		return AmazonPreprocessor.DATA_NAME;
	}
	public static void main(String[] args) {
		AmazonPreprocessor test = new AmazonPreprocessor();
		test.onPreExecute();
	}
}