package sample.NewsObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ScrapeArticle {
    // creating 10 text file represent for 10 category
    private static PrintWriter businessFile;
    private static PrintWriter entertainmentFile;
    private static PrintWriter healthFile;
    private static PrintWriter newFile;
    private static PrintWriter othersFile;
    private static PrintWriter politicsFile;
    private static PrintWriter sportsFile;
    private static PrintWriter technologyFile;
    private static PrintWriter worldFile;
    private static PrintWriter covidFile;
    int covidCount = 0;
    int businessCount = 0;
    int healthCount = 0;
    int newCount = 0;
    int othersCount = 0;
    int politicsCount = 0;
    int sportCount = 0;
    int technologyCount = 0;
    int entertainmentCount = 0;
    int worldCount = 0;

    static {
        try {
            businessFile = new PrintWriter("src/sample/Document/business.txt");
            entertainmentFile = new PrintWriter("src/sample/Document/entertainment.txt");
            healthFile = new PrintWriter("src/sample/Document/health.txt");
            newFile = new PrintWriter("src/sample/Document/new.txt");
            othersFile = new PrintWriter("src/sample/Document/others.txt");
            politicsFile = new PrintWriter("src/sample/Document/politics.txt");
            sportsFile = new PrintWriter("src/sample/Document/sports.txt");
            technologyFile = new PrintWriter("src/sample/Document/technology.txt");
            worldFile = new PrintWriter("src/sample/Document/world.txt");
            covidFile = new PrintWriter("src/sample/Document/covid.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Cannot write file");
        }
    }

    public ScrapeArticle(){
    }

    public ArrayList<String> scrapeArticleLink(String url, PrintWriter categorySource) throws IOException {
        Document document = Jsoup.connect(url).get();
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
                categorySource.println((link.attr("href").contains("http")) ? link.attr("href") : url + link.attr("href"));
            }
        }
        return articleLinks;
    }

    public void closeFile() {
        businessFile.close();
        entertainmentFile.close();
        healthFile.close();
        newFile.close();
        othersFile.close();
        politicsFile.close();
        sportsFile.close();
        technologyFile.close();
        worldFile.close();
        covidFile.close();
    }

    public boolean checkInterrupted() {
        return ((covidCount == 50) &&
                (worldCount == 50) &&
                (politicsCount == 50) &&
                (sportCount == 50) &&
                (othersCount == 50) &&
                (healthCount == 50) &&
                (entertainmentCount == 50) &&
                (technologyCount == 50) &&
                (businessCount == 50));
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

        if (!(keywords.isEmpty() || imageURLs.isEmpty() || time.isEmpty() || description.isEmpty())) {
            // write title at the beginning
            if (keywords.contains("thế giới")) {
                if (worldCount == 50) {
                    return;
                }
                worldFile.println(title);
                worldFile.println(description);
                worldFile.println(imageURLs);
                worldFile.println(time);
                worldFile.println("breakline-------------------------------------------------------");
                worldCount++;
            }
            if (keywords.contains("covid")) {
                if (covidCount == 50) {
                    return;
                }
                covidFile.println(title);
                covidFile.println(description);
                covidFile.println(imageURLs);
                covidFile.println(time);
                covidFile.println("breakline-------------------------------------------------------");
                covidCount++;
            }
            if (keywords.contains("giải trí")) {
                if (entertainmentCount == 50) {
                    System.out.println("Entertainment: " + entertainmentCount);
                    return;
                }
                entertainmentFile.println(title);
                entertainmentFile.println(description);
                entertainmentFile.println(imageURLs);
                entertainmentFile.println(time);
                entertainmentFile.println("breakline-------------------------------------------------------");
                entertainmentCount++;
            }
            if (keywords.contains("thể thao")) {
                if (sportCount == 50) {
                    System.out.println("Sport: " + sportCount);
                    return;
                }
                sportsFile.println(title);
                sportsFile.println(description);
                sportsFile.println(imageURLs);
                sportsFile.println(time);
                sportsFile.println("breakline-------------------------------------------------------");
                sportCount++;
            }
            if (keywords.contains("chính trị")) {
                if (politicsCount == 50) {
                    System.out.println("Politics: " + politicsCount);
                    return;
                }
                politicsFile.println(title);
                politicsFile.println(description);
                politicsFile.println(imageURLs);
                politicsFile.println(time);
                politicsFile.println("breakline-------------------------------------------------------");
                politicsCount++;
            }
            if (keywords.contains("công nghệ")) {
                if (technologyCount == 50) {
                    System.out.println("Tech: " + technologyCount);
                    return;
                }
                technologyFile.println(title);
                technologyFile.println(description);
                technologyFile.println(imageURLs);
                technologyFile.println(time);
                technologyFile.println("breakline-------------------------------------------------------");
                technologyCount++;
            }
            if (keywords.contains("kinh doanh")) {
                if (businessCount == 50) {
                    System.out.println("Business: " + businessCount);
                    return;
                }
                businessFile.println(title);
                businessFile.println(description);
                businessFile.println(imageURLs);
                businessFile.println(time);
                businessFile.println("breakline-------------------------------------------------------");
                businessCount++;
            }
            if (keywords.contains("sức khoẻ")) {
                if (healthCount == 50) {
                    return;
                }
                healthFile.println(title);
                healthFile.println(description);
                healthFile.println(imageURLs);
                healthFile.println(time);
                healthFile.println("breakline-------------------------------------------------------");
                healthCount++;
            }else {
                if (othersCount == 50) {
                    return;
                }
                othersFile.println(title);
                othersFile.println(description);
                othersFile.println(imageURLs);
                othersFile.println(time);
                othersFile.println("breakline-------------------------------------------------------");
                othersCount++;
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
