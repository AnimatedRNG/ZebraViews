package zebradev.zebraviews.android;

import java.util.TreeMap;

import zebradev.zebraviews.processor.Product;

import com.esotericsoftware.kryonet.Connection;

public interface RequestListener {

	public void onConnect(Connection connection);
	
	public void onDisconnect(Connection connection);
	
	public void onTreeMapReceived(Connection connection, TreeMap<String, Object> response);
	
	public void onProductReceived(Connection connection, Product product);
	
	public void onOtherReceived(Connection connection, Object object);
}
