package sample.NewsObject;

import java.util.ArrayList;

public class Article {
    private String title;
    private String description;
    private String imageUrl;
    private String time;
    private ArrayList<String> paragraphs;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getParagraphs() {
        return paragraphs;
    }

    public Article(String title, String description, String imageUrl, ArrayList<String> paragraphs, String time) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.paragraphs = paragraphs;
        this.time = time;
    }
}
