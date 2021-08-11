package sample.SupportClass;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.NewsObject.News;
import sample.NewsObject.NewsManagement;

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

    public void setTitleList(ArrayList<Label> labelList, int begin, NewsManagement newsList){
        int count = begin;
        for (Label title: labelList) {
            title.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            title.setAlignment(Pos.TOP_LEFT);
            title.setText(newsList.getNews(count).getTitle());
            count++;
        }
    }

    public void setDescriptionList(ArrayList<Label> labelList, int begin, NewsManagement newsList){
        int count = begin;
        for (Label description: labelList) {
            description.setFont(Font.font("Time New Roman", FontWeight.BOLD, 15));
            description.setAlignment(Pos.TOP_LEFT);
            description.setText(breakingString(newsList.getNews(count).getDescription(), 15));
            count++;
        }
    }

    public void setImgList(ArrayList<ImageView> imgList, int begin, NewsManagement newsList){
        int count = begin;
        for (ImageView img: imgList) {
            img.setImage(new Image(newsList.getNews(count).getImageURL()));
            count++;
        }
    }

    public void scrapeArticle(String webURL) throws IOException {
        Document doc = Jsoup.connect(webURL).get();
        String newsURL;
        String title;
        String description;
        String imageURLScraping;
        String imageURL[];
//        Elements components = doc.select("div.header-content");
//        System.out.println(components.select("span.date").text());
//        Elements img = doc.select("article.fck_detail");
//        String[] imageURL;
//        for (Element singleImg: img.select("figure")) {
//            imageURL = singleImg.select("div.fig-picture picture source").attr("data-srcset").split("\\s");
//            System.out.println(imageURL[0]);
//        }

    }
}
