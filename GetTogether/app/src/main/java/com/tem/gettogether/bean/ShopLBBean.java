package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-07.
 */

public class ShopLBBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"store_id":"78","store_name":"星语心愿","store_logo":"http://www.jsmtgou.com/jushangmatou/jushangmatou/jushangmatou/Public/upload/seller/2019/04-22/5cbd876ec10c3.png","store_address":"篁园服装市场","goodsList":[{"goods_id":"4941","goods_name":"秋季针织通勤半身裙 黑白条纹不起球包臀裙 时尚休闲显瘦A字中裙","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4941/goods_thumb_4941_400_400.jpeg"},{"goods_id":"4937","goods_name":"工装多口袋男士马甲网眼摄影背心休闲大码薄款户外套钓鱼坎肩","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4937/goods_thumb_4937_400_400.jpeg"},{"goods_id":"4934","goods_name":"老北京手工布鞋色鬼脸谱台州西山社会刺绣花豆豆鞋男精神小伙","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4934/goods_thumb_4934_400_400.jpeg"}]},{"store_id":"80","store_name":"小丑电器","store_logo":"http://www.jsmtgou.com/jushangmatou/jushangmatou/jushangmatou/Public/upload/seller/2019/04-22/5cbd7c72614d2.png","store_address":"深圳华强北","goodsList":[{"goods_id":"4946","goods_name":"测试","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4946/goods_thumb_4946_400_400.jpeg"},{"goods_id":"4538","goods_name":"小辣椒 红辣椒7A全面屏4G全网通手机电信移动联通双卡双待智能手机安卓学生手机千元内正品","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4538/goods_thumb_4538_400_400.png"},{"goods_id":"4301","goods_name":"Q9防水吸盘蓝牙音箱无线迷你插卡车载浴室小音响2019新款电子礼品","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4301/goods_thumb_4301_400_400.png"}]},{"store_id":"84","store_name":"康康特产","store_logo":"http://www.jsmtgou.com/jushangmatou/jushangmatou/jushangmatou/Public/upload/seller/2019/04-30/5cc7f4bf5fef9.jpg","store_address":"义乌是宾王路205-5-401","goodsList":[{"goods_id":"4966","goods_name":"鸡蛋果蛋黄果新鲜","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4966/goods_thumb_4966_400_400.jpeg"},{"goods_id":"4950","goods_name":"白参片生晒参片人参","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4950/goods_thumb_4950_400_400.jpeg"},{"goods_id":"4945","goods_name":"农家纸皮核桃","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4945/goods_thumb_4945_400_400.jpeg"}]}]
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
         * store_id : 78
         * store_name : 星语心愿
         * store_logo : http://www.jsmtgou.com/jushangmatou/jushangmatou/jushangmatou/Public/upload/seller/2019/04-22/5cbd876ec10c3.png
         * store_address : 篁园服装市场
         * goodsList : [{"goods_id":"4941","goods_name":"秋季针织通勤半身裙 黑白条纹不起球包臀裙 时尚休闲显瘦A字中裙","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4941/goods_thumb_4941_400_400.jpeg"},{"goods_id":"4937","goods_name":"工装多口袋男士马甲网眼摄影背心休闲大码薄款户外套钓鱼坎肩","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4937/goods_thumb_4937_400_400.jpeg"},{"goods_id":"4934","goods_name":"老北京手工布鞋色鬼脸谱台州西山社会刺绣花豆豆鞋男精神小伙","goods_logo":"http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4934/goods_thumb_4934_400_400.jpeg"}]
         */

        private String store_id;
        private String store_name;
        private String store_logo;
        private String store_address;
        private List<GoodsListBean> goodsList;

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getStore_address() {
            return store_address;
        }

        public void setStore_address(String store_address) {
            this.store_address = store_address;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class GoodsListBean {
            /**
             * goods_id : 4941
             * goods_name : 秋季针织通勤半身裙 黑白条纹不起球包臀裙 时尚休闲显瘦A字中裙
             * goods_logo : http://www.jsmtgou.com/jushangmatou/Public/upload/goods/thumb/4941/goods_thumb_4941_400_400.jpeg
             */

            private String goods_id;
            private String goods_name;
            private String goods_logo;

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

            public String getGoods_logo() {
                return goods_logo;
            }

            public void setGoods_logo(String goods_logo) {
                this.goods_logo = goods_logo;
            }
        }
    }
}
