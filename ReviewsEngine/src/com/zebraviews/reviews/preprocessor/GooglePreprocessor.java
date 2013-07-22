package com.zebraviews.reviews.preprocessor;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.zebraviews.reviews.JSONRequest;

public class GooglePreprocessor extends Preprocessor {

	private final static String searchString = "https://www.googleapis.com/" +
			"shopping/search/v1/public/products?key=" +
			"AIzaSyDFLBwQFNNxWXkfeJ_sU30XRrdroRmh6TY&country=US" +
			"&restrictBy=gtin:";
	
	protected final static String DATA_NAME = "Google";
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		JSONArray items = (JSONArray) JSONRequest.getRequest(searchString + 
				this.getFetchThread().getCompiler().getUPC()).get("items");
		
		if (items == null || items.size() == 0)
			return;
		
		String title = (String) ((JSONObject) ((JSONObject) items.get(0)).
				get("product")).get("title");
		String prices = new String();
		String description = new String();
		
		for (int itemIndex = 0; itemIndex < items.size(); itemIndex++)
		{
			JSONArray inventories = (JSONArray) ((JSONObject) 
					((JSONObject) items.get(itemIndex)).get("product"))
					.get("inventories");
			prices += ((JSONObject) inventories.get(0)).get("price") + " ";
			description += ((JSONObject)((JSONObject) items.get(itemIndex)).
					get("product")).get("description")
					+ GooglePreprocessor.DELIMITER;
		}
		
		this.getPreprocessingData().put("title", title);
		this.getPreprocessingData().put("prices", prices);
		this.getPreprocessingData().put("description", description);
		this.done();
	}

	@Override
	public String getPreprocessingDataName() {
		return GooglePreprocessor.DATA_NAME;
	}
}
