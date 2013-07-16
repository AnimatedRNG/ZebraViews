package com.zebraviews.reviews;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public class LabelAPI {

	/**
	 * @param args
	 */
	public static JSONObject createSession(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}

	public static JSONObject getProfile(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}	
	
	public static JSONObject setProfile(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}
	
	public static JSONObject addMyIngredient(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}
	
	public static JSONObject getLabel(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}

	public static JSONObject removeMyIngredient(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}

	public static JSONObject searchIngredients(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}
	
	public static JSONObject getSimilarFoods(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL label = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) label.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject foodData = null;
		try {
			foodData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return foodData;

	}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//create session
		//create profile
		//search products
			//ingredient search
			//label array
		
		/*extends preprocessor, store hashmap, hasNuts, 878*/

		JSONObject object = LabelAPI.createSession("http://api.foodessentials.com/createsession?uid=ZebraViews&devid=ZebraViews&appid=r3epxkdjfejz6b6cavxtpxth&f=json&api_key=r3epxkdjfejz6b6cavxtpxth");
		String sessionID = (String) object.get("session_id");
		System.out.println(sessionID);
		JSONObject object2 = LabelAPI.getProfile("http://api.foodessentials.com/getprofile?sid=ac4fa371-5116-4460-bfa0-3f1c1fd31995&f=json&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object2.toJSONString());
		JSONObject object3 = LabelAPI.setProfile("http://api.foodessentials.com/setprofile?json=%7B+++++%22session_id%22%3A+%22bb69a755-1fc1-40af-a85d-b554d4c32878%22%2C+++++%22nutrients%22%3A+%5B%7B+++++++++%22name%22%3A+%22Calcium%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Calories%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Calories+from+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Cholesterol%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Dietary+Fiber%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Insoluble+Fiber%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Iron%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Monounsaturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Other+Carbohydrate%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Polyunsaturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Potassium%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Protein%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Saturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Saturated+Fat+Calories%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sodium%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Soluble+Fiber%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sugar+Alcohol%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sugars%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Total+Carbohydrate%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Total+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Vitamin+A%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Vitamin+C%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%5D%2C+++++%22allergens%22%3A+%5B%7B+++++++++%22name%22%3A+%22Cereals%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Coconut%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Corn%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Egg%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Fish%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Gluten%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Lactose%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Milk%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Peanuts%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sesame+Seeds%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Shellfish%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Soybean%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sulfites%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Tree+Nuts%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Wheat%22%2C+++++++++%22value%22%3A+%22true%22+++++%7D%5D%2C+++++%22additives%22%3A+%5B%7B+++++++++%22name%22%3A+%22Acidity+Regulator%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Added+Sugar%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Anti-Caking+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Anti-Foaming+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Antioxidants%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Artificial+Color%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Artificial+Flavoring+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Artificial+Preservative%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Bulking+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Colors%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Emulsifiers%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Enzyme%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Firming+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Flavor+Enhancer%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Flour+Treating+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Food+Acids%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Gelling+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Glazing+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Humectants%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Leavening+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Mineral+Salt%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Natural+Color%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Natural+Flavoring+Agent%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Natural+Preservative%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Preservatives%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Propellant%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Raising+Agents%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Saturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sequestrant%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Stabilizers%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Sweeteners%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Thickeners%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Trans+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Unsaturated+Fat%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%2C+%7B+++++++++%22name%22%3A+%22Vegetable+Gum%22%2C+++++++++%22value%22%3A+%22false%22+++++%7D%5D%2C+++++%22myingredients%22%3A+%5B%5D%2C+++++%22mysort%22%3A+%5B%7B+++++++++%22sort_variable%22%3A+%22Calories%22%2C+++++++++%22sort_order%22%3A+1%2C+++++++++%22variable_type%22%3A+1+++++%7D%5D+%7D&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object3.toJSONString());
		JSONObject object7 = LabelAPI.searchIngredients("http://api.foodessentials.com/ingredientsearch?q=egg&sid=216cdf51-a962-44f4-9b65-edd52bbe24d&n=200&s=3&f=json&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object7.toJSONString());
		JSONObject object5 = LabelAPI.addMyIngredient("http://api.foodessentials.com/addmyingredient?sid=216cdf51-a962-44f4-9b65-edd52bbe24de&id=408&f=json&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object5.toJSONString());
		JSONObject object6 = LabelAPI.removeMyIngredient("http://api.foodessentials.com/removemyingredient?sid=8187bb23-3d4e-42fa-96db-21bf7b7f3ad7&id=408&f=json&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object6.toJSONString());
		JSONObject object4 = LabelAPI.getLabel("http://api.foodessentials.com/label?u=028400071932&sessid=216cdf51-a962-44f4-9b65-edd52bbe24de&appid=demoApp_01&f=json&long=38.6300&lat=90.2000&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object4.toJSONString());
		JSONObject object8 = LabelAPI.getSimilarFoods("http://api.foodessentials.com/labelarray?u=016000264601&sid=3460f011-27b9-483a-ae56-de5decaf173b&n=10&s=0&f=json&api_key=r3epxkdjfejz6b6cavxtpxth");
		System.out.println(object8.toJSONString());
	}

}
