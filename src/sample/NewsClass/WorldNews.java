package sample.NewsClass;

import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WorldNews {
    // declare a list of brief news for the category page
    BriefNews briefNews1 = new BriefNews();
    LinkedList<BriefNews> singleNews = new LinkedList<>();

    public void extractNewsFromWeb(String webURL) throws IOException {
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

        briefNews1.detailPageURL = importantNews.attr("href");
        briefNews1.title = importantNews.attr("title");
        briefNews1.description = webBody.select("article.item-news" +
                " p.description " +
                " a").text();

        // get image url
        briefNews1.imageURL = importantNews.select("picture" +
                " img").attr("src");
        System.out.println(briefNews1.detailPageURL);
    }

    public void extractSingleNews(String webURL) throws IOException {
        Document webDoc = Jsoup.connect(webURL).get();
        Elements webBody = webDoc.select("body" +
                " section.section_topstory" +
                " div.flexbox" +
                " div.col-left-top" +
                " div.wrapper-topstory-folder" +
                " div.sub-news-top" +
                " div.inner-sub-news-top ul");
        System.out.println(webBody.select("li h3 a").attr("title"));

    }

    public String getImageURL() { return briefNews1.imageURL; }
    public String getTitle() { return briefNews1.title; }
    public String getDescription() { return briefNews1.description; }
}
