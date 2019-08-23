package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-10.
 */

public class ShoppingXQBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store":{"store_id":"50","store_name":"sorry","store_zy":"","province_id":"12596","city_id":"13564","store_des":"","store_user_id":"128","province":"浙江省","city":"金华市","goods_num":"88","collect_num":"5","is_collect":"0"},"goods":{"goods_id":"244","shop_price":"0.00","goods_name":"皮革","batch_number":"1","sales_sum":"0","goods_remark":"","original_img":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2019/01-15/5c3d500fb1075.jpg","goods_content":"","is_enquiry":"0","best_percent":"100%","comment_count":"17%","goods_attr_list":[],"goods_spec_list":[[{"spec_name":"颜色","item_id":"174","item":"红","src":""},{"spec_name":"颜色","item_id":"175","item":"白","src":""}],[{"spec_name":"尺寸","item_id":"178","item":"200","src":""},{"spec_name":"尺寸","item_id":"177","item":"100","src":""}]],"is_collect":"0"},"spec_goods_price":{"174_177":{"key":"174_177","price":"10.00","store_count":"100"},"174_178":{"key":"174_178","price":"20.00","store_count":"200"},"175_177":{"key":"175_177","price":"30.00","store_count":"300"},"175_178":{"key":"175_178","price":"40.00","store_count":"400"}},"gallery":[{"image_url":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2018/11-26/5bfb591e66618.jpg"},{"image_url":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2018/11-26/5bfb5affd8aae.jpg"},{"image_url":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2019/01-15/5c3d500fb1075.jpg"}],"comment":[{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/11-26/5bfba8c632a7e.jpg","nickname":"匿名用户","add_time":"2018-11-26, 14:38:55","spec_key_name":"内存:32G 颜色:黑色","content":"非常的棒","impression":"","comment_id":"369","goods_rank":"5.0","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb946d496ea.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb947c86bbf.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb9488ab9b9.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb949f3b0d0.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb94bd6c9f5.jpg"}],"parent_id":"00000000000","order_id":"381","user_id":"83","goods_num":"90"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 10:49:28","spec_key_name":"内存:32G 颜色:黑色","content":"1111111","impression":"2323","comment_id":"379","goods_rank":"5.0","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfcb0b78baff.jpg"}],"parent_id":"00000000000","order_id":"385","user_id":"82","goods_num":null}]}
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
         * store : {"store_id":"50","store_name":"sorry","store_zy":"","province_id":"12596","city_id":"13564","store_des":"","store_user_id":"128","province":"浙江省","city":"金华市","goods_num":"88","collect_num":"5","is_collect":"0"}
         * goods : {"goods_id":"244","shop_price":"0.00","goods_name":"皮革","batch_number":"1","sales_sum":"0","goods_remark":"","original_img":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2019/01-15/5c3d500fb1075.jpg","goods_content":"","is_enquiry":"0","best_percent":"100%","comment_count":"17%","goods_attr_list":[],"goods_spec_list":[[{"spec_name":"颜色","item_id":"174","item":"红","src":""},{"spec_name":"颜色","item_id":"175","item":"白","src":""}],[{"spec_name":"尺寸","item_id":"178","item":"200","src":""},{"spec_name":"尺寸","item_id":"177","item":"100","src":""}]],"is_collect":"0"}
         * spec_goods_price : {"174_177":{"key":"174_177","price":"10.00","store_count":"100"},"174_178":{"key":"174_178","price":"20.00","store_count":"200"},"175_177":{"key":"175_177","price":"30.00","store_count":"300"},"175_178":{"key":"175_178","price":"40.00","store_count":"400"}}
         * gallery : [{"image_url":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2018/11-26/5bfb591e66618.jpg"},{"image_url":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2018/11-26/5bfb5affd8aae.jpg"},{"image_url":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2019/01-15/5c3d500fb1075.jpg"}]
         * comment : [{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/11-26/5bfba8c632a7e.jpg","nickname":"匿名用户","add_time":"2018-11-26, 14:38:55","spec_key_name":"内存:32G 颜色:黑色","content":"非常的棒","impression":"","comment_id":"369","goods_rank":"5.0","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb946d496ea.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb947c86bbf.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb9488ab9b9.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb949f3b0d0.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb94bd6c9f5.jpg"}],"parent_id":"00000000000","order_id":"381","user_id":"83","goods_num":"90"},{"head_pic":"http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/12-01/5c024da19da1c.jpg","nickname":"匿名用户","add_time":"2018-11-27, 10:49:28","spec_key_name":"内存:32G 颜色:黑色","content":"1111111","impression":"2323","comment_id":"379","goods_rank":"5.0","img":[{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-27/5bfcb0b78baff.jpg"}],"parent_id":"00000000000","order_id":"385","user_id":"82","goods_num":null}]
         */

        private StoreBean store;
        private GoodsBean goods;

        private List<GalleryBean> gallery;
        private List<CommentBean> comment;
        private List<VpBean> vp;
        private List<OrderBean> order;

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public List<VpBean> getVp() {
            return vp;
        }

        public void setVp(List<VpBean> vp) {
            this.vp = vp;
        }

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


        public List<GalleryBean> getGallery() {
            return gallery;
        }

        public void setGallery(List<GalleryBean> gallery) {
            this.gallery = gallery;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class StoreBean {
            /**
             * store_id : 50
             * store_name : sorry
             * store_zy :
             * province_id : 12596
             * city_id : 13564
             * store_des :
             * store_user_id : 128
             * province : 浙江省
             * city : 金华市
             * goods_num : 88
             * collect_num : 5
             * is_collect : 0
             */

            private String store_id;
            private String store_name;
            private String store_zy;
            private String province_id;
            private String city_id;
            private String store_des;
            private String store_user_id;
            private String province;
            private String city;
            private String goods_num;
            private String collect_num;
            private String is_collect;
            private String store_logo;

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
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

            public String getStore_zy() {
                return store_zy;
            }

            public void setStore_zy(String store_zy) {
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

            public String getStore_des() {
                return store_des;
            }

            public void setStore_des(String store_des) {
                this.store_des = store_des;
            }

            public String getStore_user_id() {
                return store_user_id;
            }

            public void setStore_user_id(String store_user_id) {
                this.store_user_id = store_user_id;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
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
             * goods_id : 244
             * shop_price : 0.00
             * goods_name : 皮革
             * batch_number : 1
             * sales_sum : 0
             * goods_remark :
             * original_img : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2019/01-15/5c3d500fb1075.jpg
             * goods_content :
             * is_enquiry : 0
             * best_percent : 100%
             * comment_count : 17%
             * goods_attr_list : []
             * goods_spec_list : [[{"spec_name":"颜色","item_id":"174","item":"红","src":""},{"spec_name":"颜色","item_id":"175","item":"白","src":""}],[{"spec_name":"尺寸","item_id":"178","item":"200","src":""},{"spec_name":"尺寸","item_id":"177","item":"100","src":""}]]
             * is_collect : 0
             */

            private String goods_id;
            private String shop_price;
            private String goods_name;
            private String batch_number;
            private String sales_sum;
            private String goods_remark;
            private String original_img;
            private String goods_content;
            private String is_enquiry;
            private String best_percent;
            private String comment_count;
            private String is_collect;
            private List<?> goods_attr_list;
            private List<List<GoodsSpecListBean>> goods_spec_list;
            private String detail;

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

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

            public String getGoods_remark() {
                return goods_remark;
            }

            public void setGoods_remark(String goods_remark) {
                this.goods_remark = goods_remark;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getGoods_content() {
                return goods_content;
            }

            public void setGoods_content(String goods_content) {
                this.goods_content = goods_content;
            }

            public String getIs_enquiry() {
                return is_enquiry;
            }

            public void setIs_enquiry(String is_enquiry) {
                this.is_enquiry = is_enquiry;
            }

            public String getBest_percent() {
                return best_percent;
            }

            public void setBest_percent(String best_percent) {
                this.best_percent = best_percent;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getIs_collect() {
                return is_collect;
            }

            public void setIs_collect(String is_collect) {
                this.is_collect = is_collect;
            }

            public List<?> getGoods_attr_list() {
                return goods_attr_list;
            }

            public void setGoods_attr_list(List<?> goods_attr_list) {
                this.goods_attr_list = goods_attr_list;
            }

            public List<List<GoodsSpecListBean>> getGoods_spec_list() {
                return goods_spec_list;
            }

            public void setGoods_spec_list(List<List<GoodsSpecListBean>> goods_spec_list) {
                this.goods_spec_list = goods_spec_list;
            }

            public static class GoodsSpecListBean {
                /**
                 * spec_name : 颜色
                 * item_id : 174
                 * item : 红
                 * src :
                 */

                private String spec_name;
                private String item_id;
                private String item;
                private String src;

                public String getSpec_name() {
                    return spec_name;
                }

                public void setSpec_name(String spec_name) {
                    this.spec_name = spec_name;
                }

                public String getItem_id() {
                    return item_id;
                }

                public void setItem_id(String item_id) {
                    this.item_id = item_id;
                }

                public String getItem() {
                    return item;
                }

                public void setItem(String item) {
                    this.item = item;
                }

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }
            }
        }


        public static class GalleryBean {
            /**
             * image_url : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/goods/2018/11-26/5bfb591e66618.jpg
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
            private String country_name;
            private String goods_num;
            private String add_time;
            private String order_amount;
            private String key_name;

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
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

            public String getKey_name() {
                return key_name;
            }

            public void setKey_name(String key_name) {
                this.key_name = key_name;
            }
        }

        public static class CommentBean {
            /**
             * head_pic : http://test.uonep.com/jushangmatou/Public/upload/head_pic/2018/11-26/5bfba8c632a7e.jpg
             * nickname : 匿名用户
             * add_time : 2018-11-26, 14:38:55
             * spec_key_name : 内存:32G 颜色:黑色
             * content : 非常的棒
             * impression :
             * comment_id : 369
             * goods_rank : 5.0
             * img : [{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb946d496ea.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb947c86bbf.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb9488ab9b9.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb949f3b0d0.jpg"},{"logo":"http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb94bd6c9f5.jpg"}]
             * parent_id : 00000000000
             * order_id : 381
             * user_id : 83
             * goods_num : 90
             */

            private String head_pic;
            private String nickname;
            private String add_time;
            private String spec_key_name;
            private String content;
            private String impression;
            private String comment_id;
            private String goods_rank;
            private String parent_id;
            private String order_id;
            private String user_id;
            private String goods_num;
            private List<ImgBean> img;

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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImpression() {
                return impression;
            }

            public void setImpression(String impression) {
                this.impression = impression;
            }

            public String getComment_id() {
                return comment_id;
            }

            public void setComment_id(String comment_id) {
                this.comment_id = comment_id;
            }

            public String getGoods_rank() {
                return goods_rank;
            }

            public void setGoods_rank(String goods_rank) {
                this.goods_rank = goods_rank;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public List<ImgBean> getImg() {
                return img;
            }

            public void setImg(List<ImgBean> img) {
                this.img = img;
            }

            public static class ImgBean {
                /**
                 * logo : http://test.uonep.com/jushangmatou/Public/upload/comment/2018/11-26/5bfb945f85d8a.jpg
                 */

                private String logo;

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }
            }
        }
    }
}
