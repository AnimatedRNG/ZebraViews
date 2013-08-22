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
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.Review;
import com.zebraviews.reviews.ReviewFetchThread;
import com.zebraviews.reviews.util.SignedRequestsHelper;

public class AmazonScraper implements Scraper {

	private float priority;
	private boolean interruptibility;
	private boolean complete;
	private int reviewCount = 0;
	private ReviewFetchThread fetchThread;

	@Override
	public void run()
	{
		String url = null;
		SignedRequestsHelper helper = null;
		try {
			helper = SignedRequestsHelper.getInstance("ecs.amazonaws.com", "AKIAJYAME7RKTOR2CI5Q", "YoTVtzH1OSV2/V+sEXrX6FJQ7Isl7npmhCgFHUG9");
		} catch(Exception e){

		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2013-8-2");
		params.put("Operation", "ItemLookup");
		params.put("IdType", "UPC");
		params.put("SearchIndex", "All");
		params.put("ItemId", this.fetchThread.getReviewsCompiler().getUPC());
		params.put("ResponseGroup", "Large");
		params.put("AssociateTag", "zebra030-20");
		try {
			String requestUrl = helper.sign(params);
			org.w3c.dom.Document response = getResponse(requestUrl);
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			Properties props = new Properties();
			props.put(OutputKeys.INDENT, "yes");
			trans.setOutputProperties(props);
			StreamResult res = new StreamResult(new StringWriter());
			DOMSource src = new DOMSource(response);trans.transform(src, res);
			url = response.getElementsByTagName("DetailPageURL").item(0).getTextContent();
			url = url.substring(0, url.indexOf("/dp/")+14)+"/";
		} catch (Exception ex) {
			AmazonURL address = new AmazonURL(this.fetchThread.getReviewsCompiler().getUPC());
			address.generateURL();
			url = address.getURL();
		}
		Element overallRating = null;
		if(url==null)
			url = "No ASIN found";
		if(!url.equals("No ASIN found"))
		{
			int i = 0;
			try
			{
				Document doc = Jsoup.connect(url).get();
				overallRating = doc.select(".reviews div.gry.txtnormal.acrrating").first();
				if(overallRating==null)
				{	
					AmazonURL address = new AmazonURL(this.fetchThread.getReviewsCompiler().getUPC());
					address.generateURL();
					String url2 = address.getURL();
					doc = Jsoup.connect(url).get();
					overallRating = doc.select(".reviews div.gry.txtnormal.acrrating").first();
				}
				if(overallRating==null)
				{
					Review rev= new Review("No reviews found--3", 0, reviewCount);
					rev.setTitle("");
					rev.setRating(0);
					this.fetchThread.addReview(rev);					
					this.setCompletion(true);
					return;
				}
				double overallRatingNum = Double.parseDouble(overallRating.text().
						substring(0,3))*2;
				Elements reviews = doc.select("#revMHRL .mt9.reviewtext");
				Elements titles = doc.select("#revMHRL .txtlarge.gl3.gr4.reviewTitle.valignMiddle");
				Elements ratings = doc.select("div.mt4.ttl");
				//System.out.println("here");
				while (this.reviewCount < reviews.size())
				{
					String title = titles.get(this.reviewCount).text();
					String review = reviews.get(this.reviewCount).text();
					int rating = (int) (2 * Double.parseDouble(ratings.get
							(reviewCount).text().substring(0, 3)));
					review = review.replace("Read more ›", "");

					// Amazon's reviews are out of 5
					Review rev = new Review(review,rating, reviewCount);
					rev.setTitle(title);
					rev.setRating(rating);
					rev.setOverallRating(overallRatingNum);

					this.fetchThread.addReview(rev);

					if (Thread.interrupted())
						return;
					reviewCount++;
				}
			}
			catch (IOException e) {
				if(i<6)
				{
					i++;
					this.run();
				}
				else
				{
					if(overallRating==null)
					{	
						Review rev= new Review("No reviews found--1", 0, reviewCount);
						rev.setTitle("");
						rev.setRating(0);
						this.fetchThread.addReview(rev);					
						this.setCompletion(true);
						return;
					}					
				}
			}
		}
		else
		{
			Review rev= new Review("No reviews found--2", 0, reviewCount);
			rev.setTitle("");
			rev.setRating(0);
			this.fetchThread.addReview(rev);
		}		
		this.setCompletion(true);
	}

	private static org.w3c.dom.Document getResponse(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        org.w3c.dom.Document doc = builder.parse(url);
        return doc;
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