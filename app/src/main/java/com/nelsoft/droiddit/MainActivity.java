package com.nelsoft.droiddit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.nelsoft.droiddit.reddit_display.RedditDisplayAdapter;
import com.nelsoft.droiddit.reddit_display.RedditRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private EditText searchValue;
    private String searchText;
    private String serverAddress;

    private ListView redditListView;
    private RedditDisplayAdapter adapter;

    private RecyclerView redditRecyclerView;
    private RedditRecyclerAdapter redditRecyclerAdapter;

    private LinearLayoutManager layoutManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initToolAction();
    
    }
    
    private void initControls() {
        
        searchValue = (EditText)findViewById(R.id.searchText);
        
        ((ImageView) findViewById(R.id.imageSearch)).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });
        
        ((ImageView) findViewById(R.id.clearSearchText)).setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                searchValue.setText("");
            }
        });
        
        redditRecyclerView = (RecyclerView) findViewById(R.id.redit_recycler_view);
        
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        redditRecyclerView.setLayoutManager(layoutManager);
        
    }
    
    private void doSearch() {
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
