package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

// Rmit logos link
//"https://www.rmit.edu.vn/content/dam/rmit/vn/en/assets-for-production/images/rmit-logo/rmit-logo-transparentbg-square.jpg"

public class RmitLogos extends HBox {
    public RmitLogos(BorderPane mainPane, String URL) {
        Image image = new Image(URL);
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(mainPane.widthProperty().divide(7));
        imageView.fitHeightProperty().bind(mainPane.heightProperty().divide(6));
        getChildren().add(imageView);
    }
}
