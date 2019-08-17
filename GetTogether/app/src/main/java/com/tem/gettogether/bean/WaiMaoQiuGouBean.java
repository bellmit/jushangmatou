package com.tem.gettogether.bean;

import java.util.ArrayList;
import java.util.List;

public class WaiMaoQiuGouBean {


    /**
     * msg : 获取成功
     * result : [{"goods_name":"童床","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_47203.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_15379.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_29592.jpeg"],"attach_time":"现货","user_id":"481","goods_cate":"母婴用品","session_id":"01di09cq58vb30g62aoeu2l803","release_type":"外贸大货","goods_num":"0","id":"199","goods_desc":"利比亚的黎波里","add_time":"1563331012","country_id":null}]
     * status : 1
     */
    private String msg;
    private List<ResultEntity> result;
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public List<ResultEntity> getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public class ResultEntity {
        /**
         * goods_name : 童床
         * goods_logo : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_47203.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_15379.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_29592.jpeg"]
         * attach_time : 现货
         * user_id : 481
         * goods_cate : 母婴用品
         * session_id : 01di09cq58vb30g62aoeu2l803
         * release_type : 外贸大货
         * goods_num : 0
         * id : 199
         * goods_desc : 利比亚的黎波里
         * add_time : 1563331012
         * country_id : null
         */
        private String goods_name;
        private ArrayList<String> goods_logo;
        private String attach_time;
        private String user_id;
        private String goods_cate;
        private String session_id;
        private String release_type;
        private String goods_num;
        private String id;
        private String goods_desc;
        private String add_time;
        private String country_id;
        private String mobile;
        private String country_name;
        private String head_pic;
        private String nickname;

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public void setGoods_logo(ArrayList<String> goods_logo) {
            this.goods_logo = goods_logo;
        }

        public void setAttach_time(String attach_time) {
            this.attach_time = attach_time;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setGoods_cate(String goods_cate) {
            this.goods_cate = goods_cate;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public void setRelease_type(String release_type) {
            this.release_type = release_type;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public ArrayList<String> getGoods_logo() {
            return goods_logo;
        }

        public String getAttach_time() {
            return attach_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getGoods_cate() {
            return goods_cate;
        }

        public String getSession_id() {
            return session_id;
        }

        public String getRelease_type() {
            return release_type;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public String getId() {
            return id;
        }

        public String getGoods_desc() {
            return goods_desc;
        }

        public String getAdd_time() {
            return add_time;
        }

        public String getCountry_id() {
            return country_id;
        }
    }
}
