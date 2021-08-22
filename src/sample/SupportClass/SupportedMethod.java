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

    public void loadNhandan() throws IOException {
        String originalLink = "https://nhandan.vn/";
        String newsURL;
        String title;
        String description;
        String imageURL;

        Document document = Jsoup.connect("https://nhandan.vn/thegioi").get();
        Elements elements = document.select("div.box-img a");
        Elements elements1 = document.select("div.box-des p");
        String[] obj = elements.toString().split("\\n");
//        String[] obj1 = elements1.toString().split("\\n");
        Document docScript;
        int count = 0;

        for(String Obj : obj) {
            docScript = Jsoup.parse(Obj);
            imageURL = docScript.select("img").attr("data-src");
            title = docScript.select("a").attr("title");
            newsURL = originalLink + elements.get(count).select("a").attr("href");
            description = elements1.get(count).select("p").text();
            System.out.println("--------------");
            System.out.println(title);
            System.out.println(newsURL);
            System.out.println(imageURL);
            System.out.println(description);
            count++;
        }
    }

}
