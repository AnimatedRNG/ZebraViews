package com.animatedjuzz.zebraviews;

import com.zebraviews.reviews.ReviewsData;

public interface ReviewsListener {

	public void onReviewsDataDownloaded(ReviewsData data, 
			ReviewsManager manager);
	
	public void onCompletion(ReviewsManager manager);
}
