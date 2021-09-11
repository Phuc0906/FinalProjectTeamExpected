package sample.LoadingPage;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sample.NewsObject.News;
import sample.NewsObject.NewsManagement;
import sample.NewsObject.Time;

import java.time.LocalDate;
import java.util.ArrayList;

public class SortingTime extends Service<ArrayList<Time>> {
    NewsManagement newsList;
    public SortingTime(NewsManagement newsList) {
        this.newsList = newsList;
    }

    @Override
    protected Task<ArrayList<Time>> createTask() {
        return new GetSortingTimeList(newsList);
    }


}
