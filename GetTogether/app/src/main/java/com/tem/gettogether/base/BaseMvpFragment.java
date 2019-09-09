package com.tem.gettogether.base;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.base
 * @ClassName: BaseMvpFragment
 * @Author: csc
 * @CreateDate: 2019/9/6 14:16
 * @Description:
 */
public abstract class BaseMvpFragment<T extends BasePresenter>  extends BaseFragment implements BaseView{

    protected T mPresenter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}