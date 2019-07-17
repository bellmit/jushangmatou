package com.jsmt.developer.bean;

/**
 * Created by lt on 2019-02-28.
 */

public class MessageTGBean {

    /**
     * status : 1
     * msg : success
     * result : {"article_id":"33","title":"公告:双11超值1元福袋，即享七重特权，赠好礼！","content":" 公告:双11超值1元福袋，即享七重特权，赠好礼！  "}
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
         * article_id : 33
         * title : 公告:双11超值1元福袋，即享七重特权，赠好礼！
         * content :  公告:双11超值1元福袋，即享七重特权，赠好礼！
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
