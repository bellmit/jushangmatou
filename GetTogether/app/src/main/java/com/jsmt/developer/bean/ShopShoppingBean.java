package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-07.
 */

public class ShopShoppingBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"goods_list":[{"goods_id":"633","goods_name":"新款窗帘 韩式公主风双层渐变镂空星星窗帘 配套窗纱 遮光窗帘布","shop_price":"0.30","batch_number":"9999","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/633/goods_thumb_633_400_400.jpeg"},{"goods_id":"632","goods_name":"热销高档纯色简约遮光荷兰绒窗帘试衣间门帘用布工程酒店绒布窗帘","shop_price":"3.00","batch_number":"999","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/632/goods_thumb_632_400_400.jpeg"},{"goods_id":"631","goods_name":"粗针雪花 针织面料 400g 秋冬时装服装面料 厂家直销","shop_price":"5.00","batch_number":"6666","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/631/goods_thumb_631_400_400.png"},{"goods_id":"630","goods_name":"厂家直销毛绒面料1.5扁平丝赛乐绒 床上用品家纺饰品毛绒玩具面料","shop_price":"0.00","batch_number":"199","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/630/goods_thumb_630_400_400.png"},{"goods_id":"629","goods_name":"仿超柔短毛绒 64色现货 服装里料家纺玩具毛毯 经编涤纶毛绒面料","shop_price":"0.30","batch_number":"5555","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/629/goods_thumb_629_400_400.png"},{"goods_id":"628","goods_name":"亚麻棉苎麻棉面料涤麻现货供应厂家直销皱布水洗服装家纺剪样专用","shop_price":"0.10","batch_number":"888","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/628/goods_thumb_628_400_400.png"},{"goods_id":"627","goods_name":"1111111","shop_price":"0.00","batch_number":"2000","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/627/goods_thumb_627_400_400.png"},{"goods_id":"626","goods_name":"印花","shop_price":"0.00","batch_number":"1","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/626/goods_thumb_626_400_400.png"},{"goods_id":"625","goods_name":"led吸顶灯 简约亚克力圆形灯饰吸顶灯 客厅卧室灯具吸顶灯led批发","shop_price":"90.00","batch_number":"2","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/625/goods_thumb_625_400_400.png"},{"goods_id":"624","goods_name":"厂家直销DE90荔枝纹PVC人造革软包硬包汽车脚垫皮革面料来样定制","shop_price":"4.80","batch_number":"1000","best_percent":"100%","image":"http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/624/goods_thumb_624_400_400.png"}]}
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
             * goods_id : 633
             * goods_name : 新款窗帘 韩式公主风双层渐变镂空星星窗帘 配套窗纱 遮光窗帘布
             * shop_price : 0.30
             * batch_number : 9999
             * best_percent : 100%
             * image : http://test.uonep.com/jushangmatou/Public/upload/goods/thumb/633/goods_thumb_633_400_400.jpeg
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
