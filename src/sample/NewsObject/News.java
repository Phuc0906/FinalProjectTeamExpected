/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2021B
  Assessment: Final Project
  Created  date: 17/7/2021
  Author: Nguyen Hung Anh s3877798
    Hoang Phuc s3879362
    Le Tan Phong s3877819
    Thai Thuan s3877024
  Last modified date: 11/9/2021
  Acknowledgement:
*/
package sample.NewsObject;

public class News {
    private final String newsURL;
    private final String title;
    private final String description;
    private final String imageURL;
    private String newsOutlet;
    private String newsTime;
    private String newsTimeDuration;

    public News(String newsURL, String title, String description, String imageURL, String newsTime, String newsTimeDuration) {
        this.newsURL = newsURL;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.newsTime = newsTime;
        this.newsTimeDuration = newsTimeDuration;
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

    public String getNewsTime() {
        return this.newsTime;
    }

    public String getNewsTimeDuration() {
        return this.newsTimeDuration;
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
