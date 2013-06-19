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

package com.zebraviews.reviews.scraper;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.Review;
import com.zebraviews.reviews.ReviewFetchThread;
import com.zebraviews.reviews.ReviewsCompiler;

public class AmazonScraper implements Scraper {
	
	private float priority;
	private boolean interruptibility;
	private boolean complete;
	private int reviewCount = 0;
	private ReviewFetchThread fetchThread;

	@Override
	public void run() {
		AmazonURL address = new AmazonURL(ReviewsCompiler.getUPC());
		address.generateURL();
		String url = address.getURL();
		if(!url.equals("No ASIN found"))
		{
			try
			{
				Document doc = Jsoup.connect(url).get();
				//Element prodTitle=doc.select("span#btAsinTitle").first();
				//System.out.println(prodTitle.text());
				Element overallRating = doc.select("div.gry.txtnormal.acrrating").first();
				if(overallRating==null)
				{
					this.setCompletion(true);
					return;
				}
				//System.out.println("OVERALL RATING: " + rating.text().substring(0, 18));
				Elements reviews = doc.select("#revMHRL .mt9.reviewtext");
				Elements titles = doc.select("#revMHRL .txtlarge.gl3.gr4.reviewTitle.valignMiddle");
				Elements ratings = doc.select("div.mt4.ttl");
				while (this.reviewCount < reviews.size())
				{
					String title = titles.get(this.reviewCount).text();
					String review = reviews.get(this.reviewCount).text();
					int rating = (int) Math.round((2 * Double.parseDouble(ratings.get(reviewCount).text().substring(0, 3))));
					review = review.replace("Read more �", "");
					
					// Amazon's reviews are out of 5
					Review rev = new Review(review, 
							Integer.parseInt(overallRating.text().substring(0,1))
							*2, reviewCount++);
					rev.setTitle(title);
					rev.setRating(rating);
					
					this.fetchThread.addReview(rev);
					
					//System.out.println("\nTITLE OF REVIEW: " + title);
					//System.out.println("REVIEW: " + review);
					if (Thread.interrupted())
						return;
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.setCompletion(true);
		
	}

	@Override
	public float getPriority() {
		return this.priority;
	}

	@Override
	public boolean isComplete() {
		return this.complete;
	}

	@Override
	public boolean isInterruptible() {
		return this.interruptibility;
	}

	@Override
	public void setCompletion(boolean complete) {
		this.complete = complete;
	}

	@Override
	public void setFetchThread(ReviewFetchThread thread) {
		this.fetchThread = thread;
	}

	@Override
	public void setInterruptibility(boolean interruptibility) {
		this.interruptibility = interruptibility;
	}

	@Override
	public void setPriority(float priority) {
		this.priority = priority;
	}
}