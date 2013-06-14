package com.zebraviews.reviews.scraper;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AmazonURL
{
	public AmazonURL(String upc)
	{
		this.upc=upc;
	}
	
	public void setURL()
	{
		try
		{
			Document doc = Jsoup.connect("http://www.synccentric.com/admin/keyword-results.php?search="+upc).get();
			Element asinNum = doc.select("div.datacontainer").first();
			url="amazon.com/dp/"+asinNum.text().substring(asinNum.text().indexOf("ASIN")+6, asinNum.text().indexOf("ASIN")+16);

		}
		catch (IOException e) {
			System.out.println("No ASIN found");
			e.printStackTrace();
		}		
	}
	
	public String getURL()
	{
		return url;
	}
	protected String upc;
	protected String url;
	
}
