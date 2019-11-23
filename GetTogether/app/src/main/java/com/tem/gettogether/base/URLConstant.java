package com.tem.gettogether.base;

/**
 * Created by pc on 2018/4/16.
 */

public class URLConstant {
    //正式环境
//    public final static String SERVER = "";
    //测试环境

        public final static String SERVER = "http://www.jsmtgou.com/jushangmatou/index.php";//www.jushangmat.com  www.jsmtgou.com
//    public final static String SERVER = "http://m.jsmtgou.com/jushangmatou/index.php";//www.jushangmat.com  www.jsmtgou.com
    //注册
    public final static String REGISTER = SERVER + "/Api/User/reg";
    // 绑定手机号码
    public final static String BIND_PHONE = SERVER + "/Api/User/mobile_bind";
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

    // 首页外贸联盟详情
    public final static String HONEALLIANCEDETAILDATA = SERVER + "/Api/Goods/ftrade_unionDetail";

    // 首页外贸新品
    public final static String HONENEWPRODUCTDATA = SERVER + "/Api/Goods/ftrade_newList";

    //外贸求购详情
    public final static String HOMEQIUGOUDETAIL = SERVER + "/Api/Goods/ftrade_buyDetail";
    //外贸求购详情1
    public final static String HOMEQIUGOUDETAIL1 = SERVER + "/Api/Goods/ftrade_buyDetail1";

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
    // 个人资质上传
    public final static String GERENZIZI_SHANGFC = SERVER + "/Api/Newjoin/personal_remark";
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
    public final static String ADDXINCHANPIN = SERVER + "/Api/User/new_add_goods";
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
    public final static String DIANPU_ZFB_FUWUFEI = SERVER + "/Api/Payment/mservicePay";
    //   店铺服务费微信
    public final static String DIANPU_WX_FUWUFEI = SERVER + "/Api/Wxpay/mservicePay";
    //   判断店铺审核是否通过接口
    public final static String DIAN_PU_SHEN_HE = SERVER + "/Api/Newjoin/apply_state";

    // 获取会员付费信息
    public final static String JOIN_USER_LEVER = SERVER + "/Api/User/join_user_level";

    // 获取会员信息
    public final static String MEMBER_INFORMATION = SERVER + "/Api/User/user_level";

    // 获取店铺信息
    public final static String SHOP_INFORMATION = SERVER + "/Api/User/store_info";

    // 获取商品分类信息
    public final static String CATEGORIES = SERVER + "/Api/User/get_goods_category";

    // 获取店铺认证失败接口
    public final static String RZ_FAILED = SERVER + "/Api/User/fail_reason";

    // 产品管理接口
    public final static String PRODUCT_MANAGEMENT = SERVER + "/Api/User/out_sale_goods";

    // 上架
    public final static String SHELF_URL = SERVER + "/Api/User/on_sale_button";

    // 下架
    public final static String OBTAINED_URL = SERVER + "/Api/User/out_sale_button";

    // 编辑
    public final static String EDIT_URL = SERVER + "/Api/User/will_add_goods";

    // 删除
    public final static String DELETE_URL = SERVER + "/Api/User/del_button";

    // 新分类接口
    public final static String SEARCH_URL = SERVER + "/Api/User/get_goods_category";

    // 新分类接口列表
    public final static String SEARCH_LIST_URL = SERVER + "/Api/Goods/category3_goodsList";

    // 企业信息
    public final static String ENTERPISE_INFO = SERVER + "/Api/User/enterprise_info";

    // 企业信息保存
    public final static String ENTERPISE_INFO_SAVE = SERVER + "/Api/User/enterprise_personal_info_submit";

    // 采购商个人信息保存
    public final static String CAIGOU_ENTERPISE_INFO_SAVE = SERVER + "/Api/User/personal_info_submit";

    // 个人信息
    public final static String PERSION_INFO = SERVER + "/Api/User/personal_info";

    // 采购商认证
    public final static String CAIGOUSHANG_RZ = SERVER + "/Api/Newjoin/buyer_info";

    // 采购商认证失败原因
    public final static String CAIGOUSHANG_RZ_REASON = SERVER + "/Api/User/buyer_fail_reason";

    // 采购商求购信息删除接口
    public final static String BUYING_MANAGEMENT_DELETE = SERVER + "/Api/Goods/buyer_tradeDel";

    // 购物车数量
    public final static String CART_NUM = SERVER + "/Api/Cart/mod_goods_num";

    // 确认收货
    public final static String CONFIRM_RECEIPT = SERVER + "/Api/User/buyer_receiveConfirm";

    // 确认发货
    public final static String CONFIRM_SEND = SERVER + "/Api/User/seller_sendConfirm";

