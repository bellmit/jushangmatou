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
 * date : 2019/11/9 16:56 
 */
public class IdentityDialog extends Dialog {

    private IdentityDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {

        private View mLayout;

        private ImageView close_iv;
        private TextView buyers_tv;
        private TextView supplier_tv;

        private View.OnClickListener mBuyersClickListener;
        private View.OnClickListener mSupplierClickListener;
        private View.OnClickListener mCloseClickListener;
        private IdentityDialog mDialog;

        public Builder(Context context) {
            mDialog = new IdentityDialog(context, R.style.Theme_AppCompat_Dialog);
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //加载布局文件
            mLayout = inflater.inflate(R.layout.dialog_identity_choose, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            close_iv = mLayout.findViewById(R.id.close_iv);
            buyers_tv = mLayout.findViewById(R.id.buyers_tv);
            supplier_tv = mLayout.findViewById(R.id.supplier_tv);
        }

        public Builder setBuyersClick(View.OnClickListener listener) {
            mBuyersClickListener = listener;
            return this;
        }

        public Builder setSupplierClick(View.OnClickListener listener) {
            mSupplierClickListener = listener;
            return this;
        }
        public Builder setCloseClick(View.OnClickListener listener) {
            mCloseClickListener = listener;
            return this;
        }
        public IdentityDialog create() {
            buyers_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mBuyersClickListener.onClick(v);
                }
            });

            supplier_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mSupplierClickListener.onClick(v);
                }
            });
            close_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mCloseClickListener.onClick(v);
                }
            });
            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
            mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
            return mDialog;
        }
    }
}