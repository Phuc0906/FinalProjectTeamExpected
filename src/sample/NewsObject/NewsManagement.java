package sample.NewsObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class NewsManagement {
    ArrayList<News> newsList = new ArrayList<>();

    public NewsManagement() {

    }

    public void loadVnExpress(String webURL) {
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document webDoc = Jsoup.connect(webURL).get();
        Elements webBody = webDoc.select("body" +
                " section.section_topstory" +
                " div.flexbox" +
                " div.col-left-top" +
                " div.wrapper-topstory-folder");

        // get the important news
        Elements importantNews = webBody.select("article.item-news" +
                " div.thumb-art" +
                " a");

        newsURL = importantNews.attr("href");
        title = importantNews.attr("title");
        description = webBody.select("article.item-news" +
                " p.description " +
                " a").text();

        // get image url
        imageURL = importantNews.select("picture" +
                " img").attr("src");

        this.newsList.add(new News(newsURL, title, description, imageURL));
    }

    public int searchTitle(String title) {
        for (int i = 0; i < this.newsList.size(); i++) {
            if (this.newsList.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }
}
