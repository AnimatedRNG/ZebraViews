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
