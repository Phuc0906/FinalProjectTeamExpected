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



}