    // 确认结款
    public final static String CONFIRM_GET_MONEY = SERVER + "/Api/User/seller_getMoneyConfirm";

    // 求购管理
    public final static String BUY_MANAGER = SERVER + "/Api/Goods/buyer_tradeList";

    // 上传图片接口
    public final static String UPLOAD_PICTURE = SERVER + "/Api/Index/upload_binary";

    //临沂店铺商品分类接口
    public final static String LINYI_SHOP_CLASSIFICATION = SERVER + "/Api/User/linyiget_goods_category";

    // 规格接口
    public final static String SPECIFICARIONS_DATA = SERVER + "/Api/User/get_spec_select";

    // 规格添加
    public final static String SPECIFICARIONS_ADD = SERVER + "/Api/User/add_spec_item";

    // 规格删除
    public final static String SPECIFICARIONS_DELETE = SERVER + "/Api/User/del_spec_item";

    // 商家发布商品时添加规格后返回的item拼接接口
    public final static String SPECIFICARIONS_INPUT = SERVER + "/Api/User/get_spec_input";

    // 粉丝接口
    public final static String FANS_DATA = SERVER + "/Api/Store/store_fans_list";

    // 访客接口
    public final static String VISITOR_DATA = SERVER + "/Api/Store/store_visiter_list";

    // 店铺装修
    public final static String SHOP_DECORATION = SERVER + "/Api/Store/store_interface_mod";

    // 店铺logo和背景图片上传接口
    public final static String UPLOAD_SHOP_PICTURE = SERVER + "/Api/Store/upload_binary_logobanner";

    // 店铺banner
    public final static String UPLOAD_SHOP_BANNER_PICTURE = SERVER + "/Api/Store/upload_binary_adcode";

    // 店铺装修修改
    public final static String SHOP_DECORATION_MODIFY = SERVER + "/Api/Store/store_interface_save";

    //临沂搜索
    public final static String YILIAN_SEARCH = SERVER + "/Api/Goods/linyigoodsList";

    // 临沂新分类接口列表
    public final static String LIANYI_SEARCH_LIST_URL = SERVER + "/Api/Goods/linyicategory3_goodsList";

    //App端聊天获取用户角色接口
    public final static String MESSAGE_HEAD = SERVER + "/Api/User/user_info";

    //微信退款接口
    public final static String WXPAY_REFUND_URL = SERVER + "/Api/Wxpay/mpayBack";

    //支付宝退款接口
    public final static String OLD_ALIPAY_REFUND_URL = SERVER + "/Api/Payment/mpayBack";

    // 融云获取头像昵称
    public final static String RONGYUN_NICKNAME_HEADPIC = SERVER + "/Api/User/getuser";

    // 支付宝退款
    public final static String ALIPAY_REFUND_URL = SERVER + "/Api/User/pay_back";

    // 普通会员升级高级会员
    public final static String ALIPAY_REFUND_BALANCE_URL = SERVER + "/Api/User/upgrade_pay_back";

    // 普通会员退款金额接口
    public final static String GET_REFUND_AMOUNT = SERVER + "/Api/User/pay_back_money";

    // 退款进度
    public final static String GET_REFUND_PROGRESS = SERVER + "/Api/User/refund_status";

    // 电话验证码
    public final static String PHOEN_CODE = SERVER + "/Api/User/send_csms_reg_code";

    // 邮箱验证码
    public final static String EMAIL_CODE = SERVER + "/Api/User/send_mail_reg_code";

    // 国家代码
    public final static String COUNTRY_CODE = SERVER + "/Api/User/country_code";

    // 会员手机和邮箱注册接口
    public final static String PHONE_EMAIL_REGISTER = SERVER + "/Api/User/reg1";

    // 会员手机和邮箱登录接口
    public final static String PHONE_EMAIL_LOGIN = SERVER + "/Api/User/login1";

    // 会员用手机号码或邮箱找回密码接口
    public final static String FIND_PASSWORD = SERVER + "/Api/User/forgetPwd1";

    // 认证接口
    public final static String ENTERPRICE_PERSION_STORE = SERVER + "/Api/Newjoin/enterprise_person_store";

    // 获取店铺认证资料接口
    public final static String AUTHENTICATION_DATA = SERVER + "/Api/Newjoin/store_apply_info";

    // 微信facebook第三方登录状态检查接口
    public final static String FACEBOOK_WX_CHECK = SERVER + "/Api/Newjoin/facebook_wx_check";

    public final static String ANNOUNCEMENT_URL = SERVER + "/Api/Index/announcement";

    // 聚工厂
    public final static String FACTORY_LIST = SERVER +"/Api/Index/outside_factory_list";

    // 订单详情
    public final static String ORDER_DETAIL = SERVER + "/Api/Goods/newest_orderlist_for_onegoods";
}
