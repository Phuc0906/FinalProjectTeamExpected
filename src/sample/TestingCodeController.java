package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.BaseController.ChangingCategory;
import sample.BaseController.ChangingPage;
import sample.NewsObject.NewsManagement;
import sample.SupportClass.SupportedMethod;

import java.io.IOException;
import java.util.ArrayList;

public class TestingCodeController extends ChangingPage {
    private ArrayList<ImageView> imgList = new ArrayList<>();
    private ArrayList<Label> titleList = new ArrayList<>();
    private ArrayList<Label> descriptionList = new ArrayList<>();
    private ArrayList<Button> pageButtons = new ArrayList<>();

    private SupportedMethod supportedMethod = new SupportedMethod();

    private NewsManagement newsList = new NewsManagement();

    public TestingCodeController() throws IOException {
        super("https://vnexpress.net/kinh-doanh", "https://nhandan.vn/kinhte", "https://tuoitre.vn/kinh-doanh.htm", "https://thanhnien.vn/tai-chinh-kinh-doanh/doanh-nghiep/", "https://zingnews.vn/kinh-doanh-tai-chinh.html");

    }

    public void setArticle(String vnExpressURL, String nhanDanUrl, String tuoiTreURL, String thanhNienURL, String zingURL) throws IOException {
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadTuoiTre(tuoiTreURL);
        newsList.loadThanhNien(thanhNienURL);
        newsList.loadNhanDan(nhanDanUrl);
        newsList.loadZingNews(zingURL);
    }


    @FXML
    VBox articleBox;

    @FXML
    AnchorPane coverPane;

    @FXML
    ScrollPane scrPane;

    @FXML
    Button logo;

    @FXML
    HBox buttonBox;

    public void setComponent() {
        // create a box to store 5 button
        VBox readingBox = new VBox();
        readingBox.setSpacing(5);

        HBox buttonBox = new HBox();
        for (int i = 0; i < 5; i++) {
            Button button = new Button(String.valueOf(i + 1));
            int finalI = i;
            button.setOnAction(e -> {
                switch (finalI) {
                    case 0:
                        setTitle();
                        setDescription();
                        setImgList();
                        break;
                    case 1:
                        setTitle2();
                        setDescription2();
                        setImgList2();
                        break;
                    case 2:
                        setTitle3();
                        setDescription3();
                        setImgList3();
                        break;
                    case 3:
                        setTitle4();
                        setDescription4();
                        setImgList4();
                        break;
                    case 4:
                        setTitle5();
                        setDescription5();
                        setImgList5();
                        break;
                }
            });
            buttonBox.getChildren().add(button);
            pageButtons.add(button);
        }


        coverPane.prefWidthProperty().bind(scrPane.widthProperty());
        articleBox.setSpacing(20);

        logo.prefWidthProperty().bind(articleBox.widthProperty().divide(3));
        logo.prefHeightProperty().bind(scrPane.heightProperty().divide(5));
        logo.setAlignment(Pos.CENTER);
        logo.setStyle("-fx-background-color: lightgreen");

        buttonBox.prefHeightProperty().bind(scrPane.heightProperty().divide(15));
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        // create array for imageview, title, description

        VBox newsListContent = new VBox();
        // initialize page
        for (int i = 0; i < 10; i++) {
            // create a hbox to store image, title, description
            HBox newsBox = new HBox();
            newsBox.setAlignment(Pos.TOP_LEFT);
            newsBox.setSpacing(5);

            ImageView img = new ImageView();
//            img.setImage(new Image("https://i1-vnexpress.vnecdn.net/2021/08/23/top-1629730799-8863-1629731159.jpg?w=680&h=408&q=100&dpr=2&fit=crop&s=XKmQqzXD9eqZnlBt7d6LIg"));

            img.setFitWidth(200);
            img.setFitHeight(150);
            imgList.add(img);

            // create vbox to add title and description
            VBox newsInfo = new VBox();
            newsInfo.setSpacing(5);

            Label title = new Label();
            title.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
            title.prefHeightProperty().bind(img.fitHeightProperty().divide(2));
            title.prefWidthProperty().bind(scrPane.widthProperty());
            title.setWrapText(true);
            title.setStyle("-fx-background-color: lightgreen");
            titleList.add(title);

            Label description = new Label();
            title.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
            title.prefHeightProperty().bind(img.fitHeightProperty().divide(2));
            description.setWrapText(true);
            description.prefWidthProperty().bind(scrPane.widthProperty());
            description.setStyle("-fx-background-color: lightblue");
            descriptionList.add(description);
            newsInfo.getChildren().addAll(title, description);

            newsBox.getChildren().addAll(img, newsInfo);
            newsListContent.getChildren().add(newsBox);
            System.out.println(newsList.getSize());
        }

        articleBox.getChildren().addAll(buttonBox, newsListContent);
    }

}
