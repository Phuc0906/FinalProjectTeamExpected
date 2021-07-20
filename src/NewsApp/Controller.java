package NewsApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {
    @FXML
    private Button btnCovid, btnNews;
    @FXML
    public void toCovidScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CovidPage.fxml"));
        Stage stage = (Stage) btnCovid.getScene().getWindow();
        Scene scene = new Scene(root, 1500, 1000);
        stage.setScene(scene);
    }

    @FXML
    public void toNewsScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("NewsPage.fxml"));
        Stage stage = (Stage) btnNews.getScene().getWindow();
        Scene scene = new Scene(root, 1500, 1000);
        stage.setScene(scene);
    }
}
