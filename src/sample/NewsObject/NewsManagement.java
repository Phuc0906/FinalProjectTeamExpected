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
        if (el.isEmpty()) el = doc.select("div.feature");
        for (Element element : el.select("article")) {
            newsURL = element.select("article.story--primary a").attr("href");
            title = element.select("article.story--primary a").attr("title");
            imageURL = element.select("article.story--primary a img").attr("src");
            description = "";
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void covidThanhNien(String url) throws  IOException{
        String newsURL;
        String title;
        String description;
        String imageURL;
        Document doc = Jsoup.connect(url).get();
        Elements el = doc.select("div.relative");
        for (Element element : el.select("article")) {
            newsURL = element.select("a").attr("href");
            ///in special cases
            if (!newsURL.contains("https")) newsURL = "https://thanhnien.vn" + element.select("a").attr("href");
            title = element.select("a").attr("title");
            imageURL = element.select("a img").attr("data-src");
            description = element.select("div.summary").text();
            //in special cases
            if (description==null) description = element.select("div.summary div").text();
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
        Document doc = Jsoup.connect(webURL).get();
        extractingArticleForVnExpress(doc, "div.list-news-subfolder", this.newsList );
    }

    private void extractingArticleForVnExpress(Document doc, String path, ArrayList<News> newsList) {
        String newsURL;
        String title;
        String description;
        String imageURLScraping;
        String imageURL[];

        Elements articleList = doc.select(path);
        for (Element article: articleList.select("article")) {
            title = article.select("h3.title-news a").attr("title");
            newsURL = article.select("h3 a").attr("href");
            imageURLScraping = article.select("div.thumb-art a picture source").attr("data-srcset");
            imageURL = imageURLScraping.split("\\s");
            description = article.select("p.description a").text();
            if (imageURL[0].contains("http")) {
                newsList.add(new News(newsURL, title, description, imageURL[0]));
            }

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
        return null;
    }

    public int getSize() { return this.newsList.size(); }
    public void printOut() {
        for (News news: newsList) {
            System.out.println("---------------------");
            System.out.println("article: " + news.getNewsURL());
            System.out.println("article: " + news.getTitle());
            System.out.println("article: " + news.getDescription());
            System.out.println("article: " + news.getImageURL());
        }
    }

    public News getNews(int whichNews) {
        return newsList.get(whichNews);
    }
}
