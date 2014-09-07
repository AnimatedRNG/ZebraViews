package zebradev.zebraviews.android;

import java.util.TreeMap;

import zebradev.zebraviews.processor.Product;

import com.esotericsoftware.kryonet.Connection;

import android.support.v4.app.Fragment;

public class ReviewsFragment extends Fragment implements RequestListener {

	@Override
	public void onConnect(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnect(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTreeMapReceived(Connection connection,
			TreeMap<String, Object> response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProductReceived(Connection connection, Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onOtherReceived(Connection connection, Object object) {
		// TODO Auto-generated method stub
		
	}

}
