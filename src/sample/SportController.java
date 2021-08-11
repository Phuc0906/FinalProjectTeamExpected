package sample;

import sample.BaseController.ChangingPage;

import java.io.IOException;

public class SportController extends ChangingPage {
    public SportController() throws IOException {
        super("https://vnexpress.net/the-thao", "https://nhandan.vn/thethao", "https://tuoitre.vn/the-thao.htm", "https://thanhnien.vn/the-gioi/");
    }
}
