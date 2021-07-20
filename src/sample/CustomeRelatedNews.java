package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomeRelatedNews extends VBox {
    public CustomeRelatedNews(String content) {
        // setting the pane style
        setStyle("-fx-background-color: greenyellow");
        setPadding(new Insets(10));

        // adding label to pane
        Label label = new Label();
        label.setText(content);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        getChildren().addAll(label);
    }
}
