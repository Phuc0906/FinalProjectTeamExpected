package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.*;

import java.io.IOException;

public class ChangingCategory {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    VBox articleBox;

    @FXML
    ScrollPane scrPane;

    public void toNewPage(ActionEvent actionEvent) throws IOException {
        NewController newController = new NewController();
        newController.setComponent(scrPane);
        newController.setTitle();
        newController.setDescription();
        newController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(newController.getArticleBox());
    }


    public void toCovidPage(ActionEvent actionEvent) throws IOException {
        CovidController covidController = new CovidController();
        covidController.setComponent(scrPane);
        covidController.setTitle();
        covidController.setDescription();
        covidController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(covidController.getArticleBox());
    }

    public void toPoliticsPage(ActionEvent actionEvent) throws IOException {
        PoliticsController politicsController = new PoliticsController();
        politicsController.setComponent(scrPane);
        politicsController.setTitle();
        politicsController.setDescription();
        politicsController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(politicsController.getArticleBox());
    }

    public void toBusinessPage(ActionEvent actionEvent) throws IOException {
        BusinessController businessController = new BusinessController();
        businessController.setComponent(scrPane);
        businessController.setImgList();
        businessController.setTitle();
        businessController.setDescription();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(businessController.getArticleBox());
        System.out.println("PASS");
    }

    public void toTechPage(ActionEvent actionEvent) throws IOException {
        TechController techController = new TechController();
        techController.setComponent(scrPane);
        techController.setTitle();
        techController.setDescription();
        techController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(techController.getArticleBox());
    }

    public void toHealthPage(ActionEvent actionEvent) throws IOException {
        HealthController healthController = new HealthController();
        healthController.setComponent(scrPane);
        healthController.setTitle();
        healthController.setDescription();
        healthController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(healthController.getArticleBox());
    }

    public void toSportsPage(ActionEvent actionEvent) throws IOException {
        SportController sportController = new SportController();
        sportController.setComponent(scrPane);
        sportController.setTitle();
        sportController.setDescription();
        sportController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(sportController.getArticleBox());
    }

    public void toEntertainmentPage(ActionEvent actionEvent) throws IOException {
        EntertainmentController entertainmentController = new EntertainmentController();
        entertainmentController.setComponent(scrPane);
        entertainmentController.setTitle();
        entertainmentController.setDescription();
        entertainmentController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(entertainmentController.getArticleBox());
    }

    public void toWorldPage(ActionEvent actionEvent) throws IOException {
        WorldController worldController = new WorldController();
        worldController.setComponent(scrPane);
        worldController.setTitle();
        worldController.setDescription();
        worldController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(worldController.getArticleBox());
    }

    public void toOthersPage(ActionEvent actionEvent) throws IOException {
        OthersController othersController = new OthersController();
        othersController.setComponent(scrPane);
        othersController.setTitle();
        othersController.setDescription();
        othersController.setImgList();
        articleBox.getChildren().clear();
        articleBox.getChildren().add(othersController.getArticleBox());
    }
}
