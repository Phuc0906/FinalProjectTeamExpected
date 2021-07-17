package sample;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LeftPane extends StackPane {
    public LeftPane(BorderPane mainPane) {
        getChildren().addAll(new Label("Left Content"));
    }
}
