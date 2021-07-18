package sample;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Footer extends VBox {
    public Footer() {
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 10, 10, 10));
        setHeight(5);

        Label developer = new Label("Developer");
        developer.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        Label dev1 = new Label();
        Label dev2 = new Label();
        Label dev3 = new Label();
        Label dev4 = new Label();

        dev1.setText("Developer 1");
        dev1.setFont(Font.font("Arial", FontWeight.NORMAL, 10));

        dev2.setText("Developer 2");
        dev2.setFont(Font.font("Arial", FontWeight.NORMAL, 10));

        dev3.setText("Developer 3");
        dev3.setFont(Font.font("Arial", FontWeight.NORMAL, 10));

        dev4.setText("Developer 4");
        dev4.setFont(Font.font("Arial", FontWeight.NORMAL, 10));
        getChildren().add(developer);
        getChildren().addAll(dev1, dev2, dev3, dev4);
    }
}
