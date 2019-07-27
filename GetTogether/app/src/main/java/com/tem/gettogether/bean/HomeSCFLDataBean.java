package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-02-28.
 */

public class HomeSCFLDataBean {
    /**
     * status : 1
     * msg : 获取成功
     * result : {"pavilion":{"app_banner":"","name":"龙胜手机批发中心","app_des":""},"pavilion_cate":[{"category_id":"1394","floor":"1F","goods_category":[{"category_id":"3728","name":"数码产品","app_image":""}]},{"category_id":"1397","floor":"2F","goods_category":[{"category_id":"3731","name":"手机配件","app_image":""},{"category_id":"3732","name":"电脑配件","app_image":""}]}]}
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
         * pavilion : {"app_banner":"","name":"龙胜手机批发中心","app_des":""}
         * pavilion_cate : [{"category_id":"1394","floor":"1F","goods_category":[{"category_id":"3728","name":"数码产品","app_image":""}]},{"category_id":"1397","floor":"2F","goods_category":[{"category_id":"3731","name":"手机配件","app_image":""},{"category_id":"3732","name":"电脑配件","app_image":""}]}]
         */

        private PavilionBean pavilion;
        private List<PavilionCateBean> pavilion_cate;

        public PavilionBean getPavilion() {
            return pavilion;
        }

        public void setPavilion(PavilionBean pavilion) {
            this.pavilion = pavilion;
        }

        public List<PavilionCateBean> getPavilion_cate() {
            return pavilion_cate;
        }

        public void setPavilion_cate(List<PavilionCateBean> pavilion_cate) {
            this.pavilion_cate = pavilion_cate;
        }

        public static class PavilionBean {
            /**
             * app_banner :
             * name : 龙胜手机批发中心
             * app_des :
             */

            private String app_banner;
            private String name;
            private String app_des;
            private String app_link;

            public String getApp_link() {
                return app_link;
            }

            public void setApp_link(String app_link) {
                this.app_link = app_link;
            }

            public String getApp_banner() {
                return app_banner;
            }

            public void setApp_banner(String app_banner) {
                this.app_banner = app_banner;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getApp_des() {
                return app_des;
            }

            public void setApp_des(String app_des) {
                this.app_des = app_des;
            }
        }

        public static class PavilionCateBean {
            /**
             * category_id : 1394
             * floor : 1F
             * goods_category : [{"category_id":"3728","name":"数码产品","app_image":""}]
             */

            private String category_id;
            private String floor;
            private List<GoodsCategoryBean> goods_category;

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getFloor() {
                return floor;
            }

            public void setFloor(String floor) {
                this.floor = floor;
            }

            public List<GoodsCategoryBean> getGoods_category() {
                return goods_category;
            }

            public void setGoods_category(List<GoodsCategoryBean> goods_category) {
                this.goods_category = goods_category;
            }

            public static class GoodsCategoryBean {
                /**
                 * category_id : 3728
                 * name : 数码产品
                 * app_image :
                 */

                private String category_id;
                private String name;
                private String app_image;

                public String getCategory_id() {
                    return category_id;
                }

