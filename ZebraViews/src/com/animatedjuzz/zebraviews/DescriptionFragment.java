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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class DescriptionFragment extends SherlockFragment implements DescriptionListener{

	private ProgressBar progress;
	private RatingBar ratings;
	private TextView title;
	private TextView description;
	private TextView bestPrice;
	private TextView price;
	private TextView allergens;
	private TextView suggestions;
	private TextView suggestionsLabel;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View total = inflater.inflate(R.layout.description_fragment, 
				container, false);
		this.progress = (ProgressBar) 
				total.findViewById(R.id.description_loading);
		this.progress.setVisibility(View.VISIBLE);
		
		this.ratings = (RatingBar) total.findViewById(R.id.ratings);
		
		this.ratings.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				return true;
			}
		});
		
		this.title = (TextView) total.findViewById(R.id.title);
		this.description = (TextView) total.findViewById(R.id.description);
		this.bestPrice = (TextView) total.findViewById(R.id.best_price);
		this.price = (TextView) total.findViewById(R.id.price);
		this.allergens = (TextView) total.findViewById(R.id.allergens);
		this.suggestionsLabel = (TextView) total.findViewById(R.id.suggestion_label);
		this.suggestions = (TextView) total.findViewById(R.id.suggestions);
		
		return total;
	}

	@Override
	public void onPreExecuteComplete(ReviewsManager manager) {
		this.progress.setVisibility(View.GONE);
		this.ratings.setVisibility(View.VISIBLE);
		this.ratings.setStepSize(0.1F);
		this.ratings.setRating(manager.getProductRating() / 2.0F);
		this.title.setText(manager.getProductName());
		this.description.setText(manager.getProductDescription());
		this.bestPrice.setVisibility(View.VISIBLE);
		
		if (manager.getBestPrice() == null)
			this.price.setText("Price not found"); // add to strings file
		
		this.price.setText("$" + manager.getBestPrice());
		
		String allergen = manager.getAllergens();
		if (allergen != null && allergen.length() >= 2)
		{
			this.allergens.append(" " + allergen);
			this.allergens.setVisibility(View.VISIBLE);
		}
		
		String suggestion = manager.getSuggestions();
		if (suggestion != null && suggestion.length() >= 2)
		{
			this.suggestionsLabel.setVisibility(View.VISIBLE);
			this.suggestions.setText(suggestion);
			this.suggestions.setVisibility(View.VISIBLE);
		}
	}

}