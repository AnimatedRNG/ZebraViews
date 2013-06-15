package com.zebraviews.reviews.scraper;

public class AmazonURLTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String upc="027000488287";
		AmazonURL address=new AmazonURL(upc);
		address.setURL2();
		String url=address.getURL();
		System.out.println(url);
	}

}
