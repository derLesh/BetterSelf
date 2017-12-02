package de.lesh.betterself.util.rss;

import java.util.ArrayList;
import java.util.List;

public class Feed {
	final String title;
    final String link;
    final String description;
    final String language;
    final String copyright;
    final String pubDate;

    final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed(String title, String link, String description, String language,
            String copyright, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle(int i) {
        return title;
    }

    public String getLink(int i) {
        return link;
    }

    public String getDescription(int i) {
        return description;
    }

    public String getLanguage(int i) {
        return language;
    }

    public String getCopyright(int i) {
        return copyright;
    }

    public String getPubDate(int i) {
        return pubDate;
    }

    public String toString(int i) {
        return FeedMessage.getTitle(i);
    }
}
