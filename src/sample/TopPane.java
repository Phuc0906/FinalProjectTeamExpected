package sample;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// rmit photo https://i.ytimg.com/vi/WA8uOPOsmv4/maxresdefault.jpg

public class TopPane extends StackPane {
    public TopPane(BorderPane mainPane) {
        VBox vBox = new VBox();

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
        String[] categories = { "Covid", "News", "Politics", "Business", "Technology", "Health", "Sports", "Entertainment", "World", "Others"};
        for (int i = 0; i < categories.length; i++) {
            hBox.getChildren().add(new CustomeButton(categories[i]));
        }
        hBox.setPadding(new Insets(5, 5, 5, 5));

        title.getChildren().add(label);
        rmitPhoto.getChildren().add(imageView);

        vBox.getChildren().add(rmitPhoto);
        vBox.getChildren().add(title);
        vBox.getChildren().add(hBox);

        getChildren().addAll(vBox);
    }
}
