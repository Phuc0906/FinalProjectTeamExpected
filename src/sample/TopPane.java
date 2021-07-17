package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TopPane extends StackPane {
    public TopPane(BorderPane mainPane, String title) {
        setPadding(new Insets(2));
        Label label = new Label(title);
        label.setFont(Font.font("Bass", FontWeight.NORMAL, 37));
        label.setTextFill(Color.ORANGE);

        RmitLogos rmitLogos = new RmitLogos(mainPane, "https://www.rmit.edu.vn/content/dam/rmit/vn/en/assets-for-production/images/rmit-logo/rmit-logo-transparentbg-square.jpg");
        getChildren().addAll(label, rmitLogos);
    }
}
