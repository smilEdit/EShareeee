package com.zzz.easyshare.bean;

/**
 * @创建者 zlf
 * @创建时间 2016/9/8 15:35
 */
public class HomeTestData {


    private int    image;
    private String title;

    public HomeTestData(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
