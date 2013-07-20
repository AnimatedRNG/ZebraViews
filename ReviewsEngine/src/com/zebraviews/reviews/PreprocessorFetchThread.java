package com.zebraviews.reviews;

import com.zebraviews.reviews.preprocessor.Preprocessor;

public class PreprocessorFetchThread extends Thread {

	private Preprocessor preprocessor;
	private ReviewsCompiler compiler;
	
	public PreprocessorFetchThread(ReviewsCompiler compiler, 
			Preprocessor preprocessor) {
		super(preprocessor);
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
	
	// Get this before interrupting
	public Preprocessor getPreprocessor() {
		return this.preprocessor;
	}

	public ReviewsCompiler getCompiler() {
		return compiler;
	}

	public void setCompiler(ReviewsCompiler compiler) {
		this.compiler = compiler;
	}
}
