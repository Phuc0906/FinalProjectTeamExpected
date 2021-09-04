package sample.NewsObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppendingArticle {
    private static ScrapeArticle scrapeArticle = new ScrapeArticle();
    private static ArrayList<String> vnExpressArticleLink = new ArrayList<>();
    private static ArrayList<String> zingArticleLink = new ArrayList<>();
    private static ArrayList<String> nhanDanArticleLink = new ArrayList<>();
    private static ArrayList<String> thanhNienArticleLink = new ArrayList<>();
    private static ArrayList<String> tuoiTreArticleLink = new ArrayList<>();

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
    }

    private static class ThreadScraping implements Runnable{
        private final ArrayList<String> categoryLinks = new ArrayList<>();
        private final int whichArticle;

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
            for (String link: categoryLinks) {
                try {
                    switch (this.whichArticle) {
                        case 1:
                            vnExpressArticleLink.addAll(scrapeArticle.scrapeArticleLink(link));
                            break;
                        case 2:
                            zingArticleLink.addAll(scrapeArticle.scrapeArticleLink(link));
                            break;
                        case 3:
                            nhanDanArticleLink.addAll(scrapeArticle.scrapeArticleLink(link));
                            break;
                        case 4:
                            tuoiTreArticleLink.addAll(scrapeArticle.scrapeArticleLink(link));
                            break;
                        case 5:
                            thanhNienArticleLink.addAll(scrapeArticle.scrapeArticleLink(link));
                            break;
                    }
                }catch (IOException ex) {
                    System.out.println("Error link: " + link);
                }
            }
        }
    }
}
