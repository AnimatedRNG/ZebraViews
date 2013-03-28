package com.zebraviews.reviews.scraper;

import java.util.Comparator;

public class ScraperComparator implements Comparator<Scraper> {

	@Override
	public int compare(Scraper o1, Scraper o2) {
		if (o1.getPriority() > o2.getPriority())
			return 1;
		else if (o1.getPriority() < o2.getPriority())
			return -1;
		else return (int) (Math.round(Math.random()*2-1));
	}

}
