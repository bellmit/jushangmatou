package com.tem.gettogether.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tem.gettogether.R;

/**
 *  
 * description ： TODO:类的作用
 * author : chenshichun
 * email : chenshichuen123@qq.com
 * date : 2019/12/2 14:17 
 */
public class DecorationNoticeDialog extends Dialog {

    private DecorationNoticeDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private View mLayout;


        private View.OnClickListener mBuyersClickListener;
        private View.OnClickListener mSupplierClickListener;
        private View.OnClickListener mCloseClickListener;
        private DecorationNoticeDialog mDialog;

        public Builder(Context context) {
            mDialog = new DecorationNoticeDialog(context, R.style.Theme_AppCompat_Dialog);
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.dialog_decoration_notice, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

        }

        public DecorationNoticeDialog.Builder setBuyersClick(View.OnClickListener listener) {
            mBuyersClickListener = listener;
            return this;
        }

        public DecorationNoticeDialog.Builder setSupplierClick(View.OnClickListener listener) {
            mSupplierClickListener = listener;
            return this;
        }
        public DecorationNoticeDialog.Builder setCloseClick(View.OnClickListener listener) {
            mCloseClickListener = listener;
            return this;
        }
        public DecorationNoticeDialog create() {
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
            mDialog.setCanceledOnTouchOutside(true);   //用户不可以点击外部来关闭 Dialog
            return mDialog;
        }
    }
}