package com.animatedjuzz.zebraviews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar;

public class ReviewsActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviews_layout);
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
        Intent scanner = new Intent(ReviewsActivity.this, ScannerActivity.class);
        startActivity(scanner);
	}
}
