package com.tem.gettogether.activity.my.detailpicture;

import android.widget.TextView;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseMvpActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.my.detailpicture
 * @ClassName: DetailPictureActivity
 * @Author: csc
 * @CreateDate: 2019/9/6 16:26
 * @Description:
 */

@ContentView(R.layout.activity_tu_wen_xq)
public class DetailPictureActivity extends BaseMvpActivity<DetailPicturePresenter> implements DetailPictureContract.DetailPictureView {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;

    @Override
    protected void initData() {
        x.view().inject(this);
        tv_title.setText(getText(R.string.product_graphic_details));
        tv_title_right.setText(getText(R.string.save));
    }

    @Override
    protected void initView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
