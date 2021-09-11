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

public class NewController extends ChangingPage {
    public NewController () throws IOException {
        super("https://vnexpress.net", "https://nhandan.vn", "https://tuoitre.vn", "https://thanhnien.vn", "https://zingnews.vn");
    }
}
