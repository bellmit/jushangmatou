package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-04-28.
 */

public class PingjiaBean {



    /**
     * spec_key_name :
     * comment_img : ["images1","images2"]
     * rank :
     * content :
     */

    private String spec_key_name;
    private String rank;
    private String content;
    private List<String> comment_img;

    public String getSpec_key_name() {
        return spec_key_name;
    }

    public void setSpec_key_name(String spec_key_name) {
        this.spec_key_name = spec_key_name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getComment_img() {
        return comment_img;
    }

    public void setComment_img(List<String> comment_img) {
        this.comment_img = comment_img;
    }
}
