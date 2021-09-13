package sample.LoadingPage;

import javafx.concurrent.Task;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sample.NewsObject.News;
import sample.NewsObject.NewsManagement;
import sample.NewsObject.Time;

import java.time.LocalDate;
import java.util.ArrayList;

public class GetSortingTimeList extends Task<ArrayList<Time>> {
    private NewsManagement newsList;

    public GetSortingTimeList(NewsManagement newsList) {
        this.newsList = newsList;
    }

    @Override
    protected ArrayList<Time> call() throws Exception {
        int count = 0;
        System.out.println("Start");
        ArrayList<Time> timesList = new ArrayList<>();
        for (int i = 0; i < this.newsList.getSize(); i++) {
            updateProgress(i, newsList.getSize());
            try {
                Document newsDocument = Jsoup.connect(newsList.getNews(i).getNewsURL()).timeout(200).get();
                String time = newsDocument.select("meta[itemprop=datePublished]").attr("content");
                if (time.isEmpty()) {
                    time = newsDocument.select("div.box-date").text();
                }

                if (time.isEmpty()) {
                    time = newsDocument.select("meta[property=article:published_time]").attr("content");
                }

                Time newsTime = splittedTime(time, newsList.getNews(i));
                if (!time.isEmpty() && (newsTime != null)) {
                    timesList.add(newsTime);
                    count++;
                    if (count == 50) break;
                }
            } catch (Exception ex) {
                // skip it
            }
        }


        // sorting time list
        for (int i = 0; i < timesList.size() - 1; i++) {
            for (int k = i; k < timesList.size(); k++) {
                if (timesList.get(i).getMonth() < timesList.get(k).getMonth()) {
                    Time tmp = new Time(timesList.get(k));
                    timesList.set(k, timesList.get(i));
                    timesList.set(i, tmp);
                } else if (timesList.get(i).getMonth() == timesList.get(k).getMonth()) {
                    if (timesList.get(i).getDay() < timesList.get(k).getDay()) {
                        Time tmp = new Time(timesList.get(k));
                        timesList.set(k, timesList.get(i));
                        timesList.set(i, tmp);
                    } else if (timesList.get(i).getDay() == timesList.get(k).getDay()) {
                        if (timesList.get(i).getHour() < timesList.get(k).getHour()) {
                            Time tmp = new Time(timesList.get(k));
                            timesList.set(k, timesList.get(i));
                            timesList.set(i, tmp);
                        } else if (timesList.get(i).getHour() == timesList.get(k).getHour()) {
                            if (timesList.get(i).getMinute() < timesList.get(k).getMinute()) {
                                Time tmp = new Time(timesList.get(k));
                                timesList.set(k, timesList.get(i));
                                timesList.set(i, tmp);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Done...");
        return timesList;
    }


    private Time splittedTime(String time, News news) {
        LocalDate currentDate = LocalDate.now();
        boolean dateQualified = false;
        boolean timeQualified = false;

        String publishedTime;
        String date = time.split("T")[0];
        int hour = 0;
        int minutes = 0;
        if (date.isEmpty()) {
            date = time.split(",")[1].replaceAll(" ", "");
            publishedTime = time.split(" ")[2];
        } else {
            publishedTime = time.split("T")[1].split("[+]")[0];
        }
        if ((publishedTime.length() == 8) || (publishedTime.length() == 5)) {
            hour = Integer.parseInt(publishedTime.split(":")[0]);
            minutes = Integer.parseInt(publishedTime.split(":")[1]);
            timeQualified = true;
        }


        int day = 0;
        int month = 0;
        int articleDuration = 0;
        // days scraping
        if (date.length() == 10) {
            if (date.split("-")[0].length() == 4) {
                day = Integer.parseInt(date.split("-")[2]);
            } else {
                day = Integer.parseInt(date.split("-")[4]);
            }
            month = Integer.parseInt(date.split("-")[1]);

            dateQualified = true;
        }

        if (dateQualified && timeQualified && !news.getImageURL().isEmpty()) {
            return new Time(month, day, hour, minutes, news);
        }
        return null;
    }
}
