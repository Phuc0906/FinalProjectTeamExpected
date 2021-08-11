package sample;


import sample.BaseController.ChangingPage;
import java.io.IOException;


public class WorldController extends ChangingPage {

    public WorldController() throws IOException {
        super("https://vnexpress.net/the-gioi", "https://nhandan.vn/thegioi", "https://tuoitre.vn/the-gioi.htm", "https://thanhnien.vn/the-gioi/");
    }
}
