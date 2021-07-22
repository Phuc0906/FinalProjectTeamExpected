package NewsApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLFile/HomePage.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFile/HomePage.fxml"));
        Parent root = loader.load();

        Controller homePage = loader.getController();
        homePage.setImportantTitle();
        homePage.setImportantContent();
        homePage.setRelatedNews1();
        homePage.setRelatedNews2();
        homePage.setRelatedNews3();
        homePage.setRelatedNews4();
        homePage.setRelatedNews5();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1500, 1300));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}