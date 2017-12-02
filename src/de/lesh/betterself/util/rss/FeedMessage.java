package de.lesh.betterself.util.rss;

public class FeedMessage {
	static String title;
	static String description;
	static String link;
	static String author;
	static String guid;

    public static String getTitle(int i) {
        return title;
    }

    public void setTitle(String title) {
        FeedMessage.title = title;
    }

    public static String getDescription(int i) {
        return description;
    }

    public void setDescription(String description) {
        FeedMessage.description = description;
    }

    public static String getLink(int i) {
        return link;
    }

    public void setLink(String link) {
        FeedMessage.link = link;
    }

    public static String getAuthor(int i) {
        return author;
    }

    public void setAuthor(String author) {
        FeedMessage.author = author;
    }

    public static String getGuid(int i) {
        return guid;
    }

    public void setGuid(String guid) {
        FeedMessage.guid = guid;
    }

    public String toString(int i) {
        return title;
    }
}
