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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zebraviews.reviews.scraper.Scraper;
import com.zebraviews.reviews.scraper.ScraperComparator;

public class PriorityList {
	
	private PriorityQueue<Scraper> scraperPool;
	private final static String SCRAPER_PACKAGE_PATH = 
			"com.zebraviews.reviews.scraper";
	private final static String SCRAPER_SUFFIX = "Scraper";
	private final static int MINIMUM_SIZE = 16;
	
	private InputStream readFile;
	
	public PriorityList(InputStream readFile) {
		this.readFile = readFile;
		Document scraperDoc = this.inflateList();
		this.fillScraperPool(scraperDoc);
	}
	
	private Document inflateList() {
		scraperPool = new PriorityQueue<Scraper>(PriorityList.MINIMUM_SIZE
				, new ScraperComparator());
		DocumentBuilderFactory priorityFactory = 
				DocumentBuilderFactory.newInstance();
		DocumentBuilder priorityBuilder = null;
		try {
			priorityBuilder = priorityFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Parser Exception");
			// TODO: Add error handling...
		}
		Document priority = null;
		try {
			priority = priorityBuilder.parse(this.readFile);
		} catch (SAXException e) {
			System.out.println("SAX Exception");
			// TODO Add error handling...
		} catch (IOException e) {
			System.out.println("IO Exception");
			System.out.println(e.getMessage());
			// TODO Add error handling...
		}
		priority.getDocumentElement().normalize();
		return priority;
	}
	
	private void fillScraperPool(Document scraperList) {
		NodeList scrapers = scraperList.getElementsByTagName("scraper");
		
		for (int scraperIndex = 0; scraperIndex < scrapers.getLength();
				scraperIndex++)
		{
			Node node = scrapers.item(scraperIndex);
			Element scraperElement = (Element) node;
			
			String scraperName = scraperElement.getElementsByTagName("name")
				.item(0).getTextContent();
			Float priority = Float.parseFloat(scraperElement
				.getElementsByTagName("priority").item(0).getTextContent());
			boolean enabled = Boolean.parseBoolean(scraperElement
				.getElementsByTagName("enabled").item(0).getTextContent());
			boolean interruptible = Boolean.parseBoolean(scraperElement
				.getElementsByTagName("interruptible").item(0)
				.getTextContent());
			
			if (!enabled) return;
			
			String completeScraperName = PriorityList.SCRAPER_PACKAGE_PATH +
					"." + scraperName + PriorityList.SCRAPER_SUFFIX;
			
			Class scraperClass = null;
			Scraper scraper = null;
			
			try {
				scraperClass = Class.forName(completeScraperName);
				scraper = (Scraper) scraperClass.newInstance();
			} catch (ClassNotFoundException e) {
				System.out.println("ClassNotFound Exception");
				// TODO Add error handling...
			} catch (InstantiationException e) {
				System.out.println("Instantiation Exception");
				// TODO Add error handling...
			} catch (IllegalAccessException e) {
				System.out.println("IllegalAccess Exception");
				// TODO Add error handling...
			}
			
			scraper.setPriority(priority);
			scraper.setInterruptibility(interruptible);
			
			this.scraperPool.add(scraper);
		}
	}
	
	public Scraper getScraper() {
		return this.scraperPool.poll();
	}
	
	public boolean hasScraper() {
		return !this.scraperPool.isEmpty();
	}
}
