package com.animatedjuzz.zebraviews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ReviewsTabListener<T extends SherlockFragment> implements TabListener {

	private Fragment mFragment;
    private SherlockFragmentActivity mActivity;
    private String mTag;
    private Class mClass;
	
	
    public ReviewsTabListener(SherlockFragmentActivity activity, String tag, Class clz) {
    	mActivity = activity;
        mTag = tag;
        mClass = clz;
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
        if (mFragment == null) {
            mFragment =
            		SherlockFragment.instantiate
            		(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            ft.attach(mFragment);
        }
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (mFragment != null) {
            ft.detach(mFragment);
        }
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

}