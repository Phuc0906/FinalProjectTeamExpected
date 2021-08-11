package sample;

import sample.BaseController.ChangingPage;

import java.io.IOException;

public class PoliticsController extends ChangingPage {
    public PoliticsController() throws IOException {
        super("https://vnexpress.net/phap-luat", "https://nhandan.vn/chinhtri", "https://tuoitre.vn/phap-luat.htm", "https://thanhnien.vn/thoi-su/");
    }
}
