package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-06.
 */

public class AddShopFLBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"742","name":"服装","parent_id":"0","son":[{"id":"749","name":"男装","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"748","name":"女装","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5194","name":"童鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5129","name":"童装","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5139","name":"男鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5179","name":"女鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5147","name":"家居鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]}]},{"id":"3081","name":" 韩国进口服装城","parent_id":"0","son":[{"id":"3130","name":"男装","parent_id":"3081","son":[{"id":"3130","name":"男装","parent_id":"3081"},{"id":"3131","name":"女装","parent_id":"3081"}]},{"id":"3131","name":"女装","parent_id":"3081","son":[{"id":"3130","name":"男装","parent_id":"3081"},{"id":"3131","name":"女装","parent_id":"3081"}]}]}]
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
         * id : 742
         * name : 服装
         * parent_id : 0
         * son : [{"id":"749","name":"男装","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"748","name":"女装","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5194","name":"童鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5129","name":"童装","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5139","name":"男鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5179","name":"女鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]},{"id":"5147","name":"家居鞋","parent_id":"742","son":[{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]}]
         */

        private String id;
        private String name;
        private String parent_id;
        private List<SonBeanX> son;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
             * id : 749
             * name : 男装
             * parent_id : 742
             * son : [{"id":"749","name":"男装","parent_id":"742"},{"id":"748","name":"女装","parent_id":"742"},{"id":"5194","name":"童鞋","parent_id":"742"},{"id":"5129","name":"童装","parent_id":"742"},{"id":"5139","name":"男鞋","parent_id":"742"},{"id":"5179","name":"女鞋","parent_id":"742"},{"id":"5147","name":"家居鞋","parent_id":"742"}]
             */

            private String id;
            private String name;
            private String parent_id;
            private List<SonBean> son;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
                 * id : 749
                 * name : 男装
                 * parent_id : 742
                 */

                private String id;
                private String name;
                private String parent_id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
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
