package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-08.
 */

public class FenLieRightBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"mobile_name":"玩具","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"14","parent_id":"1","sub_category":[{"mobile_name":"玩具","app_image":"","category_id":"792","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"867","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"868","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"869","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"870","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"871","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"872","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"873","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"874","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"875","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"876","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"877","parent_id":"14"}]},{"mobile_name":"花类","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"19","parent_id":"1","sub_category":[{"mobile_name":"花类","app_image":"","category_id":"793","parent_id":"19"},{"mobile_name":"","app_image":"","category_id":"878","parent_id":"19"},{"mobile_name":"","app_image":"","category_id":"879","parent_id":"19"}]},{"mobile_name":"花类配件","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"21","parent_id":"1","sub_category":[{"mobile_name":"花类配件","app_image":"","category_id":"794","parent_id":"21"}]},{"mobile_name":"辅房","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"36","parent_id":"1","sub_category":[{"mobile_name":"辅房","app_image":"http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png","category_id":"36","parent_id":"1"}]}]
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
         * mobile_name : 玩具
         * app_image : http://test.uonep.com/jushangmatou/jushangmatou/Public/upload/category/2018/12-01/5c023d9988f2c.png
         * category_id : 14
         * parent_id : 1
         * sub_category : [{"mobile_name":"玩具","app_image":"","category_id":"792","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"867","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"868","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"869","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"870","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"871","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"872","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"873","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"874","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"875","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"876","parent_id":"14"},{"mobile_name":"","app_image":"","category_id":"877","parent_id":"14"}]
         */

        private String mobile_name;
        private String app_image;
        private String category_id;
        private String parent_id;
        private String app_banner;
        private String app_link;

        public String getApp_banner() {
            return app_banner;
        }

        public void setApp_banner(String app_banner) {
            this.app_banner = app_banner;
        }

        public String getApp_link() {
            return app_link;
        }

        public void setApp_link(String app_link) {
            this.app_link = app_link;
        }

        private List<SubCategoryBean> sub_category;

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

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public List<SubCategoryBean> getSub_category() {
            return sub_category;
        }

        public void setSub_category(List<SubCategoryBean> sub_category) {
            this.sub_category = sub_category;
        }

        public static class SubCategoryBean {
            /**
             * mobile_name : 玩具
             * app_image :
             * category_id : 792
             * parent_id : 14
             */

            private String mobile_name;
            private String app_image;
            private String category_id;
            private String parent_id;
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }
        }
    }
}
