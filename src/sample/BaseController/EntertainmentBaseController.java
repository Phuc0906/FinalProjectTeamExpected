package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.*;
import sample.NewsObject.NewsManagement;

import java.io.IOException;

public class EntertainmentBaseController extends ChangingCategory{


    private NewsManagement newsList;

    public NewsManagement loadWeb(String tuoiTreURL, String nhanDanURL, String thanhNienURL, String vnExpressURL) throws IOException {
        newsList = new NewsManagement();
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadTuoiTre(tuoiTreURL);
        newsList.loadThanhNien(thanhNienURL);
        newsList.loadNhanDan( nhanDanURL);
        System.out.println("DONE SCRAPING");

        return newsList;
    }

    public NewsManagement loadWeb(NewsManagement newsList) {
        this.newsList = newsList;
        return newsList;
    }



}
