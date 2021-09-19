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
  Last modified date: 19/9/2021
  Acknowledgement:
  https://www.youtube.com/watch?v=9XJicRt_FaI&t=5536s
  https://youtu.be/f06uUtkmtDE
  https://youtu.be/o-lAsVuskKI
  https://www.tutorialspoint.com/java/index.htm
  http://tutorials.jenkov.com/javafx/index.html
*/

package sample;

import sample.BaseController.CategoryPage;

import java.io.IOException;

public class BusinessController extends CategoryPage {
    public BusinessController() throws IOException {
        super("https://vnexpress.net/kinh-doanh", "https://nhandan.vn/kinhte", "https://tuoitre.vn/kinh-doanh.htm", "https://thanhnien.vn/tai-chinh-kinh-doanh/doanh-nghiep/", "https://zingnews.vn/kinh-doanh-tai-chinh.html");
    }
}
