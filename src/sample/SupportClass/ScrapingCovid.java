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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScrapingCovid {
    private static ScrapeArticle scrapeArticle = new ScrapeArticle();
    private static ArrayList<String> vnExpressArticleLink = new ArrayList<>();
    private static ArrayList<String> zingArticleLink = new ArrayList<>();
    private static ArrayList<String> nhanDanArticleLink = new ArrayList<>();
    private static ArrayList<String> thanhNienArticleLink = new ArrayList<>();
    private static ArrayList<String> tuoiTreArticleLink = new ArrayList<>();
    private static ArrayList<String> totalLink = new ArrayList<>();

    private static PrintWriter categorySource;

    static {
        try {
            categorySource = new PrintWriter("src/sample/Document/categoryLinks.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ScrapingCovid() throws IOException {
        ExecutorService ex = Executors.newCachedThreadPool();

        ex.execute(new ThreadScraping("https://vnexpress.net", 1));
        ex.execute(new ThreadScraping("https://zingnews.vn", 2));
        ex.execute(new ThreadScraping("https://nhandan.vn", 3));
        ex.execute(new ThreadScraping("https://tuoitre.vn", 4));
        ex.execute(new ThreadScraping("https://thanhnien.vn", 5));
        ex.shutdown();

        while (!ex.isTerminated()) {

        }


        scrapeArticle.closeFile();
        categorySource.close();
    }

    private static class ThreadScraping implements Runnable{
        private final ArrayList<String> categoryLinks = new ArrayList<>();
        private final int whichArticle;
        private final String nhanDanTxt = "src/sample/Document/nhanDancategoryLinks.txt";
        private final String vnExpressTxt = "src/sample/Document/vnExpresscategoryLinks.txt";
        private final String tuoiTreTxt = "src/sample/Document/tuoiTrecategoryLinks.txt";
        private final String thanhNientxt = "src/sample/Document/thanhNiencategoryLinks.txt";
        private final String zingTxt = "src/sample/Document/categoryLinks.txt";

        public ThreadScraping(String newspaperLink, int whichArticle) throws IOException {
            scrapeCategory(newspaperLink);
            this.whichArticle = whichArticle;
        }

        public void scrapeCategory(String url) throws IOException {
            Document document = Jsoup.connect(url).get();
            boolean isScrape = false;
            Elements links = document.select("ul");
            Element ulContent;
            for (Element link: links) {
                if (link.attr("class").contains("parent")) {
                    ulContent = link;
                    isScrape = true;
                    Document ulScript = Jsoup.parse(ulContent.toString());
                    for (Element cateLink: ulScript.select("li")) {
                        if (cateLink.select("a").attr("href").contains("/")) {
                            if (!(cateLink.select("a").attr("href").length() <= 1) && !(cateLink.select("a").attr("href").contains("video"))) {
                                if (!cateLink.select("a").attr("href").contains("http")) {
                                    categoryLinks.add(url + cateLink.select("a").attr("href"));
                                }else {
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
        }

        @Override
        public void run() {
            PrintWriter writer = null;
            int countArticle = 0;
            try {
                switch (whichArticle) {
                    case 1:
                        writer  = new PrintWriter(vnExpressTxt);
                        break;
                    case 2:
                        writer  = new PrintWriter(tuoiTreTxt);
                        break;
                    case 3:
                        writer  = new PrintWriter(nhanDanTxt);
                        break;
                    case 4:
                        writer  = new PrintWriter(zingTxt);
                        break;
                    default:
                        writer  = new PrintWriter(thanhNientxt);
                        break;
                }
            }catch (IOException ex) {
                // pass
            }

            for (String link: categoryLinks) {
                try {
                    scrapeArticle.scrapeArticleLink(link, writer);
                    countArticle++;
                } catch (IOException e) {
                    //pass
                }
            }
            writer.close();

            File categoryReader;
            switch (whichArticle) {
                case 1:
                    categoryReader  = new File(vnExpressTxt);
                    break;
                case 2:
                    categoryReader  = new File(tuoiTreTxt);
                    break;
                case 3:
                    categoryReader  = new File(nhanDanTxt);
                    break;
                case 4:
                    categoryReader  = new File(zingTxt);
                    break;
                default:
                    categoryReader  = new File(thanhNientxt);
                    break;
            }
            try {
                Scanner fileReader = new Scanner(categoryReader);
                String categoryURL;
                int count = 1;
                while (fileReader.hasNextLine()) {
                    categoryURL = fileReader.nextLine();
                    if (scrapeArticle.checkInterrupted()) {
                        break;
                    }
                    try {
                        scrapeArticle.scrapeArticle(categoryURL);
                    }catch (IOException ex) {
                        //pass
                    }
                    count++;
                }
            } catch (FileNotFoundException e) {
                //pass
            }

        }
    }
}
