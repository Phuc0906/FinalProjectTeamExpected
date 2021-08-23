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
        Elements imgList = doc.select("div.thumb-art");
        String[] imgStrings = imgList.toString().split("</div>");
        Elements descriptionList = doc.select("p.description");
        String[] descriptions = descriptionList.toString().split("</p>");
        String newsURL;
        String title;
        String description;
        String imageURL[];
        int count = 0;
        for (String img: imgStrings) {
            Document linkImg = Jsoup.parse(img.replaceAll("\n", "") + "</div>");
            newsURL= linkImg.select("a").attr("href");
            imageURL = linkImg.select("source").attr("data-srcset").split("\\s");
            if (imageURL[0].isEmpty()) {
                imageURL = linkImg.select("source").attr("srcset").split("\\s");
            }
            title = linkImg.select("a").attr("title");

            Document descriptionHTML = Jsoup.parse(descriptions[count].replaceAll("\n", "").replaceAll(">\\s+<", "><") + "</p>");
            description = descriptionHTML.select("p").text();

            newsList.add(new News(newsURL, title, description, imageURL[0]));
            count++;
        }
    }

    public void loadNhanDan(String url) throws  IOException {
        String originalLink = "https://nhandan.vn/";
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div.box-img a");
        Elements elements1 = document.select("div.box-des p");
        String[] obj = elements.toString().split("\\n");
        Document docScript;
        int count = 0;

        for(String Obj : obj) {
            try {
                docScript = Jsoup.parse(Obj);
                imageURL = docScript.select("img").attr("data-src");
                title = docScript.select("a").attr("title");
                newsURL = originalLink + elements.get(count).select("a").attr("href");
                description = elements1.get(count).select("p").text();
            }catch (IndexOutOfBoundsException ex) {
                break;
            }
            newsList.add(new News(newsURL, title, description, imageURL));
            count++;
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
