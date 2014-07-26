package zebradev.zebraviews.android;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import zebradev.zebraviews.fakeclient.ClientManager;
import zebradev.zebraviews.processor.Product;
import zebradev.zebraviews.server.DatabaseManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class RequestService extends Service {
	
	public ConcurrentLinkedQueue<TreeMap<String, Object>> treeMapQueue;
	private ClientManager clientManager;
	private RequestListener listener;
	public boolean connected;
	public boolean failedToConnect;
	
	public boolean loggedIn;
	
	public static final String SERVER_CONFIG_FILE = "config/server_config.xml";
	public static final String CLIENT_CONFIG_FILE = "config/client_config.xml";
	public static final int CONNECTION_TIMEOUT = 8500;
	
	public RequestService() {
		this.treeMapQueue = new ConcurrentLinkedQueue<TreeMap<String, Object>>();
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

	public synchronized void connect() {
		// Connect to server goes here
		
		if (connected == false)
		{
			try {
				this.clientManager = new ClientManager(new ResponseHandler(this.listener),
						this.getAssets().open(SERVER_CONFIG_FILE),
						this.getAssets().open(CLIENT_CONFIG_FILE));
			} catch (Exception e) {
				Log.error("Error initializing client", e);
				this.failedToConnect = true;
				return;
			}
			
			this.connected = true;
		}
	}
	
	public synchronized void disconnect() {
		if (this.connected == true)
		{
			this.clientManager.stop();
			this.connected = false;
		}
	}
	
	public synchronized void login(String username, String password) {
		this.clientManager.login(username, password);
	}
	
	public synchronized void signup(String username, String password) {
		this.clientManager.signup(username, password);
	}
	
	// Other methods
	
	public boolean verifyValidity(String username, String password, String details) {
		if (username.length() <= DatabaseManager.USERNAME_MAX_LENGTH && username.length() >= DatabaseManager.USERNAME_MIN_LENGTH)
		{
			if (password.length() <= DatabaseManager.PASSWORD_MAX_LENGTH && password.length() >= DatabaseManager.PASSWORD_MIN_LENGTH)
			{
				if (details.length() <= DatabaseManager.DETAILS_MAX_LENGTH)
					return true;
			}
		}
		return false;
	}
	
	public RequestListener getListener() {
		return this.listener;
	}
	
	public void setListener(RequestListener listener) {
		this.listener = listener;
	}
	
	public class ResponseHandler extends Listener {
		
		private RequestListener requestListener;
		
		public ResponseHandler(RequestListener listener) {
			this.setRequestListener(listener);
		}

		public RequestListener getRequestListener() {
			return requestListener;
		}

		public void setRequestListener(RequestListener requestListener) {
			this.requestListener = requestListener;
		}
		
		@Override
		public void connected(Connection connection) {
			this.requestListener.onConnect(connection);
		}
		
		@Override
		public void disconnected(Connection connection) {
			this.requestListener.onDisconnect(connection);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void received(Connection connection, Object object) {
			if (object instanceof TreeMap)
				this.requestListener.onTreeMapReceived(connection, (TreeMap<String, Object>) object);
			else if (object instanceof Product)
				this.requestListener.onProductReceived(connection, (Product) object);
			else
				this.requestListener.onOtherReceived(connection, object);
		}
	}
}
