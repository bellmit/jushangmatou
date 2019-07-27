package com.tem.gettogether.bean;

import java.util.List;

public class HomeBuyBean {

    /**
     * msg : 获取成功
     * result : [{"goods_name":"童床","goods_logo":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_47203.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_15379.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_29592.jpeg","attach_time":"现货","trade_id":"199","goods_cate":"母婴用品","release_type":"外贸大货","goods_desc":"利比亚的黎波里","add_time":"1563331012","country_id":null},{"goods_name":"摇摇车","goods_logo":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717102354_52890.jpeg","attach_time":"现货","trade_id":"198","goods_cate":"母婴用品","release_type":"外贸大货","goods_desc":"利比亚北非","add_time":"1563330234","country_id":null},{"goods_name":"奶瓶","goods_logo":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190716/20190716105637_41484.jpeg","attach_time":"15天内","trade_id":"197","goods_cate":"母婴用品","release_type":"其他","goods_desc":"PP PC 奶瓶","add_time":"1563245797","country_id":null},{"goods_name":"داعم المحركات المطاطي","goods_logo":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190708/20190708161536_15107.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190708/20190708161538_91880.jpeg","attach_time":"خلال 30 يوماً","trade_id":"193","goods_cate":"قطع غيار السيارات","release_type":"التجارة الخارجية","goods_desc":"داعم المحركات المطاطي","add_time":"1562573739","country_id":null},{"goods_name":"ماكنه لمساج العنين","goods_logo":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190706/20190706171522_47328.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190706/20190706171522_31803.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190706/20190706171523_18789.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190706/20190706171523_30945.jpeg","attach_time":"خلال سبعة أيام","trade_id":"191","goods_cate":"الأخرى","release_type":"التجارة الخارجية","goods_desc":"ماكنه لمساج العنين بالكهربائي وبالصوتي ","add_time":"1562404523","country_id":null},{"goods_name":"点火线圈","goods_logo":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190601/20190601104354_10572.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190601/20190601104354_30527.jpeg,","attach_time":"15天内","trade_id":"172","goods_cate":"汽车配件","release_type":"外贸大货","goods_desc":"出口埃塞俄比亚，高品质，质保一年，价格19-\n20元，OEM： 27301-2B010","add_time":"1559357058","country_id":null}]
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
         * goods_logo : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_47203.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_15379.jpeg,http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_29592.jpeg
         * attach_time : 现货
         * trade_id : 199
         * goods_cate : 母婴用品
         * release_type : 外贸大货
         * goods_desc : 利比亚的黎波里
         * add_time : 1563331012
         * country_id : null
         */
        private String goods_name;
        private String goods_logo;
        private String attach_time;
        private String trade_id;
        private String goods_cate;
        private String release_type;
        private String goods_desc;
        private String add_time;
        private String country_id;

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public void setGoods_logo(String goods_logo) {
            this.goods_logo = goods_logo;
        }

        public void setAttach_time(String attach_time) {
            this.attach_time = attach_time;
        }

        public void setTrade_id(String trade_id) {
            this.trade_id = trade_id;
        }

        public void setGoods_cate(String goods_cate) {
            this.goods_cate = goods_cate;
        }

        public void setRelease_type(String release_type) {
            this.release_type = release_type;
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

        public String getGoods_logo() {
            return goods_logo;
        }

        public String getAttach_time() {
            return attach_time;
        }

        public String getTrade_id() {
            return trade_id;
        }

        public String getGoods_cate() {
            return goods_cate;
        }

        public String getRelease_type() {
            return release_type;
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
