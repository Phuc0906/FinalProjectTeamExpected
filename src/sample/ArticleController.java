package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sample.BaseController.ChangingCategory;
import sample.NewsObject.News;

import java.io.IOException;

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

    public void setError() throws  IOException {
        coverPane.prefWidthProperty().bind(scrPane.widthProperty());
        articleBox.setSpacing(20);
        Label error = new Label();
        error.setText("Error 404: File not found.");
        error.setFont(Font.font("",FontWeight.BOLD,50));
        error.setWrapText(true);
        articleBox.getChildren().clear();
        articleBox.getChildren().add(error);
    }

    public void setContent(News news) throws IOException {
        coverPane.prefWidthProperty().bind(scrPane.widthProperty());
        articleBox.setSpacing(20);

        Label title = new Label();
        title.setText(news.getTitle());
        title.setFont(Font.font("",FontWeight.BOLD,30));
        title.setWrapText(true);
        articleBox.getChildren().add(title);

        Label description = new Label();
        description.setText(news.getDescription());
        description.setFont(Font.font("",FontWeight.SEMI_BOLD, FontPosture.ITALIC,20));
        description.setWrapText(true);
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
                for (String para: paragraphList) {
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
                        }catch (Exception ex) {
                            // skipping error
                        }

                    }
                    articleBox.getChildren().add(text);
                    count++;
                }
                break;
            }

            case "Tuoi Tre": {
                Elements paragraph = doc.select("div.main-content-body p");
                String img = paragraph.select("img").attr("src");
                System.out.println(img);
                String[] paragraphString = paragraph.toString().split("\n");
                for (String para: paragraphString) {
                    Document docScript = Jsoup.parse(para);
                    if(!docScript.text().contains("Ảnh") && !docScript.text().contains("TTO")) {
                        Label text = new Label();
                        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        text.setWrapText(true);
                        text.setText(docScript.text());
                        articleBox.getChildren().add(text);
                    }
                }
                Elements author = doc.select("div.main-content-body div.author");
                articleBox.getChildren().add(new Label(author.text()));
                break;
            }
            case "Zing News": {
                Elements elements = doc.select("div.the-article-body p");
                String[] paragraphs = elements.toString().split("\n");
                for (String paragraph : paragraphs) {
                    Document docScript = Jsoup.parse(paragraph);
                    Label text = new Label();
                    text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                    text.setWrapText(true);
                    text.setText(docScript.select("p").text());
                    articleBox.getChildren().add(text);
                }
                System.out.println("Zing News");
                break;
            }
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
        for (String para: paragraphList) {
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
