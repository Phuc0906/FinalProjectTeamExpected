package sample.SupportClass;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sample.NewsObject.NewsManagement;

import javax.print.Doc;
import javax.sound.sampled.Line;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class SupportedMethod {
    PrintWriter techFile;
    PrintWriter businessFile;
    PrintWriter worldFile;
    PrintWriter sportsFile;
    PrintWriter othersFile;
    PrintWriter entertainmentFile;
    PrintWriter politicsFile;
    PrintWriter healthFile;
    PrintWriter covidFile;
    PrintWriter newFile;

    //-----------------
    File techFileRead;
    File businessFileRead;
    File worldFileRead;
    File sportsFileRead;
    File othersFileRead;
    File entertainmentFileRead;
    File politicsFileRead;
    File healthFileRead;
    File covidFileRead;
    File newFileRead;



    public void setFile() throws FileNotFoundException {
        techFile = new PrintWriter("src/sample/Document/technology.txt");
        worldFile = new PrintWriter("src/sample/Document/world.txt");
        sportsFile = new PrintWriter("src/sample/Document/sports.txt");
        othersFile = new PrintWriter("src/sample/Document/others.txt");
        entertainmentFile = new PrintWriter("src/sample/Document/entertainment.txt");
        politicsFile = new PrintWriter("src/sample/Document/politics.txt");
        healthFile = new PrintWriter("src/sample/Document/health.txt");
        covidFile = new PrintWriter("src/sample/Document/covid.txt");
        newFile = new PrintWriter("src/sample/Document/new.txt");
        businessFile = new PrintWriter("src/sample/Document/business.txt");
    }

    public void setFileArticle() throws FileNotFoundException {
        techFile = new PrintWriter("src/sample/Document/ArticleLink/technology.txt");
        worldFile = new PrintWriter("src/sample/Document/ArticleLink/world.txt");
        sportsFile = new PrintWriter("src/sample/Document/ArticleLink/sports.txt");
        othersFile = new PrintWriter("src/sample/Document/ArticleLink/others.txt");
        entertainmentFile = new PrintWriter("src/sample/Document/ArticleLink/entertainment.txt");
        politicsFile = new PrintWriter("src/sample/Document/ArticleLink/politics.txt");
        healthFile = new PrintWriter("src/sample/Document/ArticleLink/health.txt");
        covidFile = new PrintWriter("src/sample/Document/ArticleLink/covid.txt");
        newFile = new PrintWriter("src/sample/Document/ArticleLink/new.txt");
        businessFile = new PrintWriter("src/sample/Document/ArticleLink/business.txt");
    }

    public void setReadCategory() {
        techFileRead = new File("src/sample/Document/technology.txt");
        worldFileRead = new File("src/sample/Document/world.txt");
        sportsFileRead = new File("src/sample/Document/sports.txt");
        othersFileRead = new File("src/sample/Document/others.txt");
        entertainmentFileRead = new File("src/sample/Document/entertainment.txt");
        politicsFileRead = new File("src/sample/Document/politics.txt");
        healthFileRead = new File("src/sample/Document/health.txt");
        covidFileRead = new File("src/sample/Document/covid.txt");
        newFileRead = new File("src/sample/Document/new.txt");
        businessFileRead = new File("src/sample/Document/business.txt");
    }

    public void closeFile() {
        techFile.close();
        entertainmentFile.close();
        othersFile.close();
        worldFile.close();
        sportsFile.close();
        politicsFile.close();
        healthFile.close();
        covidFile.close();
        newFile.close();
        businessFile.close();
    }

    public String breakingString(String inputString, int numberOfWordPerLine) {
        // split string into individual words
        String[] splittedString = inputString.split("\\s");
        LinkedList<String> paragraphStructure = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < splittedString.length; i++) {
            if (count == numberOfWordPerLine) {
                paragraphStructure.add("\n");
                count = 0;
            }
            paragraphStructure.add(splittedString[i] + " ");
            count++;
        }

        String completedParagraph = "";
        for (int i = 0; i < paragraphStructure.size(); i++) {
            completedParagraph += paragraphStructure.get(i);
        }
        return completedParagraph;
    }

    public void setTitleList(ArrayList<Label> labelList, int begin, NewsManagement newsList, ArrayList<ImageView> imgLayouts, AnchorPane coverPane) {
        int count = begin;
        for (Label title : labelList) {
            title.setFont(Font.font("Time New Roman", FontWeight.BOLD, 30));
            title.setAlignment(Pos.TOP_LEFT);
            title.prefHeightProperty().bind(imgLayouts.get(0).fitHeightProperty().divide(2));
            title.prefWidthProperty().bind(coverPane.widthProperty().subtract(imgLayouts.get(0).fitWidthProperty()));
            title.setWrapText(true);
            title.setText(newsList.getNews(count).getTitle());
            count++;
        }
    }

    public void setDescriptionList(ArrayList<Label> labelList, int begin, NewsManagement newsList, ArrayList<ImageView> imgLayouts, AnchorPane coverPane) {
        int count = begin;
        for (Label description : labelList) {
            description.setFont(Font.font("Time New Roman", FontWeight.BOLD, 20));
            description.setAlignment(Pos.TOP_LEFT);
            description.setWrapText(true);
            description.setText(newsList.getNews(count).getDescription());
            description.prefHeightProperty().bind(imgLayouts.get(0).fitHeightProperty().divide(2));
            description.prefWidthProperty().bind(coverPane.widthProperty().subtract(imgLayouts.get(0).fitWidthProperty()).multiply(3.5).divide(5));
            count++;
        }
    }

    public void setImgList(ArrayList<ImageView> imgList, int begin, NewsManagement newsList, AnchorPane coverPane) {
        int count = begin;
        for (ImageView img : imgList) {
            img.setImage(new Image(newsList.getNews(count).getImageURL()));
            img.fitHeightProperty().bind(coverPane.heightProperty().divide(5));
            img.autosize();
            count++;
        }
    }

    int covidCount = 0;
    public void scrapeArticleLink(int whichFile) throws IOException {
        File readFile;
        PrintWriter writerFile;
        switch (whichFile) {
            case 1:
                readFile = politicsFileRead;
                writerFile = politicsFile;
                break;
            case 2:
                readFile = sportsFileRead;
                writerFile = sportsFile;
                break;
            case 3:
                readFile = techFileRead;
                writerFile = techFile;
                break;
            case 4:
                readFile = worldFileRead;
                writerFile = worldFile;
                break;
            case 5:
                readFile = businessFileRead;
                writerFile = businessFile;
                break;
            case 6:
                readFile = healthFileRead;
                writerFile = healthFile;
                break;
            case 7:
                readFile = othersFileRead;
                writerFile = othersFile;
                break;
            default:
                readFile = entertainmentFileRead;
                writerFile = entertainmentFile;
        }

        int countArticle = 0;
        Scanner categoryRead = new Scanner(readFile);
        String readURL;
        String url;
        while (categoryRead.hasNextLine()) {
            readURL = categoryRead.nextLine();
            Document document = Jsoup.connect(readURL).get();

            if (readURL.toLowerCase().contains("vnexpress")) {
                url = "https://vnexpress.net";
            }else if (readURL.toLowerCase().contains("zingnew")) {
                url = "https://zingnews.vn";
            }else if (readURL.contains("thanhnien")) {
                url = "https://thanhnien.vn";
            }else if (readURL.contains("tuoitre")) {
                url = "https://tuoitre.vn";
            }else {
                url = "https://nhandan.vn/";
            }

            Elements articles = document.select("article a");
            ArrayList<String> articleLinks = new ArrayList<>();
            if (articles.size() == 0) {
                articles = document.select("div li.news-item a");
            }

            for (Element link : articles) {
                if (!((articleLinks.contains(link.attr("href"))) && (link.attr("href").length() <= 1)) && !articleLinks.contains((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"))) {
                    String addedString = (link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href");
                    if (articleLinks.size() > 1) {
                        if (addedString.contains(articleLinks.get(articleLinks.size() - 1))) {
                            continue;
                        }
                    }
                    articleLinks.add(addedString);

                    writerFile.println(addedString);
                    countArticle++;
                    if (countArticle == 50) { return; }
                    if (addedString.contains("covid") && (covidCount < 50)) {
                        covidFile.println(addedString);
                        covidCount++;
                    }
                }
            }
        }
    }

    public void scrapeCategory(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        boolean isScrape = false;
        Elements links = document.select("ul");
        Element ulContent;
        ArrayList<String> categoryLinks = new ArrayList<>();
        for (Element link : links) {
            if (link.attr("class").contains("parent")) {
                ulContent = link;
                isScrape = true;
                Document ulScript = Jsoup.parse(ulContent.toString());
                for (Element cateLink : ulScript.select("li")) {
                    if (cateLink.select("a").attr("href").contains("/")) {
                        if (!(cateLink.select("a").attr("href").length() <= 1) && !(cateLink.select("a").attr("href").contains("video"))) {
                            if (!cateLink.select("a").attr("href").contains("http")) {
                                categoryLinks.add(url + cateLink.select("a").attr("href"));
                            } else {
                                categoryLinks.add(cateLink.select("a").attr("href"));
                            }
                        }
                    }
                }
                break;
            }
        }

        if (!isScrape) {
            Elements menu = links;
            for (Element ulLink: links) {
                if (ulLink.select("li").attr("class").contains("parent") || ulLink.select("li").attr("class").contains("menu") || ulLink.select("li a").attr("class").isEmpty()) {
                    menu = ulLink.select("li");
                    break;
                }
            }
            Document menuScrape = Jsoup.parse(menu.toString());
            for (Element menuLink: menuScrape.select("li")) {
                if (!(menuLink.select("a").attr("href").length() <= 1)) {
                    if (!menuLink.select("a").attr("href").contains("http")) {
                        categoryLinks.add(url + menuLink.select("a").attr("href"));
                    }else {
                        categoryLinks.add(menuLink.select("a").attr("href"));
                    }
                }
            }
        }

        for (String link: categoryLinks) {
            if (link.contains("the-gioi")) {
                worldFile.println(link);
            }else if (link.contains("the-thao")) {
                sportsFile.println(link);
            }else if (link.contains("so-hoa") || link.contains("cong-nghe")) {
                techFile.println(link);
            }else if (link.contains("kinh-doanh")) {
                businessFile.println(link);
            }else if (link.contains("thoi-su")) {
                politicsFile.println(link);
            }else if (link.contains("suc-khoe")){
                healthFile.println(link);
            }else if (link.contains("giai-tri")) {
                entertainmentFile.println(link);
            }else {
                othersFile.println(link);
            }
            System.out.println("Category link: " + link);
        }
    }

    public void scrapeArticle(String url) throws IOException {

        // String array for the gioi detection
        String[] countries = new String[]{ "mỹ", "việt nam", "afghanistan", "nhật bản"};
        Document document = Jsoup.connect(url).get();

        // check keywords at the beginning if there is any article do not have keyword => ignore
        // use string category to store the category which article belongs to
        String keywords = "";
        Elements keyWord = document.select("meta[property=article:tag]");
        for (Element keyword: keyWord) {
            // check category
            for (String country: countries) {
                // compare the keyword with country array
                if (keyword.attr("content").toLowerCase().contains(country)) {
                    break;
                }
            }
            keywords += keyword.attr("content").toLowerCase() + "";
        }
        if (keywords.isEmpty()) {
            return; // interrupt method
        }


        // scrape data through meta document
        String description = document.select("meta[property=og:description]").attr("content");
        String title = document.select("meta[property=og:title]").attr("content");
        String imageURLs = document.select("meta[property=og:image]").attr("content");
        String time = document.select("meta[itemprop=datePublished]").attr("content");
        if (time.isEmpty()) {
            time = document.select("div.box-date").text();
        }




//        //create array listt to store paragraph
//        ArrayList<String> paragraphs = new ArrayList<>();
//
//        // scrape all article by selecting p script
//        Elements articlesParagraph = document.select("p");
//        for (Element element: articlesParagraph) {
//            if ((element.attributes().size() == 0) || (element.attr("class").contains("Normal"))) {
//                // except vnexpress article has class normal in the p script. otherwise, just come to p script
//                paragraphs.add(element.text());
//            }
//        }
//
//        if (paragraphs.size() < 5) {
//            // if the paragraph list is less than 5 items => that is not a article paragraph => clear the paragraph list and re-scraping
//            paragraphs.clear();
//        }
//
//        //the last case is thanh nien article. It will execute thanh nien scraping
//        if (paragraphs.size() == 0) {
//
//            // only thanh nien article wrap paragraph in div script
//            articlesParagraph = document.select("div");
//            for (Element elements: articlesParagraph) {
//                if (elements.attr("class").length() == 0) {
//                    if (elements.attributes().size() == 0) {
//                        if (!(elements.text().length() == 0)) {
//                            paragraphs.add(elements.text());
//                        }
//                    }
//                }
//            }
//        }


    }
}