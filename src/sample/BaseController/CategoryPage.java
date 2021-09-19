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

package sample.BaseController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import sample.ArticleController;
import sample.NewsObject.News;
import sample.NewsObject.NewsManagement;
import sample.NewsObject.Time;
import sample.SupportClass.SupportedMethod;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

// Create the changing class, so that all the category controller can inherit because all the category page have the same consumption of methods and attributes
public class CategoryPage extends ChangingCategory implements Initializable {

    @FXML
    AnchorPane coverPane;

    @FXML
    ScrollPane parent;


    @FXML
    ArrayList<ImageView> imgList;

    @FXML
    ArrayList<Label> titleList;

    @FXML
    ArrayList<Label> descriptionList;

    @FXML
    ArrayList<Label> timeList;

    @FXML
    ArrayList<Label> outletList;

    // Create atrributes for the class
    private Stage stage;
    private Scene scene;
    private Parent root;

    // Declare the supportedMethod class to consume some method for setting component
    private SupportedMethod supportedMethod = new SupportedMethod();

    // Declare a newList to store the news' brief content and its url for scraping detail
    private NewsManagement newsList = new NewsManagement() ;

    // the default constructor for the other category, if that category is not related into any category, it will be load at other
    public CategoryPage(String vnExpressURL1, String vnExpressURL2, String vnExpressURL3,
                        String nhanDanUrl1, String nhanDanUrl2, String nhanDanUrl3, String nhanDanUrl4, String nhanDanUrl5,
                        String tuoiTreURL1, String tuoiTreURL2, String tuoiTreURL3, String tuoiTreURL4,
                        String thanhNienURL1, String thanhNienURL2,
                        String zingURL1, String zingURL2, String zingURL3) throws IOException {

        // each outlet has its own loading method
        newsList.loadVnExpress(vnExpressURL1);
        newsList.loadVnExpress(vnExpressURL2);
        newsList.loadVnExpress(vnExpressURL3);

        newsList.loadNhanDan(nhanDanUrl1);
        newsList.loadNhanDan(nhanDanUrl2);
        newsList.loadNhanDan(nhanDanUrl3);
        newsList.loadNhanDan(nhanDanUrl4);
        newsList.loadNhanDan(nhanDanUrl5);

        newsList.loadTuoiTre(tuoiTreURL1);
        newsList.loadTuoiTre(tuoiTreURL2);
        newsList.loadTuoiTre(tuoiTreURL3);
        newsList.loadTuoiTre(tuoiTreURL4);

        newsList.loadThanhNien(thanhNienURL1);
        newsList.loadThanhNien(thanhNienURL2);

        newsList.loadZingNews(zingURL1);
        newsList.loadZingNews(zingURL2);
        newsList.loadZingNews(zingURL3);

        // After loading news the system will sort it from the earliest to latest news
        sortNewsList();
    }

    // Use for loading each category of each newspaper
    public CategoryPage(String vnExpressURL, String nhanDanUrl, String tuoiTreURL, String thanhNienURL, String zingURL) throws IOException {
        // each outlet has its own loading method
        newsList.loadVnExpress(vnExpressURL);
        newsList.loadTuoiTre(tuoiTreURL);
        newsList.loadThanhNien(thanhNienURL);
        newsList.loadNhanDan(nhanDanUrl);
        newsList.loadZingNews(zingURL);

        // After loading news the system will sort it from the earliest to latest news
        sortNewsList();
    }


    // use for splitting the time in the article time box
    private Time splittedTime(String time, News news) {
        // create 2 variable to check if that article has time or not, if not -> ignore it
        boolean dateQualified = false;
        boolean timeQualified = false;

        // Create the variable to get the fully published time
        String publishedTime;

        // Scraping the date and split it first
        String date = time.split("T")[0];
        int hour = 0;
        int minutes = 0;
        // there are 2 types of date published in the article so if the date variable is empty, the program will get at differance strategy
        if (date.isEmpty()) {
            date = time.split(",")[1].replaceAll(" ", "");
            publishedTime = time.split(" ")[2];
        } else {
            publishedTime = time.split("T")[1].split("[+]")[0];
        }

        // there are 2 types of time, which thier length is 5 and 8, the program will check if it matches these length of not
        if ((publishedTime.length() == 8) || (publishedTime.length() == 5)) {
            hour = Integer.parseInt(publishedTime.split(":")[0]);
            minutes = Integer.parseInt(publishedTime.split(":")[1]);

            // if the time is fully scrape it will be qualified to set
            timeQualified = true;
        }

        // This step is for scraping the date
        int day = 0;
        int month = 0;
        // days scraping

        // for all date's length must be 10, otherwise it will be unqualified
        if (date.length() == 10) {

            // Splitting day, month algorithm
            if (date.split("-")[0].length() == 4) {
                day = Integer.parseInt(date.split("-")[2]);
            } else {
                day = Integer.parseInt(date.split("-")[4]);
            }
            month = Integer.parseInt(date.split("-")[1]);

            // after successfully scraping, it will be qualified
            dateQualified = true;
        }

        // if the article's time and date is qualified the system will get it for showing out
        if (dateQualified && timeQualified && !news.getImageURL().isEmpty()) {
            return new Time(month, day, hour, minutes, news);
        }

        return null;

    }



