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
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class AmazonURL
{
	public AmazonURL(String upc)
	{
		this.upc=upc;
		url=null;
	}
	
	//DO NOT USE THIS METHOD. USE setURL().
	/*public void setURL()
	{
		try
		{
			Document doc = Jsoup.connect("http://www.synccentric.com/admin/keyword-results.php?search="+upc).get();
			Element asinNum = doc.select("div.datacontainer").first();
			if(asinNum!=null)
				url="http://www.amazon.com/dp/"+asinNum.text().substring(asinNum.text().indexOf("ASIN")+6, asinNum.text().indexOf("ASIN")+16)+"/";
			else
				url="No ASIN found";
		}
		catch (IOException e)
		{
				url=("No ASIN found");
		}		
	}*/
	
	public void generateURL()
	{
		try
		{
			Document doc = Jsoup.connect("http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords="+upc).get();
			Element link = doc.select("#atfResults a[href]").first();
			if(link == null)
			{
				url = "No ASIN found";
				return;
			}
			url = link.attr("href");
		}
		catch (IOException e)
		{
				url=("No ASIN found");
		}
		if(url==null)
			url="No ASIN found";
	}
	
	public String getURL()
	{
		return url;
	}
	private String upc;
	private String url;
	
}