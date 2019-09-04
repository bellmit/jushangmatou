package com.tem.gettogether.bean;

public class MenuItem {
    private String imgId;

    private String title;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuItem(String imgId, String title){
        this.imgId = imgId;
        this.title = title;
    }
}
