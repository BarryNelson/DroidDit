package com.nelsoft.droiddit.reddit_display;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nelsoft.droiddit.R;

import java.util.ArrayList;

public class ListViewCustomAdapter extends BaseAdapter {

	public ArrayList<String> album_names;

	public ArrayList<String> photos;

	public Activity context;

	public LayoutInflater inflater;

	public ListViewCustomAdapter(Activity context, ArrayList<String> album_names, ArrayList<String> photos) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
		this.album_names = album_names;
		this.photos = photos;

		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return album_names.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public class ViewHolder {
		ImageView thumbnail;
		TextView title;
		TextView detail;

	}

	public View getView(int position, View convertview, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder;
		if (convertview == null) {
			holder = new ViewHolder();
			convertview = inflater.inflate(R.layout.reddit_line_item, null);

			holder.thumbnail = (ImageView) convertview.findViewById(R.id.lineThumbnail);
			holder.title = (TextView) convertview.findViewById(R.id.lineLabel);
			holder.detail = (TextView) convertview.findViewById(R.id.lineDetail);

			convertview.setTag(holder);
		} else {
			holder = (ViewHolder) convertview.getTag();
		}

		holder.thumbnail.setImageResource(R.mipmap.ic_launcher);
		holder.title.setText(album_names.get(position));
		holder.detail.setText(photos.get(position));

		return convertview;
	}

}
