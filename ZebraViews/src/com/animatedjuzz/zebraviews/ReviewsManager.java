package com.animatedjuzz.zebraviews;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.zebraviews.reviews.ReviewsCompiler;
import com.zebraviews.reviews.ReviewsData;

public class ReviewsManager extends AsyncTask<Void, ReviewsData, Void> {

	private ReviewsListener gui;
	private String upc;
	private ReviewsCompiler compiler;
	
	private final static String searchString = "https://www.googleapis.com/" +
			"shopping/search/v1/public/products?key=" +
			"AIzaSyDFLBwQFNNxWXkfeJ_sU30XRrdroRmh6TY&country=US" +
			"&restrictBy=gtin:";
	
	public ReviewsManager(ReviewsListener gui, String upc, InputStream 
			priorityListXML) {
		this.gui = gui; 
		this.upc = upc;
		this.compiler = new ReviewsCompiler(upc, priorityListXML);
	}
	
	@Override
    protected void onPreExecute() {
		this.compiler.activateAll();
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		do
		{
			try {
				publishProgress(this.compiler.executePartially());
			} catch (InterruptedException e) {
				// Bad internet connection or some problem.
				// Can just skip the review
			}
		} while (!compiler.isComplete());
		
		return null;
	}
	
	@Override
	protected void onProgressUpdate(ReviewsData... progress) {
        for (ReviewsData r : progress)
        	this.gui.onReviewsDataDownloaded(r);
    }

	@Override
	protected void onPostExecute(Void result) {
		this.gui.onCompletion();
	}
	
	public String getProductName() {
		DefaultHttpClient httpclient = 
				new DefaultHttpClient(new BasicHttpParams());
		HttpPost httppost = new HttpPost(ReviewsManager.searchString
				+ this.upc);
		
		InputStream inputStream = null;
		String result = null;
		try {
		    HttpResponse response = httpclient.execute(httppost);           
		    HttpEntity entity = response.getEntity();

		    inputStream = entity.getContent();
		    BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(inputStream, "UTF-8"), 8);
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    while ((line = reader.readLine()) != null)
		        sb.append(line + "\n");
		    result = sb.toString();
		} catch (Exception e) { 
		    return null;
		}
		
		try {
			if(inputStream != null)
				inputStream.close();
		} catch (Exception squish) {
		}
		
		JSONObject titlePage = null;
		
		try {
			titlePage = new JSONObject(result);
			return titlePage.getString("title");
		} catch (JSONException e) {
			return null;
			// This can't happen unless the shopping API is broken
		}
	}
}
