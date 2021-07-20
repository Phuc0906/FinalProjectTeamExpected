package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomeBonusNews extends StackPane {
    public CustomeBonusNews(String content) {

        Rectangle recBox = new Rectangle();
        recBox.setFill(Color.GRAY);
        recBox.xProperty().bind(layoutXProperty());
        recBox.yProperty().bind(layoutYProperty());
        recBox.widthProperty().bind(widthProperty());
        recBox.heightProperty().bind(heightProperty());

        Label label = new Label(content);
        label.setPadding(new Insets(13));
        label.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        getChildren().addAll(recBox, label);
    }
}
