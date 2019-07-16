package com.tem.gettogether.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 列表通用头部
 */

public abstract class BaseHeaderView extends LinearLayout {

    public BaseHeaderView(Context context) {
        this(context, null);
    }

    public BaseHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(getLayoutId(), this);
        init();
    }

    protected abstract void init() ;

    protected  abstract int getLayoutId();

    public <T extends View> T getView(int viewId) {
        if (this == null) {
            return null;
        }
        try {
            return (T) this.findViewById(viewId);
        } catch (Throwable e) {
            return null;
        }
    }

    public void onDestroy(){

    }
}
