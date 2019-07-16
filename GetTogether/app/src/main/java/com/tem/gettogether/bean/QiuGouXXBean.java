package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-04-22.
 */

public class QiuGouXXBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"goods_cate":[{"cat_id":"26","cat_name":"玩具"},{"cat_id":"27","cat_name":"工艺"},{"cat_id":"28","cat_name":"饰品"},{"cat_id":"29","cat_name":"宝珠"},{"cat_id":"39","cat_name":"服饰"},{"cat_id":"44","cat_name":"化妆品"},{"cat_id":"48","cat_name":"汽车配件"},{"cat_id":"53","cat_name":"包装"},{"cat_id":"54","cat_name":"数码电信"},{"cat_id":"55","cat_name":"五金工具"},{"cat_id":"64","cat_name":"量具"},{"cat_id":"70","cat_name":"母婴用品"},{"cat_id":"77","cat_name":"日用百货"}],"goods_type":[{"cat_id":"30","cat_name":"外贸大货"},{"cat_id":"31","cat_name":"内贸批发"},{"cat_id":"32","cat_name":"现货库存"},{"cat_id":"33","cat_name":"其他求购"},{"cat_id":"59","cat_name":"食品饮料"},{"cat_id":"60","cat_name":"医疗"},{"cat_id":"61","cat_name":"其他"}],"goods_time":[{"cat_id":"34","cat_name":"现货"},{"cat_id":"35","cat_name":"7天内"},{"cat_id":"36","cat_name":"15天内"},{"cat_id":"37","cat_name":"30天内"},{"cat_id":"38","cat_name":"两个月"}]}
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
        private List<GoodsCateBean> goods_cate;
        private List<GoodsTypeBean> goods_type;
        private List<GoodsTimeBean> goods_time;

        public List<GoodsCateBean> getGoods_cate() {
            return goods_cate;
        }

        public void setGoods_cate(List<GoodsCateBean> goods_cate) {
            this.goods_cate = goods_cate;
        }

        public List<GoodsTypeBean> getGoods_type() {
            return goods_type;
        }

        public void setGoods_type(List<GoodsTypeBean> goods_type) {
            this.goods_type = goods_type;
        }

        public List<GoodsTimeBean> getGoods_time() {
            return goods_time;
        }

        public void setGoods_time(List<GoodsTimeBean> goods_time) {
            this.goods_time = goods_time;
        }

        public static class GoodsCateBean {
            /**
             * cat_id : 26
             * cat_name : 玩具
             */

            private String cat_id;
            private String cat_name;

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
        }

        public static class GoodsTypeBean {
            /**
             * cat_id : 30
             * cat_name : 外贸大货
             */

            private String cat_id;
            private String cat_name;

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
        }

        public static class GoodsTimeBean {
            /**
             * cat_id : 34
             * cat_name : 现货
             */

            private String cat_id;
            private String cat_name;

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
        }
    }
}
