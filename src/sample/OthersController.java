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

public class OthersController extends ChangingPage {
    public OthersController() throws IOException {
        super("https://vnexpress.net/giao-duc","https://vnexpress.net/du-lich","https://vnexpress.net/doi-song",
                "https://nhandan.vn/vanhoa","https://nhandan.vn/kinhte","https://nhandan.vn/phapluat","https://nhandan.vn/du-lich","https://nhandan.vn/giaoduc",
                "https://tuoitre.vn/xe.htm","https://tuoitre.vn/nhip-song-tre.htm","https://tuoitre.vn/van-hoa.htm","https://tuoitre.vn/giao-duc.htm",
                "https://thanhnien.vn/giao-duc","https://thanhnien.vn/du-lich",
                "https://zingnews.vn/doi-song.html","https://zingnews.vn/tin-tuc-xuat-ban.html","https://zingnews.vn/sach-hay.html");
    }
}

