package com.tem.gettogether.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StoreManagerListEntity implements Serializable {

    public static class SkuListEntity implements Serializable {
        /*
        "sku_id": "7",
		"spec": "绿色:10寸",
		"sku_name": "颜色,尺码",
		"price": "10.00",
		"stock": "0"
         */
        public String sku_id;
        public String spec;
        public String sku_name;
        public String price;
        public String stock;
    }

    public static class GuigesEntity implements Serializable {
        /*
        "title": "颜色",
		"guigeArray": ["颜色：绿色"]
		"itemGuigeArray":["绿色"]
         */
        public String title;
        public String titleID;
        public List<String> guigeArray= new ArrayList<>();
        public List<String> itemGuigeArray= new ArrayList<>();
    }

}
