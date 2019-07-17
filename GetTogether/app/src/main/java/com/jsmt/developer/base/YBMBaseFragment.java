package com.jsmt.developer.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by asus on 2016/3/10.
 */
public abstract class YBMBaseFragment extends Fragment {

    private BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (BaseActivity) context;
        }
    }

    public Activity getNotNullActivity() {
        return mActivity;
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    public void showProgress() {
        mActivity.showProgress();
    }

    public void showProgress(String str) {
        mActivity.showProgress(str);
    }

    public void dismissProgress() {
        mActivity.dismissProgress();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
