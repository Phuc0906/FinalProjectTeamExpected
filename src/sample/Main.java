package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.TopPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: white");

        // set title area
        borderPane.setTop(new TopPane(borderPane,"Expected Time Newspaper"));

        borderPane.setLeft(new LeftPane(borderPane));

        CenterPane centerPane = new CenterPane();
        centerPane.addContent(new ImportantNews(borderPane, "https://i1-vnexpress.vnecdn.net/2021/07/17/tiem-vc-tai-fpt-jpeg-9352-1624-3177-6484-1626530579.jpg?w=680&h=408&q=100&dpr=2&fit=crop&s=L2cBvRc9Lluu-XDXY9zwiw"));
        // set content area
        borderPane.setCenter(centerPane);

        Scene scene = new Scene(borderPane, 1500, 1300);
        primaryStage.setTitle("Demo Web Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Pane rmitLogos(Pane mainPane) {
        Pane pane = new Pane();

        // set pane style
        pane.setPadding(new Insets(10, 10, 10 ,10));

        // import image from website to the newspaper
        Image image = new Image("https://www.rmit.edu.vn/content/dam/rmit/vn/en/assets-for-production/images/rmit-logo/rmit-logo-transparentbg-square.jpg");
        ImageView imageView = new ImageView(image);
        imageView.fitWidthProperty().bind(mainPane.widthProperty().divide(7));
        imageView.fitHeightProperty().bind(mainPane.heightProperty().divide(5));
        pane.getChildren().add(imageView);

        return pane;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
