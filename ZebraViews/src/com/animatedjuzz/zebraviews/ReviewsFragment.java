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

package com.animatedjuzz.zebraviews;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.actionbarsherlock.app.SherlockFragment;
import com.zebraviews.reviews.Review;
import com.zebraviews.reviews.ReviewsData;

public class ReviewsFragment extends SherlockFragment 
										implements ReviewsListener {

	private ArrayAdapter<String> adapter;
	private ArrayList<String> reviewList;
	
	private ProgressBar progress;
	
	private final static int MINIMUM_REVIEWS = 8;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View total = inflater.inflate(R.layout.reviews_fragment, 
				container, false);
		ListView list = (ListView) total.findViewById(R.id.review_list);
		this.reviewList = new ArrayList<String>();
		this.adapter = new ArrayAdapter<String>(this.getActivity(),
				android.R.layout.simple_list_item_1, reviewList);
		list.setAdapter(this.adapter);
		
		this.progress = (ProgressBar) 
				total.findViewById(R.id.review_loading);
		this.progress.setVisibility(View.VISIBLE);
		
		return total;
	}

	@Override
	public void onReviewsDataDownloaded(ReviewsData data) {
		for (Review review : data.getReviews())
			this.reviewList.add(review.getReview());
		if (this.reviewList.size() >= ReviewsFragment.MINIMUM_REVIEWS)
		{
			this.adapter.notifyDataSetChanged();
			this.progress.setVisibility(View.GONE);
		}
	}

	@Override
	public void onCompletion() {
		this.adapter.notifyDataSetChanged();
	}
	
}