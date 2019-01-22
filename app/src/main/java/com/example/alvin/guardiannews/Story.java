package com.example.alvin.guardiannews;

public class Story {

    // Private members to display for user
    private String mSectionName;
    private String mWebUrl;
    private String mWebTitle;
    private String mWebPubDate;
    private String mAuthorName;

    /**
     * Constructs a new {@link Story} object.
     *
     * @param sectionName Section name that the news story belongs in
     * @param webUrl      Link to use for an implicit intent to a web browser
     * @param webTitle    Name of news story on the web
     * @param webPubDate  Combined date and time of publication
     */
    public Story(String sectionName, String webUrl, String webTitle, String webPubDate) {
        mSectionName = sectionName;
        mWebUrl = webUrl;
        mWebTitle = webTitle;
        mWebPubDate = webPubDate;
    }

    /**
     * Constructs a new {@link Story} object.
     *
     * @param sectionName Section name that the news story belongs in
     * @param webUrl      Link to use for an implicit intent to a web browser
     * @param webTitle    Name of news story on the web
     * @param webPubDate  Combined date and time of publication
     * @param authorName  Author(s) name, if available
     */
    public Story(String sectionName, String webUrl, String webTitle, String webPubDate, String authorName) {
        mSectionName = sectionName;
        mWebUrl = webUrl;
        mWebTitle = webTitle;
        mWebPubDate = webPubDate;
        mAuthorName = authorName;
    }

    // Helper method to check if Story was created with an author resource
    public boolean hasAuthor() {
        return mAuthorName != null;
    }

    // Getters
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

    public String getAuthorName() {
        return mAuthorName;
    }
}
