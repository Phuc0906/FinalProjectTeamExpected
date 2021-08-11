package sample;


import sample.BaseController.ChangingPage;
import java.io.IOException;


public class HealthController extends ChangingPage {

    public HealthController() throws IOException {
        super("https://vnexpress.net/suc-khoe", "https://nhandan.vn/y-te", "https://tuoitre.vn/suc-khoe.htm", "https://thanhnien.vn/suc-khoe/");
    }
}
