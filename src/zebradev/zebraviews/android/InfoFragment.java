package zebradev.zebraviews.android;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

import zebradev.zebraviews.processor.Product;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esotericsoftware.kryonet.Connection;

public class InfoFragment extends ExpandableListViewFragment implements RequestListener {

	private Product product;
	private Handler updateHandler;
	private Runnable updateUI;
	private Queue<Group> queue;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.queue = new LinkedList<Group>();
		updateHandler = new Handler(Looper.getMainLooper());
		this.updateUI = new Runnable() {
			@Override
	        public void run() {
				while (!queue.isEmpty()) 
				{
					InfoFragment.this.listAdapter.addGroup(queue.poll());
					InfoFragment.this.listAdapter.notifyDataSetChanged();
				}
			}
		};
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
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
		if (this.product == null)
		{
			this.product = product;
			
			Group nameGroup = new Group("name"); product.poll();
			ArrayList<Object> names = (ArrayList<Object>) product.getTop("product_name");
			String name = (String) names.get(0);
			nameGroup.addChild(name);
			this.queue.add(nameGroup);
			this.updateHandler.post(this.updateUI);
		}
	}

	@Override
	public void onOtherReceived(Connection connection, Object object) {
		// TODO Auto-generated method stub
		
	}

}
