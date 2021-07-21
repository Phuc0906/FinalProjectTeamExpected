package NewsApp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class ScrapingWeb {
    public void main(String[] args) throws IOException {
        Document dc = Jsoup.connect("https://vnexpress.net/interactive/2021/18-lanh-dao-quoc-hoi-khoa-15").get();
        Elements body = dc.select("body");
        Elements mainInfo = body.select("section.main-info");
        // get title
        String titlePage = body.select("header.header" +
                " div.section-tab" +
                " a.title-page").text();
        System.out.println(titlePage);

        String date = mainInfo.select("span.published").text();
        String title = mainInfo.select("h1.title-cate-top").text();
        String briefIndo = mainInfo.select("p").text();
        System.out.println(date);
        System.out.println(title);
        System.out.println(briefIndo);
    }
}
