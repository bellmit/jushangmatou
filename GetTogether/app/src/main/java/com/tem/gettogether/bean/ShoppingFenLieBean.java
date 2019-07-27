package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-04.
 */

public class ShoppingFenLieBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"cat_id":"600","cat_name":"男装","parent_id":"0","store_id":"50","children":[{"cat_id":"604","cat_name":"袜子","parent_id":"600","store_id":"50","children":[]},{"cat_id":"603","cat_name":"秋裤","parent_id":"600","store_id":"50","children":[]},{"cat_id":"602","cat_name":"秋裤","parent_id":"600","store_id":"50","children":[]},{"cat_id":"601","cat_name":"上衣","parent_id":"600","store_id":"50","children":[]}]},{"cat_id":"578","cat_name":"女装","parent_id":"0","store_id":"50","children":[{"cat_id":"599","cat_name":"袜子","parent_id":"578","store_id":"50","children":[]},{"cat_id":"598","cat_name":"堆堆袜","parent_id":"578","store_id":"50","children":[]},{"cat_id":"597","cat_name":"风衣","parent_id":"578","store_id":"50","children":[]},{"cat_id":"596","cat_name":"皮裤","parent_id":"578","store_id":"50","children":[]},{"cat_id":"595","cat_name":"皮裤","parent_id":"578","store_id":"50","children":[]},{"cat_id":"594","cat_name":"牛仔裤","parent_id":"578","store_id":"50","children":[]},{"cat_id":"593","cat_name":"上衣","parent_id":"578","store_id":"50","children":[]},{"cat_id":"592","cat_name":"T恤","parent_id":"578","store_id":"50","children":[]},{"cat_id":"581","cat_name":"内衣","parent_id":"578","store_id":"50","children":[]},{"cat_id":"580","cat_name":"女士内裤","parent_id":"578","store_id":"50","children":[]},{"cat_id":"579","cat_name":"打底裤","parent_id":"578","store_id":"50","children":[]}]},{"cat_id":"564","cat_name":"电子产品","parent_id":"0","store_id":"50","children":[{"cat_id":"577","cat_name":"电脑","parent_id":"564","store_id":"50","children":[]},{"cat_id":"568","cat_name":"低音炮","parent_id":"564","store_id":"50","children":[]},{"cat_id":"567","cat_name":"电板","parent_id":"564","store_id":"50","children":[]},{"cat_id":"566","cat_name":"电子","parent_id":"564","store_id":"50","children":[{"cat_id":"582","cat_name":"LED灯","parent_id":"566","store_id":"50","children":[]}]},{"cat_id":"565","cat_name":"电筒","parent_id":"564","store_id":"50","children":[]}]}]
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
         * cat_id : 600
         * cat_name : 男装
         * parent_id : 0
         * store_id : 50
         * children : [{"cat_id":"604","cat_name":"袜子","parent_id":"600","store_id":"50","children":[]},{"cat_id":"603","cat_name":"秋裤","parent_id":"600","store_id":"50","children":[]},{"cat_id":"602","cat_name":"秋裤","parent_id":"600","store_id":"50","children":[]},{"cat_id":"601","cat_name":"上衣","parent_id":"600","store_id":"50","children":[]}]
         */

        private String cat_id;
        private String cat_name;
        private String parent_id;
        private String store_id;
        private List<ChildrenBean> children;

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

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * cat_id : 604
             * cat_name : 袜子
             * parent_id : 600
             * store_id : 50
             * children : []
             */

            private String cat_id;
            private String cat_name;
            private String parent_id;
            private String store_id;
            private List<?> children;

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

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }
    }
}
