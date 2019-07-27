package com.tem.gettogether.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tem.gettogether.R;


/**
 */
public class ShowAlertDialog {
    private static Dialog alertDialog;

    public static void closeImg() {
        if (anim != null) {
            anim.stop();
            anim = null;
        }
    }

    private static AnimationDrawable anim;

    public static Dialog loadingDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
//        ImageView iv_load = (ImageView) v.findViewById(R.id.loadimg);
//        try {
//            anim = (AnimationDrawable) iv_load.getBackground();
//            anim.start();
//        } catch (Exception w) {
//
//        }
        Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

        loadingDialog.setCancelable(true);// 不可以用“返回键”取�?
        loadingDialog.setContentView(v, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

        loadingDialog.setCanceledOnTouchOutside(false);
        return loadingDialog;
    }
//        return loadingDialog;
//    }

}
