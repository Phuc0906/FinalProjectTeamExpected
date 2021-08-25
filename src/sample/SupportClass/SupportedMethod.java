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
import sample.NewsObject.News;
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
                        System.out.println(cateLink.select("a").attr("href"));
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
                    System.out.println(menuLink.select("a").attr("href"));
                }
            }
        }
    }

    public ArrayList<String> getHref(String url) throws IOException {
        ArrayList<String> webLinksList = new ArrayList<>();
        ArrayList<String> category = new ArrayList<>();
        Document document = Jsoup.connect(url).get();
        Elements links = document.select("article a");
        if (links.size() == 0) {
            links = document.select("li.news-item a");
            System.out.println("IN");
        }


        for (Element link: links) {
            String webLink = link.attr("href");
            if (!webLink.contains("http")) {
                webLink = url + webLink;
            }
            if (webLink.toCharArray()[webLink.length() - 1] == '/') {
                if (!category.contains(webLink)) {
                    category.add(webLink);
                    System.out.println("Category: " + webLink);
                }
            }else {
                webLinksList.add(webLink);
            }
        }
        return webLinksList;
    }

    public void scrapingGeneral(String webURL) throws IOException {
        String[] sampleCountry = new String[]{ "mỹ", "việt nam", "afghanistan", "nhật bản"};
        Document document = Jsoup.connect(webURL).get();
        String keywords = document.select("meta[name=keywords]").attr("content").toLowerCase();
        String date = document.select("meta[name=pubdate]").attr("content");
        System.out.println("Keyword: " + keywords);
        String categories = "";

        // check covid category
        if (keywords.contains("covid")) {
            categories += "Covid, ";
        }

        // check in the counrty list
        for (String country: sampleCountry) {
            if (keywords.contains(country)) {
                categories += "the gioi, ";
                break;
            }
        }
        System.out.println("Date: " + date);
        System.out.println("Category: " + categories);
    }
}
