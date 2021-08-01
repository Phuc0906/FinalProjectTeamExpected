package sample.NewsClass;

import java.io.IOException;
import java.util.LinkedList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WorldNews {
    // declare a list of brief news for the category page
    LinkedList<BriefNews> briefNews = new LinkedList<>();

    public void extractNewsFromWeb(String webURL) throws IOException {
        Document webDoc = Jsoup.connect(webURL).get();
        Elements webBody = webDoc.select("body");
        // move to the newest content of section
        Elements newestContent = webBody.select("section.section_topstory" +
                " div.flexbox" +
                " div.col-left-top" +
                " div.wrapper-topstory-folder");

        // get the important news
        Elements importantNews = newestContent.select("article.item-news" +
                " div.thumb-art" +
                " a");

        // create a brief news
        BriefNews briefNews1 = new BriefNews();
        briefNews1.detailPageURL = importantNews.attr("href");
        briefNews1.title = importantNews.attr("title");

        // get image url
        briefNews1.imageURL = importantNews.select("picture" +
                " img").attr("src");
        System.out.println(briefNews1.detailPageURL);
        System.out.println(briefNews1.title);
        System.out.println(briefNews1.imageURL);
    }
}
