package com.animatedjuzz.zebraviews;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
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
		
		ActionBar.Tab reviewsTab = getSupportActionBar().newTab();
		reviewsTab.setText(R.string.reviews_tab_title);
		
		ActionBar.Tab priorSearchesTab = getSupportActionBar().newTab();
		priorSearchesTab.setText(R.string.previous_searches_title);
		
		reviewsTab.setTabListener
		(new ReviewsTabListener<SherlockFragment>
				((SherlockFragmentActivity) this,
				getResources().getString(R.string.reviews_tab_title),
				ReviewsFragment.class));
		
		priorSearchesTab.setTabListener
		(new ReviewsTabListener<SherlockFragment>
				((SherlockFragmentActivity) this,
				getResources().getString(R.string.previous_searches_title),
				PreviousSearchesFragment.class));
		
		getSupportActionBar().addTab(reviewsTab);
		getSupportActionBar().addTab(priorSearchesTab);
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
