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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class JSONRequest {
	
	// Returns JSONObject if successful, otherwise null
	public static JSONObject getRequest(String request) {
		InputStream inputStream = null;
		
		try {
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection)
					url.openConnection();
			connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject jsonData = null;
		try {
			jsonData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return jsonData;
	}
	
	// Returns true if successful, false otherwise
	public static boolean postRequest(String request, String structure) {
		
		boolean result;
		HttpClient httpClient = new DefaultHttpClient();
		
		try {
			HttpPost httprequest = new HttpPost();
			StringEntity params = new StringEntity(structure);
			httprequest.setURI(new URI(request));
			httprequest.addHeader
				("content-type", "application/x-www-form-urlencoded");
			httprequest.setEntity(params);
			HttpResponse response = httpClient.execute(httprequest);
			result = true;
		} catch (IOException e) {
			result = false;
		} catch (URISyntaxException e) {
			result = false;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		return result;
	}
	
	public static JSONObject postRequestWithoutStructure(String request)
	{
		InputStream inputStream = null;
		
		try {
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection)
					url.openConnection();
			connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type","application/json");
		    connection.setUseCaches(false);
		    inputStream = connection.getInputStream();
 		} catch (ClientProtocolException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
		JSONObject jsonData = null;
		try {
			jsonData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return jsonData;	
	}
	
	// Returns JSONObject response if successful, null otherwise
	public static JSONObject postRequestWithResponse(String request, String structure) {
		
		HttpClient httpClient = new DefaultHttpClient();
		InputStream inputStream;
		
		try {
			HttpPost httprequest = new HttpPost();
			StringEntity params = new StringEntity(structure);
			httprequest.setURI(new URI(request));
			httprequest.addHeader
				("content-type", "application/x-www-form-urlencoded");
			httprequest.setEntity(params);
			HttpResponse response = httpClient.execute(httprequest);
			inputStream = response.getEntity().getContent();
		} catch (IOException e) {
			return null;
		} catch (URISyntaxException e) {
			return null;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		
		JSONObject jsonData = null;
		try {
			jsonData = (JSONObject) JSONValue.parseWithException(inputStream);
		} catch (IOException e) {
			return null;
		} catch (ParseException e) {
			return null;
		}
		
		return jsonData;
	}
}
