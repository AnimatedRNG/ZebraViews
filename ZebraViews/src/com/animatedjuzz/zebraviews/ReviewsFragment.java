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

import java.io.IOException;
import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.actionbarsherlock.app.SherlockFragment;
import com.zebraviews.reviews.Review;
import com.zebraviews.reviews.ReviewsData;

public class ReviewsFragment extends SherlockFragment implements ReviewsListener {

	private ArrayAdapter<String> adapter;
	private ArrayList<String> reviewList;
	
	private ProgressBar progress;
	
	private final static int MINIMUM_REVIEWS = 8;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String upc = this.getActivity().getIntent().
				getStringExtra("com.animated.juzzz.zebraviews.BARCODE_TEXT");
		ReviewsManager downloader = null;
		try {
			downloader = new ReviewsManager(this, upc,
					this.getActivity().getAssets().open("XML/priority_list.xml"));
		} catch (IOException e) {
			Log.d("Priority Inflation", "No priority list found");
		}
		downloader.execute();
	}

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
			this.reviewList.add(review.toString());
		if (this.reviewList.size() == ReviewsFragment.MINIMUM_REVIEWS)
			this.progress.setVisibility(View.GONE);
		if (this.reviewList.size() >= ReviewsFragment.MINIMUM_REVIEWS)
			this.updateList();
	}

	@Override
	public void onCompletion() {
		this.progress.setVisibility(View.GONE);
		this.updateList();
	}
	
	private void updateList() {
		BaseAdapter adapter = ((BaseAdapter) ((ListView)getView().
				findViewById(R.id.review_list)).getAdapter());
		adapter.notifyDataSetChanged();
	}
	
}