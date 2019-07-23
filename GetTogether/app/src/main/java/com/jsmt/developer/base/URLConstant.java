package com.jsmt.developer.base;

/**
 * Created by pc on 2018/4/16.
 */

public class URLConstant {
    //正式环境
//    public final static String SERVER = "";
    //测试环境

    public final static String SERVER = "http://www.jsmtgou.com/jushangmatou/index.php";//www.jushangmat.com  www.jsmtgou.com
//    public final static String SERVER = "http://test.uonep.com/jushangmatou/index.php";//www.jushangmat.com  www.jsmtgou.com
    //注册
    public final static String REGISTER = SERVER + "/Api/User/reg";
    //忘记密码
    public final static String FORGET_PASS = SERVER + "/Api/User/forgetPwd";
    //获取验证码
    public final static String HUOQU_CODE = SERVER + "/Api/User/send_sms_reg_code";
    //三方登录
    public final static String DISANF_LOGIN = SERVER + "/Api/User/thirdLogin";
    //登录
    public final static String LOGIN = SERVER + "/Api/User/login";
    //首页数据
    public final static String HONEDATA = SERVER + "/Api/Index/home";

    //首页外贸求购
    public final static String HONEBUYDATA = SERVER + "/Api/Goods/ftrade_buyList";

    // 首页外贸热销
    public final static String HONEHOTSELLDATA = SERVER + "/Api/Goods/trade_order_pasted";

    // 首页外贸联盟
    public final static String HONEALLIANCEDATA = SERVER + "/Api/Goods/ftrade_union";

    // 首页外贸新品
    public final static String HONENEWPRODUCTDATA = SERVER + "/Api/Goods/ftrade_newList";

    //外贸求购详情
    public final static String HOMEQIUGOUDETAIL = SERVER + "/Api/Goods/ftrade_buyDetail";

    //首页地区分类--市场分类数据
    public final static String HONESHICHANGDATA = SERVER + "/Api/Index/goodsSecAndThirdCategoryList";
    public final static String HONE_GUANFENLIELIEBIO = SERVER + "/Api/Index/pavilion_cate";

    //通告详情
    public final static String MESSAGEXIANGQING = SERVER + "/Api/Index/about";
    //搜索历史/Api/Goods/goods_search
    public final static String SOUSUOLISHI = SERVER + "/Api/Goods/goods_search";
    //热搜列表
    public final static String RESHOULIEBIAO = SERVER + "/Api/Goods/goods_hot_search";
    //清空搜索历史
    public final static String REMOVESOUSUOLISHI = SERVER + "/Api/Goods/empty_search";
    //商品列表或商品搜索
    public final static String SHANGPINLIEBIAO = SERVER + "/Api/Goods/goodsList";
    //店铺首页头部
    public final static String SHOPHOMEHEAD = SERVER + "/Api/Store/about";
    //获取店铺商品分类
    public final static String SHOPPINGFENLIE = SERVER + "/Api/Store/getStoreGoodsClass";
    //店铺关注
    public final static String SHOPISGUANZHU = SERVER + "/Api/Store/collectStoreOrNo";
    //店铺评价
    public final static String SHOPPINGJIA = SERVER + "/Api/Store/getGoodsComment";
    //店铺动态
    public final static String SHOPPINGJIA_DONGTAI = SERVER + "/Api/Store/dynamic";

