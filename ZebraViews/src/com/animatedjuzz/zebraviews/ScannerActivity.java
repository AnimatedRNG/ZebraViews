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