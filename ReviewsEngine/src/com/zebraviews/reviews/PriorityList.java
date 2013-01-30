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
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.zebraviews.reviews.scraper.Scraper;

public class PriorityList {
	
	private ArrayList<Scraper> scraperPool;
	
	private void inflateList() {
		scraperPool = new ArrayList<Scraper>();
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
			priority = priorityBuilder.parse
					(new File("XML/priority_list.xml"));
		} catch (SAXException e) {
			System.out.println("SAX Exception");
			// TODO Add error handling...
		} catch (IOException e) {
			System.out.println("IO Exception");
			// TODO Add error handling...
		}
		priority.getDocumentElement().normalize();
	}
}
