package sample.SupportClass;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.NewsObject.NewsManagement;

import javax.print.Doc;
import javax.sound.sampled.Line;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SupportedMethod {

    public String breakingString(String inputString, int numberOfWordPerLine) {
        // split string into individual words
        String[] splittedString = inputString.split("\\s");
        LinkedList<String> paragraphStructure = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < splittedString.length; i++) {
            if (count == numberOfWordPerLine) {
                paragraphStructure.add("\n");
                count = 0;
            }
            paragraphStructure.add(splittedString[i] + " ");
            count++;
        }

        String completedParagraph = "";
        for (int i = 0; i < paragraphStructure.size(); i++) {
            completedParagraph += paragraphStructure.get(i);
        }
        return completedParagraph;
    }

    public void setTitleList(ArrayList<Label> labelList, int begin, NewsManagement newsList, ArrayList<ImageView> imgLayouts, AnchorPane coverPane){
        int count = begin;
        for (Label title: labelList) {
            title.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            title.setAlignment(Pos.TOP_LEFT);
            title.prefHeightProperty().bind(imgLayouts.get(0).fitHeightProperty().divide(2));
            title.prefWidthProperty().bind(coverPane.widthProperty().subtract(imgLayouts.get(0).fitWidthProperty()));
            title.setWrapText(true);
            title.setText(newsList.getNews(count).getTitle());
            count++;
        }
    }

    public void setDescriptionList(ArrayList<Label> labelList, int begin, NewsManagement newsList, ArrayList<ImageView> imgLayouts, AnchorPane coverPane){
        int count = begin;
        for (Label description: labelList) {
            description.setFont(Font.font("Time New Roman", FontWeight.BOLD, 20));
            description.setAlignment(Pos.TOP_LEFT);
            description.setWrapText(true);
            description.setText(newsList.getNews(count).getDescription());
            description.prefHeightProperty().bind(imgLayouts.get(0).fitHeightProperty().divide(2));
            description.prefWidthProperty().bind(coverPane.widthProperty().subtract(imgLayouts.get(0).fitWidthProperty()).multiply(3.5).divide(5));
            count++;
        }
    }

    public void setImgList(ArrayList<ImageView> imgList, int begin, NewsManagement newsList, AnchorPane coverPane){
        int count = begin;
        for (ImageView img: imgList) {
            img.setImage(new Image(newsList.getNews(count).getImageURL()));
            img.fitHeightProperty().bind(coverPane.heightProperty().divide(5));
            img.autosize();
            count++;
        }
    }

    public void scrapeCategory(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        boolean isScrape = false;
        Elements links = document.select("ul");
        Element ulContent;
        for (Element link: links) {
            if (link.attr("class").contains("parent")) {
                System.out.println("Pass");
                ulContent = link;
                isScrape = true;
                Document ulScript = Jsoup.parse(ulContent.toString());
                for (Element cateLink: ulScript.select("li")) {
                    if (cateLink.select("a").attr("href").contains("/")) {
                        if (!(cateLink.select("a").attr("href").length() <= 1)) {
                            System.out.println(cateLink.select("a").attr("href"));
                        }
                    }
                }
                break;
            }
        }

        if (!isScrape) {
            Elements menu = links;
//            System.out.println(links);
            for (Element ulLink: links) {
                if (ulLink.select("li").attr("class").contains("parent") || ulLink.select("li").attr("class").contains("menu")) {
                    menu = ulLink.select("li");
                    break;
                }
            }
            Document menuScrape = Jsoup.parse(menu.toString());
            for (Element menuLink: menuScrape.select("li")) {
                if (menuLink.attr("class").contains("parent") || menuLink.attr("class").contains("menu")) {
                    if (!(menuLink.select("a").attr("href").length() <= 1)) {
                        System.out.println(menuLink.select("a").attr("href"));
                    }
                }
            }
        }
    }

    public void scrapeArticleLink(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements articles = document.select("article a");
        if (articles.size() == 0) {
            articles = document.select("div li.news-item a");
        }
        ArrayList<String> articleLink = new ArrayList<>();
        String webLink;
        for (Element link: articles) {
            if (!articleLink.contains(link.attr("href"))) {
                if (!(link.attr("href").length() <= 1)) {
                    articleLink.add((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"));
                    System.out.println((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"));
                }

            }

        }
    }

    public void scrapeArticle(String url) throws IOException {
        // String array for the gioi detection
        String[] countries = new String[]{ "mỹ", "việt nam", "afghanistan", "nhật bản"};

        Document document = Jsoup.connect(url).get();

        // scrape data through meta document
        Elements keyWord = document.select("meta[property=article:tag]");
        String description = document.select("meta[property=og:description]").attr("content");
        String title = document.select("meta[property=og:title]").attr("content");
        String imageURLs = document.select("meta[property=og:image]").attr("content");
        String time = document.select("meta[property=article:published_time]").attr("content");

        // use string category to store the category which article belongs to
        String category = "";
        for (Element keyword: keyWord) {
            // check category
            for (String country: countries) {
                // compare the keyword with country array
                if (keyword.attr("content").toLowerCase().contains(country)) {
                    // if matched => add the gioi to category
                    category += "The gioi ";
                    break;
                }
            }

            // check the rest of the category
            if (keyword.attr("content").toLowerCase().contains("covid") || keyword.attr("content").toLowerCase().contains("ncov")) {
                category += "Covid ";
            }
            if (keyword.attr("content").toLowerCase().contains("cong nghe")) {
                category += "Cong Nghe";
            }

            // print out keywords in meta data
            System.out.println("Article keyword: " + keyword.attr("content"));
        }

        // print out scraped content
        System.out.println("Description: " + description);
        System.out.println("Title: " + title);
        System.out.println("Category: " + category);
        System.out.println("Image url: " + imageURLs);
        System.out.println("Time: " + time);

        //create array listt to store paragraph
        ArrayList<String> paragraphs = new ArrayList<>();

        // scrape all article by selecting p script
        Elements articlesParagraph = document.select("p");
        for (Element element: articlesParagraph) {
            if ((element.attributes().size() == 0) || (element.attr("class").contains("Normal"))) {
                // except vnexpress article has class normal in the p script. otherwise, just come to p script
                paragraphs.add(element.text());
                System.out.println(element.text());
            }
        }

        if (paragraphs.size() < 5) {
            // if the paragraph list is less than 5 items => that is not a article paragraph => clear the paragraph list and re-scraping
            paragraphs.clear();
        }

        //the last case is thanh nien article. It will execute thanh nien scraping
        if (paragraphs.size() == 0) {

            // only thanh nien article wrap paragraph in div script
            articlesParagraph = document.select("div");
            for (Element elements: articlesParagraph) {
                if (elements.attr("class").length() == 0) {
                    if (elements.attributes().size() == 0) {
                        if (!(elements.text().length() == 0)) {
                            System.out.println(elements.text());
                            paragraphs.add(elements.text());
                        }
                    }
                }
            }
        }

    }
}
