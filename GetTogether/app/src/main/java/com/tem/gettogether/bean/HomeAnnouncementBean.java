package com.tem.gettogether.bean;

import java.util.List;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/11/14 14:55 
 */
public class HomeAnnouncementBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"announcement":[{"store_name":"热烈祝贺红富游彩印纸品厂旗舰店入驻聚商码头"},{"store_name":"热烈祝贺电力工程设备专家旗舰店入驻聚商码头"},{"store_name":"热烈祝贺鑫亿莱旗舰店入驻聚商码头"},{"store_name":"热烈祝贺一美之光旗舰店入驻聚商码头"},{"store_name":"热烈祝贺金宇燃具旗舰店入驻聚商码头"},{"store_name":"热烈祝贺扬州天冠日化有限公司旗舰店入驻聚商码头"},{"store_name":"热烈祝贺回忆鞋厂旗舰店入驻聚商码头"},{"store_name":"热烈祝贺永康市悦途户外旗舰店入驻聚商码头"},{"store_name":"热烈祝贺义乌市骄阳围巾旗舰店入驻聚商码头"},{"store_name":"热烈祝贺骄阳围巾旗舰店入驻聚商码头"},{"store_name":"热烈祝贺胶带直卖旗舰店入驻聚商码头"},{"store_name":"热烈祝贺义乌鑫光服装辅料配件旗舰店入驻聚商码头"},{"store_name":"热烈祝贺四通五金商会旗舰店入驻聚商码头"},{"store_name":"热烈祝贺浙江智宸机械有限公司旗舰店入驻聚商码头"},{"store_name":"热烈祝贺风帆管业旗舰店入驻聚商码头"},{"store_name":"热烈祝贺义乌世嘉园林机械旗舰店入驻聚商码头"},{"store_name":"热烈祝贺义乌市晟航五金工具旗舰店入驻聚商码头"},{"store_name":"热烈祝贺巴莱凯电器有限公司旗舰店入驻聚商码头"},{"store_name":"热烈祝贺杰克凯电子科技有限公司旗舰店入驻聚商码头"},{"store_name":"热烈祝贺路时安旗舰店入驻聚商码头"}]}
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
        private List<AnnouncementBean> announcement;

        public List<AnnouncementBean> getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(List<AnnouncementBean> announcement) {
            this.announcement = announcement;
        }

        public static class AnnouncementBean {
            /**
             * store_name : 热烈祝贺红富游彩印纸品厂旗舰店入驻聚商码头
             */

            private String store_name;

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }
        }
    }
}
