package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-08.
 */

public class FenLieLeftBean {


    /**
     * status : 1
     * msg : 获取成功
     * result : [{"category_id":"110","mobile_name":"袜类/打底裤","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"1102","mobile_name":"","app_image":""},{"category_id":"1103","mobile_name":"","app_image":""},{"category_id":"1106","mobile_name":"","app_image":""},{"category_id":"1111","mobile_name":"","app_image":""},{"category_id":"2","mobile_name":"头饰/珠宝首饰/辅房","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"3","mobile_name":"喜庆工艺/装饰工艺/旅游工艺/瓷器水晶/相框/饰品配件/辅房","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"4","mobile_name":"花类/饰品/工艺品/玩具/花类/饰品及配件/工艺品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"45","mobile_name":"","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"452","mobile_name":"汽车用品/汽车配件","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"455","mobile_name":"食品/日用品/工艺品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"460","mobile_name":"床上用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"463","mobile_name":"皮革/辅料配件","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"48","mobile_name":"","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"488","mobile_name":"办公学习用品/文化体育用品/户外用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"495","mobile_name":"笔墨纸制品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"498","mobile_name":"针棉织品/帽子/手套/日用百货","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"510","mobile_name":"裤/牛仔","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"519","mobile_name":"印刷原材料/工业电器/仿真花/花类配件","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"531","mobile_name":"","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"},{"category_id":"566","mobile_name":"","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png"}]
     */

    private int status;
    private String msg;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * category_id : 110
         * mobile_name : 袜类/打底裤
         * app_image : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png
         */

        private String category_id;
        private String mobile_name;
        private String app_image;


        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getMobile_name() {
            return mobile_name;
        }

        public void setMobile_name(String mobile_name) {
            this.mobile_name = mobile_name;
        }

        public String getApp_image() {
            return app_image;
        }

        public void setApp_image(String app_image) {
            this.app_image = app_image;
        }
    }
}
