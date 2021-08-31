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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.BaseController.ChangingCategory;
import sample.NewsObject.News;
import java.io.IOException;
import java.util.ArrayList;

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
        articleBox.setSpacing(20);

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
        System.out.println(news.getNewsOutlet());
        switch (news.getNewsOutlet()) {

            case "VN Express": {
                Elements paragraph = doc.select("p");
                Elements figure = doc.select("figure");
                String[] figures = figure.toString().split("</figure>");
                String[] paragraphList = paragraph.toString().split("\n");
                int imgCount = 0;
                for (String para : paragraphList) {
                    Document docScript = Jsoup.parse(para);
                    if (!docScript.text().contains("Ảnh:") && !docScript.text().contains("TTO") && !docScript.text().replaceAll("\\s+","").equalsIgnoreCase(news.getDescription().replaceAll("\\s+",""))) {
                        Label text = new Label();
                        text.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                        text.setText(docScript.text());
                        text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                        text.setWrapText(true);
                        articleBox.getChildren().add(text);
                    }

                    if (docScript.text().contains("Ảnh:")) {
                        try {
                            Document img = Jsoup.parse(figures[imgCount].replaceAll("\n", "") + "</figure>");
                            String[] imgList = img.select("source").attr("data-srcset").split(" ");
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList[imgCount]));
                            Label photoDescription = new Label(docScript.text());
                            photoDescription.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.ITALIC,15));
                            photoDescription.setWrapText(true);
                            photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            imgCount++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }
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
                    if (docScript.text().contains("Ảnh:")) {
                        try {
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList.get(imgCount)));
                            Label photoDescription = new Label(docScript.text());
                            photoDescription.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.ITALIC,15));
                            photoDescription.setWrapText(true);
                            photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            imgCount++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }
                    // add paragraph
                    if (!docScript.text().contains("Ảnh:") && !docScript.text().contains("TTO")) {
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

            case "Nhan Dan": {
                VBox viewPhoto = new VBox();
                Elements photoBox = doc.select("div.box-detail-thumb");
                ImageView photo = new ImageView(new Image(photoBox.select("img").attr("src")));
                Label photoDescription = new Label(photoBox.text());
                photoDescription.setFont(Font.font("Arial", FontWeight.NORMAL,FontPosture.ITALIC,15));
                photoDescription.setWrapText(true);
                photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
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
            }

            case "Thanh Nien": {
                Elements paraList = doc.select("div.cms-body div");
                for (Element paragraph : paraList) {
                    if (!paragraph.select("div").text().contains("Ảnh:")) {
                        if (!paragraph.select("div").text().contains("Tin liên quan"))
                            System.out.println(paragraph.select("div").text());
                    }
                }
            }
            default:
                System.out.println(news.getNewsOutlet());
        }
    }
}
