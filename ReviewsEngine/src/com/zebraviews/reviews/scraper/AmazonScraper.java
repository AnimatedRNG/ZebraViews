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

public class AmazonScraper {
	public static void main(String[] args) {
		String upc="013803136340";
		AmazonURL address=new AmazonURL(upc);
		address.setURL();
		String url=address.getURL();
		if(!url.equals("No ASIN found"))
		{
			try
			{
				Document doc = Jsoup.connect(url).get();
				Element prodTitle=doc.select("span#btAsinTitle").first();
				System.out.println(prodTitle.text());
				Element rating = doc.select("div.jumpbar").first();
				System.out.println("OVERALL RATING: " + rating.text().substring(0, 18));
				Elements reviews = doc.select("#revMHRL .mt9.reviewtext");
				Elements titles = doc.select("#revMHRL .txtlarge.gl3.gr4.reviewTitle.valignMiddle");
				for(int i = 0; i < 10; i++)
				{
					try
					{
						String title = titles.get(i).text();
						String review = reviews.get(i).text();
						review = review.replace("Read more ›", "");
						System.out.println("\nTITLE OF REVIEW: " + title);
						System.out.println("REVIEW: " + review);
					}
					catch (IndexOutOfBoundsException e) 
					{
						System.out.println("Reviews found: "+i);
						break;
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
			System.out.print("Item does not exist in any of our product review sources.");
	}
}