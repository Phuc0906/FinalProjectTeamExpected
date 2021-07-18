package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomeRelatedNews extends StackPane {
    public CustomeRelatedNews(Pane mainPane, Pane mainNews, String content) {
        Rectangle recBox = new Rectangle();
        setPadding(new Insets(0, 5, 5, 5));
        recBox.setFill(Color.GREENYELLOW);
        recBox.xProperty().bind(mainPane.widthProperty().subtract(mainNews.widthProperty()));
        recBox.yProperty().bind(mainPane.heightProperty().divide(mainNews.heightProperty()));
        recBox.widthProperty().bind(widthProperty());
        recBox.heightProperty().bind(mainNews.heightProperty().divide(5).divide(2));
        Label label = new Label();
        label.setText(content);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        getChildren().addAll(recBox, label);
    }
}
