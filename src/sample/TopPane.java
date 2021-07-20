package sample;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// rmit photo https://i.ytimg.com/vi/WA8uOPOsmv4/maxresdefault.jpg

public class TopPane extends StackPane {
    public TopPane(BorderPane mainPane) {
        VBox vBox = new VBox();
        vBox.setSpacing(5);

        // create rmit logos
        StackPane rmitPhoto = new StackPane();
        Image titleImage = new Image("https://i.ytimg.com/vi/WA8uOPOsmv4/maxresdefault.jpg");
        ImageView imageView = new ImageView(titleImage);
        imageView.fitWidthProperty().bind(mainPane.widthProperty().divide(1.2));
        imageView.fitHeightProperty().bind(mainPane.heightProperty().divide(3));

        // Create title
        StackPane title = new StackPane();
        Label label = new Label("Rmit newspaper");
        label.setFont(Font.font("Arial", FontWeight.BOLD, 45));

        // add button
        StackPane buttonPane = new StackPane();
        FlowPane hBox = new FlowPane(Orientation.HORIZONTAL);
        hBox.setAlignment(Pos.CENTER);
        hBox.setHgap(5);
        hBox.setStyle("" +
                "-fx-border-color: black;" +
                "-fx-border-width: 3;" +
                "-fx-background-color: lightgrey;");


        Label rmitNews = new Label("Rmit Newspaper");
        rmitNews.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        rmitNews.setPadding(new Insets(5, 30, 5, 5));
        hBox.getChildren().add(rmitNews);

        // add covid button
        CustomeButton covidButton = new CustomeButton("Covid");
        hBox.getChildren().add(covidButton);
        covidButton.setOnAction((ActionEvent e) -> {
            System.out.println("Move to covid scene");
        });

        // add news button
        CustomeButton newsButton = new CustomeButton("News");
        hBox.getChildren().add(newsButton);
        newsButton.setOnAction(e -> {
            System.out.println("Move to news scene");
        });

        // add Politics Button
        CustomeButton politicsButton = new CustomeButton("Politics");
        hBox.getChildren().add(politicsButton);
        politicsButton.setOnAction(e -> {
            System.out.println("Move to politic scene");
        });


        // add business button
        CustomeButton businessButton = new CustomeButton("Business");
        hBox.getChildren().add(businessButton);
        businessButton.setOnAction(e -> {
            System.out.println("Move to business scene");
        });

        // add technology button
        CustomeButton technologyButton = new CustomeButton("Technology");
        hBox.getChildren().add(technologyButton);
        technologyButton.setOnAction(e -> {
            System.out.println("Move to technology scene");
        });

        // add Health Button
        CustomeButton healthButton = new CustomeButton("Health");
        hBox.getChildren().add(healthButton);
        healthButton.setOnAction(e -> {
            System.out.println("Move to health scene");
        });

        // add Sport Button
        CustomeButton sportButton = new CustomeButton("Sport");
        hBox.getChildren().add(sportButton);
        sportButton.setOnAction(e -> {
            System.out.println("Move to sport scene");
        });

        // add entertainment button
        CustomeButton entertainmentButton = new CustomeButton("Entertainment");
        hBox.getChildren().add(entertainmentButton);
        entertainmentButton.setOnAction(e -> {
            System.out.println("Move to entertainment scene");
        });

        // add world button
        CustomeButton worldButton = new CustomeButton("World");
        hBox.getChildren().add(worldButton);
        worldButton.setOnAction(e -> {
            System.out.println("Move to world scene");
        });

        // add Others Button
        CustomeButton othersButton = new CustomeButton("Others");
        hBox.getChildren().add(othersButton);
        othersButton.setOnAction(e -> {
            System.out.println("move to others scene");
        });

        hBox.setPadding(new Insets(5, 5, 5, 5));

        title.getChildren().add(label);
        rmitPhoto.getChildren().add(imageView);

        vBox.getChildren().add(rmitPhoto);
        vBox.getChildren().add(hBox);

        getChildren().addAll(vBox);
    }
}
