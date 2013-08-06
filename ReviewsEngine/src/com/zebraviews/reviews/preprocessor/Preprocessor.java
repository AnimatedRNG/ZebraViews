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

package com.zebraviews.reviews.preprocessor;

import com.zebraviews.reviews.PreprocessorFetchThread;

public abstract class Preprocessor implements Runnable {
	
	private boolean running;
	private PreprocessingData data;
	private PreprocessorFetchThread fetchThread;
	
	public final static String DELIMITER = "ACDQRFZJGKLTIOVQWERDAVCAGRRRRRHGSW";
	
	@Override
	public void run() {
		while (!Thread.interrupted())
		{
			if (!running)
				return;
			
			if (!this.getFetchThread().getCompiler().isPrexecuting())
				this.onSimultaneousExecute();
			else
				this.onPreExecute();
		}
	}

	public void init(String dataName) {
		this.running = true;
		this.setPreprocessingData(new PreprocessingData(dataName));
	}
	
	public PreprocessingData getPreprocessingData() {
		return this.data;
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
	
	public void resume() {
		this.running = true;
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
