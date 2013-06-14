package com.zebraviews.reviews.scraper;

public class AmazonURLTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String upc="9780451530578";
		AmazonURL address=new AmazonURL(upc);
		address.setURL();
		String url=address.getURL();
		System.out.println(url);

	}

}
