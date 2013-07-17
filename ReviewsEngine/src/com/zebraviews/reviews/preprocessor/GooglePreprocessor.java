package com.zebraviews.reviews.preprocessor;

public class GooglePreprocessor extends Preprocessor {

	private final static String searchString = "https://www.googleapis.com/" +
			"shopping/search/v1/public/products?key=" +
			"AIzaSyDFLBwQFNNxWXkfeJ_sU30XRrdroRmh6TY&country=US" +
			"&restrictBy=gtin:";
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {

	}

	@Override
	public String getPreprocessingDataName() {
		return null;
	}

}
