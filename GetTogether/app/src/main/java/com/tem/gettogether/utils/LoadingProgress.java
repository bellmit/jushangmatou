package com.tem.gettogether.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tem.gettogether.R;


public class LoadingProgress {
    private static final String TAG = "LoadingDialog";
    /**
     * 加载进度
     */
    private AlertDialog progressDialog;
    private boolean isInitContentView = false;
    private TextView tv_msg;

    private Context mContext;

    public LoadingProgress(Context context) {
        mContext = context;
    }

    /**
     * 取消loading
     */
    public void cancelLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                Log.e(TAG, "progressDialog销毁失败");
            }
        }
        //tv_msg = null;
        //progressDialog = null;
        isInitContentView = false;
    }

    /**
     * 默认载入loading
     */
    public void showLoadingDialog() {
        showLoadingDialog(null);
    }

    public void showLoadingDialog(String message) {
        showLoadingDialog(message, false);
    }

    private void showLoadingDialog(String message, boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new AlertDialog(mContext, R.style.ProgressHUD) {
                @Override
                public void onWindowFocusChanged(boolean hasFocus) {
                    super.onWindowFocusChanged(hasFocus);
                    ImageView imageView = (ImageView) findViewById(R.id.spinnerImageView);
                    AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
                    spinner.start();
                }
            };
        }
        progressDialog.setCancelable(cancelable);
        progressDialog.setCanceledOnTouchOutside(cancelable);
        if (cancelable) {
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).onBackPressed();
                    }
                }
            });
        } else {
            progressDialog.setOnCancelListener(null);
        }
        try {
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "progressDialog启动失败");
        }
        if (!isInitContentView) {
            progressDialog.setContentView(R.layout.progress_hud);
            tv_msg = (TextView) progressDialog.findViewById(R.id.message);
        }
        isInitContentView = true;
        if (TextUtils.isEmpty(message)) {
            tv_msg.setVisibility(View.GONE);
        } else {
            tv_msg.setVisibility(View.VISIBLE);
            tv_msg.setText(message);
            tv_msg.invalidate();
        }
    }
}
