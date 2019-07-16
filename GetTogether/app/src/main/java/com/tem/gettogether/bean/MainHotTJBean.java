package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-04-19.
 */

public class MainHotTJBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"banner":{"ad_name":"自定义广告名称","ad_link":"","ad_code":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/ad/2019/04-19/5cb92cfbc7eb3.png","describe":"自定义广告描述"},"list":[{"goods_id":"1530","goods_name":"领宽松女装薄款针织衫","shop_price":"56.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4d2a22c09.png","batch_number":"55"},{"goods_id":"1531","goods_name":"气质显瘦清新可爱女神针织衫 半身裙两件套","shop_price":"0.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4dca0a6f3.png","batch_number":"225"},{"goods_id":"1532","goods_name":"春季新款女式慵懒蝙蝠袖羊毛打底衫一字领宽松女装薄款针织衫","shop_price":"55.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4e44d1119.jpg","batch_number":"2"},{"goods_id":"1533","goods_name":"新款糖果色t恤女短袖学生宽松韩国ins半袖上衣","shop_price":"12.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4e68f3388.png","batch_number":"10"},{"goods_id":"1534","goods_name":"气质时尚长裙大摆裙","shop_price":"340.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4f14c9ae8.png","batch_number":"338"},{"goods_id":"1535","goods_name":"双排扣长款西装外套","shop_price":"128.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4f9bdf25c.png","batch_number":"126"},{"goods_id":"1536","goods_name":"夏季男士短袖T恤圆领纯色体恤打底衫韩版半袖男式上衣男装青年潮","shop_price":"45.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4fb94f85c.jpg","batch_number":"2"},{"goods_id":"1537","goods_name":"新款欧美高端名媛气质女装网纱蕾丝裙两件套","shop_price":"285.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad5010a1c30.png","batch_number":"280"},{"goods_id":"1538","goods_name":"新款复古棉麻印花女士长袖宽松收腰系带显瘦连衣裙","shop_price":"140.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad5246d2ee2.png","batch_number":"139"},{"goods_id":"1539","goods_name":"金丝线针织裙两件套 V领上衣+裙子 显瘦 ","shop_price":"231.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad52daeb8a0.png","batch_number":"230"}]}
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
         * banner : {"ad_name":"自定义广告名称","ad_link":"","ad_code":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/ad/2019/04-19/5cb92cfbc7eb3.png","describe":"自定义广告描述"}
         * list : [{"goods_id":"1530","goods_name":"领宽松女装薄款针织衫","shop_price":"56.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4d2a22c09.png","batch_number":"55"},{"goods_id":"1531","goods_name":"气质显瘦清新可爱女神针织衫 半身裙两件套","shop_price":"0.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4dca0a6f3.png","batch_number":"225"},{"goods_id":"1532","goods_name":"春季新款女式慵懒蝙蝠袖羊毛打底衫一字领宽松女装薄款针织衫","shop_price":"55.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4e44d1119.jpg","batch_number":"2"},{"goods_id":"1533","goods_name":"新款糖果色t恤女短袖学生宽松韩国ins半袖上衣","shop_price":"12.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4e68f3388.png","batch_number":"10"},{"goods_id":"1534","goods_name":"气质时尚长裙大摆裙","shop_price":"340.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4f14c9ae8.png","batch_number":"338"},{"goods_id":"1535","goods_name":"双排扣长款西装外套","shop_price":"128.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4f9bdf25c.png","batch_number":"126"},{"goods_id":"1536","goods_name":"夏季男士短袖T恤圆领纯色体恤打底衫韩版半袖男式上衣男装青年潮","shop_price":"45.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4fb94f85c.jpg","batch_number":"2"},{"goods_id":"1537","goods_name":"新款欧美高端名媛气质女装网纱蕾丝裙两件套","shop_price":"285.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad5010a1c30.png","batch_number":"280"},{"goods_id":"1538","goods_name":"新款复古棉麻印花女士长袖宽松收腰系带显瘦连衣裙","shop_price":"140.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad5246d2ee2.png","batch_number":"139"},{"goods_id":"1539","goods_name":"金丝线针织裙两件套 V领上衣+裙子 显瘦 ","shop_price":"231.00","original_img":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad52daeb8a0.png","batch_number":"230"}]
         */

        private BannerBean banner;
        private List<ListBean> list;

        public BannerBean getBanner() {
            return banner;
        }

        public void setBanner(BannerBean banner) {
            this.banner = banner;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class BannerBean {
            /**
             * ad_name : 自定义广告名称
             * ad_link :
             * ad_code : http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/ad/2019/04-19/5cb92cfbc7eb3.png
             * describe : 自定义广告描述
             */

            private String ad_name;
            private String ad_link;
            private String ad_code;
            private String describe;

            public String getAd_name() {
                return ad_name;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }
        }

        public static class ListBean {
            /**
             * goods_id : 1530
             * goods_name : 领宽松女装薄款针织衫
             * shop_price : 56.00
             * original_img : http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/2019/04-10/5cad4d2a22c09.png
             * batch_number : 55
             */

            private String goods_id;
            private String goods_name;
            private String shop_price;
            private String original_img;
            private String batch_number;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(String original_img) {
                this.original_img = original_img;
            }

            public String getBatch_number() {
                return batch_number;
            }

            public void setBatch_number(String batch_number) {
                this.batch_number = batch_number;
            }
        }
    }
}