    //获取店铺商品
    public final static String SHOPSHOPIINGDATA = SERVER + "/Api/Store/index";
    //店铺详情
    public final static String SHOPXIANGQINGDATA = SERVER + "/Api/Store/storeDetail";
    //店铺列表
    public final static String SHOPLIEBIAO = SERVER + "/Api/Store/storeList";
    //一级分类
    public final static String SHOPFNENLIE_LEFTDATA = SERVER + "/Api/Goods/goodsCategoryList";
    //通过一级获取下级分类
    public final static String SHOPFENLEI_RIGHT = SERVER + "/Api/Goods/goodsSecAndThirdCategoryList";
    //商品详情
    public final static String SHOPFENLEI_XIANGQING = SERVER + "/Api/Goods/goodsInfo";
    // 取消收藏、收藏商品
    public final static String SHOPPING_SHOUCANG = SERVER + "/Api/Goods/collectGoods";
    // 商品评价
    public final static String SHOPPING_PINGJIA = SERVER + "/Api/Goods/getGoodsComment";
    // 足迹
    public final static String SHOPPING_ZUJI = SERVER + "/Api/User/footprint_list";
    // 清空足迹
    public final static String SHOPPING_REMOVEALLZUJI = SERVER + "/Api/User/empty_footprint";
    // 删除足迹
    public final static String SHOPPING_REMOVEITEMZUJI = SERVER + "/Api/User/del_footprint";
    // 删除足迹
    public final static String SHOPPING_KSBH = SERVER + "/api/User/goodsList";
    // 获取省市区地址
    public final static String GET_ADDRESS = SERVER + "/Api/User/getRegion";
    // 添加、修改地址
    public final static String ADD_XIUGAI_ADDRESS = SERVER + "/Api/User/addAddress";
    // 地址列表
    public final static String GEI_ADDRESS_LIEBIAO = SERVER + "/Api/User/getAddressList";
    // 删除地址
    public final static String REMOVE_ADDRESS_LIEBIAO = SERVER + "/Api/User/del_address";
    //地址详情
    public final static String ADDRESS_XIANGQ = SERVER + "/Api/User/addressDetial";
    //设置默认收货地址接口地
    public final static String ADDRESS_SETTINGMOREN = SERVER + "/Api/User/setDefaultAddress";
    //收藏店铺列表
    public final static String SC_SHOPLIEBIAO = SERVER + "/Api/User/getUserCollectStore";
    //收藏商品列表
    public final static String SC_SHOPPINGLIEBIAO = SERVER + "/Api/User/getGoodsCollect";
    //上传图片
    public final static String SHANGCHUAN_IMAGE = SERVER + "/Api/Index/upload_base64";
    //修改个人信息
    public final static String XIUGAI_MESSAGE = SERVER + "/Api/User/updateUserInfo";
    //获取个人信息
    public final static String GET_MESSAGE = SERVER + "/Api/User/userInfo";
    //提交意见
    public final static String TIJIAOYIJIAN = SERVER + "/Api/User/message_list";
    //关于我们
    public final static String GYWMEN = SERVER + "/Api/Index/about";
    //服务协议
    public final static String FUWUXIEYI = SERVER + "/Api/User/protocol";

    //加入购物车
    public final static String ADDCART = SERVER + "/Api/Cart/addCart";
    // 进货车列表
    public final static String CART_LIEBIAO = SERVER + "/Api/Cart/cartList";
    //删除进货车
    public final static String REMOVE_CART = SERVER + "/Api/Cart/delCart";
    //结算
    public final static String TIJIAO_DiNDDAN = SERVER + "/Api/Cart/cart3";
    //结算
    public final static String JIESUAN_PAY = SERVER + "/Api/Cart/cart2";

    //商铺认证
    public final static String SHOP_RENZHENG = SERVER + "/Api/Newjoin/user_seller_info";
    //提现记录
    public final static String YUETIXIAN_JL = SERVER + "/Api/User/withdraw";
    //充值记录
    public final static String CHONGZHI_JL = SERVER + "/Api/User/recharge";
    //余额提现
    public final static String YETX_TX = SERVER + "/Api/User/withdraw_apply";
    //余额微信充值
    public final static String YECZ_WX = SERVER + "/api/Wxpay/getPay";
    //余额支付宝充值
    public final static String YECZ_ZFB = SERVER + "/api/Payment/getPay";
    //订单支付宝支付
    public final static String ORDER_ALI_PAY = SERVER + "/api/Payment/dopay";
    //订单微信支付
    public final static String ORDER_WECHAT_PAY = SERVER + "/api/Wxpay/dopay";
    //订单余额支付
    public final static String ORDER_YUE_PAY = SERVER + "/api/Money/dopay";

    //修改登录密码
    public final static String XIUGIA_LOGINPASSWORLD = SERVER + "/Api/Member/editpass";
    //修改支付密码
    public final static String XIUGIA_PAYPASS = SERVER + "/Api/Member/editpaypass";
    //验证支付密码
    public final static String YANZHEWNG_PAYPASS = SERVER + "/Api/Member/editpaypass_verify";
    //推出登录
    public final static String LOGOUT_TC = SERVER + "/Api/User/logout";
    //绑定手机号
    public final static String BANGDING_PHONE = SERVER + "/Api/Member/editphone";
    //订单列表
    public final static String DINGDAN_LIEBIAO = SERVER + "/Api/User/getOrderList";
    //取消订单
    public final static String CANCLE_LIEBIAO = SERVER + "/Api/User/cancelOrder";
    //删除订单 /Api/User/delOrder
    public final static String SHANCHUDINGDAN = SERVER + "/Api/User/delOrder";
    //提醒发货
    public final static String TIXINGFAHUO = SERVER + "/Api/User/remindOrder";

