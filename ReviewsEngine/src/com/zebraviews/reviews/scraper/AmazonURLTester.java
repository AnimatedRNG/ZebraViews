package com.zebraviews.reviews.scraper;

public class AmazonURLTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String upc="097361700045";
		AmazonURL address=new AmazonURL(upc);
		address.setURL();
		System.out.println("URL: "+address.getURL());

	}

}
