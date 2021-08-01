package sample.NewsClass;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class VnExpressCateURL {
    private String worldURL;

    public VnExpressCateURL() throws IOException {
        String mainWebURL = "https://vnexpress.net";
        Document doc = Jsoup.connect(mainWebURL).get();
        Elements body = doc.select("body");
        Elements listURL = doc.select("section.wrap-main-nav" +
                " nav.main-nav" +
                " ul.parent");
        this.worldURL = mainWebURL + listURL.select("li.thegioi" +
                " a").attr("href");
        System.out.println(this.worldURL);
    }

    public String getWorldURL() { return this.worldURL; }
}
