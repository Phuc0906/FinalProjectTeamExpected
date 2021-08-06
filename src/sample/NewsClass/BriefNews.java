package sample.NewsClass;

public class BriefNews {
    // brief news component
    String title;
    String description;
    String imageURL;
    String detailPageURL;

    public BriefNews() {

    }

    public BriefNews(String title, String description, String detailPageURL) {
        this.title = title;
        this.description = description;
        this.imageURL = " ";
        this.detailPageURL = detailPageURL;
    }
}
