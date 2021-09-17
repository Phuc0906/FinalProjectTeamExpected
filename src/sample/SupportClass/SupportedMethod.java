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
package sample.SupportClass;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.NewsObject.NewsManagement;
import java.util.ArrayList;

public class SupportedMethod {

    public void setTitleList(ArrayList<Label> labelList, int begin, NewsManagement newsList){
        int count = begin;
        for (Label title: labelList) {
            title.setFont(Font.font("Time New Roman", FontWeight.BOLD, 25));
            title.setAlignment(Pos.TOP_LEFT);
            title.setWrapText(true);
            title.setText(newsList.getNews(count).getTitle());
            count++;
        }
    }

    public void setDescriptionList(ArrayList<Label> labelList, int begin, NewsManagement newsList){
        int count = begin;
        for (Label description: labelList) {
            description.setFont(Font.font("Time New Roman", FontWeight.NORMAL, 15));
            description.setAlignment(Pos.TOP_LEFT);
            description.setWrapText(true);
            description.setText(newsList.getNews(count).getDescription());
            count++;
        }
    }

    public void setImgList(ArrayList<ImageView> imgList, int begin, NewsManagement newsList){
        int count = begin;
        for (ImageView img: imgList) {
            try {
                img.setImage(new Image(newsList.getNews(count).getImageURL()));
                img.autosize();
                count++;
            } catch (Exception exception) {
            }
        }
    }

    public void setTimeList(ArrayList<Label> timeList, int begin, NewsManagement newsList) {
        int count = begin;
        for (Label timeLabel : timeList) {
            timeLabel.setText(newsList.getNews(count).getNewsTimeDuration());
            count++;
        }
    }

    public void setOutletList(ArrayList<Label> outletList, int begin, NewsManagement newsList) {
        int count = begin;
        for (Label outletLabel : outletList) {
            outletLabel.setText(newsList.getNews(count).getNewsOutlet());
            count++;
        }
    }

}
