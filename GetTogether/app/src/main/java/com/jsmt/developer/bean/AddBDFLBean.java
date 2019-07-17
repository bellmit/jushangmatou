package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-06.
 */

public class AddBDFLBean {


    /**
     * status : 1
     * msg : 获取成功
     * result : [{"cat_id":"757","cat_name":"鞋靴","parent_id":"0","son":[{"cat_id":"758","cat_name":"男鞋","parent_id":"757","son":[{"cat_id":"941","cat_name":"测试","parent_id":"758"}]}]}]
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
         * cat_id : 757
         * cat_name : 鞋靴
         * parent_id : 0
         * son : [{"cat_id":"758","cat_name":"男鞋","parent_id":"757","son":[{"cat_id":"941","cat_name":"测试","parent_id":"758"}]}]
         */

        private String cat_id;
        private String cat_name;
        private String parent_id;
        private List<SonBeanX> son;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public List<SonBeanX> getSon() {
            return son;
        }

        public void setSon(List<SonBeanX> son) {
            this.son = son;
        }

        public static class SonBeanX {
            /**
             * cat_id : 758
             * cat_name : 男鞋
             * parent_id : 757
             * son : [{"cat_id":"941","cat_name":"测试","parent_id":"758"}]
             */

            private String cat_id;
            private String cat_name;
            private String parent_id;
            private List<SonBean> son;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public List<SonBean> getSon() {
                return son;
            }

            public void setSon(List<SonBean> son) {
                this.son = son;
            }

            public static class SonBean {
                /**
                 * cat_id : 941
                 * cat_name : 测试
                 * parent_id : 758
                 */

                private String cat_id;
                private String cat_name;
                private String parent_id;

                public String getCat_id() {
                    return cat_id;
                }

                public void setCat_id(String cat_id) {
                    this.cat_id = cat_id;
                }

                public String getCat_name() {
                    return cat_name;
                }

                public void setCat_name(String cat_name) {
                    this.cat_name = cat_name;
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
}
