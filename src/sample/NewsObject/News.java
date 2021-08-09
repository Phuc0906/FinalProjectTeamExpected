package sample.NewsObject;

public class News {
    private String newsURL;
    private String title;
    private String description;
    private String imageURL;

    public News(String newsURL, String title, String description, String imageURL) {
        this.newsURL = newsURL;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }
}
