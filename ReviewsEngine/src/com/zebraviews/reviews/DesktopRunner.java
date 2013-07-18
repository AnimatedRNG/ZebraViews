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

package com.zebraviews.reviews;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


public class DesktopRunner {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		/*long startTime = System.nanoTime();
		String upc="9780439420105";
		AmazonURL address=new AmazonURL(upc);
		address.generateURL();
		String url=address.getURL();
		System.out.println(url);
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		
		ReviewsCompiler compiler = new ReviewsCompiler(upc, 
				new FileInputStream(new File("XML/priority_list.xml")));
		compiler.activateAll();
		do
		{
			for (Review r : compiler.executePartially().getReviews()) {
				System.out.println(r);
			}
		} while (!compiler.isComplete());*/
		String apiKey = "r3epxkdjfejz6bxtpxth";
		LabelApiURI requests = new LabelApiURI("016000409958", "r3epxkdjfejz6b6cavxtpxth");
		JSONObject object = LabelAPI.createSession(requests.getCreateSessionURI());
		String sessionID = (String) object.get("session_id");
		JSONArray results = (JSONArray) JSONRequest.getRequest(requests.getLabelArrayURI(sessionID, "10", "0")).get("productsArray");
		for(int i = 0; i<results.size(); i++)
		{
			System.out.println(((JSONObject) results.get(i)).get("product_name"));
			JSONArray allergenResults = ((JSONArray) ((JSONObject)results.get(i)).get("allergens"));
			for(int j=0; j<15; j++)
			{
				if(((JSONObject) allergenResults.get(j)).get("allergen_value").equals("2"))
				{
					System.out.println("\t Contains: "+ ((JSONObject) allergenResults.get(j)).get("allergen_name"));
				}
					
			}
		}		
	}
}
