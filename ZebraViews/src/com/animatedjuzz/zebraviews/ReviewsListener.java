package com.animatedjuzz.zebraviews;

import com.zebraviews.reviews.ReviewsData;

public interface ReviewsListener {

	public void onReviewsDataDownloaded(ReviewsData data);
	
	public void onCompletion();
}
