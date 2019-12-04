package com.tem.gettogether.bean;

import java.util.List;

public class QiuGouListBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"trade_id":"237","country_id":"24","goods_name":"测试","release_type":"外贸大货","attach_time":"7天内","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115340_46949.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_57972.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_12998.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_48048.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_92653.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115351_11102.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115353_57295.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115353_75470.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115355_19887.jpeg"],"add_time":"1565236440","country_name":"亚速尔群岛"},{"trade_id":"235","country_id":"22","goods_name":"hhh","release_type":"内贸批发","attach_time":"30天内","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806142437_33377.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806142443_48125.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806142443_86162.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190806/20190806142453_75316.jpeg"],"add_time":"1565072712","country_name":"摩洛哥"},{"trade_id":"202","country_id":"7","goods_name":"陶艺","release_type":"现货库存","attach_time":"现货","goods_logo":[""],"add_time":"1564381825","country_name":"卡塔尔"},{"trade_id":"199","country_id":null,"goods_name":"童床","release_type":"外贸大货","attach_time":"现货","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_47203.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_15379.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_29592.jpeg"],"add_time":"1563331012","country_name":null},{"trade_id":"198","country_id":null,"goods_name":"摇摇车","release_type":"外贸大货","attach_time":"现货","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717102354_52890.jpeg"],"add_time":"1563330234","country_name":null},{"trade_id":"197","country_id":null,"goods_name":"奶瓶","release_type":"其他","attach_time":"15天内","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190716/20190716105637_41484.jpeg"],"add_time":"1563245797","country_name":null}]
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
         * trade_id : 237
         * country_id : 24
         * goods_name : 测试
         * release_type : 外贸大货
         * attach_time : 7天内
         * goods_logo : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115340_46949.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_57972.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_12998.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_48048.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115343_92653.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115351_11102.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115353_57295.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115353_75470.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190808/20190808115355_19887.jpeg"]
         * add_time : 1565236440
         * country_name : 亚速尔群岛
         */

        private String trade_id;
        private String country_id;
        private String goods_name;
        private String release_type;
        private String attach_time;
        private String add_time;
        private String country_name;
        private String status;
        private String end_time;
        private String end_pic;
        private List<String> goods_logo;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getEnd_pic() {
            return end_pic;
        }

        public void setEnd_pic(String end_pic) {
            this.end_pic = end_pic;
        }

        public String getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(String trade_id) {
            this.trade_id = trade_id;
        }

        public String getCountry_id() {
            return country_id;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getRelease_type() {
            return release_type;
        }

        public void setRelease_type(String release_type) {
            this.release_type = release_type;
        }

        public String getAttach_time() {
            return attach_time;
        }

        public void setAttach_time(String attach_time) {
            this.attach_time = attach_time;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public List<String> getGoods_logo() {
            return goods_logo;
        }

        public void setGoods_logo(List<String> goods_logo) {
            this.goods_logo = goods_logo;
        }
    }
}
