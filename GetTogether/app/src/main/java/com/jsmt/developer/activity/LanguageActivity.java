package com.jsmt.developer.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.utils.language.LanguageAdapter;
import com.jsmt.developer.utils.language.LanguageBean;
import com.jsmt.developer.utils.language.LanguageUtil;
import com.jsmt.developer.utils.language.UtilsLanguage;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ContentView(R.layout.activity_language)

public class LanguageActivity extends BaseActivity {
    private LanguageAdapter adapter;
    private List<LanguageBean> mDatas;
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        initData();
        initView();
    }

    @Override
    protected void initData() {
//        rl_close=findViewById(R.id.rl_close);
        rl_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        tv_title=findViewById(R.id.tv_title);
        tv_title.setText("语言切换");

    }

    @Override
    protected void initView() {
        RecyclerView rlv_language = (RecyclerView) findViewById(R.id.rlv_language);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlv_language.setLayoutManager(layoutManager);
        mDatas = new ArrayList<>();
        mDatas.add(new LanguageBean(Locale.CHINESE, UtilsLanguage.getString(R.string.chinese)));
        mDatas.add(new LanguageBean(Locale.ENGLISH, UtilsLanguage.getString(R.string.english)));

        adapter = new LanguageAdapter(LanguageActivity.this, mDatas);
        rlv_language.setAdapter(adapter);
        Locale mLocale = LanguageUtil.getUserLocale();
        adapter.setSelect(mLocale);
        setListener();
    }

    private void setListener() {
        adapter.setOnItemClick(new LanguageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                adapter.setSelect(mDatas.get(postion).getLanguage());
                LanguageUtil.updateLocale(mDatas.get(postion).getLanguage());
                recreate();

            }
        });
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("++++++++++onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("++++++++++onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("++++++++++onDestroy");
    }

}
