package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-01.
 */

public class HotSouSuoBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"num":"13","content":"刹车片"},{"num":"10","content":"笔记本"},{"num":"8","content":"饰品"},{"num":"7","content":"眼影"},{"num":"7","content":"口红"},{"num":"6","content":"丹"},{"num":"6","content":"手机"},{"num":"5","content":"打底裤"}]
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
         * num : 13
         * content : 刹车片
         */

        private String num;
        private String content;

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
