package com.tem.gettogether.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tem.gettogether.R;


public class BaseDialog extends Dialog implements View.OnClickListener {
    protected Context mContext;// 上下文
    //private LinearLayout mLayoutRoot;// 总体根布局
    private LinearLayout mLayoutTop;// 头部根布局
    private TextView mHtvTitle;// 标题
    private View v_line;//标题下分割线
    private LinearLayout mLayoutContent;// 内容根布局
    private TextView mHtvMessage;// 内容
    private LinearLayout mLayoutBottom;// 底部根布局
    private TextView mBtnButton1;// 底部按钮1
    private View line2;
    private TextView mBtnButton2;// 底部按钮2
    private View line3;
    private TextView mBtnButton3;// 底部按钮3

    private OnClickListener mOnClickListener1;// 按钮1的单击监听事件
    private OnClickListener mOnClickListener2;// 按钮2的单击监听事件
    private OnClickListener mOnClickListener3;// 按钮3的单击监听事件
    private OnClickListener mOnClickListener;// 按钮的单击监听事件

    public BaseDialog(@NonNull Context context) {
        this(context, true);
    }

    public BaseDialog(@NonNull Context context, boolean cancel) {
        super(context, R.style.Theme_Light_FullScreenDialogAct);
        initViewAndEvent(context);
        setCancelable(cancel);
        setCanceledOnTouchOutside(cancel);
    }

    private void initViewAndEvent(Context context) {
        mContext = context;
        setContentView(R.layout.dialog_common_generic);
        mLayoutTop = (LinearLayout) findViewById(R.id.ll_dialog_top);
        mHtvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        v_line = findViewById(R.id.v_line);
        mLayoutContent = (LinearLayout) findViewById(R.id.ll_dialog_content);
        mHtvMessage = (TextView) findViewById(R.id.tv_dialog_message);
        mLayoutBottom = (LinearLayout) findViewById(R.id.ll_dialog_bottom);
        mBtnButton1 = (TextView) findViewById(R.id.tv_dialog_btn1);
        line2 = findViewById(R.id.line2_dialog);
        mBtnButton2 = (TextView) findViewById(R.id.tv_dialog_btn2);
        line3 = findViewById(R.id.line3_dialog);
        mBtnButton3 = (TextView) findViewById(R.id.tv_dialog_btn3);

        mBtnButton1.setOnClickListener(this);
        mBtnButton2.setOnClickListener(this);
        mBtnButton3.setOnClickListener(this);
    }

    /**
     * 填充新布局到内容布局
     *
     * @param resource 资源文件
     */
    public void setDialogContentView(int resource) {
        View v = LayoutInflater.from(mContext).inflate(resource, null);
        if (mLayoutContent.getChildCount() > 0) {
            mLayoutContent.removeAllViews();
        }
        mLayoutContent.addView(v);
    }

    /**
     * 填充新布局到内容布局
     *
     * @param view 自定义View
     */
    public void setDialogContentView(View view) {
        if (mLayoutContent.getChildCount() > 0) {
            mLayoutContent.removeAllViews();
        }
        mLayoutContent.addView(view);
    }

    /**
     * 填充新布局到内容布局
     */
    public void setDialogContentView(int resource, LinearLayout.LayoutParams params) {
        View v = LayoutInflater.from(mContext).inflate(resource, null);
        if (mLayoutContent.getChildCount() > 0) {
            mLayoutContent.removeAllViews();
        }
        mLayoutContent.addView(v, params);
    }

    /**
     * 给title赋值
     */
    @Override
    public void setTitle(CharSequence text) {
        if (text != null) {
            mLayoutTop.setVisibility(View.VISIBLE);
            v_line.setVisibility(View.VISIBLE);
            mHtvTitle.setText(text);
        } else {
            mLayoutTop.setVisibility(View.GONE);
            v_line.setVisibility(View.GONE);
        }
    }

    /**
     * 给content赋值
     *
     * @param text
     */
    public void setMessage(CharSequence text) {
        if (text != null) {
            mLayoutContent.setVisibility(View.VISIBLE);
            mHtvMessage.setText(text);
        } else {
            mLayoutContent.setVisibility(View.GONE);
        }
    }

    public TextView getmHtvMessage() {
        return mHtvMessage;
    }

    /**
     * 给第一个按钮赋值
     */
    public void setButton1(CharSequence text, OnClickListener listener) {
        if (text != null) {// && listener != null
            mLayoutBottom.setVisibility(View.VISIBLE);
            mBtnButton1.setVisibility(View.VISIBLE);
            mBtnButton1.setText(text);
            mOnClickListener1 = listener;
        } else {
            mBtnButton1.setVisibility(View.GONE);
        }
    }

    /**
     * 给第二个按钮赋值
     */
    public void setButton2(CharSequence text, OnClickListener listener) {
        if (text != null) {//&& listener != null
            mLayoutBottom.setVisibility(View.VISIBLE);
            mBtnButton2.setVisibility(View.VISIBLE);
            line2.setVisibility(View.VISIBLE);
            mBtnButton2.setText(text);
            mOnClickListener2 = listener;
        } else {
            mBtnButton2.setVisibility(View.GONE);
            line2.setVisibility(View.GONE);
        }
    }

