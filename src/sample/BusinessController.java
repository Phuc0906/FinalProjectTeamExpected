package sample;

import sample.BaseController.ChangingPage;
import java.io.IOException;

public class BusinessController extends ChangingPage {
    public BusinessController() throws IOException {
        super("https://vnexpress.net/kinh-doanh", "https://nhandan.vn/kinhte", "https://tuoitre.vn/kinh-doanh.htm", "https://thanhnien.vn/tai-chinh-kinh-doanh/doanh-nghiep/");
    }
}
