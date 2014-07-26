package zebradev.zebraviews.android;

import java.util.TreeMap;

import zebradev.zebraviews.common.Requests;
import zebradev.zebraviews.processor.Product;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.minlog.Log;

public class RegisterLoginActivity extends Activity {

	private boolean bound;
	private RequestService service;
	
	private EditText username;
	private EditText password;
	
	private ProgressDialog progress;
	
	private static final int CHECK_TIME_LENGTH = 100;
	
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			RegisterLoginActivity.this.bound = true;
			RegisterLoginActivity.this.service =
					((RequestService.RequestBinder) service).getBoundService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			RegisterLoginActivity.this.bound = false;
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		new TemplateEula(this).show();
		
		this.username = (EditText) findViewById(R.id.username);
		this.password = (EditText) findViewById(R.id.password);
		this.progress = new ProgressDialog(this);
		this.progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.progress.setIndeterminate(true);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		Log.setLogger(new ZebraAndroidLogger());
		Intent launchService = new Intent(this, RequestService.class);
		startService(launchService);
		bindService(launchService, mConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public void onStop() {
		super.onStop();
		unbindService(mConnection);
	}
	
	public void register(View v) {
		
		String username = this.username.getText().toString();
		String password = this.password.getText().toString();
		String details = "";
		
		if (!this.service.verifyValidity(username, password, details))
		{
			Toast.makeText(this.getApplicationContext(),
					this.getResources().getString(R.string.incorrect_length),
					Toast.LENGTH_LONG).show();
			return;
		}
		
		RegisterTask register = new RegisterTask(this);
		register.execute(username, password);
	}
	
	private class RegisterTask extends AsyncTask<String, String, Boolean> implements RequestListener {

		private RegisterLoginActivity activity;
		private boolean signupResponseReceived;
		private boolean registered;
		
		public RegisterTask(RegisterLoginActivity registerLoginActivity) {
			this.activity = registerLoginActivity;
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
			this.activity.service.setListener(this);
			this.publishProgress(this.activity.getResources().
					getString(R.string.connecting));
			this.activity.service.connect();
			if (this.activity.service.failedToConnect == true ||
					this.activity.service.connected == false)
			{
				String message = null;
				if (!this.isOnline())
					message = this.activity.getResources().getString(R.string.no_network_access);
				else
					message = this.activity.getResources().getString(R.string.failed_to_connect);
				
				this.publishProgress(new String(), message);
				
				return null;
			}
			this.publishProgress(this.activity.getResources().getString(R.string.registering));
			this.activity.service.signup(params[0], params[1]);
			
			for (int t = 0; t < RequestService.CONNECTION_TIMEOUT / RegisterLoginActivity.CHECK_TIME_LENGTH; t++)
			{
				if (this.signupResponseReceived)
				{
					if (registered)
						return true;
					else
					{
						String message = this.activity.getResources().getString(R.string.incorrect_username_register);
						this.publishProgress(new String(), message);
						return false;
					}
				}
				try {
					Thread.sleep(RegisterLoginActivity.CHECK_TIME_LENGTH);
				} catch (InterruptedException e) {
					Log.error("Interrupted while waiting for signup", e);
					return null;
				}
			}
			
			String message = this.activity.getResources().getString(R.string.failed_to_connect);
			this.publishProgress(new String(), message);
			
			return false;
		}
		
		@Override
		protected void onProgressUpdate(String... strings) {
			if (strings.length == 1)
			{
				this.activity.progress.setMessage(strings[0]);
				this.activity.progress.show();
			}
			else
			{
				String message = strings[1];
				this.activity.progress.dismiss();
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.activity);
				alertDialogBuilder.setMessage(message).setCancelable(true);
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
		}
		
		@Override
		protected void onPostExecute(Boolean boolObj) {
			
			if (boolObj == null)
				return;
			
			if (boolObj == true)
			{
				this.activity.progress.dismiss();
				
				// Open new activity here
			}
			
		}
		
		public boolean isOnline() {
			ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo netInfo = cm.getActiveNetworkInfo();
			if (netInfo != null && netInfo.isConnectedOrConnecting()) {
				return true;
			}
			return false;
		}

		@Override
		public void onConnect(Connection connection) {
			// Already handled
		}

		@Override
		public void onDisconnect(Connection connection) {
			// Already handled?
		}

		@Override
		public void onTreeMapReceived(Connection connection,
				TreeMap<String, Object> response) {
			if (response.get("type").equals(Requests.SIGNUP_RESPONSE.value))
			{
				this.signupResponseReceived = true;
				if (response.get("status").equals(Requests.STATUS_SUCCESS.value))
					this.registered = true;
				else
					this.registered = false;
			}
			else
			{
				// No idea how we would get any other response....
			}
		}

		@Override
		public void onProductReceived(Connection connection, Product product) {
			// We shouldn't be receiving a product object yet
			
		}

		@Override
		public void onOtherReceived(Connection connection, Object object) {
			// No idea what such an object would be in this situation
		}
	}
}
