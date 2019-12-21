package com.tem.gettogether.activity.my.member;

import android.app.Activity;
import android.content.Context;

import com.tem.gettogether.activity.my.publishgoods.PublishGoodsModel;
import com.tem.gettogether.base.BasePresenter;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/10/18 14:35 
 */
public class MemberCentrePresenter extends BasePresenter<MemberCentreContract.MemberCentreView> implements MemberCentreContract.Presenter {
    private Context mContext;
    private Activity mActivity;

    public MemberCentrePresenter(Context context, Activity mActivity) {
        this.mContext = context;
        this.mActivity = mActivity;
    }
}
