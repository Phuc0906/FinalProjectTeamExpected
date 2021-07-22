package NewsApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    public Button btnCovid, btnNews, btnHome;

    @FXML
    public void toCovidPage(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFile/CovidPage.fxml"));
        Parent root = loader.load();

        //get covid page controller
        CovidController covidController = loader.getController();
        covidController.setMyText();
        Stage stage = (Stage) btnCovid.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Hello");
        stage.show();
    }

    @FXML
    public void toNewsPage(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFile/NewsPage.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnNews.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Hello");
        stage.show();
    }
}
