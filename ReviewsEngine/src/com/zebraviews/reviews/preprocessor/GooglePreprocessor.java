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
		JSONArray items = null;
		try {
			items = (JSONArray) JSONRequest.getRequest(searchString + 
				this.getFetchThread().getCompiler().getUPC()).get("items");
		} catch (NullPointerException p) {
			this.done();
			return;
		}
		
		if (items == null || items.size() == 0)
			return;
		
		String title = (String) ((JSONObject) ((JSONObject) items.get(0)).
				get("product")).get("title");
		String prices = new String();
		//String description = new String();
		
		for (int itemIndex = 0; itemIndex < items.size(); itemIndex++)
		{
			JSONArray inventories = (JSONArray) ((JSONObject) 
					((JSONObject) items.get(itemIndex)).get("product"))
					.get("inventories");
			prices += ((JSONObject) inventories.get(0)).get("price") + " ";
			//description += ((JSONObject)((JSONObject) items.get(itemIndex)).
			//		get("product")).get("description")
			//		+ GooglePreprocessor.DELIMITER;
		}
		
		this.getPreprocessingData().put("title", title);
		this.getPreprocessingData().put("prices", prices);
		//this.getPreprocessingData().put("description", description);
		this.done();
	}

	@Override
	public String getPreprocessingDataName() {
		return GooglePreprocessor.DATA_NAME;
	}
}
