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

    public void scrapeVnExpress(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements imgList = doc.select("div.thumb-art");
        String[] imgStrings = imgList.toString().split("</div>");
        System.out.println(imgList);
        Elements descriptionList = doc.select("p.description");
        String[] descriptions = descriptionList.toString().split("</p>");
        String newsURL;
        String title;
        String description;
        String imageURL[];
        int count = 0;
        for (String img: imgStrings) {
            System.out.println("--------------------------------");
            System.out.println("Link + imgLink: " + img.replaceAll("\n", "") + "</div>");
//            System.out.println("Description: " + descriptions[count].replaceAll("\n", "").replaceAll(">\\s+<", "><") + "</p>");
            Document linkImg = Jsoup.parse(img.replaceAll("\n", "") + "</div>");

            newsURL= linkImg.select("a").attr("href");
            System.out.println("News Link: " + newsURL);

            imageURL = linkImg.select("source").attr("data-srcset").split("\\s");
            System.out.println("imgURL: " + imageURL[0]);

            title = linkImg.select("a").attr("title");
            System.out.println("Title: " + title);

            Document descriptionHTML = Jsoup.parse(descriptions[count].replaceAll("\n", "").replaceAll(">\\s+<", "><") + "</p>");
            description = descriptionHTML.select("p").text();
            System.out.println("Description: " + description);
            count++;
        }
    }

}
