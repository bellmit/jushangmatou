package com.jsmt.developer.bean;

import java.util.List;

/**
 * Created by lt on 2019-05-05.
 */

public class PersonagerMessageBean {

    /**
     * status : 1
     * msg : 获取成功
     * result : {"store_class":[{"id":"1","name":"服饰/鞋包/配件"},{"id":"2","name":"饰品/辅料/工艺"},{"id":"3","name":"百货/食品/家居"},{"id":"4","name":"童具/母婴"},{"id":"5","name":"五金/机械/电工"},{"id":"6","name":"数码家电/汽车用品"},{"id":"7","name":"纺织/印刷/包装"},{"id":"8","name":"园艺/家装/宠物"},{"id":"9","name":"美妆/个护"},{"id":"12","name":"其他"}],"goods_category":[{"id":"1","name":"玩具/花类/花类配件"},{"id":"2","name":"珠宝工艺/厂"},{"id":"3","name":"饰品/工艺"},{"id":"1264","name":"特产水果"},{"id":"1266","name":"数码家电"},{"id":"818","name":"化纤坯布/棉坯布"},{"id":"819","name":"坯布/面料"},{"id":"820","name":"坯布/窗帘布艺"},{"id":"1268","name":"美妆"},{"id":"1270","name":"母婴用品"},{"id":"1271","name":"日用百货"},{"id":"800","name":"辅料"},{"id":"1609","name":"鞋靴"},{"id":"1611","name":"服装"},{"id":"1617","name":"特色工艺品/特色用具"},{"id":"1618","name":"针织/喜庆"},{"id":"708","name":"服饰/针织"},{"id":"1608","name":"饰品"},{"id":"814","name":"坯布/窗帘布艺"},{"id":"1621","name":"百货家居"},{"id":"742","name":"服装"},{"id":"1624","name":"汽车配件/用品"},{"id":"1436","name":"辅料"},{"id":"1426","name":"日用百货"},{"id":"2036","name":"生鲜水果/特色饮品"},{"id":"1477","name":"服装饰品"},{"id":"815","name":"坯布/窗帘工艺"},{"id":"463","name":"生产厂家"},{"id":"1593","name":"日用百货"},{"id":"802","name":"坯布"},{"id":"803","name":"窗帘布艺"},{"id":"804","name":"面料/坯布"},{"id":"805","name":"辅料"},{"id":"806","name":"面料/坯布"},{"id":"807","name":"家纺/面料"},{"id":"808","name":"窗帘布艺/坯布"},{"id":"809","name":"辅料/面料/坯布"},{"id":"810","name":"面料/坯布"},{"id":"811","name":"面料"},{"id":"812","name":"服装面料"},{"id":"813","name":"面料/坯布"},{"id":"1735","name":"糕点/豆制品"},{"id":"488","name":"办公设备/体育用品/户外用品"},{"id":"498","name":" 百货"},{"id":"519","name":"印刷原材料"},{"id":"1343","name":"数码家电"},{"id":"1283","name":"配件"},{"id":"817","name":"布艺/家纺"},{"id":"797","name":"窗帘布艺"},{"id":"5725","name":"服饰"},{"id":"772","name":"国际馆"},{"id":"754","name":"创意装饰用品"},{"id":"629","name":"饰品工艺"},{"id":"634","name":"美妆个护"},{"id":"636","name":"日用装饰品"},{"id":"642","name":"机械 / 家用电器"},{"id":"1476","name":"辅料"},{"id":"816","name":"面料/坯布"},{"id":"1276","name":"配件"},{"id":"1281","name":"服装"},{"id":"1157","name":"养生/美容"},{"id":"1025","name":"养生美容"},{"id":"1037","name":"民族工艺"},{"id":"1039","name":"特色食品/干货"},{"id":"1042","name":"养生/美容"},{"id":"1047","name":"特色食品/干货"},{"id":"1048","name":"特色工艺品"},{"id":"1049","name":"特色水果"},{"id":"1050","name":"水果生鲜"},{"id":"1051","name":"特色食品"},{"id":"1052","name":"饮品"},{"id":"1586","name":"日用百货"},{"id":"1279","name":"配件"},{"id":"5351","name":"平板产品"},{"id":"1351","name":"配件"},{"id":"1166","name":"服装"},{"id":"1277","name":"服装"},{"id":"1144","name":"生鲜水果"},{"id":"2452","name":"工艺品"},{"id":"1226","name":"数码家电"},{"id":"1160","name":"配件"},{"id":"1161","name":"腌制品"},{"id":"1163","name":"辅料"},{"id":"1159","name":"民族特色服饰"},{"id":"1313","name":"数码家电"},{"id":"1146","name":"服装"},{"id":"1149","name":"日用百货"},{"id":"1151","name":"日用百货"},{"id":"1152","name":"文体用品"},{"id":"1153","name":"日用百货"},{"id":"1154","name":"特色工艺/特色用具"},{"id":"1155","name":"数码家电"},{"id":"1139","name":"特色饮品"},{"id":"1140","name":"特色副食品"},{"id":"2040","name":"饰品配件"},{"id":"1176","name":"服装"},{"id":"1190","name":"配件"},{"id":"1197","name":"服装"},{"id":"1236","name":"特色食品"},{"id":"1198","name":"数码家电"},{"id":"1200","name":"日用百货"},{"id":"1202","name":"装饰/工艺品"},{"id":"1206","name":"服装"},{"id":"1207","name":"装饰/工艺品"},{"id":"1214","name":"服装"},{"id":"1215","name":"首饰"},{"id":"1342","name":"电工产品"},{"id":"1253","name":"服装"},{"id":"1677","name":"生鲜水果/食品"},{"id":"1430","name":"服饰"},{"id":"1431","name":"服饰"},{"id":"1285","name":"配件"},{"id":"1286","name":"服装"},{"id":"1288","name":"服饰"},{"id":"1289","name":"服装"},{"id":"1291","name":"数码家电"},{"id":"1292","name":"配件"},{"id":"1294","name":"服装"},{"id":"1295","name":"数码家电"},{"id":"1296","name":"配件"},{"id":"1300","name":"服装"},{"id":"1302","name":"服装"},{"id":"1303","name":"服装"},{"id":"1304","name":"服装"},{"id":"1306","name":"数码家电"},{"id":"1308","name":"配件"},{"id":"1309","name":"服装"},{"id":"1311","name":"数码家电"},{"id":"1312","name":"配件"},{"id":"1314","name":"配件"},{"id":"1316","name":"数码家电"},{"id":"1319","name":"配件"},{"id":"1320","name":"数码家电"},{"id":"1322","name":"五金机械"},{"id":"1323","name":"服装"},{"id":"1324","name":"配件"},{"id":"1326","name":"数码家电"},{"id":"1329","name":"服装"},{"id":"1330","name":"配件"},{"id":"1331","name":"服装"},{"id":"1333","name":"数码通讯"},{"id":"1338","name":"服装"},{"id":"2532","name":"服装"},{"id":"1340","name":"百货"},{"id":"1344","name":"配件"},{"id":"1348","name":"数码家电"},{"id":"1349","name":"服装"},{"id":"1352","name":"服装"},{"id":"1464","name":"服饰"},{"id":"1356","name":"服装"},{"id":"1359","name":"服装"},{"id":"1365","name":"服装"},{"id":"1370","name":"安防定位设备"},{"id":"1373","name":"纺织"},{"id":"1379","name":"数码家电"},{"id":"1386","name":"配件"},{"id":"1394","name":"数码家电"},{"id":"1397","name":"配件"},{"id":"1399","name":"数码家电"},{"id":"1435","name":"服装"},{"id":"1402","name":"配件"},{"id":"1404","name":"数码家电"},{"id":"1425","name":"饰品/工艺"},{"id":"1406","name":"服装"},{"id":"1407","name":"配件"},{"id":"4213","name":"特色工艺"},{"id":"1411","name":"数码家电"},{"id":"1412","name":"配件"},{"id":"1414","name":"数码电子"},{"id":"1429","name":"服饰"},{"id":"1605","name":"鞋靴"},{"id":"1439","name":"面料"},{"id":"1448","name":"服装"},{"id":"1453","name":"服装"},{"id":"1457","name":"辅料"},{"id":"1467","name":"鞋靴"},{"id":"1468","name":"服装"},{"id":"1469","name":"服装"},{"id":"1475","name":"特色干货"},{"id":"1481","name":"服装"},{"id":"1484","name":"服装"},{"id":"1491","name":"日用百货"},{"id":"1492","name":"日用百货"},{"id":"1493","name":"鞋靴"},{"id":"1494","name":"装饰/工艺品"},{"id":"1496","name":"装饰/工艺品"},{"id":"1498","name":"服装"},{"id":"1501","name":"日用百货"},{"id":"1502","name":"汽车用品"},{"id":"1503","name":"日用百货"},{"id":"1505","name":"汽车配件"},{"id":"1507","name":"鞋靴"},{"id":"1509","name":"箱包"},{"id":"1512","name":"百货"},{"id":"1517","name":"数码家电"},{"id":"1518","name":"饰品"},{"id":"1520","name":"美妆"},{"id":"1522","name":"纺织"},{"id":"1526","name":"辅料"},{"id":"1532","name":"辅料"},{"id":"1534","name":"箱包"},{"id":"1536","name":"鞋靴"},{"id":"1538","name":"鞋靴"},{"id":"1539","name":"鞋靴"},{"id":"1540","name":"饰品"},{"id":"1541","name":"箱包"},{"id":"1542","name":"鞋靴"},{"id":"1543","name":"饰品"},{"id":"1545","name":"鞋靴"},{"id":"1546","name":"饰品"},{"id":"1547","name":"鞋靴"},{"id":"1548","name":"鞋靴"},{"id":"1553","name":"数码家电"},{"id":"1580","name":"数码家电"},{"id":"1581","name":"服装"},{"id":"1589","name":"果脯/点心"},{"id":"1594","name":"饰品"},{"id":"1597","name":"鞋靴"},{"id":"1599","name":"箱包"},{"id":"1602","name":"服装"},{"id":"1604","name":"鞋靴"},{"id":"1606","name":"服装"},{"id":"1703","name":"坯布"},{"id":"1766","name":"生鲜水果/饮品"},{"id":"1772","name":"特色工艺品"},{"id":"1852","name":"生鲜水果/饮品"},{"id":"1859","name":"特色工艺品"},{"id":"2038","name":"特色工艺品"},{"id":"2041","name":"副食品/干货"},{"id":"2504","name":"数码家电"},{"id":"2106","name":"毛衣"},{"id":"2120","name":"生鲜水果"},{"id":"2122","name":"特色干货"},{"id":"2124","name":"特色工艺品"},{"id":"2130","name":"饮品"},{"id":"5512","name":"果饮"},{"id":"2328","name":"生鲜水果/干果"},{"id":"2330","name":"饮品/点心"},{"id":"2331","name":"特色工艺"},{"id":"2455","name":"生活百货"},{"id":"2475","name":"配件"},{"id":"2530","name":"美妆"},{"id":"2609","name":"特色水果/饮品"},{"id":"2611","name":"特色工艺品"},{"id":"2613","name":"特色副食品"},{"id":"2641","name":"笔筒"},{"id":"2655","name":"水果生鲜/饮品"},{"id":"2660","name":"特色工艺品"},{"id":"2702","name":"领带"},{"id":"2742","name":"特色水果/特色饮品"},{"id":"2745","name":"民族特色工艺品"},{"id":"2750","name":"特色食品/干货"},{"id":"2802","name":"牛仔夹克"},{"id":"2850","name":"特色水果/果脯"},{"id":"2851","name":"特色副食品"},{"id":"2853","name":"特色工艺"},{"id":"4215","name":"食品/特产干货"},{"id":"2984","name":"特色水果/饮品"},{"id":"2987","name":"特色工艺"},{"id":"2989","name":"特产食品/干货"},{"id":"5697","name":"运输机械"},{"id":"3073","name":"特产水果/饮品"},{"id":"3076","name":"特色工艺"},{"id":"3079","name":"特色食品/干货"},{"id":"3081","name":" 韩国进口服装城"},{"id":"3109","name":"切削刀具"},{"id":"3110","name":"钻头"},{"id":"3260","name":"生鲜水果/饮品"},{"id":"3266","name":"特色工艺"},{"id":"3268","name":"特色食品/干货"},{"id":"3290","name":"木箱"},{"id":"3323","name":"水果/饮品"},{"id":"3325","name":"特色工艺"},{"id":"3328","name":"副食品/干货"},{"id":"3390","name":"服装"},{"id":"3417","name":"水果生鲜"},{"id":"3420","name":"特色食品"},{"id":"3422","name":"特色工艺品"},{"id":"3470","name":"副食品/养生美容"},{"id":"3471","name":"中裤"},{"id":"3473","name":"特色工艺"},{"id":"3508","name":"配件"},{"id":"3567","name":"特产水果/饮品"},{"id":"3570","name":"特色食品/干货"},{"id":"3572","name":"服装"},{"id":"3574","name":"特色工艺"},{"id":"3577","name":"日用百货"},{"id":"3587","name":"鞋靴"},{"id":"3671","name":"特色食品/干货"},{"id":"3673","name":"特色工艺品"},{"id":"3729","name":"特色水果/饮品"},{"id":"3734","name":"特色食品/特色干货"},{"id":"3736","name":"特色工艺"},{"id":"3796","name":"服装"},{"id":"3812","name":"特色工艺"},{"id":"3814","name":"特色干货/食品"},{"id":"3816","name":"水果/饮品"},{"id":"3937","name":"特产水果/饮品"},{"id":"3941","name":"特色工艺"},{"id":"3943","name":"特色食品/干货"},{"id":"4045","name":"水果/饮品"},{"id":"4049","name":"特产食品/干货"},{"id":"4050","name":"特色工艺品"},{"id":"4142","name":"特色工艺"},{"id":"4147","name":"特色水果/饮品"},{"id":"4150","name":"副食品/干货"},{"id":"4211","name":"水果/特色饮品"},{"id":"4316","name":"特色水果/饮品"},{"id":"4321","name":"特色食品/干货"},{"id":"4322","name":"特色工艺品"},{"id":"4390","name":"特色工艺"},{"id":"4392","name":"特色食品/干货"},{"id":"4394","name":"服装"},{"id":"4434","name":"特产水果/饮品"},{"id":"4435","name":"特色工艺"},{"id":"4436","name":"特产食品/干货"},{"id":"4555","name":"杯子"},{"id":"4556","name":"财务用品"},{"id":"4558","name":"玩具百货"},{"id":"4633","name":"服装"},{"id":"4670","name":"女装"},{"id":"4671","name":"童装"},{"id":"5859","name":"生活用品/箱包"},{"id":"4730","name":"皮衣"},{"id":"4842","name":"更多包"},{"id":"4930","name":"童装 "},{"id":"5013","name":"汽车配件"},{"id":"5014","name":"汽车配件"},{"id":"5015","name":"汽车用品"},{"id":"5016","name":"汽车配件"},{"id":"5019","name":"汽车用品"},{"id":"5020","name":"汽车用品"},{"id":"5025","name":"传动器配件"},{"id":"5053","name":"汽车配件"},{"id":"5055","name":"汽车用品"},{"id":"5056","name":"汽车用品"},{"id":"5059","name":"汽车配件"},{"id":"5062","name":"汽车配件"},{"id":"5066","name":"汽车用品"},{"id":"5125","name":"饮品"},{"id":"5154","name":"食品 生鲜"},{"id":"5911","name":"海淘"},{"id":"5908","name":"辅料"},{"id":"5691","name":"更多腌制素食"},{"id":"5762","name":"鞋靴"},{"id":"5835","name":"家宠"},{"id":"5938","name":"工艺品"},{"id":"6008","name":"内蒙古特产"}],"pavilion":[{"id":"1","name":"义乌馆"},{"id":"2","name":"柯桥馆"},{"id":"3","name":"深圳馆"},{"id":"4","name":"广州馆"},{"id":"5","name":"精品馆"},{"id":"8","name":"全国馆"},{"id":"9","name":"百姓馆"}]}
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
        private List<StoreClassBean> store_class;
        private List<GoodsCategoryBean> goods_category;
        private List<PavilionBean> pavilion;

        public List<StoreClassBean> getStore_class() {
            return store_class;
        }

        public void setStore_class(List<StoreClassBean> store_class) {
            this.store_class = store_class;
        }

        public List<GoodsCategoryBean> getGoods_category() {
            return goods_category;
        }

        public void setGoods_category(List<GoodsCategoryBean> goods_category) {
            this.goods_category = goods_category;
        }

        public List<PavilionBean> getPavilion() {
            return pavilion;
        }

        public void setPavilion(List<PavilionBean> pavilion) {
            this.pavilion = pavilion;
        }

        public static class StoreClassBean {
            /**
             * id : 1
             * name : 服饰/鞋包/配件
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

        public static class GoodsCategoryBean {
            /**
             * id : 1
             * name : 玩具/花类/花类配件
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

        public static class PavilionBean {
            /**
             * id : 1
             * name : 义乌馆
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
