package com.animatedjuzz.zebraviews;

import android.content.Intent;
import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;

public class ReviewsActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
        Intent scanner = new Intent(ReviewsActivity.this, ScannerActivity.class);
        startActivity(scanner);
	}

}
