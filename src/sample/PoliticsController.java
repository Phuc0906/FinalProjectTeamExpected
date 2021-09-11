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

import sample.BaseController.ChangingPage;

import java.io.IOException;

public class PoliticsController extends ChangingPage {
    public PoliticsController() throws IOException {
        super("https://vnexpress.net/phap-luat", "https://nhandan.vn/chinhtri", "https://tuoitre.vn/phap-luat.htm", "https://thanhnien.vn/thoi-su/", "https://zingnews.vn/chinh-tri.html");
    }
}
