package sample.SupportClass;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class ScrapeArticle {
//    PrintWriter businessFile;
//    PrintWriter worldFile;
//    PrintWriter entertainmentFile;
//    PrintWriter sportsFile;
//    PrintWriter healthCategory;
//    PrintWriter politicsFile;
//    PrintWriter otherFile;
//    PrintWriter technologyFile;
//    PrintWriter covidFile;
//    PrintWriter newFile;

    PrintWriter vnExpressFile;
    PrintWriter nhanDanFile;
    PrintWriter tuoiTreFile;
    PrintWriter thanhNienFile;
    PrintWriter zingFile;
    public ScrapeArticle() throws FileNotFoundException {
        vnExpressFile = new PrintWriter("src/Documents/vnexpressCategory.txt");
        zingFile = new PrintWriter("src/Documents/zingCategory.txt");
        thanhNienFile = new PrintWriter("src/Documents/thanhNienCategory.txt");
        tuoiTreFile = new PrintWriter("src/Documents/tuoiTreCategory.txt");
        nhanDanFile = new PrintWriter("src/Documents/nhanDanCategory.txt");
    }

    public void setFile(int whichArticle) throws IOException {
        Document scriptReading;
        Elements categoryList;

        switch (whichArticle) {
            case 1:
                // scrape vn express
                scriptReading = Jsoup.connect("https://vnexpress.net").get();
                categoryList = scriptReading.select("ul.parent li");
                for (Element cateLink: categoryList) {
                    if (!cateLink.select("a").attr("href").contains("video") && (cateLink.select("a").attr("href").contains("/"))) {
                        if (!cateLink.select("a").attr("href").contains("http")) {
                            System.out.println("https://vnexpress.net" + cateLink.select("a").attr("href"));
                            vnExpressFile.println("https://vnexpress.net" + cateLink.select("a").attr("href"));
                        }else {
                            System.out.println(cateLink.select("a").attr("href"));
                            vnExpressFile.println(cateLink.select("a").attr("href"));
                        }
                    }

                }
            case 2:
                //scrape nhan dan
                scriptReading = Jsoup.connect("https://nhandan.vn").get();
                categoryList = scriptReading.select("ul.uk-clearfix li");
                for (Element cateLink: categoryList) {
                    if (!cateLink.select("a").attr("href").contains("video") && (cateLink.select("a").attr("href").contains("/"))) {
                        if (!cateLink.select("a").attr("href").contains("http")) {
                            System.out.println("https://nhandan.vn" + cateLink.select("a").attr("href"));
                            nhanDanFile.println("https://nhandan.vn" + cateLink.select("a").attr("href"));
                        }else {
                            System.out.println(cateLink.select("a").attr("href"));
                            nhanDanFile.println(cateLink.select("a").attr("href"));
                        }
                    }

                }
            case 3:
                scriptReading = Jsoup.connect("https://zingnews.vn").get();
                categoryList = scriptReading.select("nav.category-menu ul li");
                for (Element cateLink: categoryList) {
                    if (!cateLink.select("a").attr("href").contains("video") && (cateLink.select("a").attr("href").contains("/"))) {
                        if (!cateLink.select("a").attr("href").contains("http")) {
                            System.out.println("https://zingnews.vn" + cateLink.select("a").attr("href"));
                            zingFile.println("https://zingnews.vn" + cateLink.select("a").attr("href"));
                        }else {
                            System.out.println(cateLink.select("a").attr("href"));
                            zingFile.println(cateLink.select("a").attr("href"));
                        }
                    }

                }
            case 4:
                scriptReading = Jsoup.connect("https://thanhnien.vn").get();
                categoryList = scriptReading.select("ul.site-header__menu li");
                for (Element cateLink: categoryList) {
                    if (!cateLink.select("a").attr("href").contains("video") && (cateLink.select("a").attr("href").contains("/"))) {
                        if (!cateLink.select("a").attr("href").contains("http")) {
                            System.out.println("https://thanhnien.vn" + cateLink.select("a").attr("href"));
                            thanhNienFile.println("https://thanhnien.vn" + cateLink.select("a").attr("href"));
                        }else {
                            System.out.println(cateLink.select("a").attr("href"));
                            thanhNienFile.println(cateLink.select("a").attr("href"));
                        }
                    }
                }
            default:
                scriptReading = Jsoup.connect("https://tuoitre.vn").get();
                categoryList = scriptReading.select("ul.menu-ul li");
                for (Element cateLink: categoryList) {
                    if (!cateLink.select("a").attr("href").contains("video") && (cateLink.select("a").attr("href").contains("/"))) {
                        if (!cateLink.select("a").attr("href").contains("http")) {
                            System.out.println("https://tuoitre.vn" + cateLink.select("a").attr("href"));
                            tuoiTreFile.println("https://tuoitre.vn" + cateLink.select("a").attr("href"));
                        }else {
                            System.out.println(cateLink.select("a").attr("href"));
                            tuoiTreFile.println(cateLink.select("a").attr("href"));
                        }

                    }
                }
        }
    }

    public void closeFile() {
        nhanDanFile.close();
        tuoiTreFile.close();
        thanhNienFile.close();
        zingFile.close();
        vnExpressFile.close();
    }
}