    private void sortNewsList() {
        // create the count to count the qualified article
        int count = 0;

        // create the time list to store qualified time
        ArrayList<Time> timesList = new ArrayList<>();
        for (int i = 0; i < this.newsList.getSize(); i++) {
            try {
                // access to the article url
                Document newsDocument = Jsoup.connect(newsList.getNews(i).getNewsURL()).timeout(200).get();

                // scrape the time from the article meta, if this path does not have -> use the other path
                String time = newsDocument.select("meta[itemprop=datePublished]").attr("content");
                if (time.isEmpty()) {
                    time = newsDocument.select("div.box-date").text();
                }

                if (time.isEmpty()) {
                    time = newsDocument.select("meta[property=article:published_time]").attr("content");
                }

                // Call the Time class to store the splitted time
                Time newsTime = splittedTime(time, newsList.getNews(i));

                // check if the time is empty or the newsTime is not qualified -> ignore it
                if (!time.isEmpty() && (newsTime != null)) {
                    // add to the newsList
                    timesList.add(newsTime);
                    count++;

                    // if the system scrapped 50 article it will stop because of the time limited
                    if (count == 50) break;
                }
            } catch (Exception ex) {
                // skip it
            }
        }


        // sorting time list using selection sort
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

        // re-write newlist
        newsList.clearList();

        // use local date class to compute the time duration of article
        LocalDate localDate = LocalDate.now();
        Calendar calendar = new GregorianCalendar();
        String timeDuration = "";
        String datePublished;
        for (Time time : timesList) {
            if (localDate.getMonthValue() - time.getMonth() == 0) {
                if (localDate.getDayOfMonth() - time.getDay() == 0) {
                    if (calendar.get(Calendar.HOUR_OF_DAY) - time.getHour() == 0) {
                        if (calendar.get(Calendar.MINUTE) - time.getMinute() == 0) {
                            timeDuration = "Just now";
                        } else {
                            timeDuration = (calendar.get(Calendar.MINUTE) - time.getMinute()) + " minutes ago";
                        }
                    } else {
                        timeDuration = (calendar.get(Calendar.HOUR_OF_DAY) - time.getHour()) + " hours ago";
                    }
                } else {
                    timeDuration = (localDate.getDayOfMonth() - time.getDay()) + " days ago";
                }
            } else {
                timeDuration = (localDate.getMonthValue() - time.getMonth()) + " months ago";
            }
            datePublished = time.getDay() + "/" + time.getMonth() + "/" + localDate.getYear() + " - " + time.getHour() + ":" + time.getMinute();
            newsList.addContent(time.getNews(), datePublished, timeDuration);
        }
    }

    // set images for 1,2,3,4,5 button
    public void setImgList() {
        new SupportedMethod().setImgList(imgList, 0, this.newsList);
    }

    public void setImgList2() {
        supportedMethod.setImgList(imgList, 10, this.newsList);
    }

    public void setImgList3() {
        supportedMethod.setImgList(imgList, 20, this.newsList);
    }

    public void setImgList4() {
        supportedMethod.setImgList(imgList, 30, this.newsList);
    }

    public void setImgList5() {
        supportedMethod.setImgList(imgList, 40, this.newsList);
    }

    // set description for 1,2,3,4,5 button
    public void setDescription() {
        supportedMethod.setDescriptionList(descriptionList, 0, this.newsList);
    }

    public void setDescription2() {
        supportedMethod.setDescriptionList(descriptionList, 10, this.newsList);
    }

    public void setDescription3() {
        supportedMethod.setDescriptionList(descriptionList, 20, this.newsList);
    }

    public void setDescription4() {
        supportedMethod.setDescriptionList(descriptionList, 30, this.newsList);
    }

    public void setDescription5() {
        supportedMethod.setDescriptionList(descriptionList, 40, this.newsList);
    }

