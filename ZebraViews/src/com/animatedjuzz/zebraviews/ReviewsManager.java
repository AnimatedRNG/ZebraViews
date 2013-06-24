package com.animatedjuzz.zebraviews;

import java.io.InputStream;

import android.os.AsyncTask;

import com.zebraviews.reviews.ReviewsCompiler;
import com.zebraviews.reviews.ReviewsData;

public class ReviewsManager extends AsyncTask<Void, ReviewsData, Void> {

	private ReviewsListener gui;
	private String upc;
	private ReviewsCompiler compiler;
	
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
}
