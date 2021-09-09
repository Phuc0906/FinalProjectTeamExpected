package sample.SupportClass;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ScrapeArticle {
    private static PrintWriter covidFile;
    int covidCount = 0;

    static {
        try {

            covidFile = new PrintWriter("src/sample/Document/covid.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Cannot write file");
        }
    }

    public ScrapeArticle(){
    }

    public ArrayList<String> scrapeArticleLink(String url, PrintWriter categorySource) throws IOException {
        Document document = Jsoup.connect(url).timeout(500).get();
        Elements articles = document.select("article a");
        ArrayList<String> articleLinks = new ArrayList<>();
        if (articles.size() == 0) {
            articles = document.select("div li.news-item a");
        }

        for (Element link: articles) {
            if (!((articleLinks.contains(link.attr("href"))) && (link.attr("href").length() <= 1)) && !articleLinks.contains((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"))) {
                String addedString = (link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href");
                if (articleLinks.size() > 1) {
                    if (addedString.contains(articleLinks.get(articleLinks.size() - 1))) {
                        continue;
                    }
                }
                articleLinks.add(addedString);
                categorySource.println(addedString);
            }
        }
        return articleLinks;
    }

    public void closeFile() {

        covidFile.close();
    }

    public boolean checkInterrupted() {
        return (covidCount == 50);
    }

    public void scrapeArticle(String url) throws IOException {

        // String array for the gioi detection
        String[] countries = new String[]{ "mỹ", "việt nam", "afghanistan", "nhật bản"};
        Document document = Jsoup.connect(url).timeout(200).get();

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
        String newsURL = url;
        String time = document.select("meta[itemprop=datePublished]").attr("content");
        if (time.isEmpty()) {
            time = document.select("div.box-date").text();
        }

        if (!(keywords.isEmpty() || imageURLs.isEmpty() || time.isEmpty() || description.isEmpty())) {
            if (keywords.contains("covid")) {
                if (covidCount == 50) {
                    return;
                }
                covidFile.println(title);
                covidFile.println(description);
                covidFile.println(newsURL);
                covidFile.println(imageURLs);
                covidFile.println("breakline-------------------------------------------------------");
                covidCount++;
            }
        }


        //create array listt to store paragraph
        ArrayList<String> paragraphs = new ArrayList<>();

        // scrape all article by selecting p script
        Elements articlesParagraph = document.select("p");
        for (Element element: articlesParagraph) {
            if ((element.attributes().size() == 0) || (element.attr("class").contains("Normal"))) {
                // except vnexpress article has class normal in the p script. otherwise, just come to p script
                paragraphs.add(element.text());
            }
        }

        if (paragraphs.size() < 5) {
            // if the paragraph list is less than 5 items => that is not a article paragraph => clear the paragraph list and re-scraping
            paragraphs.clear();
        }

        //the last case is thanh nien article. It will execute thanh nien scraping
        if (paragraphs.size() == 0) {

            // only thanh nien article wrap paragraph in div script
            articlesParagraph = document.select("div");
            for (Element elements: articlesParagraph) {
                if (elements.attr("class").length() == 0) {
                    if (elements.attributes().size() == 0) {
                        if (!(elements.text().length() == 0)) {
                            paragraphs.add(elements.text());
                        }
                    }
                }
            }
        }


    }
}
