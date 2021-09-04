package sample.NewsObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ScrapeArticle {

    public ScrapeArticle(){
    }

    public ArrayList<String> scrapeArticleLink(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
        Elements articles = document.select("article a");
        ArrayList<String> articleLinks = new ArrayList<>();
        if (articles.size() == 0) {
            articles = document.select("div li.news-item a");
        }

        for (Element link: articles) {
            if (!((articleLinks.contains(link.attr("href"))) && (link.attr("href").length() <= 1)) && !articleLinks.contains((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"))) {
                articleLinks.add((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"));
            }
        }
        return articleLinks;
    }

    public void scrapeArticle(String url) throws IOException {
        // String array for the gioi detection
        String[] countries = new String[]{ "mỹ", "việt nam", "afghanistan", "nhật bản"};

        Document document = Jsoup.connect(url).get();

        // scrape data through meta document
        Elements keyWord = document.select("meta[property=article:tag]");
        String description = document.select("meta[property=og:description]").attr("content");
        String title = document.select("meta[property=og:title]").attr("content");
        String imageURLs = document.select("meta[property=og:image]").attr("content");
        String time = document.select("meta[property=article:published_time]").attr("content");

        // use string category to store the category which article belongs to
        String category = "";
        for (Element keyword: keyWord) {
            // check category
            for (String country: countries) {
                // compare the keyword with country array
                if (keyword.attr("content").toLowerCase().contains(country)) {
                    // if matched => add the gioi to category
                    category += "The gioi ";
                    break;
                }
            }

            // check the rest of the category
            if (keyword.attr("content").toLowerCase().contains("covid") || keyword.attr("content").toLowerCase().contains("ncov")) {
                category += "Covid ";
            }
            if (keyword.attr("content").toLowerCase().contains("cong nghe")) {
                category += "Cong Nghe";
            }

            // print out keywords in meta data
            System.out.println("Article keyword: " + keyword.attr("content"));
        }

        // print out scraped content
        System.out.println("Description: " + description);
        System.out.println("Title: " + title);
        System.out.println("Category: " + category);
        System.out.println("Image url: " + imageURLs);
        System.out.println("Time: " + time);

        //create array listt to store paragraph
        ArrayList<String> paragraphs = new ArrayList<>();

        // scrape all article by selecting p script
        Elements articlesParagraph = document.select("p");
        for (Element element: articlesParagraph) {
            if ((element.attributes().size() == 0) || (element.attr("class").contains("Normal"))) {
                // except vnexpress article has class normal in the p script. otherwise, just come to p script
                paragraphs.add(element.text());
                System.out.println(element.text());
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
                            System.out.println(elements.text());
                            paragraphs.add(elements.text());
                        }
                    }
                }
            }
        }

    }

}
