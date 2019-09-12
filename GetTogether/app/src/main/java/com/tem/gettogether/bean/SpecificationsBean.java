package com.tem.gettogether.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.bean
 * @ClassName: SpecificationsBean
 * @Author: csc
 * @CreateDate: 2019/9/11 10:17
 * @Description:
 */
public class SpecificationsBean implements Serializable {


    /**
     * status : 1
     * msg : 获取成功
     * result : {"specList":[{"id":"195","name":"颜色"},{"id":"196","name":"尺码"},{"id":"197","name":"服饰工艺"},{"id":"198","name":"面料"},{"id":"199","name":"版型"},{"id":"200","name":"品牌"}]}
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

    public static class ResultBean implements Serializable {
        private List<SpecListBean> specList;

        public List<SpecListBean> getSpecList() {
            return specList;
        }

        public void setSpecList(List<SpecListBean> specList) {
            this.specList = specList;
        }

        public static class SpecListBean implements Serializable {
            /**
             * id : 195
             * name : 颜色
             */

            private String id;
            private String name;
            private String isChoose;

            public String getIsChoose() {
                return isChoose;
            }

            public void setIsChoose(String isChoose) {
                this.isChoose = isChoose;
            }

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
        }
    }
}
