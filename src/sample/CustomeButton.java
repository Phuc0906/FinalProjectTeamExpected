package sample;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomeButton extends Button {
    public CustomeButton(String buttonContent) {
        setText(buttonContent);
        setFont(Font.font("Arial", FontWeight.BOLD, 16));
        setStyle("" +
                "-fx-background-color: lightgrey;" +
                "-fx-border-color: black;");
    }
}
