package com.example.alvin.guardiannews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class StoryLoader extends AsyncTaskLoader<List<Story>> {

    // Url leading to the API call
    private String mUrl;

    /**
     *  Contructs a new {@link StoryLoader}
     * @param context of the activity (usually one loader per activity)
     * @param url to load data from Guardian API
     */
    public StoryLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    // Forces the Loader to start on new activity
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Story> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform network request, parse JSON response, and extract stories.
        List<Story> stories = QueryUtils.fetchStoryData(mUrl);
        return stories;
    }
}
