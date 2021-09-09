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
