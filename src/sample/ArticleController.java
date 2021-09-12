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
  Acknowledgement: https://www.youtube.com/watch?v=9XJicRt_FaI&t=5536s
*/
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.BaseController.ChangingCategory;
import sample.NewsObject.News;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController extends ChangingCategory {
    @FXML
    VBox articleBox;

    @FXML
    BorderPane coverPane;

    @FXML
    ScrollPane parent;

//    display error message on the screen
    public void setError() {
        coverPane.prefWidthProperty().bind(parent.widthProperty());
        articleBox.setSpacing(20);
        Label error = new Label();
        error.setText("Error 404: File not found.");
        error.setFont(Font.font("", FontWeight.BOLD, 50));
        error.setWrapText(true);
        articleBox.getChildren().clear();
        articleBox.getChildren().add(error);
    }

    // add content to the page
    public void setContent(News news) throws IOException {
        // set layout
        coverPane.prefWidthProperty().bind(parent.widthProperty());
        articleBox.setSpacing(20);
        articleBox.setPadding(new Insets(10,40,10,20));

        // add title
        Label title = new Label();
        title.setText(news.getTitle());
        title.setFont(Font.font("Roboto", FontWeight.BOLD, 30));
        title.setWrapText(true);
        title.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
        articleBox.getChildren().add(title);

        // add description
        Label description = new Label();
        description.setText(news.getDescription());
        description.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 20));
        description.setWrapText(true);
        description.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
        articleBox.getChildren().add(description);

        // scrape paragraphs and images
        Document doc = Jsoup.connect(news.getNewsURL()).get();
        System.out.println(news.getNewsURL());
        switch (news.getNewsOutlet()) {
            // scrape article from VN express
            case "VN Express": {
                // create String[] to store image URLs
                Elements paragraph = doc.select("p");
                String[] paragraphList = paragraph.toString().split("\n");

                // create String[] to store paragraphs
                Elements figure = doc.select("figure");
                String[] figures = figure.toString().split("</figure>");

                int imgCount = 0;
                for (String para : paragraphList) {
                    Document docScript = Jsoup.parse(para);
                    // add paragraphs to the page
                    if (isParagraph(docScript.text(),news.getDescription())) {
                        setParagraph(docScript.text());
                    }

                    // add images to the page
                    if (!isParagraph(docScript.text(),news.getDescription()) && !docScript.text().contains("Video: ") && !docScript.text().contains("TTO")) {
                        try {
                            Document img = Jsoup.parse(figures[imgCount].replaceAll("\n", "") + "</figure>");
                            String[] imgList = img.select("source").attr("data-srcset").split(" ");
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList[imgCount]));
                            Label photoDescription = new Label(docScript.text());
                            photoDescription.setFont(Font.font("Roboto", FontWeight.NORMAL,FontPosture.ITALIC,15));
                            photoDescription.setWrapText(true);
                            photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            imgCount++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }
                }
                break;
            }

            case "Tuoi Tre": {
                // scrape article from tuoi tre
                int cnt = 0;
                // add all images to imgList
                ArrayList<String> imgList = new ArrayList<>();
                ArrayList<String> desList = new ArrayList<>();
                Elements list = doc.select("div.main-content-body div.VCSortableInPreviewMode");
                for (Element html : list) {
                    String img = html.select("img").attr("src");
                    imgList.add(img);
                }

                for (Element html : list) {
                    String desImg = html.select("div.PhotoCMS_Caption p").text();
                    desList.add(desImg);
                }

                Elements paragraph = doc.select("div.main-content-body p");
                String[] paragraphString = paragraph.toString().split("\n");

                for (String para : paragraphString) {
                    Document docScript = Jsoup.parse(para);
                    //add images
                    if (imgList.size() > 0 && paragraph.text().contains(desList.get(cnt)) && cnt < desList.size() - 1) {
                        try {
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList.get(cnt)));
                            Label photoDescription = new Label(desList.get(cnt));
                            photoDescription.setFont(Font.font("Roboto", FontWeight.NORMAL,FontPosture.ITALIC,15));
                            photoDescription.setWrapText(true);
                            photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            cnt++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }
                    // add paragraph
                        if (!docScript.text().contains("TTO")) {
                            setParagraph(docScript.text());
                        }
                }
                // add author name
                Elements author = doc.select("div.main-content-body div.author");
                articleBox.getChildren().add(new Label(author.text()));
                break;
            }

            case "Zing News": {
                // scrape article from zing news
                // add all images to imgList
                ArrayList<String> imgList = new ArrayList<>();
                List<String> desList = new ArrayList<>();

                // get related articles
                ArrayList<String> relatedNewsList = new ArrayList<>();
                Elements relatedArticleSection = doc.select("div.the-article-body div.inner-article p");
                for (Element el : relatedArticleSection) {
                    String relatedNews = el.text();
                    relatedNewsList.add(relatedNews);
                }

                // get img
                Elements Box = doc.select("table.picture tbody tr td");
                String[] BoxImg = Box.select("td.pic").toString().split("\"");
                for (String box : BoxImg) {
                    if(box.contains("https://")) {
                        imgList.add(box);
                    }
                }

                String[] BoxDes = Box.select("td.pCaption").toString().split("\n");
                for (String box : BoxDes) {
                    Document document = Jsoup.parse(box);
                    String desImg = document.select("p").text();
                    desList.add(desImg);
                }

                int count = 0;
                Elements elements = doc.select("div.the-article-body p");
                String[] paragraphs = elements.toString().split("\n");

                for (String paragraph : paragraphs) {
                    Document docScript = Jsoup.parse(paragraph);

                    if (imgList.size() > 0 && docScript.text().contains(desList.get(count)) && count < desList.size() - 1) {
                        try {
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList.get(count)));
                            photo.setFitHeight(500);
                            photo.setFitWidth(600);
                            photo.setPreserveRatio(true);
                            Label photoDescription = new Label(desList.get(count));
                            photoDescription.setWrapText(true);
                            photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                            viewPhoto.getChildren().addAll(photo,photoDescription);
                            articleBox.getChildren().add(viewPhoto);
                            count++;
                        } catch (Exception ex) {
                            // skipping error
                        }
                    }
                    if (checkZingNewsContent(docScript.text(), relatedNewsList, news.getDescription())) {
                        Label text = new Label();
                        text.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
                        text.setWrapText(true);
                        text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                        text.setText(docScript.text());
                        articleBox.getChildren().add(text);
                    }
                }
                break;
            }

            case "Nhan Dan": {
                //Add photo
                VBox viewPhoto = new VBox();
                Elements photoBox = doc.select("div.box-detail-thumb");
                ImageView photo = new ImageView(new Image(photoBox.select("img").attr("src")));
                Label photoDescription = new Label(photoBox.text());//add photo description
                photoDescription.setFont(Font.font("Roboto", FontWeight.NORMAL,FontPosture.ITALIC,15));
                photoDescription.setWrapText(true);
                photoDescription.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                viewPhoto.setSpacing(0);
                viewPhoto.getChildren().addAll(photo,photoDescription);
                articleBox.getChildren().add(viewPhoto);

                Elements docText = doc.select("div.entry-content div.detail-content-body p");
                for (Element para : docText) {
                    setParagraph(para.text());
                }
                Label author = new Label(doc.select("div.entry-content div.box-author").text());
                author.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
                author.setWrapText(true);
                author.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
                articleBox.getChildren().add(author);
                break;
            }

            case "Thanh Nien": {
                //create 
                List<String> imgList = new ArrayList<>();
                List<String> desList = new ArrayList<>();
                List<String> auList = new ArrayList<>();

                Elements Boxes = doc.select("div#abody div table.imagefull tbody tr td div");

                for (Element Box : Boxes) {
                    String urlImage = Box.select("img").attr("data-src");
                    if (urlImage.length() != 0) imgList.add(urlImage);
                }

                for (Element Box : Boxes.select("div.imgcaption p")) {
                    String imgDes = Box.text();
                    desList.add(imgDes);
                }

                for (int i = 0; i < desList.size(); i++) {
                    if (i % 2 != 0 || desList.get(i).contains("ẢNH: ")) {
                        String author = desList.get(i);
                        auList.add(author);
                    }
                }

                for (int i = 0; i < desList.size(); i++) {
                    if (i % 2 != 0 || desList.get(i).contains("ẢNH: ")) {
                        desList.remove(i);
                    }
                }

                Elements elementsID = doc.select("figure");
                ImageView idPhoto = new ImageView(new Image(elementsID.select("a img").attr("src")));
                idPhoto.setFitHeight(500);
                idPhoto.setFitWidth(600);
                idPhoto.setPreserveRatio(true);
                Label idPhotoDescription = new Label(elementsID.select("a img").attr("alt"));
                idPhotoDescription.setWrapText(true);
                articleBox.getChildren().addAll(idPhoto, idPhotoDescription);

                int cnt = 0;
                Elements elements = doc.select("div#abody div");
                for (Element paragraph : elements) {
                    if (imgList.size() > 0 && paragraph.text().contains(desList.get(cnt)) && cnt < desList.size() - 1) {
                        try {
                            VBox viewPhoto = new VBox();
                            ImageView photo = new ImageView(new Image(imgList.get(cnt)));
                            photo.setFitHeight(500);
                            photo.setFitWidth(600);
                            photo.setPreserveRatio(true);
                            Label photoDescription = new Label(desList.get(cnt));
                            Label author = new Label(auList.get(cnt));
                            photoDescription.setWrapText(true);
                            viewPhoto.getChildren().addAll(photo, photoDescription, author);
                            articleBox.getChildren().add(viewPhoto);
                            cnt++;

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    String redundancyText = paragraph.select("div.imgcaption p").text();
                    if (paragraph.text().contains(redundancyText)) {
                        String mainText = paragraph.text().replace(redundancyText, "");
                        for (String author : auList) {
                            mainText = mainText.replace(author, "");
                        }
                        if (!mainText.equals("")) {
                            setParagraph(mainText);
                        }
                    }
                }
                Elements author = doc.select("div.left h4");
                Label authLabel = new Label(author.text());
                articleBox.getChildren().add(authLabel);
                break;
            }
        }
        Label footer = new Label();
        footer.setText(news.getNewsOutlet() + " - " + news.getNewsTimeDuration());
        articleBox.getChildren().add(footer);
    }

    private void setParagraph(String paragraph) {
        Label text = new Label();
        text.setFont(Font.font("Roboto", FontWeight.NORMAL, 20));
        text.setText(paragraph);
        text.prefWidthProperty().bind(articleBox.widthProperty().divide(3).multiply(2));
        text.setWrapText(true);
        articleBox.getChildren().add(text);
    }

    private boolean isParagraph(String paragraph, String description) {
        String[] condition = {"Ảnh:","Video:","TTO","Ảnh chụp màn hình","Ảnh minh họa:","Ảnh (minh họa):" };
        for (String str : condition) {
            if (paragraph.contains(str)) return false;
        }
        if (paragraph.replaceAll("\\s+","").equalsIgnoreCase(description.replaceAll("\\s+",""))) return false;
        return true;
    }

    private boolean checkZingNewsContent(String text, ArrayList<String> relatedNewsList, String description) {
        for (String str : relatedNewsList) {
            if (text.equals(str)) return false;
        }
        if (!isParagraph(text,description)) return false;
        return true;
    }

    private Scene previousScene;

    public void getPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    // set the previous scene
    public void back(ActionEvent actionEvent) {
        // get the size of the window
        double width = ((Node)actionEvent.getSource()).getScene().getWindow().getWidth();
        double height = ((Node)actionEvent.getSource()).getScene().getWindow().getHeight();

        // get the stage
        stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        // put the previous scene inside the stage
        stage.setScene(this.previousScene);
        // stretch the stage to the current size
        stage.sizeToScene();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.show();
    }
}
