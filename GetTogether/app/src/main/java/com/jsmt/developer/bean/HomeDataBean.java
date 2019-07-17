package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-02-28.
 */

public class HomeDataBean {


    /**
     * status : 1
     * msg : 获取成功
     * result : {"ad":[{"ad_link":"http://test.uonep.com","ad_name":"自定义广告名称","ad_code":"http://test.uonep.com/jushangmatou/Public/upload/ad/2018/12-10/5c0dd9034f9e5.png"}],"notice":{"article_id":"33","title":"公告:双11超值1元福袋，即享七重特权，赠好礼！"},"top_cate":[{"category_id":"631","mobile_name":"","mobile_des":"","app_top_left_pic":"","app_top_right_pic":""},{"category_id":"488","mobile_name":"办公学习用品/文化体育用品/户外用品","mobile_des":"办公学习用品/文化体育用品/户外用品","app_top_left_pic":"","app_top_right_pic":""},{"category_id":"48","mobile_name":"","mobile_des":"","app_top_left_pic":"","app_top_right_pic":""},{"category_id":"4","mobile_name":"花类/饰品/工艺品/玩具/花类/饰品及配件/工艺品","mobile_des":"","app_top_left_pic":"","app_top_right_pic":""}],"bottom_cate":[{"category_id":"631","mobile_name":"","mobile_des":"","app_bottom_pic":""},{"category_id":"488","mobile_name":"办公学习用品/文化体育用品/户外用品","mobile_des":"办公学习用品/文化体育用品/户外用品","app_bottom_pic":""},{"category_id":"48","mobile_name":"","mobile_des":"","app_bottom_pic":""}],"store":[{"store_id":"13","store_name":"鑫奥星国际贸易有限公司","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-24/5bf96a7e4d38e.jpg","store_des":"","store_banner":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-30/5c01553cb94df.jpg"},{"store_id":"18","store_name":"丹奥思达汽配贸易","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2019/01-22/5c46c8b96b18b.jpg","store_des":"","store_banner":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-30/5c00b2cb8de77.gif"},{"store_id":"19","store_name":"珐媂娜化妆品","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/12-01/5c0281ad9541b.png","store_des":"专注高端化妆品","store_banner":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/12-01/5c0282e14ca26.png"}],"pavilion":[{"pavilion_id":0,"pavilion_name":"全部"},{"pavilion_id":"8","pavilion_name":"全国馆"},{"pavilion_id":"1","pavilion_name":"义乌馆"},{"pavilion_id":"4","pavilion_name":"广州馆"},{"pavilion_id":"3","pavilion_name":"深圳馆"},{"pavilion_id":"2","pavilion_name":"柯桥馆"},{"pavilion_id":"9","pavilion_name":"百姓馆"}]}
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
         * ad : [{"ad_link":"http://test.uonep.com","ad_name":"自定义广告名称","ad_code":"http://test.uonep.com/jushangmatou/Public/upload/ad/2018/12-10/5c0dd9034f9e5.png"}]
         * notice : {"article_id":"33","title":"公告:双11超值1元福袋，即享七重特权，赠好礼！"}
         * top_cate : [{"category_id":"631","mobile_name":"","mobile_des":"","app_top_left_pic":"","app_top_right_pic":""},{"category_id":"488","mobile_name":"办公学习用品/文化体育用品/户外用品","mobile_des":"办公学习用品/文化体育用品/户外用品","app_top_left_pic":"","app_top_right_pic":""},{"category_id":"48","mobile_name":"","mobile_des":"","app_top_left_pic":"","app_top_right_pic":""},{"category_id":"4","mobile_name":"花类/饰品/工艺品/玩具/花类/饰品及配件/工艺品","mobile_des":"","app_top_left_pic":"","app_top_right_pic":""}]
         * bottom_cate : [{"category_id":"631","mobile_name":"","mobile_des":"","app_bottom_pic":""},{"category_id":"488","mobile_name":"办公学习用品/文化体育用品/户外用品","mobile_des":"办公学习用品/文化体育用品/户外用品","app_bottom_pic":""},{"category_id":"48","mobile_name":"","mobile_des":"","app_bottom_pic":""}]
         * store : [{"store_id":"13","store_name":"鑫奥星国际贸易有限公司","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-24/5bf96a7e4d38e.jpg","store_des":"","store_banner":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-30/5c01553cb94df.jpg"},{"store_id":"18","store_name":"丹奥思达汽配贸易","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2019/01-22/5c46c8b96b18b.jpg","store_des":"","store_banner":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-30/5c00b2cb8de77.gif"},{"store_id":"19","store_name":"珐媂娜化妆品","store_logo":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/12-01/5c0281ad9541b.png","store_des":"专注高端化妆品","store_banner":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/12-01/5c0282e14ca26.png"}]
         * pavilion : [{"pavilion_id":0,"pavilion_name":"全部"},{"pavilion_id":"8","pavilion_name":"全国馆"},{"pavilion_id":"1","pavilion_name":"义乌馆"},{"pavilion_id":"4","pavilion_name":"广州馆"},{"pavilion_id":"3","pavilion_name":"深圳馆"},{"pavilion_id":"2","pavilion_name":"柯桥馆"},{"pavilion_id":"9","pavilion_name":"百姓馆"}]
         */

