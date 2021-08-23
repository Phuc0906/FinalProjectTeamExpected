package sample;

import sample.BaseController.ChangingPage;

import java.io.IOException;

public class NewController extends ChangingPage {
    public NewController () throws IOException {
        super("https://zingnews.vn/");
    }
}
