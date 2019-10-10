package com.tem.gettogether.activity.home;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.HomeLianMengBean;
import com.tem.gettogether.bean.LianMengDetailBean;
import com.tem.gettogether.utils.Contacts;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_lianmeng_detail)
public class LianMengDetailActivity extends BaseActivity {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;

    @ViewInject(R.id.pic_iv)
    private ImageView pic_iv;
    @ViewInject(R.id.name_tv)
    private TextView name_tv;
    @ViewInject(R.id.phone_tv)
    private TextView phone_tv;
    @ViewInject(R.id.weChat_tv)
    private TextView weChat_tv;
    @ViewInject(R.id.address_tv)
    private TextView address_tv;
    @ViewInject(R.id.whatsApp_tv)
    private TextView whatsApp_tv;
    @ViewInject(R.id.facebook_tv)
    private TextView facebook_tv;
    @ViewInject(R.id.official_website_tv)
    private TextView official_website_tv;
    @ViewInject(R.id.introduction_tv)
    private TextView introduction_tv;
    @ViewInject(R.id.banner)
    private Banner banner;
    @ViewInject(R.id.business_tv)
    private TextView business_tv;
    private String companyId;
    private List<LianMengDetailBean.ResultEntity> detailDataBean = new ArrayList<>();

    @Override
    protected void initData() {
        x.view().inject(this);
        companyId = getIntent().getStringExtra(Contacts.LINGMENG_COMPANY_ID);
        initDatas();
    }

    @Override
    protected void initView() {
    }

    @Event(value = {R.id.rl_close}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
        }
    }


    private void initDatas() {
        Map<String, Object> map = new HashMap<>();
        String yuyan = SharedPreferencesUtils.getLanguageString(this, BaseConstant.SPConstant.language, "");
        if (yuyan != null) {
            map.put("language", yuyan);
            map.put("companyId", companyId);
        }
        showDialog();
        XUtil.Post(URLConstant.HONEALLIANCEDETAILDATA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                closeDialog();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        detailDataBean = gson.fromJson(result, LianMengDetailBean.class).getResult();
                        setData();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private void setData() {
        tv_title.setText(detailDataBean.get(0).getCompany_name());
        name_tv.setText(getText(R.string.principal) + detailDataBean.get(0).getContact_person());
        phone_tv.setText(getText(R.string.contact_information) + detailDataBean.get(0).getCellphone());
        address_tv.setText(getText(R.string.address_tv) + detailDataBean.get(0).getAddress());
        whatsApp_tv.setText(getText(R.string.whatsApp) + detailDataBean.get(0).getWhatsapp());
        facebook_tv.setText(getText(R.string.facebook) + detailDataBean.get(0).getFacebook());
        weChat_tv.setText(getText(R.string.weChat_ID) + detailDataBean.get(0).getWechat());
        official_website_tv.setText(getText(R.string.official_website) + detailDataBean.get(0).getWebsite());
        business_tv.setText(detailDataBean.get(0).getMaindeal());
        introduction_tv.setText(detailDataBean.get(0).getDescription());

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(detailDataBean.get(0).getCompany_images2());
        //banner设置方法全部调用完毕时最后调用
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent2 = new Intent(LianMengDetailActivity.this, ShowImageDetail.class);
                intent2.putStringArrayListExtra("paths",detailDataBean.get(0).getCompany_images2());
                intent2.putExtra("index", position);
                startActivity(intent2);
            }
        });
        banner.start();
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}
