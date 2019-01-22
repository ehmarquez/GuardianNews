package com.example.alvin.guardiannews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class StoryAdapter extends ArrayAdapter<Story> {


    /**
     *  Constructs a new {@link StoryAdapter}
     * @param context of the app
     * @param stories list of stories to display for the adapter
     */
    public StoryAdapter(Context context, List<Story> stories) {
        super(context, 0, stories);
    }

    /**
     *
     * @param position in the list of stories
     * @param convertView list item view to be used for display
     * @param parent
     * @return a list item view to display a story based on the position in stories
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (convertView)
        // Else, inflate a new list item layout
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.story_list_item, parent, false);
        }

        // Find current story at given position of stories
        Story currentStory = getItem(position);

        // Find view for section name and set appropriate text
        TextView sectionNameView = listItemView.findViewById(R.id.section_name);
        sectionNameView.setText(currentStory.getSectionName());

        // Find view for web title and set appropriate text
        TextView webTitleView = listItemView.findViewById(R.id.web_title);
        webTitleView.setText(currentStory.getWebTitle());

         // Find view for section name and set appropriate text
        TextView webPubDateView = listItemView.findViewById(R.id.published_date);
        String publishedDate = formatDate(currentStory.getWebPubDate());
        webPubDateView.setText(publishedDate);

        return listItemView;

    }

    // Methods for formatting time display
    private String formatDate(String dateString) {
        // This format will be used to parse the string object such as (ie 2019-01-22T04:12:06Z)
        // and create a date object
        SimpleDateFormat inDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

        // This format will be used to convert the date object into an easy
        // to read date format (ie Jan 22, 2019)
        SimpleDateFormat outDateFormat = new SimpleDateFormat("LLL dd, yyyy");

        // If parsing fails, just display the original date received from API call
        // Should not stop app from functioning or cause a crash, so fail silently
        try {
            Date date = inDateFormat.parse(dateString);
            return outDateFormat.format(date);
        }
        catch(ParseException e) {
            Log.w(StoryAdapter.class.getName() + ": formatDate",
                    "WARN: Parsing date failed! Displaying unformatted date instead.");
            return dateString;
        }
    }
}
