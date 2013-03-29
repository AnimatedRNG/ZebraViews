package com.zebraviews.reviews.scraper;

import com.zebraviews.reviews.ReviewFetchThread;
import com.zebraviews.reviews.ReviewsData;

public class AmazonScraper implements Scraper {

	private float priority = 0.0F;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setCompletion(boolean complete) {
		// TODO Auto-generated method stub

	}

	@Override
	public float getPriority() {
		return this.priority;
	}

	@Override
	public boolean isInterruptible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setInterruptibility(boolean interruptibility) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPriority(float priority) {
		this.priority = priority;
	}

	@Override
	public void setFetchThread(ReviewFetchThread thread) {
		// TODO Auto-generated method stub
		
	}
}
