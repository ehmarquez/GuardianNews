package com.example.alvin.guardiannews;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Story>> {


    // Private variables
    private static final int STORY_LOADER_ID = 1;       // Important with multiple activities/loaders
    private StoryAdapter mAdapter;                      // adapter for list of stories
    private TextView mEmptyStateView;                   // TextView to display for empty story list
    private static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Find {@link ListView} in the activity main layout */
        ListView storyListView = findViewById(R.id.list);

        // Set empty text view to show by default first
        mEmptyStateView = findViewById(R.id.empty_view);
        storyListView.setEmptyView(mEmptyStateView);

        // Create adapter that takes list of stories as input
        mAdapter = new StoryAdapter(this, new ArrayList<Story>());

        // Set adapter on ListView to be populated
        storyListView.setAdapter(mAdapter);

        // Set item click listener on the list view to redirect user to
        // a web browser using an implicit intent
        storyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find story that was clicked
                Story currentStory = mAdapter.getItem(position);

                // Convert the string URL into a URI for the intent constructor
                Uri storyUri = Uri.parse(currentStory.getWebUrl());

                // Create intent to redirect to browser
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, storyUri);
                startActivity(browserIntent);

            }
        });

        // Use connectivity manager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // Use NetworkInfo to get details of current active network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get reference to LoaderManager
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader and pass in the ID constant above
            loaderManager.initLoader(STORY_LOADER_ID, null ,this);
        } else {
            // Catch error for no internet connectivity and hide loading circle
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Show no internet message
            mEmptyStateView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<Story>> onCreateLoader(int i, Bundle bundle) {

        // Use a URI builder to make the API query more flexible
        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendPath("search");
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("section", "games");
        uriBuilder.appendQueryParameter("order-by", "newest");
        uriBuilder.appendQueryParameter("page-size", "9");
        uriBuilder.appendQueryParameter("show-tags", "contributor");
        uriBuilder.appendQueryParameter("api-key", "test");

        Log.i("MainActivity", "TEST Current Uri:" + uriBuilder);

        return new StoryLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Story>> loader, List<Story> stories) {
        // Hide loading circle after loading
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Handle empty state text when no stories are found
        mEmptyStateView.setText(R.string.no_stories);

        // Clear adapter of previous data
        mAdapter.clear();

        // If there is a valid list of stories, then add to adapter's data set
        if (stories != null && !stories.isEmpty()) {
            mAdapter.addAll(stories);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Story>> loader) {
        mAdapter.clear();
    }
}
