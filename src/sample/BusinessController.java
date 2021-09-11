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
  Acknowledgement: Thanks and give credits to the resources that you used in this file
*/
package sample;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.BaseController.ChangingPage;

import java.io.IOException;

public class BusinessController extends ChangingPage {
    public BusinessController() throws IOException {
        super("https://vnexpress.net/kinh-doanh", "https://nhandan.vn/kinhte", "https://tuoitre.vn/kinh-doanh.htm", "https://thanhnien.vn/tai-chinh-kinh-doanh/doanh-nghiep/", "https://zingnews.vn/kinh-doanh-tai-chinh.html");
    }
}
