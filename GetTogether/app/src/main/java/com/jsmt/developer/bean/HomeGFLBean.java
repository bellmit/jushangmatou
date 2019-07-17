package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-04-04.
 */

public class HomeGFLBean {
    /**
     * status : 1
     * msg : 获取成功
     * result : {"app_title":"义乌馆优选超值","app_des":"质好价优","app_img":null,"pavilion_cate":[{"pavilion_cate_id":"1","name":"国际商贸城一区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"2","name":"国际商贸城一区东","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"3","name":"国际商贸城二区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"20","name":"国际商贸城三区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"21","name":"国际商贸城四区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"22","name":"国际商贸城五区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"23","name":"篁园服装市场","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"24","name":"国际生产资料市场","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"25","name":"副食品市场","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"39","name":"梅湖库存","app_banner":""}]}
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
         * app_title : 义乌馆优选超值
         * app_des : 质好价优
         * app_img : null
         * pavilion_cate : [{"pavilion_cate_id":"1","name":"国际商贸城一区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"2","name":"国际商贸城一区东","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"3","name":"国际商贸城二区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"20","name":"国际商贸城三区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"21","name":"国际商贸城四区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"22","name":"国际商贸城五区","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"23","name":"篁园服装市场","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"24","name":"国际生产资料市场","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"25","name":"副食品市场","app_banner":"http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg"},{"pavilion_cate_id":"39","name":"梅湖库存","app_banner":""}]
         */

        private String app_title;
        private String app_des;
        private Object app_img;
        private String app_link;
        private List<PavilionCateBean> pavilion_cate;

        public String getApp_link() {
            return app_link;
        }

        public void setApp_link(String app_link) {
            this.app_link = app_link;
        }

        public String getApp_title() {
            return app_title;
        }

        public void setApp_title(String app_title) {
            this.app_title = app_title;
        }

        public String getApp_des() {
            return app_des;
        }

        public void setApp_des(String app_des) {
            this.app_des = app_des;
        }

        public Object getApp_img() {
            return app_img;
        }

        public void setApp_img(Object app_img) {
            this.app_img = app_img;
        }

        public List<PavilionCateBean> getPavilion_cate() {
            return pavilion_cate;
        }

        public void setPavilion_cate(List<PavilionCateBean> pavilion_cate) {
            this.pavilion_cate = pavilion_cate;
        }

        public static class PavilionCateBean {
            /**
             * pavilion_cate_id : 1
             * name : 国际商贸城一区
             * app_banner : http://www.jsgogogo.cn/jushangmatou/jushangmatou/Public/upload/pavilion_cate/2018/12-01/5c023cb742f50.jpg
             */

            private String pavilion_cate_id;
            private String name;
            private String app_banner;

            public String getPavilion_cate_id() {
                return pavilion_cate_id;
            }

            public void setPavilion_cate_id(String pavilion_cate_id) {
                this.pavilion_cate_id = pavilion_cate_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getApp_banner() {
                return app_banner;
            }

            public void setApp_banner(String app_banner) {
                this.app_banner = app_banner;
            }
        }
    }

//    /**
//     * status : 1
//     * msg : 获取成功
//     * result : [{"ad_link":"","ad_name":"自定义广告名称","ad_code":"http://www.jsgogogo.cn/jushangmatou/Public/upload/ad/2018/12-10/5c0dd9034f9e5.png","pavilion_cate":[{"pavilion_cate_id":"178","name":"广西","app_banner":"","app_des":""},{"pavilion_cate_id":"62","name":"山东省","app_banner":"","app_des":""}]},{"ad_link":"","ad_name":"app首页馆推荐","ad_code":"http://www.jsgogogo.cn/jushangmatou/Public/upload/ad/2018/12-10/5c0dd9034f9e5.png","pavilion_cate":[{"pavilion_cate_id":"117","name":"集美堂服装批发市场","app_banner":"","app_des":""},{"pavilion_cate_id":"127","name":"华南城","app_banner":"","app_des":""}]}]
//     */
//
//    private int status;
//    private String msg;
//    private List<ResultBean> result;
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public List<ResultBean> getResult() {
//        return result;
//    }
//
//    public void setResult(List<ResultBean> result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        /**
//         * ad_link :
//         * ad_name : 自定义广告名称
//         * ad_code : http://www.jsgogogo.cn/jushangmatou/Public/upload/ad/2018/12-10/5c0dd9034f9e5.png
//         * pavilion_cate : [{"pavilion_cate_id":"178","name":"广西","app_banner":"","app_des":""},{"pavilion_cate_id":"62","name":"山东省","app_banner":"","app_des":""}]
//         */
//
//        private String ad_link;
//        private String ad_name;
//        private String ad_code;
//        private List<PavilionCateBean> pavilion_cate;
//
//        public String getAd_link() {
//            return ad_link;
//        }
//
//        public void setAd_link(String ad_link) {
//            this.ad_link = ad_link;
//        }
//
//        public String getAd_name() {
//            return ad_name;
//        }
//
//        public void setAd_name(String ad_name) {
//            this.ad_name = ad_name;
//        }
//
//        public String getAd_code() {
//            return ad_code;
//        }
//
//        public void setAd_code(String ad_code) {
//            this.ad_code = ad_code;
//        }
//
//        public List<PavilionCateBean> getPavilion_cate() {
//            return pavilion_cate;
//        }
//
//        public void setPavilion_cate(List<PavilionCateBean> pavilion_cate) {
//            this.pavilion_cate = pavilion_cate;
//        }
//
//        public static class PavilionCateBean {
//            /**
//             * pavilion_cate_id : 178
//             * name : 广西
//             * app_banner :
//             * app_des :
//             */
//
//            private String pavilion_cate_id;
//            private String name;
//            private String app_banner;
//            private String app_des;
//
//            public String getPavilion_cate_id() {
//                return pavilion_cate_id;
//            }
//
//            public void setPavilion_cate_id(String pavilion_cate_id) {
//                this.pavilion_cate_id = pavilion_cate_id;
//            }
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//
//            public String getApp_banner() {
//                return app_banner;
//            }
//
//            public void setApp_banner(String app_banner) {
//                this.app_banner = app_banner;
//            }
//
//            public String getApp_des() {
//                return app_des;
//            }
//
//            public void setApp_des(String app_des) {
//                this.app_des = app_des;
//            }
//        }
//    }


}
