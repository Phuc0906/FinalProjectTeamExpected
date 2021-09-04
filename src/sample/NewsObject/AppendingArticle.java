package sample.NewsObject;

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

public class AppendingArticle {
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

    public AppendingArticle() throws IOException {
        long start = System.currentTimeMillis();
        System.out.println("Start...");
        ExecutorService ex = Executors.newCachedThreadPool();

        ex.execute(new ThreadScraping("https://vnexpress.net", 1));
        ex.execute(new ThreadScraping("https://zingnews.vn", 2));
        ex.execute(new ThreadScraping("https://nhandan.vn", 3));
        ex.execute(new ThreadScraping("https://tuoitre.vn", 4));
        ex.execute(new ThreadScraping("https://thanhnien.vn", 5));
        ex.shutdown();

        while (!ex.isTerminated()) {

        }

        System.out.println("End...");
        System.out.println("Number of vnExpress articles: " + vnExpressArticleLink.size());
        System.out.println("Number of zing articles: " + zingArticleLink.size());
        System.out.println("Number of nhan dan articles: " + nhanDanArticleLink.size());
        System.out.println("Number of tuoi tre articles: " + tuoiTreArticleLink.size());
        System.out.println("Number of thanh nien articles: " + thanhNienArticleLink.size());
        System.out.println("Time: " + (System.currentTimeMillis() - start)/1000 + " seconds");
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
                System.out.println("Cannot open file");
            }

            for (String link: categoryLinks) {
                try {
                    scrapeArticle.scrapeArticleLink(link, writer);
                } catch (IOException e) {
                    System.out.println("Error link: " + link);
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
                int countErrorLink = 1;
                while (fileReader.hasNextLine()) {
                    categoryURL = fileReader.nextLine();
                    if (scrapeArticle.checkInterrupted()) {
                        break;
                    }
                    try {
                        scrapeArticle.scrapeArticle(categoryURL);
                    }catch (IOException ex) {
                        System.out.println("Error link detected: " + count + " article + case: " + whichArticle);
                        countErrorLink++;
                    }
                    if (countErrorLink > 20) {
                        break;
                    }
                    count++;
                }
            } catch (FileNotFoundException e) {
                System.out.println("File open error");

            }
            System.out.println("Done scraping..." + whichArticle);
        }
    }
}
