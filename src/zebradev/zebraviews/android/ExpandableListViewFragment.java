package zebradev.zebraviews.android;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ExpandableListViewFragment extends Fragment {

	protected ExpandableListAdapter listAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.expandable_list_fragment, container, false);
		ExpandableListView e = (ExpandableListView) v.findViewById(R.id.expandable_list);
		this.listAdapter = new ExpandableListAdapter();
		e.setAdapter(this.listAdapter);
		return v;
	}
	
	protected class ExpandableListAdapter extends BaseExpandableListAdapter {
		
		private List<Group> listData;
		
		public ExpandableListAdapter() {
			this.listData = new ArrayList<Group>();
		}
		
		public void addGroup(Group group) {
			this.listData.add(group);
		}
		
		@Override
		public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
			TextView textView = new TextView(ExpandableListViewFragment.this.getActivity());
			textView.setText(getGroup(i).toString());
			return textView;
		}
 
		@Override
		public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
			TextView textView = new TextView(ExpandableListViewFragment.this.getActivity());
			textView.setText(getChild(i, i1).toString());
			return textView;
		}
 
		@Override
		public boolean isChildSelectable(int i, int i1) {
			return true;
		}

		@Override
		public int getGroupCount() {
			return this.listData.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			int children = 0;
			
			for (Group group : listData)
				children += group.getChildren().size();
			return children;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return this.listData.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return this.listData.get(groupPosition).getChildren().get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return groupPosition * Group.MAX_SIZE + childPosition;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	}
	
	public class Group {
		public static final int MAX_SIZE = 100;
		private List<String> children;
		private String name;
		
		public Group(String name) {
			this.setName(name);
			this.children = new ArrayList<String>();
		}
		
		public String getName() {
			return this.name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public List<String> getChildren() {
			return this.children;
		}
		
		public void addChild(String child) {
			this.children.add(child);
		}
		
		@Override
		public String toString() {
			return this.getName();
		}
	}
}
