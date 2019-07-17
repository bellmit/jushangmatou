package com.jsmt.developer.dialog;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jsmt.developer.R;


/**
 *
 */

public class CommonDialogLayout extends FrameLayout {

    public enum CommonTip {
        success, failed, noNet
    }

    private ImageView ivTip;
    private TextView tvMsg;

    public CommonDialogLayout(@NonNull Context context) {
        this(context, null);
    }

    public CommonDialogLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonDialogLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.dialog_common_tip, null);
        ivTip = (ImageView) view.findViewById(R.id.iv_common_tip);
        tvMsg = (TextView) view.findViewById(R.id.tv_common_msg);
        addView(view);
    }

    public void setDialogAttr(int imgRes, String msg) {
        if (imgRes > 0) {
            ivTip.setImageResource(imgRes);
        }

        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
    }

    public ImageView getImageView() {
        return ivTip;
    }

    public TextView getTextView() {
        return tvMsg;
    }

    public void setImg(int imgRes) {
        if (imgRes > 0) {
            ivTip.setImageResource(imgRes);
        }
    }

    public void setMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
    }
}
