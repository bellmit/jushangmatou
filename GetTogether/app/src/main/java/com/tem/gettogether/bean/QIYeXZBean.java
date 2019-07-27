package com.tem.gettogether.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-05.
 */

public class QIYeXZBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"company_type":[{"id":"0","name":"股份有限公司"},{"id":"1","name":"个人独立企业"},{"id":"2","name":"有限责任公司"},{"id":"3","name":"外资"},{"id":"4","name":"中外合资"},{"id":"5","name":"国企"},{"id":"6","name":"合伙制企业"},{"id":"7","name":"其它"}],"rate_list":[{"id":"0","name":"0%"},{"id":"3","name":"3%"},{"id":"6","name":"6%"},{"id":"7","name":"7%"},{"id":"11","name":"11%"},{"id":"13","name":"13%"},{"id":"17","name":"17%"}],"taxpayer":[{"id":"1","name":"一般纳税人"},{"id":"2","name":"小规模纳税人"},{"id":"3","name":"非增值税纳税人"}]}
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
        private List<CompanyTypeBean> company_type;
        private List<RateListBean> rate_list;
        private List<TaxpayerBean> taxpayer;

        public List<CompanyTypeBean> getCompany_type() {
            return company_type;
        }

        public void setCompany_type(List<CompanyTypeBean> company_type) {
            this.company_type = company_type;
        }

        public List<RateListBean> getRate_list() {
            return rate_list;
        }

        public void setRate_list(List<RateListBean> rate_list) {
            this.rate_list = rate_list;
        }

        public List<TaxpayerBean> getTaxpayer() {
            return taxpayer;
        }

        public void setTaxpayer(List<TaxpayerBean> taxpayer) {
            this.taxpayer = taxpayer;
        }

        public static class CompanyTypeBean {
            /**
             * id : 0
             * name : 股份有限公司
             */

            private String id;
            private String name;

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

        public static class RateListBean {
            /**
             * id : 0
             * name : 0%
             */

            private String id;
            private String name;

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

        public static class TaxpayerBean {
            /**
             * id : 1
             * name : 一般纳税人
             */

            private String id;
            private String name;

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
