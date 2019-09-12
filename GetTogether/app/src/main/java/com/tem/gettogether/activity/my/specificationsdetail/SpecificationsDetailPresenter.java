package com.tem.gettogether.activity.my.specificationsdetail;

import android.app.Activity;
import android.content.Context;

import com.tem.gettogether.base.BasePresenter;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.specificationsdetail
 * @ClassName: SpecificationsDetailPresenter
 * @Author: csc
 * @CreateDate: 2019/9/9 15:37
 * @Description:
 */
public class SpecificationsDetailPresenter extends BasePresenter<SpecificationsDetailContract.SpecificationsDetailView> implements SpecificationsDetailContract.Presenter {
    private SpecificationsDetailModel model;
    private Context mContext;
    private Activity mActivity;

    public SpecificationsDetailPresenter(Context context, Activity mActivity) {
        model = new SpecificationsDetailModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }
}