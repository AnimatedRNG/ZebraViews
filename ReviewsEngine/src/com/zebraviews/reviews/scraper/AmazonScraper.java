package com.zebraviews.reviews.scraper;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonScraper {
	public static void main(String[] args) {
		String url = "http://www.amazon.com/Quilted-Northern-Ultra-Tissue-Double/dp/B007UZNS5W/ref=sr_1_1?ie=UTF8&qid=1371163773&sr=8-1&keywords=toilet+paper";
		try
		{
			Document doc = Jsoup.connect(url).get();
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
					System.out.println(i);
					break;
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}