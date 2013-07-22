package com.zebraviews.reviews.preprocessor;

import java.util.HashMap;

// More to come soon
public class PreprocessingData extends HashMap<String, String> implements Comparable<PreprocessingData> {

	private String name;
	
	private static final long serialVersionUID = -1331713568193237663L;

	public PreprocessingData(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public int compareTo(PreprocessingData o) {
		return this.getName().compareTo(o.getName());
	}
}
