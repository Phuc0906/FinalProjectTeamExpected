package NewsApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

public class CovidController {
    String demoNewspaper = " gwrbnsguobwsrugbwruogwruipwyivrtyqewfqe8yfyq8etfqwe";

    @FXML
    private Button btnCovid, btnNews, btnHome;
    @FXML
    public void toCovidScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFile/CovidPage.fxml"));
        Stage stage = (Stage) btnCovid.getScene().getWindow();
        Scene scene = new Scene(root, 1500, 1000);
        stage.setScene(scene);
    }

    @FXML
    public void toNewsScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFile/NewsPage.fxml"));
        Stage stage = (Stage) btnNews.getScene().getWindow();
        Scene scene = new Scene(root, 1500, 1000);
        stage.setScene(scene);
    }

    @FXML
    public void toHomePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFile/HomePage.fxml"));
        Stage stage = (Stage) btnHome.getScene().getWindow();
        Scene scene = new Scene(root, 1500, 1000);
        stage.setScene(scene);
    }

    @FXML
    Label myText;
    public void setMyText() {
        myText.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        myText.setStyle(" -fx-background-color: lightgrey;" +
                "-fx-background-radius: 15;");
        myText.setPadding(new Insets(5));
        myText.setText(breakingString(demoNewspaper, 20));
    }

    public String breakingString(String inputString, int numberOfCharacterPerLine) {
        char[] charStringTmp = inputString.toCharArray();
        LinkedList<Character> charString = new LinkedList<>();
        // add all character to linkedlist => add breakline
        for (int i = 0; i < inputString.length(); i++) {
            charString.add(charStringTmp[i]);
        }

        // check linked each 20 character => breakline
        int count = 0;
        for (int i = 0; i < charString.size(); i++) {
            if (count == numberOfCharacterPerLine) {
                charString.add(i, '\n');
                count = 0;
            }else if (charString.get(i) == '\n'){
                count = 0;
            }else {
                count += 1;
            }
        }
        // return string
        String s = "";
        for (int i = 0; i < charString.size(); i++) {
            s = s + charString.get(i);
        }
        return s;
    }
}
