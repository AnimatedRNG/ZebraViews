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

public class Review {

	private String review;
	private int rating;
	private int index;
	
	public Review(int index) {
		this.index = index;
	}
	
	public Review(String review, int rating, int index) {
		this.review = review;
		this.rating = rating;
		this.index = index;
	}
	
	public String getReview() {
		return this.review;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
}