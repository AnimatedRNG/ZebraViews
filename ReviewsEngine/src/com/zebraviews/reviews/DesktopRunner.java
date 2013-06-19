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


public class DesktopRunner {

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.nanoTime();
		String upc="9780439420105";
		AmazonURL address=new AmazonURL(upc);
		address.generateURL();
		String url=address.getURL();
		System.out.println(url);
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns");
		
		ReviewsCompiler compiler = new ReviewsCompiler();
		compiler.activateAll();
		while (true)
		{
			for (Review r : compiler.executePartially().getReviews()) {
				System.out.println(r);
			}
		}
	}

}
