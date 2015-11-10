package com.nelsoft.droiddit.reddit_display;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nelsoft.droiddit.MainActivity;
import com.nelsoft.droiddit.R;

import java.util.ArrayList;
import java.util.List;

public class RedditRecyclerAdapter extends RecyclerView.Adapter<RedditRecyclerAdapter.ViewHolder> {

    private List<RedditPost> redditPostList = new ArrayList<RedditPost>();

    // Provide a suitable constructor (depends on the kind of dataset)
    public RedditRecyclerAdapter(MainActivity context, List<RedditPost> redditPosts) {
        redditPostList = redditPosts;
    }

    //	public void add(int position, String item) {
    //		redditPostList.add(position, item);
    //		notifyItemInserted(position);
    //	}

    public void remove(String item) {
        int position = redditPostList.indexOf(item);
        redditPostList.remove(position);
        notifyItemRemoved(position);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RedditRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reddit_line_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final RedditPost name = redditPostList.get(position);

        holder.lineLabel.setText(redditPostList.get(position).getName());
        holder.lineDetail.setText("Footer: " + redditPostList.get(position).getTitle());

        holder.lineLabel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clicked on " + name);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return redditPostList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView lineLabel;
        public TextView lineDetail;

        public ViewHolder(View v) {
            super(v);
            lineLabel = (TextView) v.findViewById(R.id.lineLabel);
            lineDetail = (TextView) v.findViewById(R.id.lineDetail);
        }
    }

}