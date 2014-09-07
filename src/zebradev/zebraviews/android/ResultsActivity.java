package zebradev.zebraviews.android;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import zebradev.zebraviews.common.Requests;
import zebradev.zebraviews.processor.Product;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

public class ResultsActivity extends FragmentActivity implements TabListener {
	
	private ViewPager mViewPager;
	protected TabsPagerAdapter mAdapter;
	private ProgressDialog progress;
	
	protected BasicInfoTask infoTask;
	
	protected RequestService service;
	protected boolean bound;
	
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			ResultsActivity.this.bound = true;
			ResultsActivity.this.service =
					((RequestService.RequestBinder) service).getBoundService();
			
			String productCode = ResultsActivity.this.getIntent().getExtras().getString("product_code");
			String productType = ResultsActivity.this.getIntent().getExtras().getString("product_type");
			
			ResultsActivity.this.infoTask = new ResultsActivity.BasicInfoTask();
			ResultsActivity.this.infoTask.execute(productType, productCode);
			
			ResultsActivity.this.mAdapter = new TabsPagerAdapter(getSupportFragmentManager(),
					new InfoFragment(), new ReviewsFragment(), new CategoryFragment());
			ResultsActivity.this.mViewPager.setAdapter(ResultsActivity.this.mAdapter);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			ResultsActivity.this.bound = false;
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.results_activity);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		this.mViewPager = (ViewPager) findViewById(R.id.pager);
		this.mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int pos) {
				getActionBar().setSelectedNavigationItem(pos);
			}
		});
		
		final Resources resources = this.getResources();
		
		actionBar.addTab(actionBar.newTab().setText(resources.getString(R.string.info))
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(resources.getString(R.string.category))
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(resources.getString(R.string.reviews))
				.setTabListener(this));
		this.progress = new ProgressDialog(this);
		this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.progress.setIndeterminate(true);
		this.progress.setCancelable(false);
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		this.mViewPager.setCurrentItem(tab.getPosition(), true);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onStart() {
		super.onStart();
		Intent launchService = new Intent(this, RequestService.class);
		bindService(launchService, mConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		if (this.bound == true)
		{
			unbindService(mConnection);
			this.infoTask.cancel(true);
			this.bound = false;
			//this.stopService(new Intent(this, RequestService.class));
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		unbindService(mConnection);
		this.bound = false;		//stopService(new Intent(this, RequestService.class));
		this.finish();
	}
	
	private class TabsPagerAdapter extends FragmentPagerAdapter {
		
		private List<Fragment> fragments;
		
		public TabsPagerAdapter(FragmentManager fr, Fragment... fragments) {
			super(fr);
			this.fragments = new ArrayList<Fragment>();
			this.addFragments(fragments);
		}
		
		public void addFragments(Fragment... fragments) {
			RequestService requestService = (RequestService) ResultsActivity.this.service;
			for (Fragment f : fragments)
			{
				requestService.addListener((RequestListener) f);
				this.fragments.add(f);
			}
		}
		
		@Override 
		public Fragment getItem(int index) {
			return fragments.get(index);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
	}
	
	private class BasicInfoTask extends AsyncTask<String, String, Boolean> implements RequestListener {

		private boolean immediateResponseReceived;
		private boolean basicInfoReceived;
		
		@Override
		public void onConnect(Connection connection) {
			// Hopefully this isn't called... that would make no sense
		}

		@Override
		public void onDisconnect(Connection connection) {
			String message = ResultsActivity.this.getResources().getString(R.string.failed_to_connect);
			
			// This might work?
			publishProgress("", message);
		}

		@Override
		public void onTreeMapReceived(Connection connection,
				TreeMap<String, Object> response) {
			if (response.containsKey("type") && 
					response.get("type").equals(Requests.SEARCH_RESPONSE_IMMEDIATE.value))
			{
				this.immediateResponseReceived = true;
			}
		}

		@Override
		public void onProductReceived(Connection connection, Product product) {
			this.basicInfoReceived = true;
		}

		@Override
		public void onOtherReceived(Connection connection, Object object) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected Boolean doInBackground(String... params) {
			this.publishProgress("", "");
			ResultsActivity.this.service.addListener(this);
			ResultsActivity.this.service.productSearch(params[0], params[1]);
			String message = ResultsActivity.this.getResources().getString(R.string.checking_server_connection);
			this.publishProgress(message);
			
			for (int t = 0; t < RequestService.CONNECTION_TIMEOUT / RegisterLoginActivity.CHECK_TIME_LENGTH; t++)
			{
				if (this.immediateResponseReceived)
					{
						message = ResultsActivity.this.getResources().getString(R.string.getting_basic_info);
						this.publishProgress(message);
						break;
					}
				
				try {
					Thread.sleep(RegisterLoginActivity.CHECK_TIME_LENGTH);
				} catch (InterruptedException e) {
					Log.error("Interrupted while waiting for immediate response", e);
					return false;
				}
			}
			
			if (!this.immediateResponseReceived)
				return false;
			
			for (int t = 0; t < RequestService.CONNECTION_TIMEOUT / RegisterLoginActivity.CHECK_TIME_LENGTH; t++)
			{
				if (this.basicInfoReceived)
						return true;
				
				try {
					Thread.sleep(RegisterLoginActivity.CHECK_TIME_LENGTH);
				} catch (InterruptedException e) {
					Log.error("Interrupted while waiting for basic info response", e);
					return false;
				}
			}
			
			return false;
		}
		
		@Override
		protected void onProgressUpdate(String... strings) {
			if (strings.length == 1)
				ResultsActivity.this.progress.setMessage(strings[0]);
			else if (strings.length == 2)
				ResultsActivity.this.progress.show();
		}
		
		private void fail(String message) {
			String ok = ResultsActivity.this.getResources().getString(R.string.button_ok);
			
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ResultsActivity.this);
			alertDialogBuilder.setMessage(message).setCancelable(false);
			alertDialogBuilder.setPositiveButton(ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					if (bound)
					{
						unbindService(mConnection);
						ResultsActivity.this.bound = false;
						stopService(new Intent(ResultsActivity.this, RequestService.class));
					}
					ResultsActivity.this.finish();
					
					Intent intent = new Intent(getApplicationContext(), RegisterLoginActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
				}
			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
		
		@Override
		protected void onPostExecute(Boolean boolObj) {
			ResultsActivity.this.progress.dismiss();
			
			if (boolObj == false)
			{
				String message = ResultsActivity.this.getResources().getString(R.string.failed_to_connect);
				this.fail(message);
			}
		}
		
		@Override
		protected void onCancelled() {
			super.onCancelled();
			ResultsActivity.this.service.removeListener(this);
			
			for (Fragment frag : ResultsActivity.this.mAdapter.fragments)
				ResultsActivity.this.service.removeListener((RequestListener) frag);
		}
	}
}
