/*
  RMIT University Vietnam
  Course: INTE2512 Object-Oriented Programming
  Semester: 2021B
  Assessment: Final Project
  Created  date: 17/7/2021
  Author: Nguyen Hung Anh s3877798
    Hoang Phuc s3879362
    Le Tan Phong s3877819
    Thai Thuan s3877024
  Last modified date: 11/9/2021
  Acknowledgement: https://www.youtube.com/watch?v=9XJicRt_FaI&t=5536s
*/
package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    //Method change Light <-> Dark mode
    @FXML
    public void changeMode() {
        isLightMode = !isLightMode;
        if (isLightMode) setLightMode();
        else setDarkMode();
    }

    //Set style light mode for stage
    public void setLightMode() {
        parent.getStylesheets().remove("fileCss/styleDarkMode.css");
        parent.getStylesheets().add("fileCss/styleLightMode.css");
        btnMode.setText("Dark");
    }

    //Set style dark mode for stage
    public void setDarkMode() {
        parent.getStylesheets().remove("fileCss/styleLightMode.css");
        parent.getStylesheets().add("fileCss/styleDarkMode.css");
        btnMode.setText("Light");
    }

    //Sync mode with previous page when navigate to new page
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!isLightMode) setDarkMode();
    }

    public void toNewPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/NewPage.fxml"));
        root = loader.load();

        // set up content of the category
        NewController newController = loader.getController();
        newController.setTitle();
        newController.setDescription();
        newController.setImgList();
        newController.setTime();
        newController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }


    public void toCovidPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/CovidPage.fxml"));
        root = loader.load();

        // set up content of the category
        CovidController covidController = loader.getController();
        covidController.setTitle();
        covidController.setDescription();
        covidController.setImgList();
        covidController.setTime();
        covidController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/PoliticsPage.fxml"));
        root = loader.load();

        // set up content of the category
        PoliticsController politicsController = loader.getController();
        politicsController.setTitle();
        politicsController.setDescription();
        politicsController.setImgList();
        politicsController.setTime();
        politicsController.setOutlet();


        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }


    // Set the action event for each category button

    // Action event for business page
    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        // Load the fxml page and load it to the Parent class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/BusinessPage.fxml"));
        root = loader.load();

        // set up content of the category

        // Load the page controller of business page and then set the component to perform the category
        BusinessController businessController = loader.getController();
        businessController.setImgList();
        businessController.setTitle();
        businessController.setDescription();
        businessController.setTime();
        businessController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toTechPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/TechPage.fxml"));
        root = loader.load();

        // set up content of the category
        TechController techController = loader.getController();
        techController.setImgList();
        techController.setTitle();
        techController.setDescription();
        techController.setTime();
        techController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toHealthPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/HealthPage.fxml"));
        root = loader.load();

        // set up content of the category
        HealthController healthController = loader.getController();
        healthController.setImgList();
        healthController.setTitle();
        healthController.setDescription();
        healthController.setTime();
        healthController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/SportsPage.fxml"));
        root = loader.load();

        // set up content of the category
        SportController sportController = loader.getController();
        sportController.setImgList();
        sportController.setTitle();
        sportController.setDescription();
        sportController.setTime();
        sportController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/EntertainmentPage.fxml"));
        root = loader.load();

        // set up content of the category
        EntertainmentController entertainmentController = loader.getController();
        entertainmentController.setTitle();
        entertainmentController.setDescription();
        entertainmentController.setImgList();
        entertainmentController.setTime();
        entertainmentController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/WorldPage.fxml"));
        root = loader.load();

        // set up content of the category
        WorldController worldController = loader.getController();
        worldController.setImgList();
        worldController.setTitle();
        worldController.setDescription();
        worldController.setTime();
        worldController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }

    public void toOthersPage(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/OthersPage.fxml"));
        root = loader.load();

        // set up content of the category
        OthersController othersController = loader.getController();
        othersController.setImgList();
        othersController.setTitle();
        othersController.setDescription();
        othersController.setTime();
        othersController.setOutlet();

        // get current stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // using same scene to stay in the same window size
        stage.getScene().setRoot(root);
        stage.show();
    }
}
