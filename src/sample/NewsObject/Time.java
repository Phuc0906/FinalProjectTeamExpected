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

package sample.NewsObject;

public class Time {
    private int month;
    private int day;
    private int hour;
    private int minute;
    private News news;

    public Time(Time copyTime) {
        this.month = copyTime.getMonth();
        this.day = copyTime.getDay();
        this.hour = copyTime.getHour();
        this.minute = copyTime.getMinute();
        this.news = copyTime.getNews();
    }

    public Time(int month, int day, int hour, int minute, News news) {
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.news = news;
    }

    public News getNews() {
        return this.news;
    }


    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
