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
  Acknowledgement: Thanks and give credits to the resources that you used in this file
*/
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

    public void addContent(News news) {
        newsList.add(new News(news.getNewsURL(), news.getTitle(), news.getDescription(), news.getImageURL()));
    }

    public void addContent(News news, String publishedDate, String timeDuration) {
        newsList.add(new News(news.getNewsURL(), news.getTitle(), news.getDescription(), news.getImageURL(), publishedDate, timeDuration));
    }

    public void loadThanhNien(String url) throws  IOException {
        String newsURL;
        String title;
        String description;
        String imageURL;
        String origin = "https://thanhnien.vn/";
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select("div.relative article.story");
        for (Element element : elements) {
            newsURL = element.select("a").attr("href");
            if (!newsURL.contains("https:")) newsURL = origin + newsURL;
            title = element.select("a").attr("title");
            description = element.select("div.summary").text();
            imageURL = element.select("a img").attr("data-src");
            if (imageURL.isEmpty() || title.isEmpty()) {
                continue;
            }
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void loadThanhNienSport(String url) throws  IOException {
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

    public void loadZingNews(String url) throws  IOException {
        String originalURL = "https://zingnews.vn";
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document document = Jsoup.connect(url).get();
        Elements article = document.select("article.article-item");
        String[] articleSscript = article.toString().split("</article>");
        for (String script: articleSscript) {
            Document docScript = Jsoup.parse(script.replaceAll("\n", "") + "</article>");
            newsURL = originalURL + docScript.select("a").attr("href");
            title = docScript.select("header p.article-title").text();
            imageURL = docScript.select("img").attr("data-src");
            description = docScript.select("header p.article-summary").text();
            if (imageURL.isEmpty()) {
                imageURL = docScript.select("img").attr("src");
            }
            if (newsURL.isEmpty() || imageURL.isEmpty() || title.isEmpty()) {
                continue;
            }

            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void loadTuoiTre(String url) throws  IOException {
        String originalURL = "https://tuoitre.vn";
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document doc = Jsoup.connect(url).get();
        Elements articles = doc.select("li.news-item");
        String[] articleString = articles.toString().split("</li>");
        for (String art: articleString) {
            Document docScript = Jsoup.parse(art.replaceAll("\n", "") + "</li>");
            title = docScript.select("a").attr("title");
            newsURL = originalURL + docScript.select("a").attr("href");
            imageURL = docScript.select("a img").attr("src");
            description = docScript.select("div.name-news p.sapo").text();
            if(title.isEmpty() || imageURL.isEmpty() || newsURL.isEmpty() || description.isEmpty()) continue;
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
    }

    public void loadVnExpress(String webURL) throws IOException {
        Document document = Jsoup.connect(webURL).get();
        Elements articles = document.select("article.item-news");
        String newsURL;
        String title;
        String description;
        String imageURL[];
        for (Element art: articles) {
            title = art.select("h3 a").attr("title");
            description = art.select("p a").text();
            imageURL = art.select("div a picture source").attr("srcset").toString().split(" ");
            if (imageURL[0].isEmpty()) {
                imageURL = imageURL = art.select("div a picture source").attr("data-srcset").toString().split(" ");
            }
            newsURL = art.select("h3 a").attr("href");
            if (imageURL[0].isEmpty() || newsURL.isEmpty() || description.isEmpty() || title.isEmpty()) {
                continue;
            }
            this.newsList.add(new News(newsURL, title, description, imageURL[0]));
        }
    }

    public void loadNhanDan(String url) throws  IOException {
        String originalLink = "https://nhandan.vn";
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document document = Jsoup.connect(url).get();
        Elements article = document.select("article");
        for (Element art: article) {
            title = art.select("div.box-img a").attr("title");
            imageURL = art.select("div.box-img a img").attr("data-src");
            newsURL = originalLink + art.select("div.box-img a").attr("href");
            description = art.select("div.box-des p").text();
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

    public void clearList() {
        newsList.clear();
    }

    public int getSize() { return this.newsList.size(); }

    public News getNews(int whichNews) {
        return newsList.get(whichNews);
    }
}
