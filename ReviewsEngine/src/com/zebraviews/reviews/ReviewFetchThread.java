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

import java.util.LinkedList;
import java.util.Queue;

import com.zebraviews.reviews.scraper.Scraper;

public class ReviewFetchThread extends Thread {

	private Scraper scraper;
	private ReviewsCompiler compiler;
	private Queue<Review> reviewsQueue;
	
	public ReviewFetchThread() { }
	
	public ReviewFetchThread(ReviewsCompiler compiler, Scraper scraper) {
		super(scraper);
		this.reviewsQueue = new LinkedList<Review>();
		this.scraper = scraper;
		this.compiler = compiler;
	}
	
	public boolean activate() {
		if (this.isAlive())
			return false;
		else
		{
			scraper.setFetchThread(this);
			this.start();
			return true;
		}
	}
	
	public boolean init(Scraper scraper) {
		if (this.isAlive())
			return false;
		else
		{
			this.scraper = scraper;
			return true;
		}
	}
	
	public void addReview(Review review) {
		if (this.reviewsQueue == null)
			this.reviewsQueue = new LinkedList<Review>();
		this.reviewsQueue.add(review);
	}
	
	public boolean hasReviews() {
		return !this.reviewsQueue.isEmpty();
	}
	
	public Review retrieveReview() {
		return this.reviewsQueue.poll();
	}
	
	public ReviewsCompiler getReviewsCompiler() {
		return this.compiler;
	}
}