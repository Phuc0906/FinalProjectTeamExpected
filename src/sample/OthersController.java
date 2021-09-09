package sample;

import sample.BaseController.ChangingPage;

import java.io.IOException;

public class OthersController extends ChangingPage {
    public OthersController() throws IOException {
        super("https://vnexpress.net/giao-duc","https://vnexpress.net/du-lich","https://vnexpress.net/doi-song",
                "https://nhandan.vn/vanhoa","https://nhandan.vn/kinhte","https://nhandan.vn/phapluat","https://nhandan.vn/du-lich","https://nhandan.vn/giaoduc",
                "https://tuoitre.vn/xe.htm","https://tuoitre.vn/nhip-song-tre.htm","https://tuoitre.vn/van-hoa.htm","https://tuoitre.vn/giao-duc.htm",
                "https://thanhnien.vn/giao-duc","https://thanhnien.vn/du-lich",
                "https://zingnews.vn/doi-song.html","https://zingnews.vn/tin-tuc-xuat-ban.html","https://zingnews.vn/sach-hay.html");
    }
}

