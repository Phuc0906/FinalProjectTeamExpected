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


import sample.BaseController.CategoryPage;

import java.io.IOException;


public class HealthController extends CategoryPage {

    public HealthController() throws IOException {
        super("https://vnexpress.net/suc-khoe", "https://nhandan.vn/y-te", "https://tuoitre.vn/suc-khoe.htm", "https://thanhnien.vn/suc-khoe/", "https://zingnews.vn/suc-khoe.html");
    }
}
