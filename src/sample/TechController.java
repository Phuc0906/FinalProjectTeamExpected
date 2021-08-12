package sample;

import sample.BaseController.ChangingPage;

import java.io.IOException;

public class TechController extends ChangingPage {
    public TechController() throws IOException {
        super("https://vnexpress.net/khoa-hoc", "https://nhandan.vn/khoahoc-congnghe", "https://congnghe.tuoitre.vn/", "https://thanhnien.vn/cong-nghe/");
    }
}
