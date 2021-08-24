package sample.BaseController;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sample.ArticleController;
import sample.NewController;
import sample.NewsObject.NewsManagement;
import sample.SupportClass.SupportedMethod;

import java.io.IOException;
import java.util.ArrayList;

public class ChangingPage extends ChangingCategory{

    ScrollPane scrPane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private SupportedMethod supportedMethod = new SupportedMethod();
    private NewsManagement newsList;

    @FXML
    AnchorPane coverPane;



    public ChangingPage(String vnExpressURL1, String vnExpressURL2, String vnExpressURL3,
                        String nhanDanUrl1, String nhanDanUrl2, String nhanDanUrl3, String nhanDanUrl4, String nhanDanUrl5,
                        String tuoiTreURL1, String tuoiTreURL2, String tuoiTreURL3, String tuoiTreURL4,
                        String thanhNienURL1, String thanhNienURL2,
                        String zingURL1, String zingURL2, String zingURL3) throws IOException {
        newsList = new NewsManagement();

        newsList.loadVnExpress(vnExpressURL1);
        newsList.loadVnExpress(vnExpressURL2);
        newsList.loadVnExpress(vnExpressURL3);

        newsList.loadNhanDan(nhanDanUrl1);
        newsList.loadNhanDan(nhanDanUrl2);
        newsList.loadNhanDan(nhanDanUrl3);
        newsList.loadNhanDan(nhanDanUrl4);
        newsList.loadNhanDan(nhanDanUrl5);

        newsList.loadTuoiTre(tuoiTreURL1);
        newsList.loadTuoiTre(tuoiTreURL2);
        newsList.loadTuoiTre(tuoiTreURL3);
        newsList.loadTuoiTre(tuoiTreURL4);

        newsList.loadThanhNien(thanhNienURL1);
        newsList.loadThanhNien(thanhNienURL2);

        newsList.loadZingNews(zingURL1);
        newsList.loadZingNews(zingURL2);
        newsList.loadZingNews(zingURL3);
    }

    public ChangingPage(String vnExpressURL, String nhanDanUrl, String tuoiTreURL, String thanhNienURL, String zingURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadTuoiTre(tuoiTreURL);
        newsList.loadThanhNien(thanhNienURL);
        newsList.loadNhanDan(nhanDanUrl);
        newsList.loadZingNews(zingURL);
    }

    public ChangingPage(String zingURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadZingNews(zingURL);
    }

    public ChangingPage(String vnExpressURL, String nhanDanUrl, String tuoiTreURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadNhanDan(nhanDanUrl);
        newsList.loadTuoiTre(tuoiTreURL);
    }

    public void setCovidThanhNien(String thanhNienURL) throws  IOException{
        newsList.covidThanhNien(thanhNienURL);
    }



    private VBox _articleBox = new VBox();

    private ArrayList<ImageView> imgList = new ArrayList<>();
    private ArrayList<Label> titleList = new ArrayList<>();
    private ArrayList<Label> descriptionList = new ArrayList<>();
    private ArrayList<Button> pageButtons = new ArrayList<>();

    public void setComponent(ScrollPane scrPane) {
        this.scrPane = scrPane;
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
        _articleBox.setSpacing(20);

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

        _articleBox.getChildren().addAll(buttonBox, newsListContent);
    }

    public void setImgList() {
        new SupportedMethod().setImgList(imgList, 0, this.newsList, scrPane);
    }
    public void setImgList2() { supportedMethod.setImgList(imgList, 10, this.newsList, scrPane); }
    public void setImgList3() { supportedMethod.setImgList(imgList, 20, this.newsList, scrPane); }
    public void setImgList4() { supportedMethod.setImgList(imgList, 30, this.newsList, scrPane); }
    public void setImgList5() { supportedMethod.setImgList(imgList, 40, this.newsList, scrPane); }

    public void setDescription() {
        supportedMethod.setDescriptionList(descriptionList, 0, this.newsList, imgList, scrPane);
    }
    public void setDescription2() {
        supportedMethod.setDescriptionList(descriptionList, 10, this.newsList, imgList, scrPane);
    }
    public void setDescription3() {
        supportedMethod.setDescriptionList(descriptionList, 20, this.newsList, imgList, scrPane);
    }
    public void setDescription4() {
        supportedMethod.setDescriptionList(descriptionList, 30, this.newsList, imgList, scrPane);
    }
    public void setDescription5() {
        supportedMethod.setDescriptionList(descriptionList, 40, this.newsList, imgList, scrPane);
    }


    public void setTitle() {
        new SupportedMethod().setTitleList(titleList, 0, this.newsList, imgList, scrPane);
    }
    public void setTitle2() {
        supportedMethod.setTitleList(titleList, 10, this.newsList, imgList, scrPane);
    }
    public void setTitle3() { supportedMethod.setTitleList(titleList, 20, this.newsList, imgList, scrPane); }
    public void setTitle4() {
        supportedMethod.setTitleList(titleList, 30, this.newsList, imgList, scrPane);
    }
    public void setTitle5() {
        supportedMethod.setTitleList(titleList, 40, this.newsList, imgList, scrPane);
    }



    public void toArticle1(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        ArticleController newController = loader.getController();
        newController.setArticleBox(this.newsList.searchTitle(titleList.get(0).getText()));

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public VBox getArticleBox() {
        return this._articleBox;
    }

}
