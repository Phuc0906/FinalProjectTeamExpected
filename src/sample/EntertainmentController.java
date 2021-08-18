package sample;


import sample.BaseController.ChangingPage;
import java.io.IOException;


public class EntertainmentController extends ChangingPage {
    public EntertainmentController() throws IOException {
        super("https://vnexpress.net/giai-tri", "https://nhandan.vn/thegioi", "https://tuoitre.vn/giai-tri.htm", "https://thanhnien.vn/giai-tri/", "https://zingnews.vn/giai-tri.html");
    }
}
