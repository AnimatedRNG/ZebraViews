package com.animatedjuzz.zebraviews;

import android.os.Bundle;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.widget.SearchView;

public class ScannerActivity extends SherlockActivity {
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
}