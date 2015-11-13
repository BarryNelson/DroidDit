package com.nelsoft.droiddit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.nelsoft.droiddit.reddit.RedditService;
import com.nelsoft.droiddit.reddit.model.RedditLink;
import com.nelsoft.droiddit.reddit.model.RedditListing;
import com.nelsoft.droiddit.reddit.model.RedditObject;
import com.nelsoft.droiddit.reddit.model.RedditResponse;
import com.nelsoft.droiddit.reddit_display.RedditRecyclerAdapter;

import java.util.ArrayList;
import java.util.ListIterator;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements RedditRecyclerAdapter.Callback {

    private String TAG = "MainActivity";

    ArrayList<RedditLink> redditPostingList = new ArrayList<>();
    private EditText searchValue;
    private String subreddit;
    private RecyclerView redditRecyclerView;
    private RedditRecyclerAdapter redditRecyclerAdapter;
    private LinearLayoutManager layoutManager;
    private RedditResponse<RedditListing> listing;
    private String after = null;
    private String lastSearch;
    private String extra;
    private Callback<RedditResponse<RedditListing>> redditCallback;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        initToolAction();
        initControls();
        doSearch(searchValue.getText().toString(),null);
    }
    
    private void initControls() {

        searchValue = (EditText) findViewById(R.id.text_search);

        ((ImageView) findViewById(R.id.btn_Search)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                doSearch(searchValue.getText().toString(), null);
            }
        });

        ((ImageView) findViewById(R.id.btn_clear_text_search)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchValue.setText("");
            }
        });

        redditRecyclerView = (RecyclerView) findViewById(R.id.redit_recycler_view);
        redditCallback = redCallback();
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        redditRecyclerView.setLayoutManager(layoutManager);
        displayPostsInRecyclerView(redditPostingList);
    }

    @Override
    public void getNext() {
        after = "after="+listing.getData().getAfter();
        doSearch(searchValue.getText().toString(), after);
    }

    private void doSearch(String subreddit, String extra) {

        this.lastSearch = subreddit;
        this.extra = extra;

        Log.d(TAG, "search param{" + subreddit.toString() + "}");

        if (extra ==null) {
            redditPostingList.clear();
            RedditService.Implementation.get().getSubreddit(subreddit, redditCallback);
        }else{
            RedditService.Implementation.get().getSubreddit(subreddit, extra, redditCallback);
        }

    }

    @NonNull
    private Callback<RedditResponse<RedditListing>> redCallback() {
        return new Callback<RedditResponse<RedditListing>>() {
            @Override
            public void success(RedditResponse<RedditListing> listing, Response response) {
                if (isDestroyed()) {
                    return;
                }
                //                        mProgressDialog.dismiss();
                onListingReceived(listing);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "RetrofitError URL :" + error.getUrl());
                Log.e(TAG, "RetrofitError detailMessage :"+error.getMessage());
                if (isDestroyed()) {
                    return;
                }
                //                        mProgressDialog.dismiss();
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Loading failed :(")
                        .setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                after = null;
                            }
                        })
                        .show();
            }
        };
    }

    private void onListingReceived(RedditResponse<RedditListing> listing) {
        this.listing = listing;
        ListIterator<RedditObject> itr = listing.getData().getChildren().listIterator();
        while (itr.hasNext()) {
            RedditObject rObject = itr.next();
            RedditLink rLink = (RedditLink) rObject;
            redditPostingList.add(rLink);
        }
        redditRecyclerAdapter.notifyDataSetChanged();
    }


    /**
     * Display RedditModel postings in RecyclerView
     *
     * @param redditLinkList
     */

    private void displayPostsInRecyclerView(ArrayList<RedditLink> redditLinkList) {

        redditRecyclerView = (RecyclerView) findViewById(R.id.redit_recycler_view);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        redditRecyclerView.setLayoutManager(layoutManager);

        redditRecyclerAdapter = new RedditRecyclerAdapter(this, redditLinkList);
        redditRecyclerAdapter.setCallback(this);

        redditRecyclerView.setAdapter(redditRecyclerAdapter);

    }

    
    private void initToolAction() {
        //        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //        setSupportActionBar(toolbar);
        
        //        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //        fab.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                        .setAction("Action", null).show();
        //            }
        //        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }


}
