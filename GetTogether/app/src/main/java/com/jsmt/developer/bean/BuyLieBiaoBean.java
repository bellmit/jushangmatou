package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-14.
 */

public class BuyLieBiaoBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"126","user_id":"194","session_id":"i5fqtfv8s622ef1lnnth340td3","goods_name":"眼镜","goods_desc":"太阳镜","goods_cate":"日用百货","release_type":"其他","attach_time":"15天内","goods_num":"按商户起订量","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103921_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103942_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103944_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103945_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103948_.jpeg"],"add_time":"2019-05-06 10:40","mobile":null,"email":""},{"id":"118","user_id":"144","session_id":"2fj1hj1igbu65qp3i0itffec70","goods_name":"估计hs","goods_desc":"思路图","goods_cate":"工艺","release_type":"内贸批发","attach_time":"15天内","goods_num":"按商户起订量","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190501/20190501211105_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190501/20190501211106_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190501/20190501211106_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190501/20190501211107_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190501/20190501211107_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190501/20190501211107_.jpeg"],"add_time":"2019-05-01 21:12","mobile":"15605811887","email":""},{"id":"102","user_id":"176","session_id":"vh5ms0tcl27gedpveitbn2p624","goods_name":"汽车配件","goods_desc":"厂家直销","goods_cate":"工艺","release_type":"现货库存","attach_time":"15天内","goods_num":"按商户起订量","goods_logo":["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190426/20190426103132_.jpeg"],"add_time":"2019-04-26 10:32","mobile":"13676822927","email":""}]
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
         * id : 126
         * user_id : 194
         * session_id : i5fqtfv8s622ef1lnnth340td3
         * goods_name : 眼镜
         * goods_desc : 太阳镜
         * goods_cate : 日用百货
         * release_type : 其他
         * attach_time : 15天内
         * goods_num : 按商户起订量
         * goods_logo : ["http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103921_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103942_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103944_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103945_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103947_.jpeg","http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190506/20190506103948_.jpeg"]
         * add_time : 2019-05-06 10:40
         * mobile : null
         * email :
         */

        private String id;
        private String user_id;
        private String session_id;
        private String goods_name;
        private String goods_desc;
        private String goods_cate;
        private String release_type;
        private String attach_time;
        private String goods_num;
        private String add_time;
        private Object mobile;
        private String email;
        private List<String> goods_logo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_desc() {
            return goods_desc;
        }

        public void setGoods_desc(String goods_desc) {
            this.goods_desc = goods_desc;
        }

        public String getGoods_cate() {
            return goods_cate;
        }

        public void setGoods_cate(String goods_cate) {
            this.goods_cate = goods_cate;
        }

        public String getRelease_type() {
            return release_type;
        }

        public void setRelease_type(String release_type) {
            this.release_type = release_type;
        }

        public String getAttach_time() {
            return attach_time;
        }

        public void setAttach_time(String attach_time) {
            this.attach_time = attach_time;
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

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public List<String> getGoods_logo() {
            return goods_logo;
        }

        public void setGoods_logo(List<String> goods_logo) {
            this.goods_logo = goods_logo;
        }
    }
}
