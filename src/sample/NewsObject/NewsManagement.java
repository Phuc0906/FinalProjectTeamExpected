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
        String originalLink = "https://zingnews.vn/";
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document doc = Jsoup.connect(url).get();
        Elements articles = doc.select("p.article-thumbnail");
        String[] script = articles.select("img").toString().split("\\n");
        Elements descriptions = doc.select("p.article-summary");
        Document docScript;
        int count = 0;
        for (String img: script) {
            docScript = Jsoup.parse(img);
            imageURL = docScript.select("img").attr("src");
            newsURL = originalLink + articles.get(count).select("a").attr("href");
            description = descriptions.get(count).text();
            title = docScript.select("img").attr("alt");
            if (!imageURL.contains("http")) {
                imageURL = docScript.select("img").attr("data-src");
            }
            this.newsList.add(new News(newsURL, title.split("hinh")[0], description, imageURL));
            count++;
        }
    }

    public void loadTuoiTre(String url) throws  IOException {
        String originalURL = "https://tuoitre.vn/";
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
            this.newsList.add(new News(newsURL, title, description, imageURL));
        }
        System.out.println(articleString.length);
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
        String originalLink = "https://nhandan.vn/";
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

    public int getSize() { return this.newsList.size(); }

    public News getNews(int whichNews) {
        return newsList.get(whichNews);
    }
}
