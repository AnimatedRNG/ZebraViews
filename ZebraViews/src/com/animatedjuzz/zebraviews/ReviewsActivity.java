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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class ReviewsActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviews_layout);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		ActionBar.Tab reviewsTab = getSupportActionBar().newTab();
		reviewsTab.setText(R.string.reviews_tab_title);
		
		//ActionBar.Tab priorSearchesTab = getSupportActionBar().newTab();
		//priorSearchesTab.setText(R.string.previous_searches_title);
		
		ActionBar.Tab descriptionTab = getSupportActionBar().newTab();
		descriptionTab.setText(R.string.description_tab_title);
		
		reviewsTab.setTabListener
		(new ReviewsTabListener<SherlockFragment>
				((SherlockFragmentActivity) this,
				getResources().getString(R.string.reviews_tab_title),
				ReviewsFragment.class));
		
		/*priorSearchesTab.setTabListener
		(new ReviewsTabListener<SherlockFragment>
				((SherlockFragmentActivity) this,
				getResources().getString(R.string.previous_searches_title),
				PreviousSearchesFragment.class));*/
		
		descriptionTab.setTabListener
		(new ReviewsTabListener<SherlockFragment>
				((SherlockFragmentActivity) this,
				getResources().getString(R.string.description_tab_title),
				PreviousSearchesFragment.class));
		
		getSupportActionBar().addTab(reviewsTab);
		//getSupportActionBar().addTab(priorSearchesTab);
		getSupportActionBar().addTab(descriptionTab);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent scanner = new Intent(ReviewsActivity.this, ScannerActivity.class);
		startActivity(scanner);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			
			case R.id.scanner_settings:
				Intent settings = new Intent(ReviewsActivity.this,
					SettingsActivity.class);
				startActivity(settings);
				break;
			
			case (R.id.shopper):
				//Launch Google Shopper for this item.
				break;
			
			case android.R.id.home:
				NavUtils.navigateUpTo(this, new Intent(this, 
						ScannerActivity.class));
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override 
    public boolean onCreateOptionsMenu(Menu menu) {
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.layout.reviews_actionbar, menu);
    	return super.onCreateOptionsMenu(menu);
	}
}
