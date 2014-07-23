package zebradev.zebraviews.android;

import com.esotericsoftware.minlog.Log;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class RegisterLoginActivity extends Activity {

	private boolean bound;
	private RequestService service;
	private ServiceConnection mConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			RegisterLoginActivity.this.bound = true;
			RegisterLoginActivity.this.service =
					((RequestService.RequestBinder) service).getBoundService();
			
			RegisterLoginActivity.this.service.connect();
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
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		Log.setLogger(new ZebraAndroidLogger());
		Intent launchService = new Intent(this, RequestService.class);
		bindService(launchService, mConnection, Context.BIND_AUTO_CREATE);
	}
}