    // set title for 1,2,3,4,5 button
    public void setTitle() {
        supportedMethod.setTitleList(titleList, 0, this.newsList);
    }

    public void setTitle2() {
        supportedMethod.setTitleList(titleList, 10, this.newsList);
    }

    public void setTitle3() {
        supportedMethod.setTitleList(titleList, 20, this.newsList);
    }

    public void setTitle4() {
        supportedMethod.setTitleList(titleList, 30, this.newsList);
    }

    public void setTitle5() {
        supportedMethod.setTitleList(titleList, 40, this.newsList);
    }

    public void setTime() {
        supportedMethod.setTimeList(timeList, 0, this.newsList);
    }

    public void setTime2() {
        supportedMethod.setTimeList(timeList, 10, this.newsList);
    }

    public void setTime3() {
        supportedMethod.setTimeList(timeList, 20, this.newsList);
    }

    public void setTime4() {
        supportedMethod.setTimeList(timeList, 30, this.newsList);
    }

    public void setTime5() {
        supportedMethod.setTimeList(timeList, 40, this.newsList);
    }

    public void setOutlet() {
        supportedMethod.setOutletList(outletList, 0, this.newsList);
    }

    public void setOutlet2() {
        supportedMethod.setOutletList(outletList, 10, this.newsList);
    }

    public void setOutlet3() {
        supportedMethod.setOutletList(outletList, 20, this.newsList);
    }

    public void setOutlet4() {supportedMethod.setOutletList(outletList, 30, this.newsList); }

    public void setOutlet5() {
        supportedMethod.setOutletList(outletList, 40, this.newsList);
    }

    // button 1 event - go to first page of the category
    public void moveToPage1(ActionEvent event) throws IOException {
        // set content of 10 latest news
        setImgList();
        setDescription();
        setTitle();
        setTime();
        setOutlet();
        parent.setVvalue(0);
    }

    // button 2 event - go to second page of the category
    public void moveToPage2(ActionEvent event) throws IOException {
        // set content for page 2
        setImgList2();
        setDescription2();
        setTitle2();
        setTime2();
        setOutlet2();
        parent.setVvalue(0);
    }

    // button 3 event - go to third page of the category
    public void moveToPage3(ActionEvent event) throws IOException {
        // set content for page 3
        setImgList3();
        setDescription3();
        setTitle3();
        setTime3();
        setOutlet3();
        parent.setVvalue(0);
    }

    // button 4 event - go to fourth page of the category
    public void moveToPage4(ActionEvent event) throws IOException {
        // set content for page 4
        setImgList4();
        setDescription4();
        setTitle4();
        setTime4();
        setOutlet4();
        parent.setVvalue(0);
    }

    // button 5 event - go to fifth page of the category
    public void moveToPage5(ActionEvent event) throws IOException {
        // set content for page 5
        setImgList5();
        setDescription5();
        setTitle5();
        setTime5();
        setOutlet5();
        parent.setVvalue(0);
    }

    // Display the article
    public void toArticle1(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        // get the size of the previous window
        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        // get the controller
        ArticleController articleController = loader.getController();

        // pass this scene article controller to set this scene when using back button.
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        // pass the news to setContent method in articleController
        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(0).getText()));
        } catch (Exception ex) {
            // set error scene
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // set the scene to previous window size
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        // prevent the stage from getting smaller them the scene
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle2(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        // get the size of the previous window
        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        // pass this scene article controller to set this scene when using back button.
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        // pass the news to setContent method in articleController
        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(1).getText()));
        } catch (Exception ex) {
            // set error scene
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // set the scene to previous window size
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        // prevent the stage from getting smaller them the scene
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle3(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        // get the size of the previous window
        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        // pass this scene article controller to set this scene when using back button.
        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        // pass the news to setContent method in articleController
        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(2).getText()));
        } catch (Exception ex) {
            // set error scene
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // set the scene to previous window size
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        // prevent the stage from getting smaller them the scene
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle4(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(3).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle5(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(4).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle6(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(5).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle7(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(6).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle8(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(7).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle9(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(8).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public void toArticle10(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pageFXML/Article.fxml"));
        root = loader.load();

        double width = ((Node) event.getSource()).getScene().getWindow().getWidth();
        double height = ((Node) event.getSource()).getScene().getWindow().getHeight();

        ArticleController articleController = loader.getController();

        Node node = (Node) event.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        Scene thisScene = thisStage.getScene();
        articleController.getPreviousScene(thisScene);

        try {
            articleController.setContent(this.newsList.searchTitle(titleList.get(9).getText()));
        } catch (Exception ex) {
            articleController.setError();
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}
