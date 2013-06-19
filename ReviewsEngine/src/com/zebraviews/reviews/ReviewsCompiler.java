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

import java.util.ArrayList;
import java.util.List;

import com.zebraviews.reviews.scraper.Scraper;

public class ReviewsCompiler {
	
	public final static int NUMBER_OF_THREADS = 8;
	public final static long POLL_TIME = 100;
	
	private List<ReviewFetchThread> fetchers;
	private PriorityList priorityList;
	
	public ReviewsCompiler() {
		fetchers = new ArrayList<ReviewFetchThread>();
		priorityList = new PriorityList();
		
		for (int i = 0; i < 8; i++)
		{
			Scraper poolScraper = priorityList.getScraper();
			if (poolScraper==null)
				break;
			else
				fetchers.add(new ReviewFetchThread(poolScraper));
		}
	}
	
	public void activateAll() {
		for (ReviewFetchThread r : fetchers)
			r.activate();
	}
	
	public ReviewsData executePartially() throws InterruptedException {
		ReviewsData reviews = new ReviewsData();
		Thread.sleep(ReviewsCompiler.POLL_TIME);
		for (ReviewFetchThread r : fetchers)
		{
			while (r.hasReviews())
				reviews.addReview(r.retrieveReview());
			
			if (!r.isAlive() && priorityList.hasScraper())
				r.init(priorityList.getScraper());
		}
		return reviews;
	}
	
	public boolean isComplete() {
		if (!priorityList.hasScraper())
		{
			for (ReviewFetchThread r : fetchers)
				if (r.isAlive()) return false;
			return true;
		}
		else
			return false;
	}
	
	public static String getUPC()
	{
		return null;
	}
	
	public static String getProductString()
	{
		return null;
	}
	
	public static String getAmazonURL()
	{
		return null;
	}
}