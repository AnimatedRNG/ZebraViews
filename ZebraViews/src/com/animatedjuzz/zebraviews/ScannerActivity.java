package com.animatedjuzz.zebraviews;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

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
    	SearchView search = (SearchView) menu.findItem(R.id.scanner_search).getActionView();
    	//SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    	//search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    	//search.setIconifiedByDefault(false);
    	return super.onCreateOptionsMenu(menu);
    }
}