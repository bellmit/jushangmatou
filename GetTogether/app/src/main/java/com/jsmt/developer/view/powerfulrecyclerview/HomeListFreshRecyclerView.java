package com.jsmt.developer.view.powerfulrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.jsmt.developer.R;
import com.jsmt.developer.utils.UiUtils;


/**
 * Created by LITAO on 2019/1/16.
 */

public class HomeListFreshRecyclerView extends RecyclerView {
    private Context mContext;

    //分割线的颜色
    private int mDividerColor;
    //分割线的大小
    private int mDividerSize = 1;
    //分割线的drawable
    private Drawable mDividerDrawable;
    //是否使用瀑布流布局,默认不是
    private boolean mUseStaggerLayout;
    //列数，默认为1
    private int mNumColumns = 1;
    //RecyclerView的方向，默认为垂直方向
    private int mOrientation = LinearLayoutManager.VERTICAL;

    private int mmarginStart;
    private int mmarginEnd;

    private LayoutManager mLayoutManager;
    private DividerDecoration mDividerDecoration;
    private Drawable mItemDrawable;


    public HomeListFreshRecyclerView(Context context) {
        this(context,null);
    }

    public HomeListFreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HomeListFreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PowerfulRecyclerView);

        mDividerColor = ta.getColor(R.styleable.PowerfulRecyclerView_dividerColor, Color.parseColor("#F2F3FC"));
        mDividerSize = ta.getDimensionPixelSize(R.styleable.PowerfulRecyclerView_dividerSize, UiUtils.dip2Px(context,1));

        mDividerDrawable = ta.getDrawable(R.styleable.PowerfulRecyclerView_dividerDrawable);

        mUseStaggerLayout = ta.getBoolean(R.styleable.PowerfulRecyclerView_useStaggerLayout, mUseStaggerLayout);
        mNumColumns = ta.getInt(R.styleable.PowerfulRecyclerView_numColumns,mNumColumns);

        mOrientation = ta.getInt(R.styleable.PowerfulRecyclerView_rvOrientation, mOrientation);

        mmarginStart = ta.getDimensionPixelSize(R.styleable.PowerfulRecyclerView_dividermarginStart, 0);
        mmarginEnd = ta.getDimensionPixelSize(R.styleable.PowerfulRecyclerView_dividermarginEnd, 0);

        ta.recycle();

        setLayoutManager();
        setDivider();
    }

    /**
     * 设置layoutManager
     */
    private void setLayoutManager() {
        if (!mUseStaggerLayout){
            //不是瀑布流布局，即是列表或网格布局
            if (mOrientation == LinearLayoutManager.VERTICAL){
                mLayoutManager =  new GridLayoutManager(mContext, mNumColumns);
            }else{
                mLayoutManager = new GridLayoutManager(mContext, mNumColumns,mOrientation,false);
            }
        }else{
            //瀑布流布局
            mLayoutManager = new StaggeredGridLayoutManager(mNumColumns,mOrientation);
        }

        setLayoutManager(mLayoutManager);
    }

    /**
     * 设置分割线
     */
    private void setDivider() {
        if (mDividerDrawable == null){
            //绘制颜色分割线
            mDividerDecoration = new DividerDecoration(mContext, mOrientation,mDividerColor, mDividerSize,mmarginStart,mmarginEnd);
        }else{
            //绘制图片分割线
            mDividerDecoration = new DividerDecoration(mContext,mOrientation,mDividerDrawable,mDividerSize,mmarginStart,mmarginEnd);
        }
        this.addItemDecoration(mDividerDecoration);
    }

    /**
     * 设置不滚动
     */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }
}
