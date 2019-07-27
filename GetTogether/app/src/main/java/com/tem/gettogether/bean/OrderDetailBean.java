package com.tem.gettogether.bean;

import java.util.List;

public class OrderDetailBean {

    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }



    public static class ResultBean {
        private String name;
        private String address;
        private String color;
        private String size;
        private String price;
        private String num;
        private String time;

        public ResultBean(String name, String address, String color,String size,String price,String num,String time) {
            this.name = name;
            this.address = address;
            this.color = color;
            this.size = size;
            this.price = price;
            this.num = num;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

}
