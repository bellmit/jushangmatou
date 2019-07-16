package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-11.
 */

public class AddressDataBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"1","name":"北京市","level":"1","parent_id":"0"},{"id":"338","name":"天津市","level":"1","parent_id":"0"},{"id":"636","name":"河北省","level":"1","parent_id":"0"},{"id":"3102","name":"山西省","level":"1","parent_id":"0"},{"id":"4670","name":"内蒙古自治区","level":"1","parent_id":"0"},{"id":"5827","name":"辽宁省","level":"1","parent_id":"0"},{"id":"7531","name":"吉林省","level":"1","parent_id":"0"},{"id":"8558","name":"黑龙江省","level":"1","parent_id":"0"},{"id":"10543","name":"上海市","level":"1","parent_id":"0"},{"id":"10808","name":"江苏省","level":"1","parent_id":"0"},{"id":"12596","name":"浙江省","level":"1","parent_id":"0"},{"id":"14234","name":"安徽省","level":"1","parent_id":"0"},{"id":"16068","name":"福建省","level":"1","parent_id":"0"},{"id":"17359","name":"江西省","level":"1","parent_id":"0"},{"id":"19280","name":"山东省","level":"1","parent_id":"0"},{"id":"21387","name":"河南省","level":"1","parent_id":"0"},{"id":"24022","name":"湖北省","level":"1","parent_id":"0"},{"id":"25579","name":"湖南省","level":"1","parent_id":"0"},{"id":"28240","name":"广东省","level":"1","parent_id":"0"},{"id":"30164","name":"广西壮族自治区","level":"1","parent_id":"0"},{"id":"31563","name":"海南省","level":"1","parent_id":"0"},{"id":"31929","name":"重庆市","level":"1","parent_id":"0"},{"id":"33007","name":"四川省","level":"1","parent_id":"0"},{"id":"37906","name":"贵州省","level":"1","parent_id":"0"},{"id":"39556","name":"云南省","level":"1","parent_id":"0"},{"id":"41103","name":"西藏自治区","level":"1","parent_id":"0"},{"id":"41877","name":"陕西省","level":"1","parent_id":"0"},{"id":"43776","name":"甘肃省","level":"1","parent_id":"0"},{"id":"45286","name":"青海省","level":"1","parent_id":"0"},{"id":"45753","name":"宁夏回族自治区","level":"1","parent_id":"0"},{"id":"46047","name":"新疆维吾尔自治区","level":"1","parent_id":"0"},{"id":"47493","name":"台湾省","level":"1","parent_id":"0"},{"id":"47494","name":"香港特别行政区","level":"1","parent_id":"0"},{"id":"47495","name":"澳门特别行政区","level":"1","parent_id":"0"}]
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
         * id : 1
         * name : 北京市
         * level : 1
         * parent_id : 0
         */

        private String id;
        private String name;
        private String level;
        private String parent_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }
    }
}
