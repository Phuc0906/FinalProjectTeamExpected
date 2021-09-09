package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import sample.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangingCategory implements Initializable {
    public Stage stage;
    private Parent root;

    @FXML
    private ScrollPane parent;

    @FXML
    private Button btnMode;

    protected static boolean isLightMode = true;

    @FXML
    public void changeMode() {
        isLightMode = !isLightMode;
        if (isLightMode) setLightMode();
        else setDarkMode();
    }


    public void setLightMode() {
        parent.getStylesheets().remove("fileCss/styleDarkMode.css");
        parent.getStylesheets().add("fileCss/styleLightMode.css");
        btnMode.setText("Dark");
    }

    public void setDarkMode() {
        parent.getStylesheets().remove("fileCss/styleLightMode.css");
        parent.getStylesheets().add("fileCss/styleDarkMode.css");
        btnMode.setText("Light");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!isLightMode) setDarkMode();
    }

    public void toNewPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
        root = loader.load();

        NewController newController = loader.getController();
        newController.setTitle();
        newController.setDescription();
        newController.setImgList();
        newController.setTime();
        newController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }


    public void toCovidPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/CovidPage.fxml"));
        root = loader.load();

        CovidController covidController = loader.getController();
        covidController.setTitle();
        covidController.setDescription();
        covidController.setImgList();
        covidController.setTime();
        covidController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/PoliticsPage.fxml"));
        root = loader.load();

        PoliticsController politicsController = loader.getController();
        politicsController.setTitle();
        politicsController.setDescription();
        politicsController.setImgList();
        politicsController.setTime();
        politicsController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/BusinessPage.fxml"));
        root = loader.load();

        BusinessController businessController = loader.getController();
        businessController.setImgList();
        businessController.setTitle();
        businessController.setDescription();
        businessController.setTime();
        businessController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toTechPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/TechPage.fxml"));
        root = loader.load();

        TechController techController = loader.getController();
        techController.setImgList();
        techController.setTitle();
        techController.setDescription();
        techController.setTime();
        techController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toHealthPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/HealthPage.fxml"));
        root = loader.load();

        HealthController healthController = loader.getController();
        healthController.setImgList();
        healthController.setTitle();
        healthController.setDescription();
        healthController.setTime();
        healthController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/SportsPage.fxml"));
        root = loader.load();

        SportController sportController = loader.getController();
        sportController.setImgList();
        sportController.setTitle();
        sportController.setDescription();
        sportController.setTime();
        sportController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentPage.fxml"));
        root = loader.load();

        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.setTitle();
        entertainmentController.setDescription();
        entertainmentController.setImgList();
        entertainmentController.setTime();
        entertainmentController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();

        WorldController worldController = loader.getController();
        worldController.setImgList();
        worldController.setTitle();
        worldController.setDescription();
        worldController.setTime();
        worldController.setOutlet();

        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toOthersPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/OthersPage.fxml"));
        root = loader.load();

        OthersController othersController = loader.getController();
        othersController.setImgList();
        othersController.setTitle();
        othersController.setDescription();
        othersController.setTime();
        othersController.setOutlet();
        
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.getScene().setRoot(root);
        stage.show();
    }
}
