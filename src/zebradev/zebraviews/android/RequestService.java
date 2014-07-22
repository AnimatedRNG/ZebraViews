package zebradev.zebraviews.android;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import zebradev.zebraviews.fakeclient.ClientManager;
import zebradev.zebraviews.fakeclient.ClientRequestListener;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Binder;
import android.os.IBinder;

import com.esotericsoftware.minlog.Log;

public class RequestService extends Service {
	
	public ConcurrentLinkedQueue<TreeMap<String, Object>> treeMapQueue;
	private static ClientManager clientManager;
	private Thread networkingThread;
	private boolean connected;
	
	public static final String SERVER_CONFIG_FILE = "config/server_config.xml";
	public static final String CLIENT_CONFIG_FILE = "config/client_config.xml";
	
	public RequestService() {
		this.treeMapQueue = new ConcurrentLinkedQueue<TreeMap<String, Object>>();
		this.networkingThread = new Thread();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new RequestBinder();
	}
	
	// Use the binder to get the service after you've bound to an activity
	public class RequestBinder extends Binder {
		RequestService getBoundService() {
			return RequestService.this;
		}
	}

	public void connect() {
		// Connect to server goes here
		
		if (connected == false)
		{
			this.networkingThread = new Thread(new LoginTask(this.getAssets()));
			this.networkingThread.start();
			this.connected = true;
		}
	}
	
	public void disconnect() {
		// Disconnect from server goes here
		this.networkingThread = new Thread(new Runnable() {
			@Override
			public void run() {
				RequestService.clientManager.stop();
			}
		});
		this.networkingThread.start();
		this.connected = false;
	}
	
	// Other methods like login, etc
	
	public boolean isConnected() {
		return this.connected;
	}
	
	public class LoginTask implements Runnable {

		private AssetManager assets;
		
		public LoginTask(AssetManager assets) {
			this.assets = assets;
		}
		
		@Override
		public void run() {
			try {
				// Change listener later
				RequestService.clientManager = new ClientManager(new ClientRequestListener(),
						assets.open(SERVER_CONFIG_FILE),
						assets.open(CLIENT_CONFIG_FILE));
			} catch (Exception e) {
				Log.error("Error initializing client", e);
				return;
			}
		}
		
	}
}
