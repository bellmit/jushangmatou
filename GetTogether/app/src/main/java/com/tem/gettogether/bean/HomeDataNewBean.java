package com.tem.gettogether.bean;

import java.util.List;

public class HomeDataNewBean {

    private String msg;
    private ResultEntity result;
    private int status;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public ResultEntity getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public static class ResultEntity {
        private Ara_noticeEntity ara_notice;
        private List<AdEntity> ad;
        private En_noticeEntity en_notice;
        private List<PavilionEntity> pavilion;
        private List<Ftrade_newEntity> ftrade_new;
        private List<Top_cateEntity> top_cate;
        private List<StoreEntity> store;
        private List<Trade_unionEntity> trade_union;
        private List<Ftrade_buyEntity> ftrade_buy;
        private List<Order_pastedEntity> order_pasted;
        private NoticeEntity notice;
        private List<Bottom_cateEntity> bottom_cate;
        private List<Top_navEntity> top_nav;
        private List<SpecialRecommendBean> special_recommend;

        public List<SpecialRecommendBean> getSpecial_recommend() {
            return special_recommend;
        }

        public void setSpecial_recommend(List<SpecialRecommendBean> special_recommend) {
            this.special_recommend = special_recommend;
        }

        public List<Top_navEntity> getTop_nav() {
            return top_nav;
        }

        public void setTop_nav(List<Top_navEntity> top_nav) {
            this.top_nav = top_nav;
        }

        public void setAra_notice(Ara_noticeEntity ara_notice) {
            this.ara_notice = ara_notice;
        }

        public void setAd(List<AdEntity> ad) {
            this.ad = ad;
        }

        public void setEn_notice(En_noticeEntity en_notice) {
            this.en_notice = en_notice;
        }

        public void setPavilion(List<PavilionEntity> pavilion) {
            this.pavilion = pavilion;
        }

        public void setFtrade_new(List<Ftrade_newEntity> ftrade_new) {
            this.ftrade_new = ftrade_new;
        }

        public void setTop_cate(List<Top_cateEntity> top_cate) {
            this.top_cate = top_cate;
        }

        public void setStore(List<StoreEntity> store) {
            this.store = store;
        }

        public void setTrade_union(List<Trade_unionEntity> trade_union) {
            this.trade_union = trade_union;
        }

        public void setFtrade_buy(List<Ftrade_buyEntity> ftrade_buy) {
            this.ftrade_buy = ftrade_buy;
        }

        public void setOrder_pasted(List<Order_pastedEntity> order_pasted) {
            this.order_pasted = order_pasted;
        }

        public void setNotice(NoticeEntity notice) {
            this.notice = notice;
        }

        public void setBottom_cate(List<Bottom_cateEntity> bottom_cate) {
            this.bottom_cate = bottom_cate;
        }

        public Ara_noticeEntity getAra_notice() {
            return ara_notice;
        }

        public List<AdEntity> getAd() {
            return ad;
        }

        public En_noticeEntity getEn_notice() {
            return en_notice;
        }

        public List<PavilionEntity> getPavilion() {
            return pavilion;
        }

        public List<Ftrade_newEntity> getFtrade_new() {
            return ftrade_new;
        }

        public List<Top_cateEntity> getTop_cate() {
            return top_cate;
        }

        public List<StoreEntity> getStore() {
            return store;
        }

        public List<Trade_unionEntity> getTrade_union() {
            return trade_union;
        }

        public List<Ftrade_buyEntity> getFtrade_buy() {
            return ftrade_buy;
        }

        public List<Order_pastedEntity> getOrder_pasted() {
            return order_pasted;
        }

        public NoticeEntity getNotice() {
            return notice;
        }

        public List<Bottom_cateEntity> getBottom_cate() {
            return bottom_cate;
        }

        public class Ara_noticeEntity {
            /**
             * article_id : 128
             * title : دمج السلع في العالم ، ودمج الفرص التجارية ، وإنشاء أكبر منصة للتجارة الخارجية عبر الإنترنت وخارجها ، واستهداف مدينة تشجيانغ ييوو للسلع الصغيرة ، ومدينة تشجيانغ Keqiao للأقمشة ، ومدينة قوانغتشو بالجملة ، ومدينة شنتشن الإلكترونية ، وحتى سوق الجملة للتجارة الخارجية الوطنية لإنشاء قاعدة مصدر عالمي. ، ونجمع بين المنتجات الوطنية ودفع منتجات التجار إلى كل محطة في العالم. Jushang Terminal Network Technology Service Co.، Ltd. ترحب بكم!
             */
            private String article_id;
            private String title;

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArticle_id() {
                return article_id;
            }

            public String getTitle() {
                return title;
            }
        }

        public class AdEntity {
            /**
             * ad_code : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/ad/2019/05-15/5cdbc8f82f47c.jpg
             * ad_link :
             * ad_name : 自定义广告名称
             * type : 0
             */
            private String ad_code;
            private String ad_link;
            private String ad_name;
            private int type;

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getAd_code() {
                return ad_code;
            }

            public String getAd_link() {
                return ad_link;
            }

            public String getAd_name() {
                return ad_name;
            }

            public int getType() {
                return type;
            }
        }

        public class En_noticeEntity {
            /**
             * article_id : 127
             * title : Converging the world's commodities, integrating business opportunities and creating the world's largest online and offline foreign trade platform, targeting Zhejiang Yiwu Small Commodity City, Zhejiang Keqiao Textile City, Guangzhou Wholesale City, Shenzhen Electronic City, and even the national foreign trade wholesale market to ｃｒｅａｔｅ a global ｓｏｕｒｃｅ base. , bringing together national products and pushing the products of the merchants to every terminal in the world. Jushang Terminal Network Technology Service Co., Ltd. welcomes you!
             */
            private String article_id;
            private String title;

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArticle_id() {
                return article_id;
            }

            public String getTitle() {
                return title;
            }
        }

        public class PavilionEntity {
            /**
             * pavilion_name : 义乌馆
             * en_pavilion_name : Yiwu Pavilion
             * ara_pavilion_name : سوق إيوو
             * pavilion_id : 1
             */
            private String pavilion_name;
            private String en_pavilion_name;
            private String ara_pavilion_name;
            private String pavilion_id;

            public void setPavilion_name(String pavilion_name) {
                this.pavilion_name = pavilion_name;
            }

            public void setEn_pavilion_name(String en_pavilion_name) {
                this.en_pavilion_name = en_pavilion_name;
            }

            public void setAra_pavilion_name(String ara_pavilion_name) {
                this.ara_pavilion_name = ara_pavilion_name;
            }

            public void setPavilion_id(String pavilion_id) {
                this.pavilion_id = pavilion_id;
            }

            public String getPavilion_name() {
                return pavilion_name;
            }

            public String getEn_pavilion_name() {
                return en_pavilion_name;
            }

            public String getAra_pavilion_name() {
                return ara_pavilion_name;
            }

            public String getPavilion_id() {
                return pavilion_id;
            }
        }

        public class Ftrade_newEntity {
            /**
             * comment_count : 2
             * keywords : 男装 短袖   polo衫 男士休闲纯色工作服
             * is_real : 1
             * click_count : 23
             * goods_content : &lt;table style=&quot;width: 990px; table-layout: fixed; clear: both; font-family: &amp;quot;Hiragino Sans GB&amp;quot;, Tahoma, Arial, 宋体, sans-serif; font-size: 12px; white-space: normal; background-color: rgb(255, 255, 255);&quot;&gt;&lt;tbody&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;建议零售价&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;¥88.00&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;货源类别&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;现货&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;风格&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;韩版&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;袖长&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;短袖&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;版型&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;合体型&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;图案&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;纯色&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;特殊工艺&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;布标&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;厚薄&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;普通&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;适合季节&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;夏季&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;淘货类别&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;青春流行（18-24岁）&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;货号&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;827&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;品牌&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;鲲海鹏云&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;上市年份/季节&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;2019年夏季&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;面料名称&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;奥代尔&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;主面料成分的含量&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;95（%）&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;主面料成分&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;棉&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;适用场景&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;日常&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;库存类型&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;整单&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;颜色&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;白色,灰色,黑色,豆绿色&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;尺码&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;M,L,XL,XXL,XXXL&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;主要下游平台&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;wish,亚马逊,速卖通,ebay&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;主要销售地区&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;东南亚,欧洲,中东,北美,南美&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;有可授权的自有品牌&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;是&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 86px; color: rgb(153, 153, 153);&quot;&gt;是否跨境货源&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;是&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;细致风格&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 206px;&quot;&gt;时尚潮人&lt;/td&gt;&lt;td class=&quot;de-feature&quot; style=&quot;margin: 0px; padding: 14px 14px 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 87px; color: rgb(153, 153, 153);&quot;&gt;上新类型&lt;/td&gt;&lt;td class=&quot;de-value&quot; style=&quot;margin: 0px; padding-top: 10px; padding-bottom: 0px; padding-left: 0px; word-wrap: break-word; word-break: break-all; font-size: 12px; vertical-align: text-top; width: 205px;&quot;&gt;新款&lt;/td&gt;&lt;/tr&gt;&lt;/tbody&gt;&lt;/table&gt;&lt;p&gt;&lt;br/&gt;&lt;/p&gt;
             * enquiry_price_low : null
             * cat_id2 : 1633
             * cat_id3 : 1745
             * goods_remark :
             * is_special : 0
             * cat_id1 : 1166
             * sales_sum : 78
             * suppliers_id : null
             * is_hot : 0
             * last_update : 0
             * is_free_shipping : 0
             * sku :
             * goods_name : 男装 短袖2019夏季新款翻领polo衫 男士休闲纯色工作服 广告衫t恤
             * cover_image : http://www.jsmtgou.com/jushangmatou/Public/upload/goods/2019/05-08/5cd26dab49220.png
             * is_new : 1
             * goods_id : 4909
             * weight : 0
             * sort : 50
             * brand_id : 0
             * is_recommend : 0
             * market_price : 0.00
             * spu :
             * store_count : 81111028
             * give_integral : 0
             * prom_type : 0
             * shipping_area_ids :
             * batch_number : 2
             * is_enquiry : 0
             * store_cat_id1 : 0
             * prom_id : 0
             * video :
             * collect_sum : 0
             * is_best : 0
             * pavilion_cate_id : 156
             * on_time : 1562116218
             * store_cat_id2 : 693
             * store_cat_id3 : 697
             * pavilion_id : 4
             * cost_price : 0.00
             * store_id : 82
             * shop_price : 0.00
             * goods_sn : TP0004909
             * is_hot_re : 0
             * distribut : 0.00
             * is_on_sale : 1
             * enquiry_price_high : null
             * exchange_integral : 0
             * goods_state : 1
             * goods_type : 52
             */
            private String comment_count;
            private String keywords;
            private String is_real;
            private String click_count;
            private String goods_content;
            private String enquiry_price_low;
            private String cat_id2;
            private String cat_id3;
            private String goods_remark;
            private String is_special;
            private String cat_id1;
            private String sales_sum;
            private String suppliers_id;
            private String is_hot;
            private String last_update;
            private String is_free_shipping;
            private String sku;
            private String goods_name;
            private String cover_image;
            private String is_new;
            private String goods_id;
            private String weight;
            private String sort;
            private String brand_id;
            private String is_recommend;
            private String market_price;
            private String spu;
            private String store_count;
            private String give_integral;
            private String prom_type;
            private String shipping_area_ids;
            private String batch_number;
            private String is_enquiry;
            private String store_cat_id1;
            private String prom_id;
            private String video;
            private String collect_sum;
            private String is_best;
            private String pavilion_cate_id;
            private String on_time;
            private String store_cat_id2;
            private String store_cat_id3;
            private String pavilion_id;
            private String cost_price;
            private String store_id;
            private String shop_price;
            private String goods_sn;
            private String is_hot_re;
            private String distribut;
            private String is_on_sale;
            private String enquiry_price_high;
            private String exchange_integral;
            private String goods_state;
            private String goods_type;

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public void setGoods_content(String goods_content) {
                this.goods_content = goods_content;
            }

            public void setEnquiry_price_low(String enquiry_price_low) {
                this.enquiry_price_low = enquiry_price_low;
            }

            public void setCat_id2(String cat_id2) {
                this.cat_id2 = cat_id2;
            }

            public void setCat_id3(String cat_id3) {
                this.cat_id3 = cat_id3;
            }

            public void setGoods_remark(String goods_remark) {
                this.goods_remark = goods_remark;
            }

            public void setIs_special(String is_special) {
                this.is_special = is_special;
            }

            public void setCat_id1(String cat_id1) {
                this.cat_id1 = cat_id1;
            }

            public void setSales_sum(String sales_sum) {
                this.sales_sum = sales_sum;
            }

            public void setSuppliers_id(String suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public void setLast_update(String last_update) {
                this.last_update = last_update;
            }

            public void setIs_free_shipping(String is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setcover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public void setSpu(String spu) {
                this.spu = spu;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }

            public void setGive_integral(String give_integral) {
                this.give_integral = give_integral;
            }

            public void setProm_type(String prom_type) {
                this.prom_type = prom_type;
            }

            public void setShipping_area_ids(String shipping_area_ids) {
                this.shipping_area_ids = shipping_area_ids;
            }

            public void setBatch_number(String batch_number) {
                this.batch_number = batch_number;
            }

            public void setIs_enquiry(String is_enquiry) {
                this.is_enquiry = is_enquiry;
            }

            public void setStore_cat_id1(String store_cat_id1) {
                this.store_cat_id1 = store_cat_id1;
            }

            public void setProm_id(String prom_id) {
                this.prom_id = prom_id;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public void setCollect_sum(String collect_sum) {
                this.collect_sum = collect_sum;
            }

            public void setIs_best(String is_best) {
                this.is_best = is_best;
            }

            public void setPavilion_cate_id(String pavilion_cate_id) {
                this.pavilion_cate_id = pavilion_cate_id;
            }

            public void setOn_time(String on_time) {
                this.on_time = on_time;
            }

            public void setStore_cat_id2(String store_cat_id2) {
                this.store_cat_id2 = store_cat_id2;
            }

            public void setStore_cat_id3(String store_cat_id3) {
                this.store_cat_id3 = store_cat_id3;
            }

            public void setPavilion_id(String pavilion_id) {
                this.pavilion_id = pavilion_id;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public void setIs_hot_re(String is_hot_re) {
                this.is_hot_re = is_hot_re;
            }

            public void setDistribut(String distribut) {
                this.distribut = distribut;
            }

            public void setIs_on_sale(String is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public void setEnquiry_price_high(String enquiry_price_high) {
                this.enquiry_price_high = enquiry_price_high;
            }

            public void setExchange_integral(String exchange_integral) {
                this.exchange_integral = exchange_integral;
            }

            public void setGoods_state(String goods_state) {
                this.goods_state = goods_state;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getComment_count() {
                return comment_count;
            }

            public String getKeywords() {
                return keywords;
            }

            public String getIs_real() {
                return is_real;
            }

            public String getClick_count() {
                return click_count;
            }

            public String getGoods_content() {
                return goods_content;
            }

            public String getEnquiry_price_low() {
                return enquiry_price_low;
            }

            public String getCat_id2() {
                return cat_id2;
            }

            public String getCat_id3() {
                return cat_id3;
            }

            public String getGoods_remark() {
                return goods_remark;
            }

            public String getIs_special() {
                return is_special;
            }

            public String getCat_id1() {
                return cat_id1;
            }

            public String getSales_sum() {
                return sales_sum;
            }

            public String getSuppliers_id() {
                return suppliers_id;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public String getLast_update() {
                return last_update;
            }

            public String getIs_free_shipping() {
                return is_free_shipping;
            }

            public String getSku() {
                return sku;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getcover_image() {
                return cover_image;
            }

            public String getIs_new() {
                return is_new;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getWeight() {
                return weight;
            }

            public String getSort() {
                return sort;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public String getMarket_price() {
                return market_price;
            }

            public String getSpu() {
                return spu;
            }

            public String getStore_count() {
                return store_count;
            }

            public String getGive_integral() {
                return give_integral;
            }

            public String getProm_type() {
                return prom_type;
            }

            public String getShipping_area_ids() {
                return shipping_area_ids;
            }

            public String getBatch_number() {
                return batch_number;
            }

            public String getIs_enquiry() {
                return is_enquiry;
            }

            public String getStore_cat_id1() {
                return store_cat_id1;
            }

            public String getProm_id() {
                return prom_id;
            }

            public String getVideo() {
                return video;
            }

            public String getCollect_sum() {
                return collect_sum;
            }

            public String getIs_best() {
                return is_best;
            }

            public String getPavilion_cate_id() {
                return pavilion_cate_id;
            }

            public String getOn_time() {
                return on_time;
            }

            public String getStore_cat_id2() {
                return store_cat_id2;
            }

            public String getStore_cat_id3() {
                return store_cat_id3;
            }

            public String getPavilion_id() {
                return pavilion_id;
            }

            public String getCost_price() {
                return cost_price;
            }

            public String getStore_id() {
                return store_id;
            }

            public String getShop_price() {
                return shop_price;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public String getIs_hot_re() {
                return is_hot_re;
            }

            public String getDistribut() {
                return distribut;
            }

            public String getIs_on_sale() {
                return is_on_sale;
            }

            public String getEnquiry_price_high() {
                return enquiry_price_high;
            }

            public String getExchange_integral() {
                return exchange_integral;
            }

            public String getGoods_state() {
                return goods_state;
            }

            public String getGoods_type() {
                return goods_type;
            }
        }

        public class Top_cateEntity {
            /**
             * app_top_right_pic : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/category/2019/04-17/5cb6d88d661e8.jpg
             * category_id : 634
             * mobile_name : 美妆个护
             * app_top_left_pic : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/category/2019/04-17/5cb6d85223a36.jpg
             * name : 美妆个护
             * en_mobile_name : Beauty care
             * ara_mobile_name : ماكياج جميل
             * mobile_des :
             */
            private String app_top_right_pic;
            private String category_id;
            private String mobile_name;
            private String app_top_left_pic;
            private String name;
            private String en_mobile_name;
            private String ara_mobile_name;
            private String mobile_des;

            public void setApp_top_right_pic(String app_top_right_pic) {
                this.app_top_right_pic = app_top_right_pic;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public void setMobile_name(String mobile_name) {
                this.mobile_name = mobile_name;
            }

            public void setApp_top_left_pic(String app_top_left_pic) {
                this.app_top_left_pic = app_top_left_pic;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setEn_mobile_name(String en_mobile_name) {
                this.en_mobile_name = en_mobile_name;
            }

            public void setAra_mobile_name(String ara_mobile_name) {
                this.ara_mobile_name = ara_mobile_name;
            }

            public void setMobile_des(String mobile_des) {
                this.mobile_des = mobile_des;
            }

            public String getApp_top_right_pic() {
                return app_top_right_pic;
            }

            public String getCategory_id() {
                return category_id;
            }

            public String getMobile_name() {
                return mobile_name;
            }

            public String getApp_top_left_pic() {
                return app_top_left_pic;
            }

            public String getName() {
                return name;
            }

            public String getEn_mobile_name() {
                return en_mobile_name;
            }

            public String getAra_mobile_name() {
                return ara_mobile_name;
            }

            public String getMobile_des() {
                return mobile_des;
            }
        }

        public class StoreEntity {
            /**
             * store_id : 78
             * store_banner : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/seller/2019/06-01/5cf2136dac7d2.jpg
             * store_logo : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/seller/2019/06-01/5cf213231c404.JPG
             * store_name : 欣欣内衣店
             * store_des : 我是卖衣服的
             */
            private String store_id;
            private String store_banner;
            private String store_logo;
            private String store_name;
            private String store_des;

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public void setStore_banner(String store_banner) {
                this.store_banner = store_banner;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public void setStore_des(String store_des) {
                this.store_des = store_des;
            }

            public String getStore_id() {
                return store_id;
            }

            public String getStore_banner() {
                return store_banner;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public String getStore_name() {
                return store_name;
            }

            public String getStore_des() {
                return store_des;
            }
        }

        public class Trade_unionEntity {
            /**
             * qq : null
             * company_images : null
             * createtime : 1563005942
             * address : 浙江省义乌市稠州北路601号10栋7号201室
             * contact_person : 王伟亚
             * description : null
             * telephone : null
             * companyid : 10
             * company_logo : null
             * company_name : 义乌市赛瑞斯国际贸易有限公司
             * cellphone : 15381750152
             * email : null
             * status : 1
             */
            private String qq;
            private String company_images;
            private String createtime;
            private String address;
            private String contact_person;
            private String description;
            private String telephone;
            private String companyid;
            private String company_logo;
            private String company_name;
            private String cellphone;
            private String email;
            private String status;

            public void setQq(String qq) {
                this.qq = qq;
            }

            public void setCompany_images(String company_images) {
                this.company_images = company_images;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setContact_person(String contact_person) {
                this.contact_person = contact_person;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setCompanyid(String companyid) {
                this.companyid = companyid;
            }

            public void setCompany_logo(String company_logo) {
                this.company_logo = company_logo;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public void setCellphone(String cellphone) {
                this.cellphone = cellphone;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getQq() {
                return qq;
            }

            public String getCompany_images() {
                return company_images;
            }

            public String getCreatetime() {
                return createtime;
            }

            public String getAddress() {
                return address;
            }

            public String getContact_person() {
                return contact_person;
            }

            public String getDescription() {
                return description;
            }

            public String getTelephone() {
                return telephone;
            }

            public String getCompanyid() {
                return companyid;
            }

            public String getCompany_logo() {
                return company_logo;
            }

            public String getCompany_name() {
                return company_name;
            }

            public String getCellphone() {
                return cellphone;
            }

            public String getEmail() {
                return email;
            }

            public String getStatus() {
                return status;
            }
        }

        public class Ftrade_buyEntity {
            /**
             * goods_name : 童床
             * goods_logo : http://www.jsmtgou.com/jushangmatou/Uploads/head_img/20190717/20190717103652_47203.jpeg
             * attach_time : 现货
             * trade_id : 199
             * goods_cate : 母婴用品
             * release_type : 外贸大货
             * goods_desc : 利比亚的黎波里
             * add_time : 1563331012
             * country_id : null
             */
            private String goods_name;
            private String goods_logo;
            private String attach_time;
            private String trade_id;
            private String goods_cate;
            private String release_type;
            private String goods_desc;
            private String add_time;
            private String country_id;

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }

            private String country_name;

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_logo(String goods_logo) {
                this.goods_logo = goods_logo;
            }

            public void setAttach_time(String attach_time) {
                this.attach_time = attach_time;
            }

            public void setTrade_id(String trade_id) {
                this.trade_id = trade_id;
            }

            public void setGoods_cate(String goods_cate) {
                this.goods_cate = goods_cate;
            }

            public void setRelease_type(String release_type) {
                this.release_type = release_type;
            }

            public void setGoods_desc(String goods_desc) {
                this.goods_desc = goods_desc;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_logo() {
                return goods_logo;
            }

            public String getAttach_time() {
                return attach_time;
            }

            public String getTrade_id() {
                return trade_id;
            }

            public String getGoods_cate() {
                return goods_cate;
            }

            public String getRelease_type() {
                return release_type;
            }

            public String getGoods_desc() {
                return goods_desc;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getCountry_id() {
                return country_id;
            }
        }

        public class Order_pastedEntity {
            /**
             * country : 0
             * spec_key : 284_285_1399_1400
             * shipping_code : shentong
             * pay_code :
             * discount : 0.00
             * del_app : 0
             * order_status : 2
             * member_goods_price : 0.10
             * province : 12596
             * twon : 13679
             * remind_time : 0
             * order_amount : 0.00
             * coupon_price : null
             * spec_key_name : 尺寸:1 型号:2 适用人群:100 颜色:1
             * shipping_name : 申通物流
             * master_order_sn : 201907081716007937
             * shipping_price : 0.00
             * goods_name : 8855
             * cover_image : http://www.jsmtgou.com/jushangmatou/Public/upload/goods/2019/07-03/5d1c528595002.jpg
             * consignee : 马玉康
             * goods_id : 5588
             * integral_money : 0.00
             * shipping_status : 1
             * pay_time : 1562577360
             * zipcode : 10010
             * user_money : 0.10
             * user_id : 1
             * total_amount : 0.10
             * order_prom_amount : 0.00
             * district : 13678
             * goods_num : 1
             * market_price : 0.00
             * order_prom_id : 0
             * order_id : 568
             * city : 13564
             * invoice_title :
             * goods_price : 0.10
             * delivery_id : 95
             * pay_status : 1
             * integral : 0
             * is_comment : 0
             * user_note :
             * shipping_time : 1562577391
             * email :
             * cost_price : 0.00
             * store_id : 78
             * is_send : 1
             * address : 宾王路205-5号4楼
             * admin_note :
             * mobile : 15958912033
             * pay_name :
             * confirm_time : 1562577605
             * is_checkout : 0
             * deleted : 0
             * parent_sn : null
             * add_time : 1562577360
             * order_sn : 201907081716007140
             * transaction :
             */
            private String country;
            private String spec_key;
            private String shipping_code;
            private String pay_code;
            private String discount;
            private String del_app;
            private String order_status;
            private String member_goods_price;
            private String province;
            private String twon;
            private String remind_time;
            private String order_amount;
            private String coupon_price;
            private String spec_key_name;
            private String shipping_name;
            private String master_order_sn;
            private String shipping_price;
            private String goods_name;
            private String cover_image;
            private String consignee;
            private String goods_id;
            private String integral_money;
            private String shipping_status;
            private String pay_time;
            private String zipcode;
            private String user_money;
            private String user_id;
            private String total_amount;
            private String order_prom_amount;
            private String district;
            private String goods_num;
            private String market_price;
            private String order_prom_id;
            private String order_id;
            private String city;
            private String invoice_title;
            private String goods_price;
            private String delivery_id;
            private String pay_status;
            private String integral;
            private String is_comment;
            private String user_note;
            private String shipping_time;
            private String email;
            private String cost_price;
            private String store_id;
            private String is_send;
            private String address;
            private String admin_note;
            private String mobile;
            private String pay_name;
            private String confirm_time;
            private String is_checkout;
            private String deleted;
            private String parent_sn;
            private String add_time;
            private String order_sn;
            private String transaction;
            private String is_enquiry;
            private String sales_sum;
            private String shop_price;

            public String getSales_sum() {
                return sales_sum;
            }

            public void setSales_sum(String sales_sum) {
                this.sales_sum = sales_sum;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public String getIs_enquiry() {
                return is_enquiry;
            }

            public void setIs_enquiry(String is_enquiry) {
                this.is_enquiry = is_enquiry;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public void setSpec_key(String spec_key) {
                this.spec_key = spec_key;
            }

            public void setShipping_code(String shipping_code) {
                this.shipping_code = shipping_code;
            }

            public void setPay_code(String pay_code) {
                this.pay_code = pay_code;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public void setDel_app(String del_app) {
                this.del_app = del_app;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public void setMember_goods_price(String member_goods_price) {
                this.member_goods_price = member_goods_price;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public void setTwon(String twon) {
                this.twon = twon;
            }

            public void setRemind_time(String remind_time) {
                this.remind_time = remind_time;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public void setCoupon_price(String coupon_price) {
                this.coupon_price = coupon_price;
            }

            public void setSpec_key_name(String spec_key_name) {
                this.spec_key_name = spec_key_name;
            }

            public void setShipping_name(String shipping_name) {
                this.shipping_name = shipping_name;
            }

            public void setMaster_order_sn(String master_order_sn) {
                this.master_order_sn = master_order_sn;
            }

            public void setShipping_price(String shipping_price) {
                this.shipping_price = shipping_price;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setcover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public void setIntegral_money(String integral_money) {
                this.integral_money = integral_money;
            }

            public void setShipping_status(String shipping_status) {
                this.shipping_status = shipping_status;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public void setUser_money(String user_money) {
                this.user_money = user_money;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public void setOrder_prom_amount(String order_prom_amount) {
                this.order_prom_amount = order_prom_amount;
            }

            public void setDistrict(String district) {
                this.district = district;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public void setOrder_prom_id(String order_prom_id) {
                this.order_prom_id = order_prom_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public void setInvoice_title(String invoice_title) {
                this.invoice_title = invoice_title;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setDelivery_id(String delivery_id) {
                this.delivery_id = delivery_id;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public void setIntegral(String integral) {
                this.integral = integral;
            }

            public void setIs_comment(String is_comment) {
                this.is_comment = is_comment;
            }

            public void setUser_note(String user_note) {
                this.user_note = user_note;
            }

            public void setShipping_time(String shipping_time) {
                this.shipping_time = shipping_time;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public void setIs_send(String is_send) {
                this.is_send = is_send;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public void setAdmin_note(String admin_note) {
                this.admin_note = admin_note;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public void setPay_name(String pay_name) {
                this.pay_name = pay_name;
            }

            public void setConfirm_time(String confirm_time) {
                this.confirm_time = confirm_time;
            }

            public void setIs_checkout(String is_checkout) {
                this.is_checkout = is_checkout;
            }

            public void setDeleted(String deleted) {
                this.deleted = deleted;
            }

            public void setParent_sn(String parent_sn) {
                this.parent_sn = parent_sn;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public void setTransaction(String transaction) {
                this.transaction = transaction;
            }

            public String getCountry() {
                return country;
            }

            public String getSpec_key() {
                return spec_key;
            }

            public String getShipping_code() {
                return shipping_code;
            }

            public String getPay_code() {
                return pay_code;
            }

            public String getDiscount() {
                return discount;
            }

            public String getDel_app() {
                return del_app;
            }

            public String getOrder_status() {
                return order_status;
            }

            public String getMember_goods_price() {
                return member_goods_price;
            }

            public String getProvince() {
                return province;
            }

            public String getTwon() {
                return twon;
            }

            public String getRemind_time() {
                return remind_time;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public String getCoupon_price() {
                return coupon_price;
            }

            public String getSpec_key_name() {
                return spec_key_name;
            }

            public String getShipping_name() {
                return shipping_name;
            }

            public String getMaster_order_sn() {
                return master_order_sn;
            }

            public String getShipping_price() {
                return shipping_price;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getcover_image() {
                return cover_image;
            }

            public String getConsignee() {
                return consignee;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public String getIntegral_money() {
                return integral_money;
            }

            public String getShipping_status() {
                return shipping_status;
            }

            public String getPay_time() {
                return pay_time;
            }

            public String getZipcode() {
                return zipcode;
            }

            public String getUser_money() {
                return user_money;
            }

            public String getUser_id() {
                return user_id;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public String getOrder_prom_amount() {
                return order_prom_amount;
            }

            public String getDistrict() {
                return district;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public String getMarket_price() {
                return market_price;
            }

            public String getOrder_prom_id() {
                return order_prom_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public String getCity() {
                return city;
            }

            public String getInvoice_title() {
                return invoice_title;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public String getDelivery_id() {
                return delivery_id;
            }

            public String getPay_status() {
                return pay_status;
            }

            public String getIntegral() {
                return integral;
            }

            public String getIs_comment() {
                return is_comment;
            }

            public String getUser_note() {
                return user_note;
            }

            public String getShipping_time() {
                return shipping_time;
            }

            public String getEmail() {
                return email;
            }

            public String getCost_price() {
                return cost_price;
            }

            public String getStore_id() {
                return store_id;
            }

            public String getIs_send() {
                return is_send;
            }

            public String getAddress() {
                return address;
            }

            public String getAdmin_note() {
                return admin_note;
            }

            public String getMobile() {
                return mobile;
            }

            public String getPay_name() {
                return pay_name;
            }

            public String getConfirm_time() {
                return confirm_time;
            }

            public String getIs_checkout() {
                return is_checkout;
            }

            public String getDeleted() {
                return deleted;
            }

            public String getParent_sn() {
                return parent_sn;
            }

            public String getAdd_time() {
                return add_time;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public String getTransaction() {
                return transaction;
            }
        }

        public static class NoticeEntity {
            /**
             * article_id : 78
             * title : 汇天下商品，融八方商机，打造全球最大的线上线下外贸平台，面向浙江义乌小商品城，浙江柯桥轻纺城、广州批发城、深圳电子城，乃至全国外贸批发市场招商，创建全球货源基地，汇集全国商品，把商家的产品推向全球的每个码头，聚商码头网络科技服务有限公司欢迎您！
             */
            private String article_id;
            private String title;

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getArticle_id() {
                return article_id;
            }

            public String getTitle() {
                return title;
            }
        }
        public class Top_navEntity{
            private String nav_id;
            private String nav_name;
            private String nav_logo;

            public String getNav_id() {
                return nav_id;
            }

            public void setNav_id(String nav_id) {
                this.nav_id = nav_id;
            }

            public String getNav_name() {
                return nav_name;
            }

            public void setNav_name(String nav_name) {
                this.nav_name = nav_name;
            }

            public String getNav_logo() {
                return nav_logo;
            }

            public void setNav_logo(String nav_logo) {
                this.nav_logo = nav_logo;
            }
        }
        public static class SpecialRecommendBean {
            /**
             * goods_id : 6589
             * cat_id1 : 0
             * cat_id2 : 6069
             * cat_id3 : 6184
             * pavilion_id : null
             * pavilion_cate_id : null
             * store_cat_id1 : 0
             * store_cat_id2 : 0
             * store_cat_id3 : 0
             * goods_sn :
             * goods_name : 志愿者马甲LOGO定制Volunteer vest logo customization
             * goods_en_name :
             * goods_ara_name :
             * click_count : 0
             * brand_id : 0
             * store_count : 10
             * collect_sum : 0
             * comment_count : 0
             * weight : 0
             * market_price : 0.00
             * shop_price : 0.00
             * cost_price : 0.00
             * exchange_integral : 0
             * keywords : 志愿者马甲LOGO定制Volunteer vest logo customization
             * en_keywords : null
             * ara_keywords : null
             * goods_remark : null
             * goods_en_remark : null
             * goods_ara_remark : null
             * goods_content : </p><p></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213648_85001.jpg"/></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213722_40844.jpg"/></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_61987.jpg"/></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_84228.jpg"/></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_23517.jpg"/></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_10894.jpg"/></p><p><img src="http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213829_90457.jpg"/></p>
             * goods_en_content : null
             * goods_ara_content : null
             * original_img : ["http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213648_85001.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213722_40844.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_61987.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_84228.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_23517.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213743_10894.jpg","http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213829_90457.jpg"]
             * is_en : 0
             * is_ara : 0
             * is_real : 1
             * is_on_sale : 1
             * is_free_shipping : 0
             * on_time : 1572010789
             * sort : 50
             * is_recommend : 1
             * is_new : 0
             * is_hot_re : 0
             * is_hot : 0
             * is_special : 0
             * is_best : 0
             * last_update : 0
             * goods_type : 0
             * give_integral : 0
             * sales_sum : 0
             * prom_type : 0
             * prom_id : 0
             * distribut : 0.00
             * store_id : 360
             * spu : null
             * sku : null
             * goods_state : 1
             * suppliers_id : null
             * shipping_area_ids :
             * batch_number : 500
             * is_enquiry : 1
             * enquiry_price_low : null
             * enquiry_price_high : null
             * video : null
             * cover_image : http://m.jsmtgou.com/jushangmatou/Uploads/head_img/20191025/20191025213632_47907.jpg
             * ad_image : null
             * ad_image_position : 0
             */

            private String goods_id;
            private String cat_id1;
            private String cat_id2;
            private String cat_id3;
            private Object pavilion_id;
            private Object pavilion_cate_id;
            private String store_cat_id1;
            private String store_cat_id2;
            private String store_cat_id3;
            private String goods_sn;
            private String goods_name;
            private String goods_en_name;
            private String goods_ara_name;
            private String click_count;
            private String brand_id;
            private String store_count;
            private String collect_sum;
            private String comment_count;
            private String weight;
            private String market_price;
            private String shop_price;
            private String cost_price;
            private String exchange_integral;
            private String keywords;
            private Object en_keywords;
            private Object ara_keywords;
            private Object goods_remark;
            private Object goods_en_remark;
            private Object goods_ara_remark;
            private String goods_content;
            private Object goods_en_content;
            private Object goods_ara_content;
            private String is_en;
            private String is_ara;
            private String is_real;
            private String is_on_sale;
            private String is_free_shipping;
            private String on_time;
            private String sort;
            private String is_recommend;
            private String is_new;
            private String is_hot_re;
            private String is_hot;
            private String is_special;
            private String is_best;
            private String last_update;
            private String goods_type;
            private String give_integral;
            private String sales_sum;
            private String prom_type;
            private String prom_id;
            private String distribut;
            private String store_id;
            private Object spu;
            private Object sku;
            private String goods_state;
            private Object suppliers_id;
            private String shipping_area_ids;
            private String batch_number;
            private String is_enquiry;
            private Object enquiry_price_low;
            private Object enquiry_price_high;
            private Object video;
            private String cover_image;
            private Object ad_image;
            private String ad_image_position;
            private List<String> original_img;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getCat_id1() {
                return cat_id1;
            }

            public void setCat_id1(String cat_id1) {
                this.cat_id1 = cat_id1;
            }

            public String getCat_id2() {
                return cat_id2;
            }

            public void setCat_id2(String cat_id2) {
                this.cat_id2 = cat_id2;
            }

            public String getCat_id3() {
                return cat_id3;
            }

            public void setCat_id3(String cat_id3) {
                this.cat_id3 = cat_id3;
            }

            public Object getPavilion_id() {
                return pavilion_id;
            }

            public void setPavilion_id(Object pavilion_id) {
                this.pavilion_id = pavilion_id;
            }

            public Object getPavilion_cate_id() {
                return pavilion_cate_id;
            }

            public void setPavilion_cate_id(Object pavilion_cate_id) {
                this.pavilion_cate_id = pavilion_cate_id;
            }

            public String getStore_cat_id1() {
                return store_cat_id1;
            }

            public void setStore_cat_id1(String store_cat_id1) {
                this.store_cat_id1 = store_cat_id1;
            }

            public String getStore_cat_id2() {
                return store_cat_id2;
            }

            public void setStore_cat_id2(String store_cat_id2) {
                this.store_cat_id2 = store_cat_id2;
            }

            public String getStore_cat_id3() {
                return store_cat_id3;
            }

            public void setStore_cat_id3(String store_cat_id3) {
                this.store_cat_id3 = store_cat_id3;
            }

            public String getGoods_sn() {
                return goods_sn;
            }

            public void setGoods_sn(String goods_sn) {
                this.goods_sn = goods_sn;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_en_name() {
                return goods_en_name;
            }

            public void setGoods_en_name(String goods_en_name) {
                this.goods_en_name = goods_en_name;
            }

            public String getGoods_ara_name() {
                return goods_ara_name;
            }

            public void setGoods_ara_name(String goods_ara_name) {
                this.goods_ara_name = goods_ara_name;
            }

            public String getClick_count() {
                return click_count;
            }

            public void setClick_count(String click_count) {
                this.click_count = click_count;
            }

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getStore_count() {
                return store_count;
            }

            public void setStore_count(String store_count) {
                this.store_count = store_count;
            }

            public String getCollect_sum() {
                return collect_sum;
            }

            public void setCollect_sum(String collect_sum) {
                this.collect_sum = collect_sum;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getCost_price() {
                return cost_price;
            }

            public void setCost_price(String cost_price) {
                this.cost_price = cost_price;
            }

            public String getExchange_integral() {
                return exchange_integral;
            }

            public void setExchange_integral(String exchange_integral) {
                this.exchange_integral = exchange_integral;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public Object getEn_keywords() {
                return en_keywords;
            }

            public void setEn_keywords(Object en_keywords) {
                this.en_keywords = en_keywords;
            }

            public Object getAra_keywords() {
                return ara_keywords;
            }

            public void setAra_keywords(Object ara_keywords) {
                this.ara_keywords = ara_keywords;
            }

            public Object getGoods_remark() {
                return goods_remark;
            }

            public void setGoods_remark(Object goods_remark) {
                this.goods_remark = goods_remark;
            }

            public Object getGoods_en_remark() {
                return goods_en_remark;
            }

            public void setGoods_en_remark(Object goods_en_remark) {
                this.goods_en_remark = goods_en_remark;
            }

            public Object getGoods_ara_remark() {
                return goods_ara_remark;
            }

            public void setGoods_ara_remark(Object goods_ara_remark) {
                this.goods_ara_remark = goods_ara_remark;
            }

            public String getGoods_content() {
                return goods_content;
            }

            public void setGoods_content(String goods_content) {
                this.goods_content = goods_content;
            }

            public Object getGoods_en_content() {
                return goods_en_content;
            }

            public void setGoods_en_content(Object goods_en_content) {
                this.goods_en_content = goods_en_content;
            }

            public Object getGoods_ara_content() {
                return goods_ara_content;
            }

            public void setGoods_ara_content(Object goods_ara_content) {
                this.goods_ara_content = goods_ara_content;
            }

            public String getIs_en() {
                return is_en;
            }

            public void setIs_en(String is_en) {
                this.is_en = is_en;
            }

            public String getIs_ara() {
                return is_ara;
            }

            public void setIs_ara(String is_ara) {
                this.is_ara = is_ara;
            }

            public String getIs_real() {
                return is_real;
            }

            public void setIs_real(String is_real) {
                this.is_real = is_real;
            }

            public String getIs_on_sale() {
                return is_on_sale;
            }

            public void setIs_on_sale(String is_on_sale) {
                this.is_on_sale = is_on_sale;
            }

            public String getIs_free_shipping() {
                return is_free_shipping;
            }

            public void setIs_free_shipping(String is_free_shipping) {
                this.is_free_shipping = is_free_shipping;
            }

            public String getOn_time() {
                return on_time;
            }

            public void setOn_time(String on_time) {
                this.on_time = on_time;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            public String getIs_new() {
                return is_new;
            }

            public void setIs_new(String is_new) {
                this.is_new = is_new;
            }

            public String getIs_hot_re() {
                return is_hot_re;
            }

            public void setIs_hot_re(String is_hot_re) {
                this.is_hot_re = is_hot_re;
            }

            public String getIs_hot() {
                return is_hot;
            }

            public void setIs_hot(String is_hot) {
                this.is_hot = is_hot;
            }

            public String getIs_special() {
                return is_special;
            }

            public void setIs_special(String is_special) {
                this.is_special = is_special;
            }

            public String getIs_best() {
                return is_best;
            }

            public void setIs_best(String is_best) {
                this.is_best = is_best;
            }

            public String getLast_update() {
                return last_update;
            }

            public void setLast_update(String last_update) {
                this.last_update = last_update;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getGive_integral() {
                return give_integral;
            }

            public void setGive_integral(String give_integral) {
                this.give_integral = give_integral;
            }

            public String getSales_sum() {
                return sales_sum;
            }

            public void setSales_sum(String sales_sum) {
                this.sales_sum = sales_sum;
            }

            public String getProm_type() {
                return prom_type;
            }

            public void setProm_type(String prom_type) {
                this.prom_type = prom_type;
            }

            public String getProm_id() {
                return prom_id;
            }

            public void setProm_id(String prom_id) {
                this.prom_id = prom_id;
            }

            public String getDistribut() {
                return distribut;
            }

            public void setDistribut(String distribut) {
                this.distribut = distribut;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public Object getSpu() {
                return spu;
            }

            public void setSpu(Object spu) {
                this.spu = spu;
            }

            public Object getSku() {
                return sku;
            }

            public void setSku(Object sku) {
                this.sku = sku;
            }

            public String getGoods_state() {
                return goods_state;
            }

            public void setGoods_state(String goods_state) {
                this.goods_state = goods_state;
            }

            public Object getSuppliers_id() {
                return suppliers_id;
            }

            public void setSuppliers_id(Object suppliers_id) {
                this.suppliers_id = suppliers_id;
            }

            public String getShipping_area_ids() {
                return shipping_area_ids;
            }

            public void setShipping_area_ids(String shipping_area_ids) {
                this.shipping_area_ids = shipping_area_ids;
            }

            public String getBatch_number() {
                return batch_number;
            }

            public void setBatch_number(String batch_number) {
                this.batch_number = batch_number;
            }

            public String getIs_enquiry() {
                return is_enquiry;
            }

            public void setIs_enquiry(String is_enquiry) {
                this.is_enquiry = is_enquiry;
            }

            public Object getEnquiry_price_low() {
                return enquiry_price_low;
            }

            public void setEnquiry_price_low(Object enquiry_price_low) {
                this.enquiry_price_low = enquiry_price_low;
            }

            public Object getEnquiry_price_high() {
                return enquiry_price_high;
            }

            public void setEnquiry_price_high(Object enquiry_price_high) {
                this.enquiry_price_high = enquiry_price_high;
            }

            public Object getVideo() {
                return video;
            }

            public void setVideo(Object video) {
                this.video = video;
            }

            public String getCover_image() {
                return cover_image;
            }

            public void setCover_image(String cover_image) {
                this.cover_image = cover_image;
            }

            public Object getAd_image() {
                return ad_image;
            }

            public void setAd_image(Object ad_image) {
                this.ad_image = ad_image;
            }

            public String getAd_image_position() {
                return ad_image_position;
            }

            public void setAd_image_position(String ad_image_position) {
                this.ad_image_position = ad_image_position;
            }

            public List<String> getOriginal_img() {
                return original_img;
            }

            public void setOriginal_img(List<String> original_img) {
                this.original_img = original_img;
            }
        }
        public class Bottom_cateEntity {
            /**
             * category_id : 634
             * mobile_name : 美妆个护
             * name : 美妆个护
             * app_bottom_pic : http://www.jsmtgou.com/jushangmatou/jushangmatou/Public/upload/category/2019/05-10/5cd4eaf9e566d.jpg
             * en_mobile_name : Beauty care
             * id : 1268_634_2530
             * ara_mobile_name : ماكياج جميل
             * mobile_des :
             */
            private String category_id;
            private String mobile_name;
            private String name;
            private String app_bottom_pic;
            private String en_mobile_name;
            private String id;
            private String ara_mobile_name;
            private String mobile_des;
            private String app_image;

            public String getApp_image() {
                return app_image;
            }

            public void setApp_image(String app_image) {
                this.app_image = app_image;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public void setMobile_name(String mobile_name) {
                this.mobile_name = mobile_name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setApp_bottom_pic(String app_bottom_pic) {
                this.app_bottom_pic = app_bottom_pic;
            }

            public void setEn_mobile_name(String en_mobile_name) {
                this.en_mobile_name = en_mobile_name;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setAra_mobile_name(String ara_mobile_name) {
                this.ara_mobile_name = ara_mobile_name;
            }

            public void setMobile_des(String mobile_des) {
                this.mobile_des = mobile_des;
            }

            public String getCategory_id() {
                return category_id;
            }

            public String getMobile_name() {
                return mobile_name;
            }

            public String getName() {
                return name;
            }

            public String getApp_bottom_pic() {
                return app_bottom_pic;
            }

            public String getEn_mobile_name() {
                return en_mobile_name;
            }

            public String getId() {
                return id;
            }

            public String getAra_mobile_name() {
                return ara_mobile_name;
            }

            public String getMobile_des() {
                return mobile_des;
            }
        }
    }
}