    //收货确认
    public final static String QUERENSH_LIEBIAO = SERVER + "/Api/User/orderConfirm";
    // 订单详情
    public final static String QUERENSH_XIANGQING = SERVER + "/Api/User/getOrderDetail";
    // 查看物流
    public final static String LOOK_WULIUZHUANGTAI = SERVER + "/api/Goods/getlogistics";
    // 热门推荐
    public final static String HOT_TUIJIAN = SERVER + "/api/goods/hot";
    // 特价专区
    public final static String HOT_TEJIAZUANQU = SERVER + "/api/goods/special";
    // 求购相关选项
    public final static String MAIN_QGFBXGXX = SERVER + "/Api/User/getReleaseInfo";
    // 求购上传
    public final static String MAIN_GQSC = SERVER + "/Api/User/addRelease";
    // 绑定银行卡
    public final static String BANNGDING = SERVER + "/Api/Member/bind_bank";
    // 银行卡列表
    public final static String YINHANGFKALIEBIAO = SERVER + "/Api/User/bank_list";
    // 发布评价
    public final static String FABUPINGJIA = SERVER + "/Api/User/add_comment";
    //  基本信息上传
    public final static String JINBENXINXI_UPLOADING = SERVER + "/Api/Newjoin/index";
    //  获取个人信息列表
    public final static String GRXINXLIEB = SERVER + "/Api/Newjoin/storeOption";
    //  上传认证个人
    public final static String GERENRUZHU_RENZHENG = SERVER + "/Api/Newjoin/personal";
    //  企业选择信息列表
    public final static String QIYEXUANZ_LIBIAO = SERVER + "/Api/Newjoin/enterpriseOption";
    //  企业认证信息上传
    public final static String QIYERZXXSC = SERVER + "/Api/Newjoin/enterprise";
    //  企业店铺信息上传
    public final static String QIYESHANGPU_RENZHENG = SERVER + "/Api/Newjoin/enterpriseStore";
    //  企业资质上传
    public final static String QIYEZIZI_SHANGFC = SERVER + "/Api/Newjoin/remark";
    //  馆分类选项
    public final static String GUANFEN_XUANXIANGF = SERVER + "/Api/Store/getPavilion";
    //   商品分类选项
    public final static String SHANPING_FENLEI = SERVER + "/Api/Store/getGoodsCate";
    //   本店分类
    public final static String BENDIANFENLEI = SERVER + "/Api/Store/getStoreCate";
    //   商品规格选项
    public final static String SHANGPING_GUIGE = SERVER + "/Api/Store/getSkuStatus";
    //   系统信息未读数量
    public final static String XITONGXIAO_WEIDU = SERVER + "/Api/user/getInformation";
    //   系统信息列表
    public final static String XITONGXIAO_LIEBIAO = SERVER + "/Api/user/getInformationList";
    //   系统信息列表
    public final static String REMOVE_MESSAGE = SERVER + "/Api/user/delInformation";

    //   修改我的店铺资料
    public final static String DIANPU_XINXI = SERVER + "/Api/Store/setmystoreDetail";
    //   我的店铺详情
    public final static String MYDEDIANPOU_XINXI = SERVER + "/Api/Store/getmystoreDetail";
    //   商品上传
    public final static String ADDXINCHANPIN = SERVER + "/Api/Store/addGoods";
    //   店铺发布动态
    public final static String FABUDONGTAI = SERVER + "/Api/Store/addDynamic";
    //   店铺发布动态
    public final static String DINAPUFABU = SERVER + "/Api/Store/getGoods";
    //   注册协议
    public final static String ZHUCEXIYEYI = SERVER + "/Api/User/getAgreement";
    //   商品列表
    public final static String SHOPPING_LIEBIAO = SERVER + "/Api/Store/getGoodsList";
    //   下架
    public final static String SHOPPING_XIAJIALIEBIAO = SERVER + "/Api/Store/setSale";
    //    求购列表
    public final static String SHOPPING_QIUGOULIEBIAO = SERVER + "/Api/Store/getPurchaseList";
    //    语言切换  首页
    public final static String YUYAN_QIEHUAN = SERVER + "/Api/Index/home";
    //   店铺服务费
    public final static String DIANPU_FUWUFEI = SERVER + "/Api/Store/getService";
    //   店铺服务费支付宝
    public final static String DIANPU_ZFB_FUWUFEI = SERVER + "/Api/Payment/servicePay";
    //   店铺服务费微信
    public final static String DIANPU_WX_FUWUFEI = SERVER + "/Api/Wxpay/servicePay";

}
