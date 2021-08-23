package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import sample.BaseController.ChangingCategory;
import sample.NewsObject.News;

public class ArticleController extends ChangingCategory {
    @FXML
    VBox articleBox;

    public void setArticleBox(News newsList, String title) {

    }
}
