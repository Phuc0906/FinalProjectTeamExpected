package sample.NewsObject;

public class News {
    private final String newsURL;
    private final String title;
    private final String description;
    private final String imageURL;
    private String newsOutlet;
    private String newsTime;

    public News(String newsURL, String title, String description, String imageURL, String newsTime) {
        this.newsURL = newsURL;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.newsTime = newsTime;
        if (newsURL.contains("https://thanhnien.vn")) this.newsOutlet = "Thanh Nien";
        else if (newsURL.contains("https://vnexpress.net")) this.newsOutlet = "VN Express";
        else if (newsURL.contains("https://zingnews.vn")) this.newsOutlet = "Zing News";
        else if (newsURL.contains("https://nhandan.vn")) this.newsOutlet = "Nhan Dan";
        else if (newsURL.contains("https://tuoitre.vn")) this.newsOutlet = "Tuoi Tre";
    }

    public News(String newsURL, String title, String description, String imageURL) {
        this.newsURL = newsURL;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        if (newsURL.contains("https://thanhnien.vn")) this.newsOutlet = "Thanh Nien";
        else if (newsURL.contains("https://vnexpress.net")) this.newsOutlet = "VN Express";
        else if (newsURL.contains("https://zingnews.vn")) this.newsOutlet = "Zing News";
        else if (newsURL.contains("https://nhandan.vn")) this.newsOutlet = "Nhan Dan";
        else if (newsURL.contains("https://tuoitre.vn")) this.newsOutlet = "Tuoi Tre";
    }

    public void setNewsTime(String time) {
        this.newsTime = time;
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

    public String getNewsOutlet() {
        return newsOutlet;
    }
}
