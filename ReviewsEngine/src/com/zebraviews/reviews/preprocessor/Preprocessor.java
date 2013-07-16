package com.zebraviews.reviews.preprocessor;

public abstract class Preprocessor implements Runnable {
	
	private boolean simulataneous;
	private boolean initialized;
	private PreprocessingData data;
	
	@Override
	public void run() {
		if (!initialized)
			return;
		while (!Thread.interrupted())
		{
			if (simulataneous)
				this.onSimultaneousExecute();
			else
				this.onPreExecute();	
		}
	}

	public void init(boolean simultaneous) {
		this.simulataneous = simultaneous;
		this.initialized = true;
		this.setPreprocessingData(new PreprocessingData());
	}
	
	public PreprocessingData getPreprocessingData() {
		if (this.initialized)
			return this.data;
		else
			return null;
	}

	public void setPreprocessingData(PreprocessingData data) {
		this.data = data;
	}

	// Write simultaneously executing code with this
	// Save data every execute in PreprocessingData object
	public abstract void onSimultaneousExecute();
	
	// Write pre-execution code with this
	// Save data every execute in PreprocessingData object
	public abstract void onPreExecute();
}
