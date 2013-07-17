package com.zebraviews.reviews.preprocessor;

import com.zebraviews.reviews.PreprocessorFetchThread;

public abstract class Preprocessor implements Runnable {
	
	private boolean running;
	private PreprocessingData data;
	private PreprocessorFetchThread fetchThread;
	
	@Override
	public void run() {
		while (!Thread.interrupted())
		{
			if (!running)
				return;
			this.onSimultaneousExecute();
			this.onPreExecute();
		}
	}

	public void init(String dataName) {
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
	
	// Deprecated for obvious reasons
	public PreprocessorFetchThread getFetchThread() {
		return this.fetchThread;
	}

	public void setFetchThread(PreprocessorFetchThread fetchThread) {
		this.fetchThread = fetchThread;
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
	
	// What do you want to call the data object?
	public abstract String getPreprocessingDataName();
}
