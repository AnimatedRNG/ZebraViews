package com.zebraviews.reviews.preprocessor;

public abstract class Preprocessor implements Runnable {
	
	private boolean simulataneous;
	private boolean running;
	private PreprocessingData data;
	
	@Override
	public void run() {
		while (!Thread.interrupted())
		{
			if (!running)
				return;
			if (simulataneous)
				this.onSimultaneousExecute();
			else
				this.onPreExecute();	
		}
	}

	public void init(boolean simultaneous, String dataName) {
		this.simulataneous = simultaneous;
		this.running = true;
		this.setPreprocessingData(new PreprocessingData(dataName));
	}
	
	public PreprocessingData getPreprocessingData() {
		if (this.running)
			return this.data;
		else
			return null;
	}

	public void setPreprocessingData(PreprocessingData data) {
		this.data = data;
	}
	
	public void done() {
		this.running = false;
	}

	// Write simultaneously executing code with this
	// Save data every execute in PreprocessingData object
	public abstract void onSimultaneousExecute();
	
	// Write pre-execution code with this
	// Save data every execute in PreprocessingData object
	public abstract void onPreExecute();
}
