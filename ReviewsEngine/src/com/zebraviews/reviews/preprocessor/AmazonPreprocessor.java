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

package com.zebraviews.reviews.preprocessor;

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
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.util.SignedRequestsHelper;

public class AmazonPreprocessor extends Preprocessor{

	private final static String DATA_NAME = "Amazon";
	//private static int attempts = 0;
	//private boolean scraping;
	public boolean running;
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		this.running = true;
		String description = null;
		String productName = null;
		NodeList similarProducts = null;
		String similarProductsList = null;
		String price = null;
		Double overallRatingNum = 0.0;
		/*AmazonURL address = new AmazonURL(this.getFetchThread().
				getCompiler().getUPC());
		address.generateURL();
		String url = address.getURL();
		double overallRatingNum = 0.0F;
		if(!url.equals("No ASIN found"))
		{*/
			//do
			//{
				/*scraping = false;
				Document doc = null;
				try {
					doc = Jsoup.connect(url).get();
				} catch (Exception e1) {
					scraping = true;
					AmazonPreprocessor.attempts++;
				}*/

				/*try
				{
					productName = doc.select(".buying span[id=btAsinTitle]").first();
				} catch (Exception e2){
					scraping = true;
					AmazonPreprocessor.attempts++;
				}
				try
				{
					overallRating = doc.select("span.asinreviewssummary.acr-popover").first();
					overallRatingNum = Double.parseDouble(overallRating.text().substring(0,3))*2; 
				} catch (Exception e3){
					scraping = true;
					AmazonPreprocessor.attempts++;
				}
				try
				{
					description = doc.select("#postBodyPS").first();
					if (description == null) 
						description = doc.select(".content .productdescriptionwrapper").first();
					if (description == null)
						description = doc.select(".aplus").first();
				} catch (Exception e4){
					scraping = true;
					AmazonPreprocessor.attempts++;
				}
				try
				{
					similarProducts = doc.select("div.asindetails");
					if(!similarProducts.text().equals(""))
					{
						for(int i = 0; (i < similarProducts.size() && i < 4); i++)
							similarProductsNames+=similarProducts.get(i).text().substring(0, similarProducts.get(i).text().indexOf("stars")+5)+GooglePreprocessor.DELIMITER;
					}
					else
					{
						similarProducts = doc.select("div.shoveler-content");
						String[] stuff = similarProducts.first().text().split("\\d\\d\\p{Punct}\\d\\d\\p{Blank}");
						for(String e: stuff)
							similarProductsNames+=e.substring(0,e.indexOf("stars")-13)+Preprocessor.DELIMITER;
					}
				} catch (Exception e5){
					scraping = true;
					AmazonPreprocessor.attempts++;
				} */
			SignedRequestsHelper helper = null;
			try {
				helper = SignedRequestsHelper.getInstance("ecs.amazonaws.com", "AKIAJYAME7RKTOR2CI5Q", "YoTVtzH1OSV2/V+sEXrX6FJQ7Isl7npmhCgFHUG9");
			} catch(Exception e){
				
			}
			
			String upc = this.getFetchThread().getCompiler().
					getUPC();
			Map<String, String> params = new HashMap<String, String>();
			params.put("Service", "AWSECommerceService");
			params.put("Version", "2013-8-2");
			params.put("Operation", "ItemLookup");
			if (upc.length() == 12)
				params.put("IdType", "UPC");
			else// if (upc.length() == 10 || upc.length() == 13)
				params.put("IdType", "ISBN");
			params.put("SearchIndex", "All");
			params.put("ItemId", upc);
			params.put("ResponseGroup", "Large");
			params.put("AssociateTag", "zebra030-20");
			try {
				String requestUrl = helper.sign(params);
				Document response = getResponse(requestUrl);
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				Properties props = new Properties();
				props.put(OutputKeys.INDENT, "yes");
				trans.setOutputProperties(props);
				StreamResult res = new StreamResult(new StringWriter());
				DOMSource src = new DOMSource(response);trans.transform(src, res);
				productName = response.getElementsByTagName("Title").item(0).getTextContent();
				description = response.getElementsByTagName("Content").item(0).getTextContent()
						.replace("<strong>", "").replace("<li>", "").replace("<ul>", "")
						.replace("</strong>", "").replace("</li>", "").replace("</ul>", "")
						.replace("<p>", "").replace("<b>", "").replace("<br>", "")
						.replace("</b>", "").replace("</p>", "").replace("<i>", "")
						.replace("</i>", "").replace("<a>", "").replace("</a>", "")
						.replace("<ul id=list1\">", "");
				String url = response.getElementsByTagName("DetailPageURL").item(0).getTextContent();
				try {
					price = response.getElementsByTagName("SalePrice").item(0).getTextContent();
				}
				catch (Exception e) {
				}
				if (price == null) {
					try {
						price = response.getElementsByTagName("LowestNewPrice").item(0).getTextContent();
					}
					catch (Exception e) {			
					}
				}
				if (price == null) {
					try {
						price = response.getElementsByTagName("ListPrice").item(0).getTextContent();
					}
					catch (Exception e) {
					}
				}
				if (price != null) {
					price = price.substring(price.indexOf("$"));
				}
				url = url.substring(0, url.indexOf("/dp/")+14)+"/";
				//AmazonURL genURL = new AmazonURL(upc); genURL.generateURL();
				//String url = genURL.getURL();
				org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
				similarProducts = response.getElementsByTagName("SimilarProduct");
				if (similarProducts.getLength() > 1){
					similarProductsList = similarProducts.item(0).getTextContent().substring(10);
					for (int i = 1; i < similarProducts.getLength(); i ++){
						similarProductsList += Preprocessor.DELIMITER + similarProducts.item(i).getTextContent().substring(10); 
					}
				}
				org.jsoup.nodes.Element overallRating = doc.select("span.asinreviewssummary.acr-popover").first();
				overallRatingNum = Double.parseDouble(overallRating.text().substring(0, 3)) * 2;
			} catch (Exception ex) {
				
			}

			/*if(AmazonPreprocessor.attempts==10)
			{
				scraping = false;
				this.done();
				
			}*/
			//}
		//while (scraping);
		if (!this.getPreprocessingData().containsKey("overallRating") && overallRatingNum != null)
			this.getPreprocessingData().put("overallRating", "" + overallRatingNum);
		if (!this.getPreprocessingData().containsKey("description") && description != null)
			this.getPreprocessingData().put("description", description);
		if (!this.getPreprocessingData().containsKey("name") && productName != null)
			this.getPreprocessingData().put("name", productName);
		if (!this.getPreprocessingData().containsKey("similarProducts") && similarProductsList!=null && !similarProductsList.equals(""))
			this.getPreprocessingData().put("similarProducts", similarProductsList);
		if (!this.getPreprocessingData().containsKey("prices") && price!=null && !price.equals(""))
			this.getPreprocessingData().put("prices", price);
		this.done();
		this.running = false;
	}

	@Override
	public String getPreprocessingDataName() {
		return AmazonPreprocessor.DATA_NAME;
	}
	
	private static Document getResponse(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(url);
        return doc;
    }
}
