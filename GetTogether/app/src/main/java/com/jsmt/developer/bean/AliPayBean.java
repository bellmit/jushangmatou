package com.jsmt.developer.bean;

/**
 * Created by 18339 on 2017/12/14.
 */

public class AliPayBean {


    /**
     * msg : 获取成功
     * status : 1
     * result : app_id=2018072560761382&biz_content=%7B%22body%22%3A%22%5Cu805a%5Cu5546%5Cu7801%5Cu5934APP%5Cu5145%5Cu503c%5Cu652f%5Cu4ed8%22%2C%22subject%22%3A%22%5Cu805a%5Cu5546%5Cu7801%5Cu5934APP%5Cu5145%5Cu503c%5Cu652f%5Cu4ed8%22%2C%22out_trade_no%22%3A%22recharge6C5HEeaCiG%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A1%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay¬ify_url=http%3A%2F%2Ftest.uonep.com%2Fjushangmatou%2Findex.php%3Fm%3DApi%26c%3DPayment%26a%3DalipayNotify&sign_type=RSA2×tamp=2019-03-06+09%3A43%3A42&version=1.0&sign=K5pHW%2BwTVWuoxHPbE5sIWqCPniFtvRl%2BdWVrb1hIABvG5DvJ3x998IH%2FmBTIuZraIlq4FAMe3AnUz84O6lU9T1P9JfYMO%2FLHmsdus4es%2BnL3cA%2FQfxtfOtApzXA25b8UxPWHy72NfWpoAWjNo5V%2FBxVyRmVyzj9FiSs9yRsW4Z8GndvRU1MVJ%2FUUbTknqIM%2BVIOqoayIEBWLpofpVrXtgFOQHbeCY2yjtPDkJhr3WT0ElysqJen6y6mKPzA7lrXZvjgithv1hnm8w5jPOdKCVyqv0afEtKfweZIGVKulh30yTw%2BI3U5VARWrAV%2Bu%2FlL2OwFUMUcMUVd%2BZexX%2Fq0t%2Fw%3D%3D
     */

    private String msg;
    private int status;
    private String result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
