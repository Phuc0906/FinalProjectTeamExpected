package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.BaseController.ChangingCategory;
import sample.NewsObject.News;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController extends ChangingCategory {
    @FXML
    VBox articleBox;

    @FXML
    AnchorPane coverPane;

    @FXML
    ScrollPane scrPane;

    @FXML
    Button logo;

    @FXML
    HBox buttonBox;

    public void setError() throws IOException {
        coverPane.prefWidthProperty().bind(scrPane.widthProperty());
        articleBox.setSpacing(20);
        Label error = new Label();
        error.setText("Error 404: File not found.");
        error.setFont(Font.font("", FontWeight.BOLD, 50));
        error.setWrapText(true);
        articleBox.getChildren().clear();
        articleBox.getChildren().add(error);
    }

    public void setContent(News news) throws IOException {
        coverPane.prefWidthProperty().bind(scrPane.widthProperty());
        articleBox.setSpacing(10);

        Label title = new Label();
        title.setText(news.getTitle());
        title.setFont(Font.font("", FontWeight.BOLD, 30));
        title.setWrapText(true);
        title.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
        articleBox.getChildren().add(title);

        Label description = new Label();
        description.setText(news.getDescription());
        description.setFont(Font.font("", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20));
        description.setWrapText(true);
        description.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
        articleBox.getChildren().add(description);

        Document doc = Jsoup.connect(news.getNewsURL()).get();

        switch (news.getNewsOutlet()) {

            case "VN Express": {
                Elements paragraph = doc.select("p");
                Elements figure = doc.select("figure");
                String[] figures = figure.toString().split("</figure>");
                String[] paragraphList = paragraph.toString().split("\n");
                int count = 1;
                int imgCount = 0;
                for (String para : paragraphList) {
                    Document docScript = Jsoup.parse(para);
                    Label text = new Label();
                    text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                    text.setText(docScript.text());
                    text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                    text.setWrapText(true);

                    if (docScript.text().contains("Ảnh")) {
                        try {
                            Document img = Jsoup.parse(figures[imgCount].replaceAll("\n", "") + "</figure>");
                            String[] imgList = img.select("source").attr("data-srcset").split(" ");
                            articleBox.getChildren().add(new ImageView(new Image(imgList[imgCount])));
                            imgCount++;
                        } catch (Exception ex) {
                            // skipping error
                        }

                    }
                    articleBox.getChildren().add(text);
                    count++;
                }
                break;
            }

            case "Tuoi Tre": {
                int imgCount = 0;
                // add all img to imgList
                ArrayList<String> imgList = new ArrayList<>();
                Elements list = doc.select("div.main-content-body div.VCSortableInPreviewMode");
                for (Element html : list) {
                    String img = html.select("img").attr("src");
                    imgList.add(img);
                }

                Elements paragraph = doc.select("div.main-content-body p");
                String[] paragraphString = paragraph.toString().split("\n");
                for (String para : paragraphString) {
                    Document docScript = Jsoup.parse(para);
                    //add img
                    if (docScript.text().contains("Ảnh")) {
                        try {
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList.get(imgCount)));
                            Label photoDescription = new Label(docScript.text());
                            photoDescription.setWrapText(true);
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            imgCount++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }
                    // add paragraph
                    if (!docScript.text().contains("Ảnh") && !docScript.text().contains("TTO")) {
                        Label text = new Label();
                        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        text.setWrapText(true);
                        text.setText(docScript.text());
                        text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                        articleBox.getChildren().add(text);
                    }
                }
                Elements author = doc.select("div.main-content-body div.author");
                articleBox.getChildren().add(new Label(author.text()));
                break;
            }

            case "Zing News": {
                ArrayList<String> imgList = new ArrayList<>();

                Elements Box = doc.select("table.picture tbody tr td");
                String[] BoxImg = Box.select("td.pic").toString().split("\"");
                for (String box : BoxImg) {
                    if(box.contains("https://")) {
                        imgList.add(box);
                    }
                }
                
                int count = 0;
                Elements elements = doc.select("div.the-article-body p");
                String[] paragraphs = elements.toString().split("\n");

                for (String paragraph : paragraphs) {
                    Document docScript = Jsoup.parse(paragraph);

                    if (docScript.text().contains("Ảnh: ")) {
                        try {
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList.get(count)));
                            photo.setFitHeight(500);
                            photo.setFitWidth(600);
                            photo.setPreserveRatio(true);
                            Text photoDescription = new Text(docScript.text());
                            photoDescription.setWrappingWidth(550);
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            count++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }

                    if (!docScript.text().contains("Ảnh: ")) {
                        Label text = new Label();
                        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        text.setWrapText(true);
                        text.setText(docScript.text());
                        articleBox.getChildren().add(text);
                    }
                }
                System.out.println("Zing News");
                break;
            }

            case "Nhan Dan": {
                VBox viewPhoto = new VBox();
                Elements photoBox = doc.select("div.box-detail-thumb");
                ImageView photo = new ImageView(new Image(photoBox.select("img").attr("src")));
                Label photoDescription = new Label(photoBox.text());
                photoDescription.setWrapText(true);
                viewPhoto.setSpacing(0);
                viewPhoto.getChildren().addAll(photo,photoDescription);
                articleBox.getChildren().add(viewPhoto);

                Elements docText = doc.select("div.entry-content div.detail-content-body p");
                for (Element para : docText) {
                    String paragraph = para.text();
                    Label text = new Label();
                    text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                    text.setWrapText(true);
                    text.setText(paragraph);
                    text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                    articleBox.getChildren().add(text);
                }
                Label author = new Label(doc.select("div.entry-content div.box-author").text());
                author.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                author.setWrapText(true);
                author.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                articleBox.getChildren().add(author);
                break;
            }
//            case "Thanh Nien": {
//                List<String> imgList = new ArrayList<>();
//                List<String> desList = new ArrayList<>();
//
//                //find image and description then save to Arraylist
//                Elements Boxes = doc.select("div#abody div table.imagefull tbody tr td div");
//                for (Element Box : Boxes) {
//                    String urlImage = Box.select("img").attr("data-src");
//                    if (urlImage.length() != 0) imgList.add(urlImage);
//                }
//
//                for (Element Box : Boxes.select("div.imgcaption p")) {
//                    String imgDes = Box.text();
//                    desList.add(imgDes);
//                }
//
//                int cnt = 0;
//                Elements elements = doc.select("div#abody div");
//                String[] paragraphs = elements.toString().split("\n");
//
//                for (String paragraph : paragraphs) {
//                    Document docScript = Jsoup.parse(paragraph);
//                    try {
//                        cnt++;
//                        VBox viewPhoto = new VBox();
//                        ImageView photo = new ImageView(new Image(imgList.get(cnt - 1)));
//                        photo.setPreserveRatio(true);
//                        Text photoDescription = new Text(desList.get(cnt - 1));
//                        Text author = new Text(desList.get(cnt));
//                        photoDescription.setWrappingWidth(550);
//                        viewPhoto.getChildren().addAll(photo, photoDescription, author);
//                        articleBox.getChildren().add(viewPhoto);
//                        cnt++;
//                    } catch (Exception ex) {
//                        // skipping error
//                    }
//
//                    if (!docScript.text().contains(desList.get(cnt))) {
//                        Label text = new Label();
//                        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
//                        text.setWrapText(true);
//                        text.setText(docScript.text());
//                        text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
//                        articleBox.getChildren().add(text);
//                    }
//                }
//                break;
//            }

            default:
                System.out.println("From unknown outlet");
        }
    }

    public void setContent() throws IOException {
        coverPane.prefWidthProperty().bind(scrPane.widthProperty());
        articleBox.setSpacing(20);

        logo.prefWidthProperty().bind(articleBox.widthProperty().divide(3));
        logo.prefHeightProperty().bind(scrPane.heightProperty().divide(5));
        logo.setAlignment(Pos.CENTER);
        logo.setStyle("-fx-background-color: lightgreen");

        buttonBox.prefHeightProperty().bind(scrPane.heightProperty().divide(15));
        buttonBox.setAlignment(Pos.CENTER_LEFT);


        Document doc = Jsoup.connect("https://vnexpress.net/di-tim-so-ca-tu-vong-covid-19-thuc-te-toan-cau-4345051.html").get();
        Elements paragraph = doc.select("p.normal");
        String[] paragraphList = paragraph.toString().split("\n");
        for (String para : paragraphList) {
            Document docScript = Jsoup.parse(para);
            Label text = new Label();
            text.setText(docScript.text());
            text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
            text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
            text.setWrapText(true);
            articleBox.getChildren().add(text);
        }

    }
}
