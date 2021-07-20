package sample;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// main news photo link: https://cdn.tuoitre.vn/zoom/504_315/2021/7/18/qltt-bhx-16265948769284065416-crop-16265965687281989892182.jpg

public class ImportantNews extends VBox {
    public ImportantNews(Pane mainPane) {
        setAlignment(Pos.TOP_CENTER);
        setFillWidth(true);
        // create main news

        // Create HBox to contain some related news with main news
        FlowPane hBox = new FlowPane(Orientation.HORIZONTAL);
        hBox.setHgap(6);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.setPadding(new Insets(25, 20, 25, 50));
        hBox.autosize();

        FlowPane mainNews = new FlowPane(Orientation.VERTICAL);
        // add photo to main news
        ImageView mainNewsPhoto = new ImageView(new Image("https://cdn.tuoitre.vn/zoom/504_315/2021/7/18/qltt-bhx-16265948769284065416-crop-16265965687281989892182.jpg"));
        mainNewsPhoto.fitWidthProperty().bind(mainPane.widthProperty().divide(3));
        mainNewsPhoto.fitHeightProperty().bind(mainPane.heightProperty().divide(4));
        mainNews.getChildren().add(mainNewsPhoto);

        // main news topic
        Label mainNewsTopic = new Label();
        mainNewsTopic.setText("Topic of main news");
        mainNewsTopic.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        mainNewsTopic.setTextFill(Color.BLACK);
        mainNews.getChildren().add(mainNewsTopic);

        // related news with main news
        FlowPane relatedNews = new FlowPane(Orientation.VERTICAL);
        relatedNews.vgapProperty().bind(mainNews.heightProperty().divide(20));
        for (int i = 0; i < 5; i++) {
            relatedNews.getChildren().add(new CustomeRelatedNews("Related news " + i));
        }

        // create bonus news
        FlowPane bonusNews = new FlowPane(Orientation.HORIZONTAL);
        bonusNews.setAlignment(Pos.TOP_CENTER);
        bonusNews.setHgap(20);
        for (int i = 0; i < 4; i++) {
            bonusNews.getChildren().add(new CustomeBonusNews("Bonus new " + i));

        }

        hBox.getChildren().add(mainNews);
        hBox.getChildren().add(relatedNews);
        getChildren().addAll(hBox, bonusNews);
    }
}
