package com.zebraviews.reviews;

import java.util.LinkedList;
import java.util.Queue;

import com.zebraviews.reviews.preprocessor.PreprocessingData;
import com.zebraviews.reviews.preprocessor.Preprocessor;

public class PreprocessorFetchThread extends Thread {

	private Queue<PreprocessingData> preprocessingData;
	private Preprocessor preprocessor;
	private ReviewsCompiler compiler;
	
	public PreprocessorFetchThread(ReviewsCompiler compiler, 
			Preprocessor preprocessor) {
		super(preprocessor);
		this.preprocessingData = new LinkedList<PreprocessingData>();
		this.preprocessor = preprocessor;
		this.setCompiler(compiler);
	}
	
	public boolean activate() {
		if (this.isAlive())
			return false;
		else
		{
			this.preprocessor.init(this.preprocessor.
					getPreprocessingDataName());
			this.preprocessor.setFetchThread(this);
			this.start();
			return true;
		}
	}
	
	public boolean init(Preprocessor preprocessor) {
		if (this.isAlive())
			return false;
		else
		{
			this.preprocessor = preprocessor;
			return true;
		}
	}
	
	public void addPreprocessingData(PreprocessingData data) {
		if (this.preprocessingData == null)
			this.preprocessingData = new LinkedList<PreprocessingData>();
		this.preprocessingData.add(data);
	}
	
	public boolean hasPreprocessingData() {
		return !this.preprocessingData.isEmpty();
	}
	
	public PreprocessingData retrievePreprocessingData() {
		return this.preprocessingData.poll();
	}

	public ReviewsCompiler getCompiler() {
		return compiler;
	}

	public void setCompiler(ReviewsCompiler compiler) {
		this.compiler = compiler;
	}
}