                public void setCategory_id(String category_id) {
                    this.category_id = category_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getApp_image() {
                    return app_image;
                }

                public void setApp_image(String app_image) {
                    this.app_image = app_image;
                }
            }
        }
    }


//    /**
//     * status : 1
//     * msg : 获取成功
//     * result : [{"mobile_name":"办公学习用品/文化体育用品/户外用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"488","parent_id":"0","sub_category":[{"mobile_name":"办公学习用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"489","parent_id":"488"},{"mobile_name":"文化体育用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"494","parent_id":"488"},{"mobile_name":"户外用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"497","parent_id":"488"}]},{"mobile_name":"办公学习用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"489","parent_id":"488","sub_category":[{"mobile_name":"办公学习用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"489","parent_id":"488"}]},{"mobile_name":"文化体育用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"494","parent_id":"488","sub_category":[{"mobile_name":"文化体育用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"494","parent_id":"488"}]},{"mobile_name":"户外用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"497","parent_id":"488","sub_category":[{"mobile_name":"户外用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"497","parent_id":"488"}]},{"mobile_name":"","app_image":"","category_id":"1011","parent_id":"494","sub_category":[{"mobile_name":"","app_image":"","category_id":"1011","parent_id":"494"}]},{"mobile_name":"投影幕布","app_image":"","category_id":"1003","parent_id":"489","sub_category":[{"mobile_name":"投影幕布","app_image":"","category_id":"1003","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1008","parent_id":"489","sub_category":[{"mobile_name":"","app_image":"","category_id":"1008","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1005","parent_id":"489","sub_category":[{"mobile_name":"","app_image":"","category_id":"1005","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1006","parent_id":"489","sub_category":[{"mobile_name":"","app_image":"","category_id":"1006","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1007","parent_id":"489","sub_category":[{"mobile_name":"","app_image":"","category_id":"1007","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1009","parent_id":"489","sub_category":[{"mobile_name":"","app_image":"","category_id":"1009","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1010","parent_id":"489","sub_category":[{"mobile_name":"","app_image":"","category_id":"1010","parent_id":"489"}]},{"mobile_name":"","app_image":"","category_id":"1012","parent_id":"494","sub_category":[{"mobile_name":"","app_image":"","category_id":"1012","parent_id":"494"}]},{"mobile_name":"","app_image":"","category_id":"1013","parent_id":"494","sub_category":[{"mobile_name":"","app_image":"","category_id":"1013","parent_id":"494"}]},{"mobile_name":"","app_image":"","category_id":"1014","parent_id":"494","sub_category":[{"mobile_name":"","app_image":"","category_id":"1014","parent_id":"494"}]},{"mobile_name":"","app_image":"","category_id":"1015","parent_id":"494","sub_category":[{"mobile_name":"","app_image":"","category_id":"1015","parent_id":"494"}]},{"mobile_name":"","app_image":"","category_id":"1016","parent_id":"497","sub_category":[{"mobile_name":"","app_image":"","category_id":"1016","parent_id":"497"}]},{"mobile_name":"","app_image":"","category_id":"1017","parent_id":"497","sub_category":[{"mobile_name":"","app_image":"","category_id":"1017","parent_id":"497"}]},{"mobile_name":"","app_image":"","category_id":"1018","parent_id":"497","sub_category":[{"mobile_name":"","app_image":"","category_id":"1018","parent_id":"497"}]},{"mobile_name":"","app_image":"","category_id":"1020","parent_id":"497","sub_category":[{"mobile_name":"","app_image":"","category_id":"1020","parent_id":"497"}]}]
//     */
//
//    private int status;
//    private String msg;
//    private List<ResultBean> result;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<ResultBean> getResult() {
//        return result;
//    }
//
//    public void setResult(List<ResultBean> result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        /**
//         * mobile_name : 办公学习用品/文化体育用品/户外用品
//         * app_image : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png
//         * category_id : 488
//         * parent_id : 0
//         * sub_category : [{"mobile_name":"办公学习用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"489","parent_id":"488"},{"mobile_name":"文化体育用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"494","parent_id":"488"},{"mobile_name":"户外用品","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"497","parent_id":"488"}]
//         */
//
//        private String mobile_name;
//        private String app_image;
//        private String category_id;
//        private String parent_id;
//        private List<SubCategoryBean> sub_category;
//
//        public String getMobile_name() {
//            return mobile_name;
//        }
//
//        public void setMobile_name(String mobile_name) {
//            this.mobile_name = mobile_name;
//        }
//
//        public String getApp_image() {
//            return app_image;
//        }
//
//        public void setApp_image(String app_image) {
//            this.app_image = app_image;
//        }
//
//        public String getCategory_id() {
//            return category_id;
//        }
//
//        public void setCategory_id(String category_id) {
//            this.category_id = category_id;
//        }
//
//        public String getParent_id() {
//            return parent_id;
//        }
//
//        public void setParent_id(String parent_id) {
//            this.parent_id = parent_id;
//        }
//
//        public List<SubCategoryBean> getSub_category() {
//            return sub_category;
//        }
//
//        public void setSub_category(List<SubCategoryBean> sub_category) {
//            this.sub_category = sub_category;
//        }
//
//        public static class SubCategoryBean {
//            /**
//             * mobile_name : 办公学习用品
//             * app_image : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png
//             * category_id : 489
//             * parent_id : 488
//             */
//
//            private String mobile_name;
//            private String app_image;
//            private String category_id;
//            private String parent_id;
//
//            public String getMobile_name() {
//                return mobile_name;
//            }
//
//            public void setMobile_name(String mobile_name) {
//                this.mobile_name = mobile_name;
//            }
//
//            public String getApp_image() {
//                return app_image;
//            }
//
//            public void setApp_image(String app_image) {
//                this.app_image = app_image;
//            }
//
//            public String getCategory_id() {
//                return category_id;
//            }
//
//            public void setCategory_id(String category_id) {
//                this.category_id = category_id;
//            }
//
//            public String getParent_id() {
//                return parent_id;
//            }
//
//            public void setParent_id(String parent_id) {
//                this.parent_id = parent_id;
//            }
//        }
//    }

}
