package com.animatedjuzz.zebraviews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.client.android.CaptureActivity;

public class ScannerActivity extends CaptureActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_screen_layout);
    }
    
    @Override 
    public boolean onCreateOptionsMenu(Menu menu) {
    	com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
    	inflater.inflate(R.layout.scanner_search, menu);
    	SearchView search = 
    			(SearchView) menu.findItem(R.id.scanner_search).getActionView();
    	search.setQueryHint(getResources().getString(R.string.scanner_menu_search));
    	search.setIconifiedByDefault(false);
    	return super.onCreateOptionsMenu(menu);
    }
	
	@Override
	public void handleDecode(Result rawResult, Bitmap barcode) {
		Toast.makeText(this.getApplicationContext(), "Scanned code "+ rawResult.getText(), Toast.LENGTH_LONG).show();
		if (rawResult.getBarcodeFormat() == BarcodeFormat.QR_CODE)
		{
			Intent browserQR = new Intent(Intent.ACTION_VIEW, Uri.parse(rawResult.getText()));
			startActivity(browserQR);
		}
		else
		{
			Intent reviews = new Intent(ScannerActivity.this, ReviewsActivity.class);
			reviews.putExtra("com.animated.juzzz.zebraviews.BARCODE_TEXT",
					rawResult.getText());
			startActivity(reviews);
			finish();
		}
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			
			case R.id.scanner_settings:
				Intent settings = new Intent(ScannerActivity.this,
					SettingsActivity.class);
				startActivity(settings);
			}
		return super.onOptionsItemSelected(item);
	}
}