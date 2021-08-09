package sample.NewsObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class NewsManagement {
    ArrayList<News> newsList = new ArrayList<>();

    public NewsManagement() {
    }

    public void loadThanhNien(String url) throws  IOException {
        String newsURL;
        String title;
        String description;
        String imageURL;
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.select("div.col390");
        for (Element element : el.select("article")) {
            newsURL = element.select("article.story--primary a").attr("href");
            title = element.select("article.story--primary a").attr("title");
            imageURL = element.select("article.story--primary a img").attr("src");
            description = "";
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void loadZingNews(String url) throws  IOException {
        String newsURL;
        String title;
        String description;
        String imageURL;
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.select("div.infinite-load");
        for (Element element : el.select("article")) {
            newsURL = element.select("p.article-thumbnail a").attr("href");
            title = element.select("header p.article-title a").text();
            imageURL = element.select("p.article-thumbnail a img").attr("src");
            description = element.select("header p.article-summary").text();
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void loadTuoiTre(String url) throws  IOException {
        String newsURL;
        String title;
        String description;
        String imageURL;
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.select("ul.list-news-content");
        for (Element element : el.select("li")) {
            newsURL = "https://tuoitre.vn" + element.select("a").attr("href");
            title = element.select("a").attr("title");
            imageURL = element.select("a img").attr("src");
            description = element.select("div.name-news p.sapo").text();
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void loadVnExpress(String webURL) throws IOException {
        String newsURL;
        String title;
        String description;
        String imageURL;

        // add first news
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

        // add second new
//        /html/body/section[6]/div/div[1]
        Elements news1 = webDoc.select("div#automation_5News");
//        System.out.println(news1.select("article").size());
        for (Element singleArticle: news1.select("article")) {
            title = singleArticle.select("h3 a").attr("title");
            newsURL = singleArticle.select("h3 a").attr("href");
            description = singleArticle.select("p a").text();
            imageURL = singleArticle.select("div.thumb-art a picture img").attr("src");
            newsList.add(new News(newsURL, title, description, imageURL));
        }

    }

    public void loadNhanDan(String url) throws  IOException {
        String newsURL;
        String title;
        String description;
        String imageURL;
        Document doc = Jsoup.connect(url).get();
        Elements ele = doc.select("div.box-widget-loaded");
        Elements element = ele.select("article");
        for (Element element1 : element) {
            title = element1.select("div.box-title a").attr("title");
            newsURL = "https://nhandan.vn/" + element1.select("div.box-title a").attr("href");
            imageURL = element1.select("div.box-img a img").attr("data-src");
            description = element1.select("div.box-des").text();
            newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public News searchTitle(String title) {
        for (News news : this.newsList) {
            if (news.getTitle().equals(title)) {
                return news;
            }
        }
        return this.newsList.get(0);
    }

    public News getNews(int whichNews) {
        return newsList.get(whichNews);
    }
}
