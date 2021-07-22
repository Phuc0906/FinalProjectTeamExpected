package NewsApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    public Label importantTitle;
    public Label importantContent;
    public void setImportantTitle() {
        importantTitle.setText("This is title for important news");
    }
    public void setImportantContent() {
        importantContent.setText("This is content of important news");
    }

    @FXML
    public Label relatedNews1, relatedNews2, relatedNews3, relatedNews4, relatedNews5;
    public void setRelatedNews1() {
        relatedNews1.setText("Related News 1");
    }
    public void setRelatedNews2() {
        relatedNews2.setText("RelatedNews 2");
    }
    public void setRelatedNews3() {
        relatedNews3.setText("RelatedNews 3");
    }
    public void setRelatedNews4() {
        relatedNews4.setText("RelatedNews 4");
    }
    public void setRelatedNews5() {
        relatedNews5.setText("RelatedNews 5");
    }

}
