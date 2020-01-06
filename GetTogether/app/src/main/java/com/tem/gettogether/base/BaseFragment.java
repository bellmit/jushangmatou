package com.tem.gettogether.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tem.gettogether.utils.LoadingProgress;


/**
 * Created by asus on 2016/3/10.
 */
public abstract class BaseFragment extends YBMBaseFragment {

    public boolean isDestroy = false;

    private boolean isFirstEnter = true;//是否是第一次进入,默认是
    private boolean isReuseView = true ;//是否进行复用，默认复用
    private boolean isFragmentVisible;
    private View rootView;
    private LoadingProgress mLoadingProgress;
    //setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
    //如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
    //如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
    //总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
    //如果我们需要在 Fragment 可见与不可见时干点事，用这个的话就会有多余的回调了，那么就需要重新封装一个
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            //如果view还未初始化，不进行处理
            return;
        }

        if (isFirstEnter && isVisibleToUser) {
            //如果是第一次进入并且可见
            onFragmentFirstVisible();//回调当前fragment首次可见
            isFirstEnter = false;//第一次进入的标识改为false
        }
        if (isVisibleToUser) {
            //如果不是第一次进入，可见的时候
            isFragmentVisible = true;
            onFragmentVisibleChange(isFragmentVisible);//回调当前fragment可见
            return;
        }

        if (isFragmentVisible) {
            //如果当前fragment不可见且标识为true
            isFragmentVisible = false;//更改标识
            onFragmentVisibleChange(isFragmentVisible);//回调当前fragment不可见
        }
    }
    public void showProgress(boolean flag) {
        showProgress(flag, null);
    }

    public void showProgress(boolean flag, String msg) {
        if (mLoadingProgress == null) {
            mLoadingProgress = new LoadingProgress(getActivity());
        }
        if (flag) {
            mLoadingProgress.showLoadingDialog(msg);
        } else {
            mLoadingProgress.cancelLoadingDialog();
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //如果setUserVisibleHint()在rootView创建前调用时，那么
        //就等到rootView创建完后才回调onFragmentVisibleChange(true)
        //保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstEnter) {
                    onFragmentFirstVisible();
                    isFirstEnter = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     *
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {

    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    /**重置变量*/
    private void resetVariavle(){
        isFirstEnter = true;
        isReuseView = true;
        isFragmentVisible = false;
    }


    protected void gotoActivity( Class<? extends Activity> cls){
        getNotNullActivity().startActivity(new Intent(getNotNullActivity(),cls));
    }

    protected void gotoActivity(Class<? extends Activity> cls, Bundle bundle){
        Intent intent = new Intent(getNotNullActivity(),cls);
        intent.putExtras(bundle);
        getNotNullActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        isDestroy = true;
        resetVariavle();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        isDestroy = true;
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}