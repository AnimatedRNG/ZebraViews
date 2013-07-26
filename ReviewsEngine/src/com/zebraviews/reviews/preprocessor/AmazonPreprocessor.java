package com.zebraviews.reviews.preprocessor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zebraviews.reviews.AmazonURL;
import com.zebraviews.reviews.PreprocessorFetchThread;

public class AmazonPreprocessor extends Preprocessor{

	private final static String DATA_NAME = "Amazon";
	
	@Override
	public void onSimultaneousExecute() {
		
	}

	@Override
	public void onPreExecute() {
		Element description = null;
		Element productName = null;
		Elements similarProducts = null;
		String similarProductsNames = new String();
		AmazonURL address = new AmazonURL(this.getFetchThread().
				getCompiler().getUPC());
		address.generateURL();
		String url = address.getURL();
		if(!url.equals("No ASIN found"))
		{
			try
			{
				Document doc = Jsoup.connect(url).get();
				description = doc.select("#postBodyPS").first();
				productName = doc.select(".buying span[id=btAsinTitle]").first();
				if (description == null) 
					description = doc.select(".content .productdescriptionwrapper").first();
				if (description == null)
					description = doc.select(".aplus").first();
				similarProducts = doc.select("div.asindetails");
				if(!similarProducts.text().equals(""))
				{
					for(int i = 0; (i < similarProducts.size() && i < 4); i++)
						similarProductsNames+=similarProducts.get(i).text().substring(0, similarProducts.get(i).text().indexOf("stars")+5);
				}
				else
				{
					similarProducts = doc.select("div.shoveler-content");
					String[] stuff = similarProducts.first().text().split("\\d\\d\\p{Punct}\\d\\d\\p{Blank}");
					for(String e: stuff)
						similarProductsNames+=e.substring(0,e.indexOf("stars")-13)+GooglePreprocessor.DELIMITER;
				}
			}
			catch (Exception e) {
			}
		}
		if (!this.getPreprocessingData().containsKey("description") && description != null)
			this.getPreprocessingData().put("description", description.text());
		if (!this.getPreprocessingData().containsKey("name") && productName != null)
			this.getPreprocessingData().put("name", productName.text());
		if (!this.getPreprocessingData().containsKey("similarProducts") && similarProductsNames!=null && !similarProductsNames.equals(""))
			this.getPreprocessingData().put("similarProducts", similarProductsNames);
		this.done();
	}

	@Override
	public String getPreprocessingDataName() {
		return AmazonPreprocessor.DATA_NAME;
	}
}