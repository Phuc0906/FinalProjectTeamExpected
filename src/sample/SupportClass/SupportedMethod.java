package sample.SupportClass;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
import java.util.List;

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

//    public void loadZingArticle (String zingUrl) throws IOException {
//        String para;
//        String imgURL;
//
//        Document doc = Jsoup.connect(zingUrl).get();
//
//        ArrayList<String> imgList = new ArrayList<>();
//        List<String> desList = new ArrayList<>();
//
//        Elements Box = doc.select("table.picture tbody tr td");
//        String[] BoxImg = Box.select("td.pic").toString().split("\"");
//
//        for (String box : BoxImg) {
//            if(box.contains("https://")) {
//                imgURL = box;
//                System.out.println(imgURL);
//                imgList.add(imgURL);
//            }
//        }
//
//        String[] BoxDes = Box.select("td.pCaption").toString().split("\n");
//        for (String box : BoxDes) {
//            Document document = Jsoup.parse(box);
//            String desImg = document.select("p").text();
//            desList.add(desImg);
//            System.out.println(desImg);
//            }
//        System.out.println(Box.select("td.pCaption"));
//        System.out.println("---------");
//
//        int count = 0;
//        Elements elements = doc.select("div.the-article-body");
//        String[] paragraphs = elements.toString().split("\n");
//        for (String paragraph : paragraphs) {
//            Document docScript = Jsoup.parse(paragraph);
//            if (docScript.text().contains("Ảnh")) {
//                try {
//                    VBox viewPhoto = new VBox();
//                    ImageView photo = new ImageView(new Image(imgList.get(count));
//                    Label photoDescription = new Label(desList.get(count));
//                    photoDescription.setWrapText(true);
//                    viewPhoto.getChildren().addAll(photo,photoDescription);
//                    articleBox.getChildren().add(viewPhoto);
//                    count++;
//                } catch (Exception ex) {
//                    // skipping error
//                }
//            }
//            Label text = new Label();
//            text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
//            text.setWrapText(true);
//            text.setText(docScript.select("p").text());
//            para = docScript.select("p").text();
//            System.out.println(para);
//        }
//    }
}
