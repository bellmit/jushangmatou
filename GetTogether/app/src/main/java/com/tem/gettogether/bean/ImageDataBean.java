package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-12.
 */

public class ImageDataBean {

    /**
     * status : 1
     * msg : success
     * result : {"image":["/Uploads/head_img/20190225/20190225165146_.jpeg"],"image_show":["http://test.uonep.com/jushangmatou/Uploads/head_img/20190225/20190225165146_.jpeg"]}
     */

    private int status;
    private String msg;
    private ResultBean result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private List<String> image;
        private List<String> image_show;

        public List<String> getImage() {
            return image;
        }

        public void setImage(List<String> image) {
            this.image = image;
        }

        public List<String> getImage_show() {
            return image_show;
        }

        public void setImage_show(List<String> image_show) {
            this.image_show = image_show;
        }
    }
}
