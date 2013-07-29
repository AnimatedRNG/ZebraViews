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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.zebraviews.reviews.Review;
import com.zebraviews.reviews.ReviewsData;

public class ReviewsFragment extends SherlockFragment implements ReviewsListener {

	private ArrayList<String> reviewList;
	
	//private float overallRating = 0;
	//private int ratings = 0;
	
	//private ReviewsManager tempReviews;
	private boolean progressDisable;
	
	private ProgressBar progress;
	//private RatingBar ratingsBar;
	private TextView titleText;
	
	private final static int MINIMUM_REVIEWS = 8;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		/*String upc = this.getActivity().getIntent().
				getStringExtra("com.animated.juzzz.zebraviews.BARCODE_TEXT");
		this.reviews = null;
		try {
			reviews = new ReviewsManager(this, (DescriptionListener)
					this.getActivity().getSupportFragmentManager().
						findFragmentByTag(getResources().getString
						(R.string.description_tab_title)), upc, this.
						getActivity().getAssets().
						open("XML/priority_list.xml"));
		} catch (IOException e) {
			Log.d("Priority Inflation", "No priority list found");
		}
		reviews.execute();*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View total = inflater.inflate(R.layout.reviews_fragment, 
				container, false);
		ListView list = (ListView) total.findViewById(R.id.review_list);
		if (this.reviewList == null)
			this.reviewList = new ArrayList<String>();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.
				getActivity(),android.R.layout.simple_list_item_1, reviewList);
		list.setAdapter(adapter);
		
		this.progress = (ProgressBar) 
				total.findViewById(R.id.review_loading);
		if (!progressDisable)
			this.progress.setVisibility(View.VISIBLE);
		//this.ratingsBar = (RatingBar) total.findViewById(R.id.product_rating);
		//this.ratingsBar.setStepSize(0.1F);
		
		//this.titleText = (TextView) total.findViewById(R.id.product_title);
		
		return total;
	}

	// Messy, will clean up later
	@Override
	public void onReviewsDataDownloaded(ReviewsData data, ReviewsManager manager) {
		for (Review review : data.getReviews())
		{
			if (this.reviewList == null)
				this.reviewList = new ArrayList<String>();
			//this.overallRating += review.getOverallRating();
			this.reviewList.add(review.toString());
			//this.ratingsBar.setRating((overallRating/((float) ++ratings))
				//	/ 2.0F);
		}
		
		if (this.progress == null)
		{
			if (this.reviewList == null)
				this.reviewList = new ArrayList<String>();
			if (this.reviewList.size() == ReviewsFragment.MINIMUM_REVIEWS)
				this.progressDisable = true;
			return;
		}
		
		if (this.reviewList.size() == ReviewsFragment.MINIMUM_REVIEWS)
		{
			this.progress.setVisibility(View.GONE);
			//this.titleText.setText(manager.getProductName());
			//this.getView().findViewById(R.id.product_rating).
			//setVisibility(View.VISIBLE);
		}
		if (this.reviewList.size() >= ReviewsFragment.MINIMUM_REVIEWS)
		{
			this.updateList();
		}
	}

	@Override
	public void onCompletion(ReviewsManager manager) {
		try {
			this.progress.setVisibility(View.GONE);
		} catch (NullPointerException n) {
			this.progressDisable = true;
			return;
		}
		//this.titleText.setText(manager.getProductName());
		this.updateList();
		//this.getView().findViewById(R.id.product_rating).
		//setVisibility(View.VISIBLE);
	}
	
	private void updateList() {
		BaseAdapter adapter = ((BaseAdapter) ((ListView)getView().
				findViewById(R.id.review_list)).getAdapter());
		adapter.notifyDataSetChanged();
	}
	
}