    /**
     * 给第三个按钮赋值
     */
    public void setButton3(CharSequence text, OnClickListener listener) {
        //if (text != null && listener != null) {
        if (text != null) {
            mLayoutBottom.setVisibility(View.VISIBLE);
            mBtnButton3.setVisibility(View.VISIBLE);
            line3.setVisibility(View.VISIBLE);
            mBtnButton3.setText(text);
            mOnClickListener3 = listener;
        } else {
            mBtnButton3.setVisibility(View.GONE);
            line3.setVisibility(View.GONE);
        }
    }
    /* *************************************************************************************************/

    /**
     * 设置通用的点击事件
     */
    public BaseDialog setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
        return this;
    }

    /**
     * 修改按钮的颜色值
     */
    public BaseDialog setButtonTextColor(int index, int resId) {
        switch (index) {
            case 1:
                mBtnButton1.setTextColor(mContext.getResources().getColor(resId));
                break;
            case 2:
                mBtnButton2.setTextColor(mContext.getResources().getColor(resId));
                break;
            case 3:
                mBtnButton3.setTextColor(mContext.getResources().getColor(resId));
                break;
        }
        return this;
    }

    public BaseDialog setCancelFlag(boolean flag) {
        super.setCancelable(flag);
        setCanceledOnTouchOutside(flag);
        return this;
    }


    /* *************************************************************************************************/

    /**
     * 二个按钮+一个事件
     */
    public static BaseDialog getDialog(Context context, CharSequence title, CharSequence message, CharSequence button1,
                                       CharSequence button2, OnClickListener listener) {
        return getDialog(context, title, message, button1, null, button2, null, null, null).setOnClickListener(listener);
    }

    /**
     * 自定义
     *
     * @return
     */
    public static BaseDialog getDialog(Context context, CharSequence button1,
                                       OnClickListener listener1, CharSequence button2,
                                       OnClickListener listener2) {
        return getDialog(context, null, "", button1, listener1, button2, listener2, null, null);
    }

    /**
     * 三个按钮+一个事件
     */
    public static BaseDialog getDialog(Context context, CharSequence title, CharSequence message, CharSequence button1,
                                       CharSequence button2, CharSequence button3, OnClickListener listener) {
        return getDialog(context, title, message, button1, null, button2, null, button3, null).setOnClickListener(listener);
    }

    /**
     * 一个按钮的dialog
     */
    public static BaseDialog getDialog(Context context, CharSequence title, CharSequence message,
                                       CharSequence button1, OnClickListener listener1) {
        return getDialog(context, title, message, button1, listener1, null, null, null, null);
    }

    /**
     * 两个按钮的dialog
     */
    public static BaseDialog getDialog(Context context, CharSequence title,
                                       CharSequence message, CharSequence button1,
                                       OnClickListener listener1, CharSequence button2,
                                       OnClickListener listener2) {
        return getDialog(context, title, message, button1, listener1, button2, listener2, null, null);
    }

    /**
     * 三个按钮的Dialog
     */
    public static BaseDialog getDialog(Context context, CharSequence title,
                                       CharSequence message, CharSequence button1,
                                       OnClickListener listener1, CharSequence button2,
                                       OnClickListener listener2, CharSequence button3,
                                       OnClickListener listener3) {
        BaseDialog mBaseDialog = new BaseDialog(context, false);
        mBaseDialog.setTitle(title);
        mBaseDialog.setMessage(message);
        mBaseDialog.setButton1(button1, listener1);
        mBaseDialog.setButton2(button2, listener2);
        mBaseDialog.setButton3(button3, listener3);
        return mBaseDialog;
    }

    @Override
    public void onClick(View v) {
        dismiss();
        if (mOnClickListener != null) {
            if (v.getId() == R.id.tv_dialog_btn1) {
                mOnClickListener.onClick(this, 1);
            } else if (v.getId() == R.id.tv_dialog_btn2) {
                mOnClickListener.onClick(this, 2);
            } else if (v.getId() == R.id.tv_dialog_btn3) {
                mOnClickListener.onClick(this, 3);
            }
        } else {
            if (v.getId() == R.id.tv_dialog_btn1) {
                if (mOnClickListener1 != null) {
                    mOnClickListener1.onClick(this, 1);
                }
            } else if (v.getId() == R.id.tv_dialog_btn2) {
                if (mOnClickListener2 != null) {
                    mOnClickListener2.onClick(this, 2);
                }
            } else if (v.getId() == R.id.tv_dialog_btn3) {
                if (mOnClickListener3 != null) {
                    mOnClickListener3.onClick(this, 3);
                }
            }
        }
    }


    @Override
    public void show() {
        //bug_ _ android.view.WindowManager$BadTokenException: Unable to add window -- token  修复
        //来源https://www.cnblogs.com/awkflf11/p/5293267.html和http://www.jianshu.com/p/e46b843b95f4
        if (mContext instanceof Activity) {
            final Activity activity = (Activity) mContext;
            if (activity.isFinishing()) {
                return;
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
                    return;
                }
            }
        }
        super.show();
    }
}
