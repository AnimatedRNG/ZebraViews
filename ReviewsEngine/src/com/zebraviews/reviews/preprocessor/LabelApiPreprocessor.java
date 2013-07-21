package com.zebraviews.reviews.preprocessor;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.zebraviews.reviews.JSONRequest;
import com.zebraviews.reviews.LabelApiURI;
import com.zebraviews.reviews.PreprocessorFetchThread;

public class LabelApiPreprocessor extends Preprocessor{

	public static final String API_KEY = "r3epxkdjfejz6b6cavxtpxth";
	public static final String DATA_NAME = "LabelAPI"; 
	
	private PreprocessorFetchThread fetchThread;
	
	@Override
	public void onSimultaneousExecute() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void onPreExecute() {
		String product = "";
		String upc = this.fetchThread.getCompiler().getUPC();
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
						getLabelArrayURI(sessionID, "1", "0")).
						get("productsArray");
		product = (((JSONObject) results.get(0)).
				get("product_name")).toString();
		String allergens = "";
		JSONArray allergenResults = ((JSONArray)
				((JSONObject) results.get(0)).get("allergens"));
		for (int j = 0; j < 15; j++) {
			if(((JSONObject) allergenResults.get(j)).
					get("allergen_value").equals("2")) {
				allergens += (((String)((JSONObject) allergenResults.
						get(j)).get("allergen_name")).replace(" ", "_")) + " ";
			}
		}
		this.getPreprocessingData().put("allergens", allergens);
	}

	@Override
	public String getPreprocessingDataName() {
		return LabelApiPreprocessor.DATA_NAME;
	}
}