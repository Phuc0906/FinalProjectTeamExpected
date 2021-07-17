package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

// main content image
// https://i1-vnexpress.vnecdn.net/2021/07/17/tiem-vc-tai-fpt-jpeg-9352-1624-3177-6484-1626530579.jpg?w=680&h=408&q=100&dpr=2&fit=crop&s=L2cBvRc9Lluu-XDXY9zwiw

public class ImportantNews extends HBox {
    public ImportantNews(BorderPane mainPane, String URL) {
        Image image = new Image(URL);
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(mainPane.widthProperty().divide(3.5));
        imageView.fitHeightProperty().bind(mainPane.heightProperty().divide(5));
        setStyle("-fx-background-color: lightgrey");

        // add information
        Label label = new Label("Updating...");
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        label.setTextFill(Color.BLACK);

        setPadding(new Insets(5));

        getChildren().addAll(imageView, label);
    }
}
