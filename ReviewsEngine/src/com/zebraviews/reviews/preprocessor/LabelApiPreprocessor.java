package com.zebraviews.reviews.preprocessor;

import java.util.ArrayList;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.zebraviews.reviews.JSONRequest;
import com.zebraviews.reviews.LabelApiProduct;
import com.zebraviews.reviews.LabelApiURI;
import com.zebraviews.reviews.ReviewFetchThread;

public class LabelApiPreprocessor extends Preprocessor{

	public static final String API_KEY = "r3epxkdjfejz6b6cavxtpxth";
	public static final String DATA_NAME = "LabelAPI"; 
	
	private ReviewFetchThread fetchThread;
	
	@Override
	public void onSimultaneousExecute() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onPreExecute() {
		String product = "";
		//String upc = this.fetchThread.getReviewsCompiler().getUPC();
		String upc = "016000409958";
		LabelApiURI requestGenerator = 
				new LabelApiURI(upc, API_KEY);
		String sessionID = (String) 
				JSONRequest.
				getRequest(requestGenerator.getCreateSessionURI()).
				get("session_id");
		JSONRequest.postRequestWithoutStructure(requestGenerator.
				getSetProfileURI(sessionID, true, true, true, true, true,
						true, true, true, true, true, true, true ,true,
						true, true));
		JSONArray results = (JSONArray) JSONRequest.
				getRequest(requestGenerator.
						getLabelArrayURI(sessionID, "10", "0")).
						get("productsArray");
		for(int i = 0; i < results.size(); i++){
			product = (((JSONObject) results.get(i)).
					get("product_name")).toString();
			ArrayList <String> allergens = new ArrayList<String>();
			JSONArray allergenResults = ((JSONArray)
					((JSONObject) results.get(i)).get("allergens"));
			for (int j = 0; j < 15; j++){
				if(((JSONObject) allergenResults.get(j)).
						get("allergen_value").equals("2")) {
					allergens.add(((String)((JSONObject) allergenResults.
							get(j)).get("allergen_name")));
				}
			}
			// Add hashmap entry ("allergens", "nuts gluten etc")
			// Use space as delimiter
		}
	}

	@Override
	public String getPreprocessingDataName() {
		return LabelApiPreprocessor.DATA_NAME;
	}
}