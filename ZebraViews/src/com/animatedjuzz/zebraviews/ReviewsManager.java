package com.animatedjuzz.zebraviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import net.minidev.json.parser.ParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import android.os.AsyncTask;

import com.zebraviews.reviews.ReviewsCompiler;
import com.zebraviews.reviews.ReviewsData;

public class ReviewsManager extends AsyncTask<Void, ReviewsData, Void> {

	private ReviewsListener gui;
	private static String upc;
	private ReviewsCompiler compiler;
	
	private final static String searchString = "https://www.googleapis.com/" +
			"shopping/search/v1/public/products?key=" +
			"AIzaSyDFLBwQFNNxWXkfeJ_sU30XRrdroRmh6TY&country=US" +
			"&restrictBy=gtin:";
	
	public ReviewsManager(ReviewsListener gui, String upc, InputStream 
			priorityListXML) {
		this.gui = gui; 
		ReviewsManager.upc = upc;
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
		ProductNameRetriever retriever = new ProductNameRetriever();
		retriever.execute();
		return retriever.title;
	}
	
	private class ProductNameRetriever extends AsyncTask<Void, Void, Void> {

		protected String title;
		
		@Override
		protected Void doInBackground(Void... params) {
			DefaultHttpClient httpclient = 
					new DefaultHttpClient(new BasicHttpParams());
			HttpGet request = new HttpGet();
			InputStream inputStream = null;
			try {
				request.setURI(new URI(ReviewsManager.searchString
						+ ReviewsManager.upc));
				HttpResponse response = httpclient.execute(request);
				inputStream = response.getEntity().getContent();
			} catch (URISyntaxException e1) {
				this.title = null;
				return null;
			} catch (IllegalStateException e) {
				this.title = null;
				return null;
			} catch (IOException e) {
				this.title = null;
				return null;
			}
			
			JSONObject titlePage = null;
			try {
				titlePage = (JSONObject) JSONValue.parseWithException(inputStream);
			} catch (IOException e) {
				this.title = null;
				return null;
			} catch (ParseException e) {
				this.title = null;
				return null;
			}
			JSONArray items = (JSONArray) titlePage.get("items");
			JSONObject titleObj = (JSONObject) items.get(0);
			this.title = (String) titleObj.get("title");
			return null;
		}
	}
}
