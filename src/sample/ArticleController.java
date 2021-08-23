package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import sample.BaseController.ChangingCategory;
import sample.NewsObject.News;
import sample.NewsObject.NewsManagement;

import java.io.IOException;

public class ArticleController extends ChangingCategory {
    @FXML
    VBox articleBox;

    public void setArticleBox(News news) throws IOException {
        Label title = new Label();
        title.setText(news.getTitle());
        title.setFont(Font.font("",FontWeight.BOLD,20));
        ImageView imageView = new ImageView(new Image(news.getImageURL()));
        articleBox.getChildren().add(title);
        articleBox.getChildren().add(new Label(news.getDescription()));
        articleBox.getChildren().add(imageView);

        Document doc = Jsoup.connect(news.getNewsURL()).get();
        switch (news.getNewsOutlet()) {

            case "VN Express": {
                Elements paragraph = doc.select("p.normal");
                String[] paragraphList = paragraph.toString().split("\n");
                for (String para: paragraphList) {
                    Document docScript = Jsoup.parse(para);
//            System.out.println(docScript.text());
                    Label text = new Label();
                    text.setText(docScript.text());
                    articleBox.getChildren().add(text);
                }
                break;
            }

            case "Tuoi Tre": {
                Elements paragraph = doc.select("div.main-content-body p");
//        System.out.println(paragraph);
                String[] paragraphString = paragraph.toString().split("\n");
                for (String para: paragraphString) {
                    Document docScript = Jsoup.parse(para);
                    if(!docScript.text().contains("Ảnh") && !docScript.text().contains("TTO")) {
                        Label text = new Label();
                        text.setText(docScript.text());
                        articleBox.getChildren().add(text);
                    }
                }
                Elements author = doc.select("div.main-content-body div.author");
//                System.out.println(author.text());
                articleBox.getChildren().add(new Label(author.text()));
                break;
            }
        }

    }
}
