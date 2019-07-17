package com.jsmt.developer.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 平滑的MyScrollView
 */
public class MyScrollView extends ScrollView {

    //操纵的布局
    private View innerView;
    private float y;

    private Rect normal = new Rect();
    private boolean animationFinish = true;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            innerView = getChildAt(0);
        }
        super.onFinishInflate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 加上自定义的touch处理
     *
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (animationFinish) {
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    //记录一下一段移动距离的2个Y点的值
                    float preY = y == 0 ? ev.getY() : y;
                    float nowY = ev.getY();
                    //detailY作为innerview移动的距离的参考值
                    int detailY = (int) (preY - nowY);
                    y = nowY;
                    if (isNeedMove()) {
                        //移动之前把正常位置记录一下
                        if (normal.isEmpty()) {
                            normal.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                        }
                        //移动view的位置
                        innerView.layout(innerView.getLeft(), innerView.getTop() - detailY / 2, innerView.getRight(), innerView.getBottom() - detailY / 2);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    if (isNeedAnimaton()) {
                        animation();
                    }
                    break;
            }
        }
    }

    private void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - innerView.getTop());
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerView.clearAnimation();
                innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
                animationFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        innerView.startAnimation(ta);
    }

    private boolean isNeedAnimaton() {
        if (!normal.isEmpty()) {
            return true;
        }
        return false;
    }

    private boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();

        if (scrollY == 0 || scrollY == offset) {
            return true;

        }
        return false;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (this.mScrollListener != null)
            this.mScrollListener.onScrollChanged(l, t);
    }

    private OnScrollListener mScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mScrollListener = onScrollListener;
    }

    /**
     * 滚动监听
     */
    public interface OnScrollListener {
        public void onScrollChanged(int x, int y);
    }
}