        private NoticeBean notice;
        private List<AdBean> ad;
        private List<TopCateBean> top_cate;
        private List<BottomCateBean> bottom_cate;
        private List<StoreBean> store;
        private List<PavilionBean> pavilion;

        public NoticeBean getNotice() {
            return notice;
        }

        public void setNotice(NoticeBean notice) {
            this.notice = notice;
        }

        public List<AdBean> getAd() {
            return ad;
        }

        public void setAd(List<AdBean> ad) {
            this.ad = ad;
        }

        public List<TopCateBean> getTop_cate() {
            return top_cate;
        }

        public void setTop_cate(List<TopCateBean> top_cate) {
            this.top_cate = top_cate;
        }

        public List<BottomCateBean> getBottom_cate() {
            return bottom_cate;
        }

        public void setBottom_cate(List<BottomCateBean> bottom_cate) {
            this.bottom_cate = bottom_cate;
        }

        public List<StoreBean> getStore() {
            return store;
        }

        public void setStore(List<StoreBean> store) {
            this.store = store;
        }

        public List<PavilionBean> getPavilion() {
            return pavilion;
        }

        public void setPavilion(List<PavilionBean> pavilion) {
            this.pavilion = pavilion;
        }

        public static class NoticeBean {
            /**
             * article_id : 33
             * title : 公告:双11超值1元福袋，即享七重特权，赠好礼！
             */

            private String article_id;
            private String title;

            public String getArticle_id() {
                return article_id;
            }

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class AdBean {
            /**
             * ad_link : http://test.uonep.com
             * ad_name : 自定义广告名称
             * ad_code : http://test.uonep.com/jushangmatou/Public/upload/ad/2018/12-10/5c0dd9034f9e5.png
             */

            private String ad_link;
            private String ad_name;
            private String ad_code;
            private int type;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public String getAd_name() {
                return ad_name;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }
        }

        public static class TopCateBean {
            /**
             * category_id : 631
             * mobile_name :
             * mobile_des :
             * app_top_left_pic :
             * app_top_right_pic :
             */

            private String category_id;
            private String mobile_name;
            private String mobile_des;
            private String app_top_left_pic;
            private String app_top_right_pic;

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

            public String getMobile_des() {
                return mobile_des;
            }

            public void setMobile_des(String mobile_des) {
                this.mobile_des = mobile_des;
            }

            public String getApp_top_left_pic() {
                return app_top_left_pic;
            }

            public void setApp_top_left_pic(String app_top_left_pic) {
                this.app_top_left_pic = app_top_left_pic;
            }

            public String getApp_top_right_pic() {
                return app_top_right_pic;
            }

            public void setApp_top_right_pic(String app_top_right_pic) {
                this.app_top_right_pic = app_top_right_pic;
            }
        }

        public static class BottomCateBean {
            /**
             * category_id : 631
             * mobile_name :
             * mobile_des :
             * app_bottom_pic :
             */

            private String category_id;
            private String mobile_name;
            private String mobile_des;
            private String app_bottom_pic;
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

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

            public String getMobile_des() {
                return mobile_des;
            }

            public void setMobile_des(String mobile_des) {
                this.mobile_des = mobile_des;
            }

            public String getApp_bottom_pic() {
                return app_bottom_pic;
            }

            public void setApp_bottom_pic(String app_bottom_pic) {
                this.app_bottom_pic = app_bottom_pic;
            }
        }

        public static class StoreBean {
            /**
             * store_id : 13
             * store_name : 鑫奥星国际贸易有限公司
             * store_logo : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-24/5bf96a7e4d38e.jpg
             * store_des :
             * store_banner : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/seller/2018/11-30/5c01553cb94df.jpg
             */

            private String store_id;
            private String store_name;
            private String store_logo;
            private String store_des;
            private String store_banner;

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

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }

            public String getStore_des() {
                return store_des;
            }

            public void setStore_des(String store_des) {
                this.store_des = store_des;
            }

            public String getStore_banner() {
                return store_banner;
            }

            public void setStore_banner(String store_banner) {
                this.store_banner = store_banner;
            }
        }

        public static class PavilionBean {
            /**
             * pavilion_id : 0
             * pavilion_name : 全部
             */

            private int pavilion_id;
            private String pavilion_name;

            public int getPavilion_id() {
                return pavilion_id;
            }

            public void setPavilion_id(int pavilion_id) {
                this.pavilion_id = pavilion_id;
            }

            public String getPavilion_name() {
                return pavilion_name;
            }

            public void setPavilion_name(String pavilion_name) {
                this.pavilion_name = pavilion_name;
            }
        }
    }
}
