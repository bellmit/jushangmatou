package com.tem.gettogether.bean;

import java.util.List;

public class ProductDetailBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store":{"deleted":"0","store_state":"1","expire_time":"0","is_fees":"1","store_id":"273","store_name":"峰峰店铺","store_zy":null,"province_id":"17359","city_id":"18330","store_des":null,"store_user_id":"641","store_logo":null,"province":"江西省","dynamic_count":"0","city":"吉安市","goods_num":"5","collect_num":"1","is_collect":"0"},"goods":{"goods_id":"5842","shop_price":"10000.00","goods_name":"Mac电脑","batch_number":"1","sales_sum":"2","goods_remark":null,"original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094022_51002.jpeg","is_enquiry":"0","video":null,"goods_content":"&lt;/p&gt;&lt;p&gt;苹果电脑不错么！！！！\n联系电话：13751102746&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094022_51002.jpeg&quot;/&gt;&lt;/p&gt;","is_new":"1","is_hot":"0","best_percent":"100%","comment_count":0,"goods_attr_list":[],"is_collect":"0","detail":"http://www.jsmtgou.com/jushangmatou/index.php/Api/Goods/getGoodsInnerDetail/goodsId/5842"},"spec_goods_price":null,"gallery":[{"image_url":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094150_53968.jpeg"}],"comment":[],"vp":[{"country_name":"伊拉克","nickname":"哈哈哈哈","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820171253_63177.jpeg"},{"country_name":null,"nickname":"Myy","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820171557_51308.jpeg"},{"country_name":null,"nickname":"彭于晏","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190821/20190821113705_59277.jpeg"},{"country_name":null,"nickname":"小兔子","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190821/20190821155838_31622.jpeg"},{"country_name":null,"nickname":"春总","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820183331_68441.jpeg"},{"country_name":"中国","nickname":"Mie","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190821/20190821092233_71600.jpeg"},{"country_name":null,"nickname":"昆明智越科技有限公司","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822140707_62732.jpeg"},{"country_name":null,"nickname":"多一点","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820165512_47920.jpeg"}],"order":[{"user_name":"小兔子","country_name":null,"goods_num":"2","add_time":"2019-08-22 10:39:47","order_amount":"20000.00","key_name":null}]}
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
         * store : {"deleted":"0","store_state":"1","expire_time":"0","is_fees":"1","store_id":"273","store_name":"峰峰店铺","store_zy":null,"province_id":"17359","city_id":"18330","store_des":null,"store_user_id":"641","store_logo":null,"province":"江西省","dynamic_count":"0","city":"吉安市","goods_num":"5","collect_num":"1","is_collect":"0"}
         * goods : {"goods_id":"5842","shop_price":"10000.00","goods_name":"Mac电脑","batch_number":"1","sales_sum":"2","goods_remark":null,"original_img":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094022_51002.jpeg","is_enquiry":"0","video":null,"goods_content":"&lt;/p&gt;&lt;p&gt;苹果电脑不错么！！！！\n联系电话：13751102746&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094022_51002.jpeg&quot;/&gt;&lt;/p&gt;","is_new":"1","is_hot":"0","best_percent":"100%","comment_count":0,"goods_attr_list":[],"is_collect":"0","detail":"http://www.jsmtgou.com/jushangmatou/index.php/Api/Goods/getGoodsInnerDetail/goodsId/5842"}
         * spec_goods_price : null
         * gallery : [{"image_url":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094150_53968.jpeg"}]
         * comment : []
         * vp : [{"country_name":"伊拉克","nickname":"哈哈哈哈","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820171253_63177.jpeg"},{"country_name":null,"nickname":"Myy","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820171557_51308.jpeg"},{"country_name":null,"nickname":"彭于晏","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190821/20190821113705_59277.jpeg"},{"country_name":null,"nickname":"小兔子","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190821/20190821155838_31622.jpeg"},{"country_name":null,"nickname":"春总","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820183331_68441.jpeg"},{"country_name":"中国","nickname":"Mie","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190821/20190821092233_71600.jpeg"},{"country_name":null,"nickname":"昆明智越科技有限公司","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822140707_62732.jpeg"},{"country_name":null,"nickname":"多一点","head_pic":"http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820165512_47920.jpeg"}]
         * order : [{"user_name":"小兔子","country_name":null,"goods_num":"2","add_time":"2019-08-22 10:39:47","order_amount":"20000.00","key_name":null}]
         */

        private StoreBean store;
        private GoodsBean goods;
        private Object spec_goods_price;
        private List<GalleryBean> gallery;
        private List<?> comment;
        private List<VpBean> vp;
        private List<OrderBean> order;

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public Object getSpec_goods_price() {
            return spec_goods_price;
        }

        public void setSpec_goods_price(Object spec_goods_price) {
            this.spec_goods_price = spec_goods_price;
        }

        public List<GalleryBean> getGallery() {
            return gallery;
        }

        public void setGallery(List<GalleryBean> gallery) {
            this.gallery = gallery;
        }

        public List<?> getComment() {
            return comment;
        }

        public void setComment(List<?> comment) {
            this.comment = comment;
        }

        public List<VpBean> getVp() {
            return vp;
        }

        public void setVp(List<VpBean> vp) {
            this.vp = vp;
        }

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public static class StoreBean {
            /**
             * deleted : 0
             * store_state : 1
             * expire_time : 0
             * is_fees : 1
             * store_id : 273
             * store_name : 峰峰店铺
             * store_zy : null
             * province_id : 17359
             * city_id : 18330
             * store_des : null
             * store_user_id : 641
             * store_logo : null
             * province : 江西省
             * dynamic_count : 0
             * city : 吉安市
             * goods_num : 5
             * collect_num : 1
             * is_collect : 0
             */

            private String deleted;
            private String store_state;
            private String expire_time;
            private String is_fees;
            private String store_id;
            private String store_name;
            private Object store_zy;
            private String province_id;
            private String city_id;
            private Object store_des;
            private String store_user_id;
            private Object store_logo;
            private String province;
            private String dynamic_count;
            private String city;
            private String goods_num;
            private String collect_num;
            private String is_collect;

            public String getDeleted() {
                return deleted;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }

            public String getStore_state() {
                return store_state;
            }

            public void setStore_state(String store_state) {
                this.store_state = store_state;
            }

            public String getExpire_time() {
                return expire_time;
            }

            public void setExpire_time(String expire_time) {
                this.expire_time = expire_time;
            }

            public String getIs_fees() {
                return is_fees;
            }

            public void setIs_fees(String is_fees) {
                this.is_fees = is_fees;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public Object getStore_zy() {
                return store_zy;
            }

            public void setStore_zy(Object store_zy) {
                this.store_zy = store_zy;
            }

            public String getProvince_id() {
                return province_id;
            }

            public void setProvince_id(String province_id) {
                this.province_id = province_id;
            }

            public String getCity_id() {
                return city_id;
            }

            public void setCity_id(String city_id) {
                this.city_id = city_id;
            }

            public Object getStore_des() {
                return store_des;
            }

            public void setStore_des(Object store_des) {
                this.store_des = store_des;
            }

            public String getStore_user_id() {
                return store_user_id;
            }

            public void setStore_user_id(String store_user_id) {
                this.store_user_id = store_user_id;
            }

            public Object getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(Object store_logo) {
                this.store_logo = store_logo;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getDynamic_count() {
                return dynamic_count;
            }

            public void setDynamic_count(String dynamic_count) {
                this.dynamic_count = dynamic_count;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getCollect_num() {
                return collect_num;
            }

            public void setCollect_num(String collect_num) {
                this.collect_num = collect_num;
            }

            public String getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(String is_collect) {
                this.is_collect = is_collect;
            }
        }

        public static class GoodsBean {
            /**
             * goods_id : 5842
             * shop_price : 10000.00
             * goods_name : Mac电脑
             * batch_number : 1
             * sales_sum : 2
             * goods_remark : null
             * original_img : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094022_51002.jpeg
             * is_enquiry : 0
             * video : null
             * goods_content : &lt;/p&gt;&lt;p&gt;苹果电脑不错么！！！！
             联系电话：13751102746&lt;/p&gt;&lt;p&gt;&lt;img src=&quot;http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094022_51002.jpeg&quot;/&gt;&lt;/p&gt;
             * is_new : 1
             * is_hot : 0
             * best_percent : 100%
             * comment_count : 0
             * goods_attr_list : []
             * is_collect : 0
             * detail : http://www.jsmtgou.com/jushangmatou/index.php/Api/Goods/getGoodsInnerDetail/goodsId/5842
             */

            private String goods_id;
            private String shop_price;
            private String goods_name;
            private String batch_number;
            private String sales_sum;
            private Object goods_remark;
            private String original_img;
            private String is_enquiry;
            private Object video;
            private String goods_content;
            private String is_new;
            private String is_hot;
            private String best_percent;
            private int comment_count;
            private String is_collect;
            private String detail;
            private List<?> goods_attr_list;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getBatch_number() {
                return batch_number;
            }

            public void setBatch_number(String batch_number) {
                this.batch_number = batch_number;
            }

            public String getSales_sum() {
                return sales_sum;
            }

            public void setSales_sum(String sales_sum) {
                this.sales_sum = sales_sum;
            }

            public Object getGoods_remark() {
                return goods_remark;
            }

            public void setGoods_remark(Object goods_remark) {
                this.goods_remark = goods_remark;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getIs_enquiry() {
                return is_enquiry;
            }

            public void setIs_enquiry(String is_enquiry) {
                this.is_enquiry = is_enquiry;
            }

            public Object getVideo() {
                return video;
            }

            public void setVideo(Object video) {
                this.video = video;
            }

            public String getGoods_content() {
                return goods_content;
            }

            public void setGoods_content(String goods_content) {
                this.goods_content = goods_content;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getBest_percent() {
                return best_percent;
            }

            public void setBest_percent(String best_percent) {
                this.best_percent = best_percent;
            }

            public int getComment_count() {
                return comment_count;
            }

            public void setComment_count(int comment_count) {
                this.comment_count = comment_count;
            }

            public String getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(String is_collect) {
                this.is_collect = is_collect;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public List<?> getGoods_attr_list() {
                return goods_attr_list;
            }

            public void setGoods_attr_list(List<?> goods_attr_list) {
                this.goods_attr_list = goods_attr_list;
            }
        }

        public static class GalleryBean {
            /**
             * image_url : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190822/20190822094150_53968.jpeg
             */

            private String image_url;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }

        public static class VpBean {
            /**
             * country_name : 伊拉克
             * nickname : 哈哈哈哈
             * head_pic : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190820/20190820171253_63177.jpeg
             */

            private String country_name;
            private String nickname;
            private String head_pic;

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getHead_pic() {
                return head_pic;
            }

            public void setHead_pic(String head_pic) {
                this.head_pic = head_pic;
            }
        }

        public static class OrderBean {
            /**
             * user_name : 小兔子
             * country_name : null
             * goods_num : 2
             * add_time : 2019-08-22 10:39:47
             * order_amount : 20000.00
             * key_name : null
             */

            private String user_name;
            private Object country_name;
            private String goods_num;
            private String add_time;
            private String order_amount;
            private Object key_name;

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public Object getCountry_name() {
                return country_name;
            }

            public void setCountry_name(Object country_name) {
                this.country_name = country_name;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public Object getKey_name() {
                return key_name;
            }

            public void setKey_name(Object key_name) {
                this.key_name = key_name;
            }
        }
    }
}
