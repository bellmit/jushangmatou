package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-04-18.
 */

public class ShoppingKuBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"goods_list":[{"goods_id":"3367","goods_name":"内蒙特产 风干牛肉干500g 即食牛肉厂家批发 休闲牛肉零食","shop_price":"0.00","batch_number":"100","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/3367/goods_thumb_3367_400_400.png"},{"goods_id":"3364","goods_name":"酥品惠 义乌特产红糖酥饼传统糕点 独立包装办公 休闲零食","shop_price":"0.00","batch_number":"30","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/3364/goods_thumb_3364_400_400.png"},{"goods_id":"3359","goods_name":"产地货源 浙江临安特产山核桃仁250g罐装 散装休闲食品 小核桃仁","shop_price":"0.00","batch_number":"33","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/3359/goods_thumb_3359_400_400.png"},{"goods_id":"2870","goods_name":"卤牛肉干真空袋","shop_price":"55.00","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2870/goods_thumb_2870_400_400.png"},{"goods_id":"2868","goods_name":" 冷冻腌制牛肉","shop_price":"10.90","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2868/goods_thumb_2868_400_400.png"},{"goods_id":"2863","goods_name":"手工香菇猪肉丸","shop_price":"22.00","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2863/goods_thumb_2863_400_400.png"},{"goods_id":"2858","goods_name":"德先生潮州汕头特产鲜牛肉牛筋丸火锅专用","shop_price":"25.00","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2858/goods_thumb_2858_400_400.png"},{"goods_id":"2852","goods_name":"汕头牛筋丸","shop_price":"25.00","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2852/goods_thumb_2852_400_400.png"},{"goods_id":"2848","goods_name":"农村土鸡蛋","shop_price":"185.00","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2848/goods_thumb_2848_400_400.png"},{"goods_id":"2844","goods_name":"农家乌鸡蛋","shop_price":"26.90","batch_number":"32","best_percent":"100%","image":"http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/2844/goods_thumb_2844_400_400.png"}]}
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
        private List<GoodsListBean> goods_list;

        public List<GoodsListBean> getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(List<GoodsListBean> goods_list) {
            this.goods_list = goods_list;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 3367
             * goods_name : 内蒙特产 风干牛肉干500g 即食牛肉厂家批发 休闲牛肉零食
             * shop_price : 0.00
             * batch_number : 100
             * best_percent : 100%
             * image : http://www.jsgogogo.cn/jushangmatou/Public/upload/goods/thumb/3367/goods_thumb_3367_400_400.png
             */

            private String goods_id;
            private String goods_name;
            private String shop_price;
            private String batch_number;
            private String best_percent;
            private String image;

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

            public String getBatch_number() {
                return batch_number;
            }

            public void setBatch_number(String batch_number) {
                this.batch_number = batch_number;
            }

            public String getBest_percent() {
                return best_percent;
            }

            public void setBest_percent(String best_percent) {
                this.best_percent = best_percent;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
