package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/29 16:08 
 */
public class ShopEditBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"goods_images":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094402_45764.jpg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094512_69126.jpg"],"this_goods_spec":"颜色:黑色,面料:面料","cat":{"cat2_name":"男装","cat3_name":"上衣"},"goods":{"goods_id":"6777","cat_id1":"0","cat_id2":"6069","cat_id3":"6071","store_cat_id1":"0","store_cat_id2":"0","store_cat_id3":"0","goods_sn":"货号","goods_name":"商品名称","goods_en_name":"","goods_ara_name":"","click_count":"0","brand_id":"0","store_count":"0","collect_sum":"0","comment_count":"0","weight":"0","market_price":"0.00","shop_price":"0.00","cost_price":"0.00","exchange_integral":"0","keywords":"关键词","goods_remark":null,"goods_en_remark":null,"goods_ara_remark":null,"goods_content":"文字介绍 ","goods_en_content":null,"goods_ara_content":null,"original_img":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094256_76857.jpg"],"is_real":"1","is_on_sale":"1","is_free_shipping":"0","on_time":"2019-11-30 09:45:14","sort":"50","is_recommend":"0","is_new":"0","is_hot_re":"0","is_hot":"0","is_special":"0","is_best":"0","last_update":"0","goods_type":"0","give_integral":"0","sales_sum":"0","prom_type":"0","prom_id":"0","distribut":"0.00","store_id":"286","spu":null,"sku":null,"goods_state":"1","suppliers_id":null,"shipping_area_ids":"","batch_number":"0","is_enquiry":"1","enquiry_price_low":null,"enquiry_price_high":null,"video":null,"cover_image":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094342_59264.jpg"}}
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
        /**
         * goods_images : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094402_45764.jpg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094512_69126.jpg"]
         * this_goods_spec : 颜色:黑色,面料:面料
         * cat : {"cat2_name":"男装","cat3_name":"上衣"}
         * goods : {"goods_id":"6777","cat_id1":"0","cat_id2":"6069","cat_id3":"6071","store_cat_id1":"0","store_cat_id2":"0","store_cat_id3":"0","goods_sn":"货号","goods_name":"商品名称","goods_en_name":"","goods_ara_name":"","click_count":"0","brand_id":"0","store_count":"0","collect_sum":"0","comment_count":"0","weight":"0","market_price":"0.00","shop_price":"0.00","cost_price":"0.00","exchange_integral":"0","keywords":"关键词","goods_remark":null,"goods_en_remark":null,"goods_ara_remark":null,"goods_content":"文字介绍 ","goods_en_content":null,"goods_ara_content":null,"original_img":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094256_76857.jpg"],"is_real":"1","is_on_sale":"1","is_free_shipping":"0","on_time":"2019-11-30 09:45:14","sort":"50","is_recommend":"0","is_new":"0","is_hot_re":"0","is_hot":"0","is_special":"0","is_best":"0","last_update":"0","goods_type":"0","give_integral":"0","sales_sum":"0","prom_type":"0","prom_id":"0","distribut":"0.00","store_id":"286","spu":null,"sku":null,"goods_state":"1","suppliers_id":null,"shipping_area_ids":"","batch_number":"0","is_enquiry":"1","enquiry_price_low":null,"enquiry_price_high":null,"video":null,"cover_image":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094342_59264.jpg"}
         */

        private String this_goods_spec;
        private CatBean cat;
        private GoodsBean goods;
        private List<String> goods_images;

        public String getThis_goods_spec() {
            return this_goods_spec;
        }

        public void setThis_goods_spec(String this_goods_spec) {
            this.this_goods_spec = this_goods_spec;
        }

        public CatBean getCat() {
            return cat;
        }

        public void setCat(CatBean cat) {
            this.cat = cat;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public List<String> getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(List<String> goods_images) {
            this.goods_images = goods_images;
        }

        public static class CatBean {
            /**
             * cat2_name : 男装
             * cat3_name : 上衣
             */

            private String cat2_name;
            private String cat3_name;

            public String getCat2_name() {
                return cat2_name;
            }

            public void setCat2_name(String cat2_name) {
                this.cat2_name = cat2_name;
            }

            public String getCat3_name() {
                return cat3_name;
            }

            public void setCat3_name(String cat3_name) {
                this.cat3_name = cat3_name;
            }
        }

        public static class GoodsBean {
            /**
             * goods_id : 6777
             * cat_id1 : 0
             * cat_id2 : 6069
             * cat_id3 : 6071
             * store_cat_id1 : 0
             * store_cat_id2 : 0
             * store_cat_id3 : 0
             * goods_sn : 货号
             * goods_name : 商品名称
             * goods_en_name :
             * goods_ara_name :
             * click_count : 0
             * brand_id : 0
             * store_count : 0
             * collect_sum : 0
             * comment_count : 0
             * weight : 0
             * market_price : 0.00
             * shop_price : 0.00
             * cost_price : 0.00
             * exchange_integral : 0
             * keywords : 关键词
             * goods_remark : null
             * goods_en_remark : null
             * goods_ara_remark : null
             * goods_content : 文字介绍
             * goods_en_content : null
             * goods_ara_content : null
             * original_img : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094256_76857.jpg"]
             * is_real : 1
             * is_on_sale : 1
             * is_free_shipping : 0
             * on_time : 2019-11-30 09:45:14
             * sort : 50
             * is_recommend : 0
             * is_new : 0
             * is_hot_re : 0
             * is_hot : 0
             * is_special : 0
             * is_best : 0
             * last_update : 0
             * goods_type : 0
             * give_integral : 0
             * sales_sum : 0
             * prom_type : 0
             * prom_id : 0
             * distribut : 0.00
             * store_id : 286
             * spu : null
             * sku : null
             * goods_state : 1
             * suppliers_id : null
             * shipping_area_ids :
             * batch_number : 0
             * is_enquiry : 1
             * enquiry_price_low : null
             * enquiry_price_high : null
             * video : null
             * cover_image : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20191130/20191130094342_59264.jpg
             */

            private String goods_id;
            private String cat_id1;
            private String cat_id2;
            private String cat_id3;
            private String store_cat_id1;
            private String store_cat_id2;
            private String store_cat_id3;
            private String goods_sn;
            private String goods_name;
            private String goods_en_name;
            private String goods_ara_name;
            private String click_count;
            private String brand_id;
            private String store_count;
            private String collect_sum;
            private String comment_count;
            private String weight;
            private String market_price;
            private String shop_price;
            private String cost_price;
            private String exchange_integral;
            private String keywords;
            private Object goods_remark;
            private Object goods_en_remark;
            private Object goods_ara_remark;
            private String goods_content;
            private Object goods_en_content;
            private Object goods_ara_content;
            private String is_real;
            private String is_on_sale;
            private String is_free_shipping;
            private String on_time;
            private String sort;
            private String is_recommend;
            private String is_new;
            private String is_hot_re;
            private String is_hot;
            private String is_special;
            private String is_best;
            private String last_update;
            private String goods_type;
            private String give_integral;
            private String sales_sum;
            private String prom_type;
            private String prom_id;
            private String distribut;
            private String store_id;
            private Object spu;
            private Object sku;
            private String goods_state;
            private Object suppliers_id;
            private String shipping_area_ids;
            private String batch_number;
            private String is_enquiry;
            private Object enquiry_price_low;
            private Object enquiry_price_high;
            private Object video;
            private String cover_image;
            private List<String> original_img;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getCat_id1() {
                return cat_id1;
            }

            public void setCat_id1(String cat_id1) {
                this.cat_id1 = cat_id1;
            }

            public String getCat_id2() {
                return cat_id2;
            }

            public void setCat_id2(String cat_id2) {
                this.cat_id2 = cat_id2;
            }

            public String getCat_id3() {
                return cat_id3;
            }

            public void setCat_id3(String cat_id3) {
                this.cat_id3 = cat_id3;
            }

            public String getStore_cat_id1() {
                return store_cat_id1;
            }

            public void setStore_cat_id1(String store_cat_id1) {
                this.store_cat_id1 = store_cat_id1;
            }

            public String getStore_cat_id2() {
                return store_cat_id2;
            }

            public void setStore_cat_id2(String store_cat_id2) {
                this.store_cat_id2 = store_cat_id2;
            }

            public String getStore_cat_id3() {
                return store_cat_id3;
            }

            public void setStore_cat_id3(String store_cat_id3) {
                this.store_cat_id3 = store_cat_id3;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_en_name() {
                return goods_en_name;
            }

            public void setGoods_en_name(String goods_en_name) {
                this.goods_en_name = goods_en_name;
            }

            public String getGoods_ara_name() {
                return goods_ara_name;
            }

            public void setGoods_ara_name(String goods_ara_name) {
                this.goods_ara_name = goods_ara_name;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }

            public String getCollect_sum() {
                return collect_sum;
            }

            public void setCollect_sum(String collect_sum) {
                this.collect_sum = collect_sum;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getExchange_integral() {
                return exchange_integral;
            }

            public void setExchange_integral(String exchange_integral) {
                this.exchange_integral = exchange_integral;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public Object getGoods_remark() {
                return goods_remark;
            }

            public void setGoods_remark(Object goods_remark) {
                this.goods_remark = goods_remark;
            }

            public Object getGoods_en_remark() {
                return goods_en_remark;
            }

            public void setGoods_en_remark(Object goods_en_remark) {
                this.goods_en_remark = goods_en_remark;
            }

            public Object getGoods_ara_remark() {
                return goods_ara_remark;
            }

            public void setGoods_ara_remark(Object goods_ara_remark) {
                this.goods_ara_remark = goods_ara_remark;
            }

            public String getGoods_content() {
                return goods_content;
            }

            public void setGoods_content(String goods_content) {
                this.goods_content = goods_content;
            }

            public Object getGoods_en_content() {
                return goods_en_content;
            }

            public void setGoods_en_content(Object goods_en_content) {
                this.goods_en_content = goods_en_content;
            }

            public Object getGoods_ara_content() {
                return goods_ara_content;
            }

            public void setGoods_ara_content(Object goods_ara_content) {
                this.goods_ara_content = goods_ara_content;
            }

            public String getIs_real() {
                return is_real;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public String getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(String is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public String getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(String is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getOn_time() {
                return on_time;
            }

            public void setOn_time(String on_time) {
                this.on_time = on_time;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getIs_hot_re() {
                return is_hot_re;
            }

            public void setIs_hot_re(String is_hot_re) {
                this.is_hot_re = is_hot_re;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getIs_special() {
                return is_special;
            }

            public void setIs_special(String is_special) {
                this.is_special = is_special;
            }

            public String getIs_best() {
                return is_best;
            }

            public void setIs_best(String is_best) {
                this.is_best = is_best;
            }

            public String getLast_update() {
                return last_update;
            }

            public void setLast_update(String last_update) {
                this.last_update = last_update;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getGive_integral() {
                return give_integral;
            }

            public void setGive_integral(String give_integral) {
                this.give_integral = give_integral;
            }

            public String getSales_sum() {
                return sales_sum;
            }

            public void setSales_sum(String sales_sum) {
                this.sales_sum = sales_sum;
            }

            public String getProm_type() {
                return prom_type;
            }

            public void setProm_type(String prom_type) {
                this.prom_type = prom_type;
            }

            public String getProm_id() {
                return prom_id;
            }

            public void setProm_id(String prom_id) {
                this.prom_id = prom_id;
            }

            public String getDistribut() {
                return distribut;
            }

            public void setDistribut(String distribut) {
                this.distribut = distribut;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public Object getSpu() {
                return spu;
            }

            public void setSpu(Object spu) {
                this.spu = spu;
            }

            public Object getSku() {
                return sku;
            }

            public void setSku(Object sku) {
                this.sku = sku;
            }

            public String getGoods_state() {
                return goods_state;
            }

            public void setGoods_state(String goods_state) {
                this.goods_state = goods_state;
            }

            public Object getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(Object suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public String getShipping_area_ids() {
                return shipping_area_ids;
            }

            public void setShipping_area_ids(String shipping_area_ids) {
                this.shipping_area_ids = shipping_area_ids;
            }

            public String getBatch_number() {
                return batch_number;
            }

            public void setBatch_number(String batch_number) {
                this.batch_number = batch_number;
            }

            public String getIs_enquiry() {
                return is_enquiry;
            }

            public void setIs_enquiry(String is_enquiry) {
                this.is_enquiry = is_enquiry;
            }

            public Object getEnquiry_price_low() {
                return enquiry_price_low;
            }

            public void setEnquiry_price_low(Object enquiry_price_low) {
                this.enquiry_price_low = enquiry_price_low;
            }

            public Object getEnquiry_price_high() {
                return enquiry_price_high;
            }

            public void setEnquiry_price_high(Object enquiry_price_high) {
                this.enquiry_price_high = enquiry_price_high;
            }

            public Object getVideo() {
                return video;
            }

            public void setVideo(Object video) {
                this.video = video;
            }

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public List<String> getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(List<String> original_img) {
                this.original_img = original_img;
            }
        }
    }
}
