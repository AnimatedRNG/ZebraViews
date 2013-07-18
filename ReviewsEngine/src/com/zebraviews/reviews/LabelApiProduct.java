package com.zebraviews.reviews;

import java.util.ArrayList;

public class LabelApiProduct {
	private String name;
	private ArrayList<String> allergens;
	
	public LabelApiProduct(String productName, ArrayList<String>containedAllergens){
		name = productName;
		allergens = containedAllergens;
	}
}
