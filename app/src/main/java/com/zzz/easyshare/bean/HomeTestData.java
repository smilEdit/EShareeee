package com.zzz.easyshare.bean;

import android.graphics.Bitmap;

/**
 * @创建者 zlf
 * @创建时间 2016/9/8 15:35
 */
public class HomeTestData {


    private String    image;
    private String title;

    public HomeTestData(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public static class ResultsBean {
        private Bitmap bitmap;
        private int    bitmapWidth;
        private int    bitmapHeight;

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public void setBitmapWidth(int bitmapWidth) {
            this.bitmapWidth = bitmapWidth;
        }

        public void setBitmapHeight(int bitmapHeight) {
            this.bitmapHeight = bitmapHeight;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public int getBitmapWidth() {
            return bitmapWidth;
        }

        public int getBitmapHeight() {
            return bitmapHeight;
        }
    }
}
