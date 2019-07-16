package com.tem.gettogether.activity.my;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tem.gettogether.R;
import com.tem.gettogether.ShowImageDetail;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.base.BaseApplication;
import com.tem.gettogether.base.BaseRVAdapter;
import com.tem.gettogether.base.BaseViewHolder;
import com.tem.gettogether.base.URLConstant;
import com.tem.gettogether.bean.ImageDataBean;
import com.tem.gettogether.bean.OrderDataBean;
import com.tem.gettogether.bean.PingjiaBean;
import com.tem.gettogether.utils.Base64BitmapUtil;
import com.tem.gettogether.utils.BitnapUtils;
import com.tem.gettogether.utils.Confirg;
import com.tem.gettogether.utils.permissions.PermissionsActivity;
import com.tem.gettogether.utils.permissions.PermissionsChecker;
import com.tem.gettogether.utils.xutils3.MyCallBack;
import com.tem.gettogether.utils.xutils3.XUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.duduhuo.custoast.CusToast;
import io.rong.imageloader.core.DisplayImageOptions;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imageloader.core.download.ImageDownloader;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import me.nereo.multi_image_selector.bean.Image;

@ContentView(R.layout.activity_post_evaluation)
public class PostEvaluationActivity extends BaseActivity   {
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_item_xx1)
    private ImageView iv_item_xx1;
    @ViewInject(R.id.iv_item_xx2)
    private ImageView iv_item_xx2;
    @ViewInject(R.id.iv_item_xx3)
    private ImageView iv_item_xx3;
    @ViewInject(R.id.iv_item_xx4)
    private ImageView iv_item_xx4;
    @ViewInject(R.id.iv_item_xx5)
    private ImageView iv_item_xx5;
    @ViewInject(R.id.iv_MJitem_xx1)
    private ImageView iv_MJitem_xx1;
    @ViewInject(R.id.iv_MJitem_xx2)
    private ImageView iv_MJitem_xx2;
    @ViewInject(R.id.iv_MJitem_xx3)
    private ImageView iv_MJitem_xx3;
    @ViewInject(R.id.iv_MJitem_xx4)
    private ImageView iv_MJitem_xx4;
    @ViewInject(R.id.iv_MJitem_xx5)
    private ImageView iv_MJitem_xx5;
    @ViewInject(R.id.iv_WLitem_xx1)
    private ImageView iv_WLitem_xx1;
    @ViewInject(R.id.iv_WLitem_xx2)
    private ImageView iv_WLitem_xx2;
    @ViewInject(R.id.iv_WLitem_xx3)
    private ImageView iv_WLitem_xx3;
    @ViewInject(R.id.iv_WLitem_xx4)
    private ImageView iv_WLitem_xx4;
    @ViewInject(R.id.iv_WLitem_xx5)
    private ImageView iv_WLitem_xx5;
    @ViewInject(R.id.recycler_shop)
    private RecyclerView recycler_shop;
    @ViewInject(R.id.tv_title_right)
    private TextView tv_title_right;
    @ViewInject(R.id.iv_niming)
    private ImageView iv_niming;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> imagePaths1 = new ArrayList<>();
    private ArrayList<String> imagePaths2 = new ArrayList<>();
    private ArrayList<String> imagePaths3 = new ArrayList<>();
    private ArrayList<String> imagePaths4= new ArrayList<>();
    private ArrayList<String> imagePaths5 = new ArrayList<>();
    private ArrayList<String> imagePaths6 = new ArrayList<>();
    private ArrayList<String> imagePaths7 = new ArrayList<>();
    private ArrayList<String> imagePaths8 = new ArrayList<>();
    private ArrayList<String> imagePaths9 = new ArrayList<>();
    private ArrayList<String> imagePaths10 = new ArrayList<>();
    private ArrayList<String> imagePaths11= new ArrayList<>();
    private ArrayList<String> imagePaths12= new ArrayList<>();
    private ArrayList<String> imagePaths13 = new ArrayList<>();

    private ArrayList<String> compressPaths = new ArrayList<>();
    private ArrayList<String> compressPaths1 = new ArrayList<>();
    private ArrayList<String> compressPaths2 = new ArrayList<>();
    private ArrayList<String> compressPaths3 = new ArrayList<>();
    private ArrayList<String> compressPaths4 = new ArrayList<>();
    private ArrayList<String> compressPaths5= new ArrayList<>();
    private ArrayList<String> compressPaths6= new ArrayList<>();
    private ArrayList<String> compressPaths7= new ArrayList<>();
    private ArrayList<String> compressPaths8= new ArrayList<>();
    private ArrayList<String> compressPaths9= new ArrayList<>();
    private ArrayList<String> compressPaths10= new ArrayList<>();
    private ArrayList<String> compressPaths11= new ArrayList<>();
    private ArrayList<String> compressPaths12= new ArrayList<>();
    private ArrayList<String> compressPaths13= new ArrayList<>();
    private  final List<String> cartImage = new ArrayList<>();
    private  final List<String> cartImage1 = new ArrayList<>();
    private  final List<String> cartImage2 = new ArrayList<>();
    private  final List<String> cartImage3 = new ArrayList<>();
    private  final List<String> cartImage4 = new ArrayList<>();
    private  final List<String> cartImage5 = new ArrayList<>();
    private  final List<String> cartImage6 = new ArrayList<>();
    private  final List<String> cartImage7 = new ArrayList<>();
    private  final List<String> cartImage8 = new ArrayList<>();
    private  final List<String> cartImage9 = new ArrayList<>();
    private  final List<String> cartImage10 = new ArrayList<>();
    private  final List<String> cartImage11 = new ArrayList<>();
    private  final List<String> cartImage12 = new ArrayList<>();
    private  final List<String> cartImage13 = new ArrayList<>();
    private String starLevel="0";
    private String starLevel1="0";
    private String starLevel2="0";
    private String starLevel3="0";
    private String starLevel4="0";
    private String starLevel5="0";
    private String starLevel6="0";
    private String starLevel7="0";
    private String starLevel8="0";
    private String starLevel9="0";
    private String starLevel10="0";
    private String starLevel11="0";
    private String starLevel12="0";
    private String starLevel13="0";
    private String Content="";
    private String Content1="";
    private String Content2="";
    private String Content3="";
    private String Content4="";
    private String Content5="";
    private String Content6="";
    private String Content7="";
    private String Content8="";
    private String Content9="";
    private String Content10="";
    private String Content11="";
    private String Content12="";
    private String Content13="";

    private String compressImageFilePath;
    private PermissionsChecker checker;
    private String[] strPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_CODEBBB = 100;
    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code
    private int ShoppingType=0;
    private String order_id;
    private List<OrderDataBean.ResultBean.GoodsListBean> goodsListBeans=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        checker = new PermissionsChecker(this);

        initData();
        initView();
    }

    @Override
    protected void initData() {
        tv_title.setText("发布评价");
        order_id=getIntent().getStringExtra("order_id");
        tv_title_right.setVisibility(View.VISIBLE);
        tv_title_right.setText("发布");
        tv_title_right.setTextColor(getResources().getColor(R.color.my_yellow));
        imagePaths.clear();
        imagePaths1.clear();
        imagePaths2.clear();
        imagePaths3.clear();
        imagePaths4.clear();
        imagePaths5.clear();
        imagePaths6.clear();
        imagePaths7.clear();
        imagePaths8.clear();
        imagePaths9.clear();
        imagePaths10.clear();
        imagePaths11.clear();
        imagePaths12.clear();
        imagePaths13.clear();

        imagePaths.add(R.drawable.addtupian + "");
        imagePaths1.add(R.drawable.addtupian + "");
        imagePaths2.add(R.drawable.addtupian + "");
        imagePaths3.add(R.drawable.addtupian + "");
        imagePaths4.add(R.drawable.addtupian + "");
        imagePaths5.add(R.drawable.addtupian + "");
        imagePaths6.add(R.drawable.addtupian + "");
        imagePaths7.add(R.drawable.addtupian + "");
        imagePaths8.add(R.drawable.addtupian + "");
        imagePaths9.add(R.drawable.addtupian + "");
        imagePaths10.add(R.drawable.addtupian + "");
        imagePaths11.add(R.drawable.addtupian + "");
        imagePaths12.add(R.drawable.addtupian + "");
        imagePaths13.add(R.drawable.addtupian + "");

        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress2/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        recycler_shop.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        upOrderXQData();
    }
    private    BaseRVAdapter photoadapter;
    private BaseRVAdapter  adapter;

    @Override
    protected void initView() {
         List<String > list23=new ArrayList<>();

        list23.add("");
        list23.add("");
        list23.add("");

        adapter=new BaseRVAdapter(this,goodsListBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.shop_post_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                RecyclerView recycler_image=holder.getView(R.id.recycler_image);
                final ImageView iv_item_xx1=holder.getView(R.id.iv_item_xx1);
                final ImageView iv_item_xx2=holder.getView(R.id.iv_item_xx2);
                final ImageView iv_item_xx3=holder.getView(R.id.iv_item_xx3);
                final ImageView iv_item_xx4=holder.getView(R.id.iv_item_xx4);
                final ImageView iv_item_xx5=holder.getView(R.id.iv_item_xx5);
                final TextView tv_xingji=holder.getView(R.id.tv_xingji);
                final EditText   et_pjContent=holder.getView(R.id.et_pjContent);
                et_pjContent.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        switch (position){
                            case 0:
                                Content=et_pjContent.getText().toString();
                                initImage(position,imagePaths,compressPaths,cartImage);
                                break;
                            case 1:
                                Content1=et_pjContent.getText().toString();
                                initImage(position,imagePaths1,compressPaths1,cartImage1);
                                break;
                            case 2:
                                Content2=et_pjContent.getText().toString();
                                initImage(position,imagePaths2,compressPaths2,cartImage2);
                                break;
                            case 3:
                                Content3=et_pjContent.getText().toString();
                                initImage(position,imagePaths3,compressPaths3,cartImage3);
                                break;
                            case 4:
                                Content4=et_pjContent.getText().toString();
                                initImage(position,imagePaths4,compressPaths4,cartImage4);
                                break;
                            case 5:
                                Content5=et_pjContent.getText().toString();
                                initImage(position,imagePaths5,compressPaths5,cartImage5);
                                break;
                            case 6:
                                Content6=et_pjContent.getText().toString();
                                initImage(position,imagePaths6,compressPaths6,cartImage6);
                                break;
                            case 7:
                                Content7=et_pjContent.getText().toString();
                                initImage(position,imagePaths7,compressPaths7,cartImage7);
                                break;
                            case 8:
                                Content8=et_pjContent.getText().toString();
                                initImage(position,imagePaths8,compressPaths8,cartImage8);
                                break;
                            case 9:
                                Content9=et_pjContent.getText().toString();
                                initImage(position,imagePaths9,compressPaths9,cartImage9);
                                break;
                            case 10:
                                Content10=et_pjContent.getText().toString();
                                initImage(position,imagePaths10,compressPaths10,cartImage10);
                                break;
                            case 11:
                                Content11=et_pjContent.getText().toString();
                                initImage(position,imagePaths11,compressPaths11,cartImage11);
                                break;
                            case 12:
                                Content12=et_pjContent.getText().toString();
                                initImage(position,imagePaths12,compressPaths12,cartImage12);
                                break;
                            case 13:
                                Content13=et_pjContent.getText().toString();
                                initImage(position,imagePaths13,compressPaths13,cartImage13);
                                break;

                        }

                    }
                });
                iv_item_xx1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx2.setImageResource(R.drawable.pingj_xx);
                        iv_item_xx3.setImageResource(R.drawable.pingj_xx);
                        iv_item_xx4.setImageResource(R.drawable.pingj_xx);
                        iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                        switch (position){
                            case 0:
                                starLevel="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 1:
                                starLevel1="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 2:
                                starLevel2="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 3:
                                starLevel3="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 4:
                                starLevel4="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 5:
                                starLevel5="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 6:
                                starLevel6="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 7:
                                starLevel7="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 8:
                                starLevel8="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 9:
                                starLevel9="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 10:
                                starLevel10="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 11:
                                starLevel11="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 12:
                                starLevel12="1";
                                tv_xingji.setText("非常差");
                                break;
                            case 13:
                                starLevel13="1";
                                tv_xingji.setText("非常差");
                                break;
                        }
                    }
                });
                iv_item_xx2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx3.setImageResource(R.drawable.pingj_xx);
                        iv_item_xx4.setImageResource(R.drawable.pingj_xx);
                        iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                        switch (position){
                            case 0:
                                starLevel="2";
                                tv_xingji.setText("差");
                                break;
                            case 1:
                                starLevel1="2";
                                tv_xingji.setText("差");
                                break;
                            case 2:
                                starLevel2="2";
                                tv_xingji.setText("差");
                                break;
                            case 3:
                                starLevel3="2";
                                tv_xingji.setText("差");
                                break;
                            case 4:
                                starLevel4="2";
                                tv_xingji.setText("差");
                                break;
                            case 5:
                                starLevel5="2";
                                tv_xingji.setText("差");
                                break;
                            case 6:
                                starLevel6="2";
                                tv_xingji.setText("差");
                                break;
                            case 7:
                                starLevel7="2";
                                tv_xingji.setText("差");
                                break;
                            case 8:
                                starLevel8="2";
                                tv_xingji.setText("差");
                                break;
                            case 9:
                                starLevel9="2";
                                tv_xingji.setText("差");
                                break;
                            case 10:
                                starLevel10="2";
                                tv_xingji.setText("差");
                                break;
                            case 11:
                                starLevel11="2";
                                tv_xingji.setText("差");
                                break;
                            case 12:
                                starLevel12="2";
                                tv_xingji.setText("差");
                                break;
                            case 13:
                                starLevel13="2";
                                tv_xingji.setText("差");
                                break;
                        }
                    }
                });
                iv_item_xx3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx3.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx4.setImageResource(R.drawable.pingj_xx);
                        iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                        switch (position){
                            case 0:
                                starLevel="3";
                                tv_xingji.setText("一般");
                                break;
                            case 1:
                                starLevel1="3";
                                tv_xingji.setText("一般");
                                break;
                            case 2:
                                starLevel2="3";
                                tv_xingji.setText("一般");
                                break;
                            case 3:
                                starLevel3="3";
                                tv_xingji.setText("一般");
                                break;
                            case 4:
                                starLevel4="3";
                                tv_xingji.setText("一般");
                                break;
                            case 5:
                                starLevel5="3";
                                tv_xingji.setText("一般");

                                break;
                            case 6:
                                starLevel6="3";
                                tv_xingji.setText("一般");
                                break;
                            case 7:
                                starLevel7="3";
                                tv_xingji.setText("一般");
                                break;
                            case 8:
                                starLevel8="3";
                                tv_xingji.setText("一般");
                                break;
                            case 9:
                                starLevel9="3";
                                tv_xingji.setText("一般");
                                break;
                            case 10:
                                starLevel10="3";
                                tv_xingji.setText("一般");
                                break;
                            case 11:
                                starLevel11="3";
                                tv_xingji.setText("一般");
                                break;
                            case 12:
                                starLevel12="3";
                                tv_xingji.setText("一般");
                                break;
                            case 13:
                                starLevel13="3";
                                tv_xingji.setText("一般");
                                break;
                        }
                    }
                });
                iv_item_xx4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx3.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx4.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                        switch (position){
                            case 0:
                                starLevel="4";
                                tv_xingji.setText("好");
                                break;
                            case 1:
                                starLevel1="4";
                                tv_xingji.setText("好");
                                break;
                            case 2:
                                starLevel2="4";
                                tv_xingji.setText("好");
                                break;
                            case 3:
                                starLevel3="4";
                                tv_xingji.setText("好");
                                break;
                            case 4:
                                starLevel4="4";
                                tv_xingji.setText("好");
                                break;
                            case 5:
                                starLevel5="4";
                                tv_xingji.setText("好");
                                break;
                            case 6:
                                starLevel6="4";
                                tv_xingji.setText("好");
                                break;
                            case 7:
                                starLevel7="4";
                                tv_xingji.setText("好");
                                break;
                            case 8:
                                starLevel8="4";
                                tv_xingji.setText("好");
                                break;
                            case 9:
                                starLevel9="4";
                                tv_xingji.setText("好");
                                break;
                            case 10:
                                starLevel10="4";
                                tv_xingji.setText("好");
                                break;
                            case 11:
                                starLevel11="4";
                                tv_xingji.setText("好");
                                break;
                            case 12:
                                starLevel12="4";
                                tv_xingji.setText("好");
                                break;
                            case 13:
                                starLevel13="4";
                                tv_xingji.setText("好");
                                break;
                        }
                    }
                });
                iv_item_xx5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx3.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx4.setImageResource(R.drawable.pingj_xx_xz);
                        iv_item_xx5.setImageResource(R.drawable.pingj_xx_xz);
                        switch (position){
                            case 0:
                                starLevel="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 1:
                                starLevel1="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 2:
                                starLevel2="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 3:
                                starLevel3="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 4:
                                starLevel4="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 5:
                                starLevel5="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 6:
                                starLevel6="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 7:
                                starLevel7="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 8:
                                starLevel8="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 9:
                                starLevel9="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 10:
                                starLevel10="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 11:
                                starLevel11="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 12:
                                starLevel12="5";
                                tv_xingji.setText("非常好");
                                break;
                            case 13:
                                starLevel13="5";
                                tv_xingji.setText("非常好");
                                break;
                        }
                    }
                });

                recycler_image.setLayoutManager(new GridLayoutManager(PostEvaluationActivity.this, 3, LinearLayoutManager.VERTICAL, false));
                switch (position){
                    case 0:
                        initImage(position,imagePaths,compressPaths,cartImage);
                        break;
                    case 1:
                        initImage(position,imagePaths1,compressPaths1,cartImage1);
                        break;
                    case 2:
                        initImage(position,imagePaths2,compressPaths2,cartImage2);
                        break;
                    case 3:
                        initImage(position,imagePaths3,compressPaths3,cartImage3);
                        break;
                    case 4:
                        initImage(position,imagePaths4,compressPaths4,cartImage4);
                        break;
                    case 5:
                        initImage(position,imagePaths5,compressPaths5,cartImage5);
                        break;
                    case 6:
                        initImage(position,imagePaths6,compressPaths6,cartImage6);
                        break;
                    case 7:
                        initImage(position,imagePaths7,compressPaths7,cartImage7);
                        break;
                    case 8:
                        initImage(position,imagePaths8,compressPaths8,cartImage8);
                        break;
                    case 9:
                        initImage(position,imagePaths9,compressPaths9,cartImage9);
                        break;
                    case 10:
                        Content10=et_pjContent.getText().toString();
                        initImage(position,imagePaths10,compressPaths10,cartImage10);
                        break;
                    case 11:
                        initImage(position,imagePaths11,compressPaths11,cartImage11);
                        break;
                    case 12:
                        initImage(position,imagePaths12,compressPaths12,cartImage12);
                        break;
                    case 13:
                        initImage(position,imagePaths13,compressPaths13,cartImage13);
                        break;

                }
                recycler_image.setAdapter(photoadapter);
            }

        };
        recycler_shop.setAdapter(adapter);
    }
    private void initImage(final int position, final ArrayList<String> imagePathsAll,final ArrayList<String> compressPathsAll,final List<String> cartImageAll){
        photoadapter=  new BaseRVAdapter(PostEvaluationActivity.this, imagePathsAll) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_advice_image;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position2) {
                ImageView item_publishTask_image=holder.getImageView(R.id.item_publishTask_image);
                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true)
                        .build();
                String drawableUrl;
                if (position2 ==imagePathsAll.size() - 1) {
                    drawableUrl = ImageDownloader.Scheme.DRAWABLE.wrap(imagePathsAll.get(position2));
                } else {
                    drawableUrl = ImageDownloader.Scheme.FILE.wrap(imagePathsAll.get(position2));
                }

                ImageLoader.getInstance().displayImage(drawableUrl, item_publishTask_image, options);
                item_publishTask_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        CusToast.showToast(String.valueOf(position));
                        imageOnClick(position,position2,imagePathsAll);
                       notifyDataSetChanged();

                    }
                });
                item_publishTask_image.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        removeImage(position2,imagePathsAll,compressPathsAll,cartImageAll);
                        return true;
                    }
                });
            }

        };
    }
    private void imageOnClick(int position,int position2, ArrayList<String> imagePathsAll){
        if(position2== imagePathsAll.size()-1){
            if (imagePathsAll.size() >= 10) {
                CusToast.showToast("最多选择9张图片");
                return;
            }
            Log.e("====第几个商品11---",String.valueOf(ShoppingType)+"");
            ShoppingType=position;
            Intent intent = new Intent(PostEvaluationActivity.this, MultiImageSelectorActivity.class);
            // 是否显示调用相机拍照
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
            // 最大图片选择数量
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10 - imagePathsAll.size());

            // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

            startActivityForResult(intent, FROM_ALBUM_CODE);
        }else {
            ArrayList<String> paths = new ArrayList<>();
            paths.addAll(imagePathsAll);
            paths.remove(paths.get(paths.size() - 1));
            Intent intent = new Intent(PostEvaluationActivity.this, ShowImageDetail.class);
            intent.putStringArrayListExtra("paths", paths);
            intent.putExtra("index", position2);
            startActivity(intent);
        }
    }
    private int niming=0;
    private void removeImage( final int position2, final ArrayList<String> imagePathsAll, final ArrayList<String> compressPathsAll, final List<String> cartImageAll){
        /**
         这里使用了 android.support.v7.app.AlertDialog.Builder
         可以直接在头部写 import android.support.v7.app.AlertDialog
         那么下面就可以写成 AlertDialog.Builder
         */
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(PostEvaluationActivity.this);
        builder.setMessage("是否确认删除？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(imagePathsAll.size()>0){
                    imagePathsAll.remove(imagePathsAll.get(position2));
                }
                if(compressPathsAll.size()>0){
                    compressPathsAll.remove(compressPathsAll.get(position2));
                }
                if(cartImageAll.size()>0){
                    cartImageAll.remove(cartImage.get(position2));
                }
                photoadapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });
        if (imagePathsAll.size() - 1 != position2)
            builder.show();

    }
    private int msxfRank=0;
    private int mjfwRank=0;
    private int wlRank=0;

    @Event(value = {R.id.rl_close,R.id.rl_title_right,R.id.iv_item_xx1,R.id.iv_item_xx2,R.id.iv_item_xx3,R.id.iv_item_xx4,R.id.iv_item_xx5,R.id.iv_MJitem_xx1,R.id.iv_MJitem_xx2,R.id.iv_MJitem_xx3,R.id.iv_MJitem_xx4,R.id.iv_MJitem_xx5
    ,R.id.iv_WLitem_xx1,R.id.iv_WLitem_xx2,R.id.iv_WLitem_xx3,R.id.iv_niming,R.id.iv_WLitem_xx4,R.id.iv_WLitem_xx5}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                finish();
                break;
            case R.id.iv_niming:
                if(niming==0){
                    iv_niming.setImageResource(R.drawable.niming_wgx);
                    niming=1;
                }else{
                    iv_niming.setImageResource(R.drawable.niming_xz);
                    niming=0;
                }
                break;
            case R.id.iv_item_xx1:
                msxfRank=1;
                iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx2.setImageResource(R.drawable.pingj_xx);
                iv_item_xx3.setImageResource(R.drawable.pingj_xx);
                iv_item_xx4.setImageResource(R.drawable.pingj_xx);
                iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_item_xx2:
                msxfRank=2;
                iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx3.setImageResource(R.drawable.pingj_xx);
                iv_item_xx4.setImageResource(R.drawable.pingj_xx);
                iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_item_xx3:
                msxfRank=3;
                iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx4.setImageResource(R.drawable.pingj_xx);
                iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_item_xx4:
                msxfRank=4;
                iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx4.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_item_xx5:
                msxfRank=5;
                iv_item_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx4.setImageResource(R.drawable.pingj_xx_xz);
                iv_item_xx5.setImageResource(R.drawable.pingj_xx_xz);
                break;
            case R.id.iv_MJitem_xx1:
                mjfwRank=1;
                iv_MJitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx2.setImageResource(R.drawable.pingj_xx);
                iv_MJitem_xx3.setImageResource(R.drawable.pingj_xx);
                iv_MJitem_xx4.setImageResource(R.drawable.pingj_xx);
                iv_MJitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_MJitem_xx2:
                mjfwRank=2;
                iv_MJitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx3.setImageResource(R.drawable.pingj_xx);
                iv_MJitem_xx4.setImageResource(R.drawable.pingj_xx);
                iv_MJitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_MJitem_xx3:
                mjfwRank=3;
                iv_MJitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx4.setImageResource(R.drawable.pingj_xx);
                iv_MJitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_MJitem_xx4:
                mjfwRank=4;
                iv_MJitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx4.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_MJitem_xx5:
                mjfwRank=5;
                iv_MJitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx4.setImageResource(R.drawable.pingj_xx_xz);
                iv_MJitem_xx5.setImageResource(R.drawable.pingj_xx_xz);
                break;
            case R.id.iv_WLitem_xx1:
                wlRank=1;
                iv_WLitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx2.setImageResource(R.drawable.pingj_xx);
                iv_WLitem_xx3.setImageResource(R.drawable.pingj_xx);
                iv_WLitem_xx4.setImageResource(R.drawable.pingj_xx);
                iv_WLitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_WLitem_xx2:
                wlRank=2;
                iv_WLitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx3.setImageResource(R.drawable.pingj_xx);
                iv_WLitem_xx4.setImageResource(R.drawable.pingj_xx);
                iv_WLitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_WLitem_xx3:
                wlRank=3;
                iv_WLitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx4.setImageResource(R.drawable.pingj_xx);
                iv_WLitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_WLitem_xx4:
                wlRank=4;
                iv_WLitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx4.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx5.setImageResource(R.drawable.pingj_xx);
                break;
            case R.id.iv_WLitem_xx5:
                wlRank=5;
                iv_WLitem_xx1.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx2.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx3.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx4.setImageResource(R.drawable.pingj_xx_xz);
                iv_WLitem_xx5.setImageResource(R.drawable.pingj_xx_xz);
                break;
            case R.id.rl_title_right:
                if(msxfRank==0){
                    CusToast.showToast("请您对综合服务进行评价");
                    return;
                }else if(msxfRank==0){
                    CusToast.showToast("请您对商家服务进行评价");
                    return;
                }else if(msxfRank==0){
                    CusToast.showToast("请您对物流服务进行评价");
                    return;
                }
                Map<String,Object> map=new HashMap<>();
                Map<String, Object> mapSC = new HashMap<>();

                switch (goodsListBeans.size()){
                   case 1:
                       if(starLevel.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }

                       PingjiaBean pingjiaBean=new PingjiaBean();
                       pingjiaBean.setContent(Content);
                       pingjiaBean.setRank(starLevel);
                       pingjiaBean.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean.setComment_img(cartImage);
                       String value=new Gson().toJson(pingjiaBean);
                       map.put(goodsListBeans.get(0).getGoods_id(),value);
                       String ramank= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank);
                       upFBPJData(mapSC);
                       break;
                   case 2:
                       if(starLevel.equals("0")&&starLevel1.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean1=new PingjiaBean();
                       pingjiaBean1.setContent(Content);
                       pingjiaBean1.setRank(starLevel);
                       pingjiaBean1.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean1.setComment_img(cartImage);
                       String value1=new Gson().toJson(pingjiaBean1);

                       PingjiaBean pingjiaBean11=new PingjiaBean();
                       pingjiaBean11.setContent(Content1);
                       pingjiaBean11.setRank(starLevel1);
                       pingjiaBean11.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean11.setComment_img(cartImage1);
                       String value11=new Gson().toJson(pingjiaBean11);

                       map.put(goodsListBeans.get(0).getGoods_id(),value1);
                       map.put(goodsListBeans.get(1).getGoods_id(),value11);
                       String ramank2= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank2);
                       upFBPJData(mapSC);
                       break;
                   case 3:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean3=new PingjiaBean();
                       pingjiaBean3.setContent(Content);
                       pingjiaBean3.setRank(starLevel);
                       pingjiaBean3.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean3.setComment_img(cartImage);

                       PingjiaBean pingjiaBean33=new PingjiaBean();
                       pingjiaBean33.setContent(Content1);
                       pingjiaBean33.setRank(starLevel1);
                       pingjiaBean33.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean33.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean333=new PingjiaBean();
                       pingjiaBean333.setContent(Content2);
                       pingjiaBean333.setRank(starLevel2);
                       pingjiaBean333.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean333.setComment_img(cartImage2);
                       String value3=new Gson().toJson(pingjiaBean3);
                       String value33=new Gson().toJson(pingjiaBean33);
                       String value333=new Gson().toJson(pingjiaBean33);
                           map.put(goodsListBeans.get(0).getGoods_id(),value3);
                           map.put(goodsListBeans.get(1).getGoods_id(),value33);
                           map.put(goodsListBeans.get(2).getGoods_id(),value333);
                       String ramank3= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank3);
                       upFBPJData(mapSC);
                       break;
                   case 4:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")&&starLevel3.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean4=new PingjiaBean();
                       pingjiaBean4.setContent(Content);
                       pingjiaBean4.setRank(starLevel);
                       pingjiaBean4.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean4.setComment_img(cartImage);

                       PingjiaBean pingjiaBean42=new PingjiaBean();
                       pingjiaBean42.setContent(Content1);
                       pingjiaBean42.setRank(starLevel1);
                       pingjiaBean42.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean42.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean43=new PingjiaBean();
                       pingjiaBean43.setContent(Content2);
                       pingjiaBean43.setRank(starLevel2);
                       pingjiaBean43.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean43.setComment_img(cartImage2);

                       PingjiaBean pingjiaBean44=new PingjiaBean();
                       pingjiaBean44.setContent(Content3);
                       pingjiaBean44.setRank(starLevel3);
                       pingjiaBean44.setSpec_key_name(goodsListBeans.get(3).getSpec_key_name());
                       pingjiaBean44.setComment_img(cartImage3);

                       String value4=new Gson().toJson(pingjiaBean4);
                       String value42=new Gson().toJson(pingjiaBean42);
                       String value43=new Gson().toJson(pingjiaBean43);
                       String value44=new Gson().toJson(pingjiaBean44);

                       map.put(goodsListBeans.get(0).getGoods_id(),value4);
                       map.put(goodsListBeans.get(1).getGoods_id(),value42);
                       map.put(goodsListBeans.get(2).getGoods_id(),value43);
                       map.put(goodsListBeans.get(3).getGoods_id(),value44);

                       String ramank4= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank4);
                       upFBPJData(mapSC);
                       break;
                   case 5:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")&&starLevel3.equals("0")&&starLevel4.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean5=new PingjiaBean();
                       pingjiaBean5.setContent(Content);
                       pingjiaBean5.setRank(starLevel);
                       pingjiaBean5.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean5.setComment_img(cartImage);

                       PingjiaBean pingjiaBean52=new PingjiaBean();
                       pingjiaBean52.setContent(Content1);
                       pingjiaBean52.setRank(starLevel1);
                       pingjiaBean52.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean52.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean53=new PingjiaBean();
                       pingjiaBean53.setContent(Content2);
                       pingjiaBean53.setRank(starLevel2);
                       pingjiaBean53.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean53.setComment_img(cartImage2);

                       PingjiaBean pingjiaBean54=new PingjiaBean();
                       pingjiaBean54.setContent(Content3);
                       pingjiaBean54.setRank(starLevel3);
                       pingjiaBean54.setSpec_key_name(goodsListBeans.get(3).getSpec_key_name());
                       pingjiaBean54.setComment_img(cartImage3);

                       PingjiaBean pingjiaBean55=new PingjiaBean();
                       pingjiaBean55.setContent(Content4);
                       pingjiaBean55.setRank(starLevel4);
                       pingjiaBean55.setSpec_key_name(goodsListBeans.get(4).getSpec_key_name());
                       pingjiaBean55.setComment_img(cartImage4);

                       String value5=new Gson().toJson(pingjiaBean5);
                       String value52=new Gson().toJson(pingjiaBean52);
                       String value53=new Gson().toJson(pingjiaBean53);
                       String value54=new Gson().toJson(pingjiaBean54);
                       String value55=new Gson().toJson(pingjiaBean55);

                       map.put(goodsListBeans.get(0).getGoods_id(),value5);
                       map.put(goodsListBeans.get(1).getGoods_id(),value52);
                       map.put(goodsListBeans.get(2).getGoods_id(),value53);
                       map.put(goodsListBeans.get(3).getGoods_id(),value54);
                       map.put(goodsListBeans.get(4).getGoods_id(),value55);

                       String ramank5= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank5);
                       upFBPJData(mapSC);
                       break;
                   case 6:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")&&starLevel3.equals("0")&&starLevel4.equals("0")&&starLevel5.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean6=new PingjiaBean();
                       pingjiaBean6.setContent(Content);
                       pingjiaBean6.setRank(starLevel);
                       pingjiaBean6.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean6.setComment_img(cartImage);

                       PingjiaBean pingjiaBean62=new PingjiaBean();
                       pingjiaBean62.setContent(Content1);
                       pingjiaBean62.setRank(starLevel1);
                       pingjiaBean62.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean62.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean63=new PingjiaBean();
                       pingjiaBean63.setContent(Content2);
                       pingjiaBean63.setRank(starLevel2);
                       pingjiaBean63.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean63.setComment_img(cartImage2);

                       PingjiaBean pingjiaBean64=new PingjiaBean();
                       pingjiaBean64.setContent(Content3);
                       pingjiaBean64.setRank(starLevel3);
                       pingjiaBean64.setSpec_key_name(goodsListBeans.get(3).getSpec_key_name());
                       pingjiaBean64.setComment_img(cartImage3);
                       PingjiaBean pingjiaBean65=new PingjiaBean();
                       pingjiaBean65.setContent(Content4);
                       pingjiaBean65.setRank(starLevel4);
                       pingjiaBean65.setSpec_key_name(goodsListBeans.get(4).getSpec_key_name());
                       pingjiaBean65.setComment_img(cartImage4);

                       PingjiaBean pingjiaBean66=new PingjiaBean();
                       pingjiaBean66.setContent(Content5);
                       pingjiaBean66.setRank(starLevel5);
                       pingjiaBean66.setSpec_key_name(goodsListBeans.get(5).getSpec_key_name());
                       pingjiaBean66.setComment_img(cartImage5);

                       String value6=new Gson().toJson(pingjiaBean6);
                       String value62=new Gson().toJson(pingjiaBean62);
                       String value63=new Gson().toJson(pingjiaBean63);
                       String value64=new Gson().toJson(pingjiaBean64);
                       String value65=new Gson().toJson(pingjiaBean65);
                       String value66=new Gson().toJson(pingjiaBean66);

                       map.put(goodsListBeans.get(0).getGoods_id(),value6);
                       map.put(goodsListBeans.get(1).getGoods_id(),value62);
                       map.put(goodsListBeans.get(2).getGoods_id(),value63);
                       map.put(goodsListBeans.get(3).getGoods_id(),value64);
                       map.put(goodsListBeans.get(4).getGoods_id(),value65);
                       map.put(goodsListBeans.get(5).getGoods_id(),value66);

                       String ramank6= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank6);
                       upFBPJData(mapSC);
                       break;
                   case 7:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")&&starLevel3.equals("0")
                               &&starLevel4.equals("0")&&starLevel5.equals("0")&&starLevel6.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean7=new PingjiaBean();
                       pingjiaBean7.setContent(Content);
                       pingjiaBean7.setRank(starLevel);
                       pingjiaBean7.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean7.setComment_img(cartImage);

                       PingjiaBean pingjiaBean72=new PingjiaBean();
                       pingjiaBean72.setContent(Content1);
                       pingjiaBean72.setRank(starLevel1);
                       pingjiaBean72.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean72.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean73=new PingjiaBean();
                       pingjiaBean73.setContent(Content2);
                       pingjiaBean73.setRank(starLevel2);
                       pingjiaBean73.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean73.setComment_img(cartImage2);

                       PingjiaBean pingjiaBean74=new PingjiaBean();
                       pingjiaBean74.setContent(Content3);
                       pingjiaBean74.setRank(starLevel3);
                       pingjiaBean74.setSpec_key_name(goodsListBeans.get(3).getSpec_key_name());
                       pingjiaBean74.setComment_img(cartImage3);
                       PingjiaBean pingjiaBean75=new PingjiaBean();
                       pingjiaBean75.setContent(Content4);
                       pingjiaBean75.setRank(starLevel4);
                       pingjiaBean75.setSpec_key_name(goodsListBeans.get(4).getSpec_key_name());
                       pingjiaBean75.setComment_img(cartImage4);

                       PingjiaBean pingjiaBean76=new PingjiaBean();
                       pingjiaBean76.setContent(Content5);
                       pingjiaBean76.setRank(starLevel5);
                       pingjiaBean76.setSpec_key_name(goodsListBeans.get(5).getSpec_key_name());
                       pingjiaBean76.setComment_img(cartImage5);

                       PingjiaBean pingjiaBean77=new PingjiaBean();
                       pingjiaBean77.setContent(Content6);
                       pingjiaBean77.setRank(starLevel6);
                       pingjiaBean77.setSpec_key_name(goodsListBeans.get(6).getSpec_key_name());
                       pingjiaBean77.setComment_img(cartImage6);

                       String value7=new Gson().toJson(pingjiaBean7);
                       String value72=new Gson().toJson(pingjiaBean72);
                       String value73=new Gson().toJson(pingjiaBean73);
                       String value74=new Gson().toJson(pingjiaBean74);
                       String value75=new Gson().toJson(pingjiaBean75);
                       String value76=new Gson().toJson(pingjiaBean76);
                       String value77=new Gson().toJson(pingjiaBean77);

                       map.put(goodsListBeans.get(0).getGoods_id(),value7);
                       map.put(goodsListBeans.get(1).getGoods_id(),value72);
                       map.put(goodsListBeans.get(2).getGoods_id(),value73);
                       map.put(goodsListBeans.get(3).getGoods_id(),value74);
                       map.put(goodsListBeans.get(4).getGoods_id(),value75);
                       map.put(goodsListBeans.get(5).getGoods_id(),value76);
                       map.put(goodsListBeans.get(6).getGoods_id(),value76);

                       String ramank7= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank7);
                       upFBPJData(mapSC);
                       break;
                   case 8:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")&&starLevel3.equals("0")
                               &&starLevel4.equals("0")&&starLevel5.equals("0")&&starLevel6.equals("0")&&starLevel7.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean8=new PingjiaBean();
                       pingjiaBean8.setContent(Content);
                       pingjiaBean8.setRank(starLevel);
                       pingjiaBean8.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean8.setComment_img(cartImage);

                       PingjiaBean pingjiaBean82=new PingjiaBean();
                       pingjiaBean82.setContent(Content1);
                       pingjiaBean82.setRank(starLevel1);
                       pingjiaBean82.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean82.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean83=new PingjiaBean();
                       pingjiaBean83.setContent(Content2);
                       pingjiaBean83.setRank(starLevel2);
                       pingjiaBean83.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean83.setComment_img(cartImage2);

                       PingjiaBean pingjiaBean84=new PingjiaBean();
                       pingjiaBean84.setContent(Content3);
                       pingjiaBean84.setRank(starLevel3);
                       pingjiaBean84.setSpec_key_name(goodsListBeans.get(3).getSpec_key_name());
                       pingjiaBean84.setComment_img(cartImage3);
                       PingjiaBean pingjiaBean85=new PingjiaBean();
                       pingjiaBean85.setContent(Content4);
                       pingjiaBean85.setRank(starLevel4);
                       pingjiaBean85.setSpec_key_name(goodsListBeans.get(4).getSpec_key_name());
                       pingjiaBean85.setComment_img(cartImage4);

                       PingjiaBean pingjiaBean86=new PingjiaBean();
                       pingjiaBean86.setContent(Content5);
                       pingjiaBean86.setRank(starLevel5);
                       pingjiaBean86.setSpec_key_name(goodsListBeans.get(5).getSpec_key_name());
                       pingjiaBean86.setComment_img(cartImage5);

                       PingjiaBean pingjiaBean87=new PingjiaBean();
                       pingjiaBean87.setContent(Content6);
                       pingjiaBean87.setRank(starLevel6);
                       pingjiaBean87.setSpec_key_name(goodsListBeans.get(6).getSpec_key_name());
                       pingjiaBean87.setComment_img(cartImage6);
                       PingjiaBean pingjiaBean88=new PingjiaBean();
                       pingjiaBean88.setContent(Content7);
                       pingjiaBean88.setRank(starLevel7);
                       pingjiaBean88.setSpec_key_name(goodsListBeans.get(7).getSpec_key_name());
                       pingjiaBean88.setComment_img(cartImage7);

                       String value8=new Gson().toJson(pingjiaBean8);
                       String value82=new Gson().toJson(pingjiaBean82);
                       String value83=new Gson().toJson(pingjiaBean83);
                       String value84=new Gson().toJson(pingjiaBean84);
                       String value85=new Gson().toJson(pingjiaBean85);
                       String value86=new Gson().toJson(pingjiaBean86);
                       String value87=new Gson().toJson(pingjiaBean87);
                       String value88=new Gson().toJson(pingjiaBean88);

                       map.put(goodsListBeans.get(0).getGoods_id(),value8);
                       map.put(goodsListBeans.get(1).getGoods_id(),value82);
                       map.put(goodsListBeans.get(2).getGoods_id(),value83);
                       map.put(goodsListBeans.get(3).getGoods_id(),value84);
                       map.put(goodsListBeans.get(4).getGoods_id(),value85);
                       map.put(goodsListBeans.get(5).getGoods_id(),value86);
                       map.put(goodsListBeans.get(6).getGoods_id(),value87);
                       map.put(goodsListBeans.get(7).getGoods_id(),value88);

                       String ramank8= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank8);
                       upFBPJData(mapSC);
                       break;
                   case 9:
                       if(starLevel.equals("0")&&starLevel1.equals("0")&&starLevel2.equals("0")&&starLevel3.equals("0")
                               &&starLevel4.equals("0")&&starLevel5.equals("0")&&starLevel6.equals("0")&&starLevel7.equals("0")&&starLevel8.equals("0")){
                           CusToast.showToast("请您评价商品满意度");
                           return;
                       }
                       PingjiaBean pingjiaBean9=new PingjiaBean();
                       pingjiaBean9.setContent(Content);
                       pingjiaBean9.setRank(starLevel);
                       pingjiaBean9.setSpec_key_name(goodsListBeans.get(0).getSpec_key_name());
                       pingjiaBean9.setComment_img(cartImage);

                       PingjiaBean pingjiaBean92=new PingjiaBean();
                       pingjiaBean92.setContent(Content1);
                       pingjiaBean92.setRank(starLevel1);
                       pingjiaBean92.setSpec_key_name(goodsListBeans.get(1).getSpec_key_name());
                       pingjiaBean92.setComment_img(cartImage1);

                       PingjiaBean pingjiaBean93=new PingjiaBean();
                       pingjiaBean93.setContent(Content2);
                       pingjiaBean93.setRank(starLevel2);
                       pingjiaBean93.setSpec_key_name(goodsListBeans.get(2).getSpec_key_name());
                       pingjiaBean93.setComment_img(cartImage2);

                       PingjiaBean pingjiaBean94=new PingjiaBean();
                       pingjiaBean94.setContent(Content3);
                       pingjiaBean94.setRank(starLevel3);
                       pingjiaBean94.setSpec_key_name(goodsListBeans.get(3).getSpec_key_name());
                       pingjiaBean94.setComment_img(cartImage3);
                       PingjiaBean pingjiaBean95=new PingjiaBean();
                       pingjiaBean95.setContent(Content4);
                       pingjiaBean95.setRank(starLevel4);
                       pingjiaBean95.setSpec_key_name(goodsListBeans.get(4).getSpec_key_name());
                       pingjiaBean95.setComment_img(cartImage4);

                       PingjiaBean pingjiaBean96=new PingjiaBean();
                       pingjiaBean96.setContent(Content5);
                       pingjiaBean96.setRank(starLevel5);
                       pingjiaBean96.setSpec_key_name(goodsListBeans.get(5).getSpec_key_name());
                       pingjiaBean96.setComment_img(cartImage5);

                       PingjiaBean pingjiaBean97=new PingjiaBean();
                       pingjiaBean97.setContent(Content6);
                       pingjiaBean97.setRank(starLevel6);
                       pingjiaBean97.setSpec_key_name(goodsListBeans.get(6).getSpec_key_name());
                       pingjiaBean97.setComment_img(cartImage6);
                       PingjiaBean pingjiaBean98=new PingjiaBean();
                       pingjiaBean98.setContent(Content7);
                       pingjiaBean98.setRank(starLevel7);
                       pingjiaBean98.setSpec_key_name(goodsListBeans.get(7).getSpec_key_name());
                       pingjiaBean98.setComment_img(cartImage7);
                       PingjiaBean pingjiaBean99=new PingjiaBean();
                       pingjiaBean99.setContent(Content8);
                       pingjiaBean99.setRank(starLevel8);
                       pingjiaBean99.setSpec_key_name(goodsListBeans.get(8).getSpec_key_name());
                       pingjiaBean99.setComment_img(cartImage8);

                       String value9=new Gson().toJson(pingjiaBean9);
                       String value92=new Gson().toJson(pingjiaBean92);
                       String value93=new Gson().toJson(pingjiaBean93);
                       String value94=new Gson().toJson(pingjiaBean94);
                       String value95=new Gson().toJson(pingjiaBean95);
                       String value96=new Gson().toJson(pingjiaBean96);
                       String value97=new Gson().toJson(pingjiaBean97);
                       String value98=new Gson().toJson(pingjiaBean98);
                       String value99=new Gson().toJson(pingjiaBean99);

                       map.put(goodsListBeans.get(0).getGoods_id(),value9);
                       map.put(goodsListBeans.get(1).getGoods_id(),value92);
                       map.put(goodsListBeans.get(2).getGoods_id(),value93);
                       map.put(goodsListBeans.get(3).getGoods_id(),value94);
                       map.put(goodsListBeans.get(4).getGoods_id(),value95);
                       map.put(goodsListBeans.get(5).getGoods_id(),value96);
                       map.put(goodsListBeans.get(6).getGoods_id(),value97);
                       map.put(goodsListBeans.get(7).getGoods_id(),value98);
                       map.put(goodsListBeans.get(8).getGoods_id(),value99);

                       String ramank9= new Gson().toJson(map);
                       mapSC.put("token", BaseApplication.getInstance().userBean.getToken());
                       mapSC.put("order_id",order_id);
                       mapSC.put("store_packge_hidden",msxfRank);
                       mapSC.put("store_speed_hidden",mjfwRank);
                       mapSC.put("store_sever_hidden",wlRank);
                       mapSC.put("anonymous",niming);
                       mapSC.put("remark",ramank9);
                       upFBPJData(mapSC);
                       break;

               }
                break;
        }
    }

    private void upFBPJData(Map<String, Object> map) {


        showDialog();
        XUtil.Post(URLConstant.FABUPINGJIA, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====发布评价===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        finish();
                    }
                    CusToast.showToast(msg);

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
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
    private void upOrderXQData() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", BaseApplication.getInstance().userBean.getToken());
        map.put("order_id",order_id);
        showDialog();
        XUtil.Post(URLConstant.QUERENSH_XIANGQING, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====订单详情===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        OrderDataBean orderDataBean=gson.fromJson(result,OrderDataBean.class);
                        goodsListBeans=orderDataBean.getResult().getGoods_list();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                closeDialog();
                initView();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
                closeDialog();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 23) {
            if (checker.lacksPermissions(strPermissions)) {
                permissionActivity();
            }
        }
    }

    private void permissionActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODEBBB, strPermissions);
    }
    private ArrayList<String> arrayList1=new ArrayList<>();
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                //相册
                case FROM_ALBUM_CODE:
                    if (null != data) {
                        if (!Confirg.compressFile.exists()) {
                            Confirg.compressFile = new File(Confirg.FilesPath);
                            Confirg.compressFile.mkdirs();
                        }

                        List<String> list = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                        System.out.println("========：" + list.size());
                        switch (ShoppingType){
                            case 0:
                                if (imagePaths.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        compressPaths.add(compressImage);
                                        imagePaths.add(imagePaths.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                         upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 1:
                                if (imagePaths1.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        compressPaths1.add(compressImage);
                                        imagePaths1.add(imagePaths1.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }

                                break;
                            case 2:
                                if (imagePaths2.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        compressPaths2.add(compressImage);
                                        imagePaths2.add(imagePaths2.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }

                                break;
                            case 3:
                                if (imagePaths3.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        compressPaths3.add(compressImage);
                                        imagePaths3.add(imagePaths3.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }

                                break;
                            case 4:
                                if (imagePaths4.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths4.add(compressImage);
                                        imagePaths4.add(imagePaths4.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }

                                break;
                            case 5:
                                if (imagePaths5.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths5.add(compressImage);
                                        imagePaths5.add(imagePaths5.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }

                                break;
                            case 6:
                                if (imagePaths6.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths6.add(compressImage);
                                        imagePaths6.add(imagePaths6.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 7:
                                if (imagePaths7.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths7.add(compressImage);
                                        imagePaths7.add(imagePaths7.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 8:
                                if (imagePaths8.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths8.add(compressImage);
                                        imagePaths8.add(imagePaths8.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 9:
                                if (imagePaths9.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths9.add(compressImage);
                                        imagePaths9.add(imagePaths9.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 10:
                                if (imagePaths10.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths10.add(compressImage);
                                        imagePaths10.add(imagePaths10.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 11:
                                if (imagePaths11.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths11.add(compressImage);
                                        imagePaths11.add(imagePaths11.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 12:
                                if (imagePaths12.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths12.add(compressImage);
                                        imagePaths12.add(imagePaths12.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            case 13:
                                if (imagePaths13.size() < 10) {
                                    for (int i = 0; i < list.size(); i++) {
                                        System.out.println("选中的路径：" + list.get(i));
                                        String pic_path = list.get(i);
                                        String targetPath = compressImageFilePath + Confirg.df.
                                                format(new Date()) + ".jpg";
                                        //调用压缩图片的方法，返回压缩后的图片path
                                        final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                        Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
                                        Log.e("====第几个商品---",String.valueOf(ShoppingType)+"");
                                        compressPaths13.add(compressImage);
                                        imagePaths13.add(imagePaths13.size() - 1, list.get(i));
                                        Bitmap bitmap = BitmapFactory.decodeFile(compressImage.toString());
                                        Map<String,Object> map=new HashMap<>();
                                        map.put("image_base_64_arr", "data:image/jpeg;base64,"+ Base64BitmapUtil.bitmapToBase64(bitmap));
                                    upMessageData(map);
                                    }
                                } else {
                                    CusToast.showToast("无法添加更多图片！");
                                }
                                break;
                            default:
                                break;

                        }
                        photoadapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    private List<String> resultImageBeans=new ArrayList<>();
    private void upMessageData(Map<String, Object> map) {
        XUtil.Post(URLConstant.SHANGCHUAN_IMAGE, map, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("====上传图片===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");
                    if (res.equals("1")) {
                        Gson gson = new Gson();
                        ImageDataBean imageDataBean=gson.fromJson(result,ImageDataBean.class);
                        resultImageBeans=imageDataBean.getResult().getImage_show();
                        Map<String,Object> map=new HashMap<>();
                        map.put("token", BaseApplication.getInstance().userBean.getToken());
                        if(imageDataBean.getResult().getImage_show().size()>=0){
                            map.put("head_pic", imageDataBean.getResult().getImage_show().get(0));
                            switch (ShoppingType){
                                case 0:
                                    cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                                    break;
                                case 1:
                                    cartImage1.add(imageDataBean.getResult().getImage_show().get(0));
                                    break;
                                case 2:
                                    cartImage2.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 3:
                                    cartImage3.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 4:
                                    cartImage4.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 5:
                                    cartImage5.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 6:
                                    cartImage6.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 7:
                                    cartImage7.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 8:
                                    cartImage8.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 9:
                                    cartImage9.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 10:
                                    cartImage10.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 11:
                                    cartImage11.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 12:
                                    cartImage12.add(imageDataBean.getResult().getImage_show().get(0));

                                    break;
                                case 13:
                                    cartImage13.add(imageDataBean.getResult().getImage_show().get(0));
                                    break;
                            }
                        }
                    }else {
                        CusToast.showToast(msg);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                ex.printStackTrace();
            }
        });
    }

}
