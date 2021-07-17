package sample;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CenterPane extends VBox {
    public CenterPane() {

    }

    public void addContent(Pane content) {
        getChildren().addAll(content);
    }
}
