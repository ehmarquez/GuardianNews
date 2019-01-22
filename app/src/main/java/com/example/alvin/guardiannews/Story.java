package com.example.alvin.guardiannews;

public class Story {

    // Private members to display for user
    private String mSectionName;
    private String mWebUrl;
    private String mWebTitle;
    private String mWebPubDate;

    /**
     * Constructs a new {@link Story} object.
     * @param sectionName Section name that the news story belongs in
     * @param webUrl Link to use for an implicit intent to a web browser
     * @param webTitle Name of news story on the web
     * @param webPubDate Combined date and time of publication
     */
    public Story(String sectionName, String webUrl, String webTitle, String webPubDate) {
        mSectionName = sectionName;
        mWebUrl = webUrl;
        mWebTitle = webTitle;
        mWebPubDate = webPubDate;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getWebTitle() {
        return mWebTitle;
    }

    public String getWebPubDate() {
        return mWebPubDate;
    }
}
