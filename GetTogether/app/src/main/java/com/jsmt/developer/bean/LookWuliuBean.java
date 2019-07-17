package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-03-28.
 */

public class LookWuliuBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : [{"id":"72","order_id":"415","order_sn":"201902281512091751","user_id":"131","admin_id":"47","consignee":"测试","zipcode":"111111","mobile":"18705535535","country":"0","province":"1","city":"2","district":"3","address":"测试","shipping_code":"shentong","shipping_name":"申通物流","shipping_price":"22.00","invoice_no":"123456","tel":null,"note":"","best_time":null,"create_time":"1552639757","is_del":"0","store_id":"50","list":{"message":"ok","nu":"1193282791013","ischeck":"1","condition":"F00","com":"ems","status":"200","state":"3","data":[{"time":"2019-03-12 17:47:45","ftime":"2019-03-12 17:47:45","context":"[杭州市邮政速递物流公司高新区分公司古翠路揽投站]在 杭州市 投递并签收，签收人：他人收 91号菜鸟驿站"},{"time":"2019-03-12 14:52:35","ftime":"2019-03-12 14:52:35","context":"杭州市邮政速递物流公司高新区分公司古翠路揽投站安排投递，预计21:00:00前投递（投递员姓名：聂清清;联系电话：18072853715）"},{"time":"2019-03-12 13:00:19","ftime":"2019-03-12 13:00:19","context":"离开杭州处理中心 发往杭州市邮政速递物流公司高新区分公司古翠路揽投站"},{"time":"2019-03-12 10:17:56","ftime":"2019-03-12 10:17:56","context":"已离开杭州邮政速递东区钱江新城揽投站，发往杭州处理中心"},{"time":"2019-03-12 08:54:00","ftime":"2019-03-12 08:54:00","context":"杭州市 杭州邮政速递东区钱江新城揽投站已收件（朱诚凯）"}]}}]
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
         * id : 72
         * order_id : 415
         * order_sn : 201902281512091751
         * user_id : 131
         * admin_id : 47
         * consignee : 测试
         * zipcode : 111111
         * mobile : 18705535535
         * country : 0
         * province : 1
         * city : 2
         * district : 3
         * address : 测试
         * shipping_code : shentong
         * shipping_name : 申通物流
         * shipping_price : 22.00
         * invoice_no : 123456
         * tel : null
         * note :
         * best_time : null
         * create_time : 1552639757
         * is_del : 0
         * store_id : 50
         * list : {"message":"ok","nu":"1193282791013","ischeck":"1","condition":"F00","com":"ems","status":"200","state":"3","data":[{"time":"2019-03-12 17:47:45","ftime":"2019-03-12 17:47:45","context":"[杭州市邮政速递物流公司高新区分公司古翠路揽投站]在 杭州市 投递并签收，签收人：他人收 91号菜鸟驿站"},{"time":"2019-03-12 14:52:35","ftime":"2019-03-12 14:52:35","context":"杭州市邮政速递物流公司高新区分公司古翠路揽投站安排投递，预计21:00:00前投递（投递员姓名：聂清清;联系电话：18072853715）"},{"time":"2019-03-12 13:00:19","ftime":"2019-03-12 13:00:19","context":"离开杭州处理中心 发往杭州市邮政速递物流公司高新区分公司古翠路揽投站"},{"time":"2019-03-12 10:17:56","ftime":"2019-03-12 10:17:56","context":"已离开杭州邮政速递东区钱江新城揽投站，发往杭州处理中心"},{"time":"2019-03-12 08:54:00","ftime":"2019-03-12 08:54:00","context":"杭州市 杭州邮政速递东区钱江新城揽投站已收件（朱诚凯）"}]}
         */

        private String id;
        private String order_id;
        private String order_sn;
        private String user_id;
        private String admin_id;
        private String consignee;
        private String zipcode;
        private String mobile;
        private String country;
        private String province;
        private String city;
        private String district;
        private String address;
        private String shipping_code;
        private String shipping_name;
        private String shipping_price;
        private String invoice_no;
        private Object tel;
        private String note;
        private Object best_time;
        private String create_time;
        private String is_del;
        private String store_id;
        private ListBean list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public String getShipping_name() {
            return shipping_name;
        }

        public void setShipping_name(String shipping_name) {
            this.shipping_name = shipping_name;
        }

        public String getShipping_price() {
            return shipping_price;
        }

        public void setShipping_price(String shipping_price) {
            this.shipping_price = shipping_price;
        }

        public String getInvoice_no() {
            return invoice_no;
        }

        public void setInvoice_no(String invoice_no) {
            this.invoice_no = invoice_no;
        }

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Object getBest_time() {
            return best_time;
        }

        public void setBest_time(Object best_time) {
            this.best_time = best_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * message : ok
             * nu : 1193282791013
             * ischeck : 1
             * condition : F00
             * com : ems
             * status : 200
             * state : 3
             * data : [{"time":"2019-03-12 17:47:45","ftime":"2019-03-12 17:47:45","context":"[杭州市邮政速递物流公司高新区分公司古翠路揽投站]在 杭州市 投递并签收，签收人：他人收 91号菜鸟驿站"},{"time":"2019-03-12 14:52:35","ftime":"2019-03-12 14:52:35","context":"杭州市邮政速递物流公司高新区分公司古翠路揽投站安排投递，预计21:00:00前投递（投递员姓名：聂清清;联系电话：18072853715）"},{"time":"2019-03-12 13:00:19","ftime":"2019-03-12 13:00:19","context":"离开杭州处理中心 发往杭州市邮政速递物流公司高新区分公司古翠路揽投站"},{"time":"2019-03-12 10:17:56","ftime":"2019-03-12 10:17:56","context":"已离开杭州邮政速递东区钱江新城揽投站，发往杭州处理中心"},{"time":"2019-03-12 08:54:00","ftime":"2019-03-12 08:54:00","context":"杭州市 杭州邮政速递东区钱江新城揽投站已收件（朱诚凯）"}]
             */

            private String message;
            private String nu;
            private String ischeck;
            private String condition;
            private String com;
            private String status;
            private String state;
            private List<DataBean> data;

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getNu() {
                return nu;
            }

            public void setNu(String nu) {
                this.nu = nu;
            }

            public String getIscheck() {
                return ischeck;
            }

            public void setIscheck(String ischeck) {
                this.ischeck = ischeck;
            }

            public String getCondition() {
                return condition;
            }

            public void setCondition(String condition) {
                this.condition = condition;
            }

            public String getCom() {
                return com;
            }

            public void setCom(String com) {
                this.com = com;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * time : 2019-03-12 17:47:45
                 * ftime : 2019-03-12 17:47:45
                 * context : [杭州市邮政速递物流公司高新区分公司古翠路揽投站]在 杭州市 投递并签收，签收人：他人收 91号菜鸟驿站
                 */

                private String time;
                private String ftime;
                private String context;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getFtime() {
                    return ftime;
                }

                public void setFtime(String ftime) {
                    this.ftime = ftime;
                }

                public String getContext() {
                    return context;
                }

                public void setContext(String context) {
                    this.context = context;
                }
            }
        }
    }
}
