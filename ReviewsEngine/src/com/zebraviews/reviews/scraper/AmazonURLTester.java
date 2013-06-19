package com.zebraviews.reviews.scraper;

public class AmazonURLTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		String upc="9780439420105";
		AmazonURL address=new AmazonURL(upc);
		address.setURL();
		String url=address.getURL();
		System.out.println(url);
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");

	}

}
