package com.jsmt.developer.bean;

/**
 * Created by lt on 2019-03-12.
 */

public class GYWMBean {

    /**
     * status : 1
     * msg : success
     * result : {"article_id":"25","title":"关于我们","content":" 聚商码头成立于2018年10月，注册资金5000万，公司位于中国最大的小商品批发市场--浙江义乌。本着客户第一，诚信至上的服务宗旨，开阔思维，乘势而上，致力于打造客商之间快速，安全，便捷交易的桥梁。打造全球最大的线上线下外贸平台，面向浙江义乌小商品城，浙江柯桥轻纺城、广州批发城、深圳电子城，乃至全国外贸批发市场招商，创建全球货源基地，汇集全国商品，把商家的产品推向全球的每个码头。  "}
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
         * article_id : 25
         * title : 关于我们
         * content :  聚商码头成立于2018年10月，注册资金5000万，公司位于中国最大的小商品批发市场--浙江义乌。本着客户第一，诚信至上的服务宗旨，开阔思维，乘势而上，致力于打造客商之间快速，安全，便捷交易的桥梁。打造全球最大的线上线下外贸平台，面向浙江义乌小商品城，浙江柯桥轻纺城、广州批发城、深圳电子城，乃至全国外贸批发市场招商，创建全球货源基地，汇集全国商品，把商家的产品推向全球的每个码头。
         */

        private String article_id;
        private String title;
        private String content;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
