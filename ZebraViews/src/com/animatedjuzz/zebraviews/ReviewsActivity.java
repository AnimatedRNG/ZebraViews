package com.animatedjuzz.zebraviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar;

public class ReviewsActivity extends SherlockActivity implements ActionBar.TabListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
        Intent scanner = new Intent(ReviewsActivity.this, ScannerActivity.class);
        startActivity(scanner);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

}
