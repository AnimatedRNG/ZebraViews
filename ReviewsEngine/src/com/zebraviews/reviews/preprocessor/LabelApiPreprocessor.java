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

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import com.zebraviews.reviews.JSONRequest;
import com.zebraviews.reviews.LabelApiURI;

public class LabelApiPreprocessor extends Preprocessor {

	public static final String API_KEY = "r3epxkdjfejz6b6cavxtpxth";
	public static final String DATA_NAME = "LabelAPI"; 
	private static int attempts = 0;
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		try
		{
			String product = new String();
			String similarProducts = new String();
			String upc = this.getFetchThread().getCompiler().getUPC();
			LabelApiURI requestGenerator = 
					new LabelApiURI(upc, API_KEY);
			if(JSONRequest.getRequest(
					requestGenerator.getCreateSessionURI()) == null)
			{
				this.done();
				return;
			}
			String sessionID = (String) JSONRequest.getRequest(requestGenerator.getCreateSessionURI()).get("session_id");
			JSONRequest.postRequestWithoutStructure(requestGenerator.
					getSetProfileURI(sessionID, true, true, true, true, true,
							true, true, true, true, true, true, true ,true,
							true, true));
			JSONObject results = (JSONObject) JSONRequest.
					getRequest(requestGenerator.
							getLabelURI(sessionID));
			JSONArray similarResults = (JSONArray) JSONRequest.getRequest(requestGenerator.getLabelArrayURI(sessionID, "5", "0")).get("productsArray");
			for(int i = 0; i<similarResults.size(); i++)
			{
				similarProducts+=((JSONObject) similarResults.get(i)).get("product_name")+Preprocessor.DELIMITER;

			}
			product = (((JSONObject) results).
					get("product_name")).toString();
			String allergens = "";
			JSONArray allergenResults = ((JSONArray)
					((JSONObject) results).get("allergens"));
			for (int j = 0; j < 15; j++)
			{
				if (((Integer)((JSONObject) allergenResults.get(j)).get("allergen_value")).equals(new Integer(2))) {
					allergens += (((String)((JSONObject) allergenResults.get(j)).get("allergen_name")).replace(" ", "_")) + " ";
				}
			}
			this.getPreprocessingData().put("allergens", allergens);
			this.getPreprocessingData().put("title", product);
			this.getPreprocessingData().put("similarProducts", similarProducts);
			this.done();
		}
		catch(Exception e)
		{
			LabelApiPreprocessor.attempts++;
			if (LabelApiPreprocessor.attempts < 5)
			this.onPreExecute();
			this.done();
			return;
		}
	}

	@Override
	public String getPreprocessingDataName() {
		return LabelApiPreprocessor.DATA_NAME;
	}
}