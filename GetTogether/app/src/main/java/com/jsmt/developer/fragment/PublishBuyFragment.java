package com.jsmt.developer.fragment;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jsmt.developer.R;
import com.jsmt.developer.ShowImageDetail;
import com.jsmt.developer.activity.LoginActivity;
import com.jsmt.developer.adapter.MyPublicTaskRecycleAdapter;
import com.jsmt.developer.base.Base2Fragment;
import com.jsmt.developer.base.BaseActivity;
import com.jsmt.developer.base.BaseApplication;
import com.jsmt.developer.base.BaseConstant;
import com.jsmt.developer.base.BaseRVAdapter;
import com.jsmt.developer.base.BaseViewHolder;
import com.jsmt.developer.base.URLConstant;
import com.jsmt.developer.bean.ImageDataBean;
import com.jsmt.developer.bean.QiuGouXXBean;
import com.jsmt.developer.utils.Base64BitmapUtil;
import com.jsmt.developer.utils.BitnapUtils;
import com.jsmt.developer.utils.Confirg;
import com.jsmt.developer.utils.SharedPreferencesUtils;
import com.jsmt.developer.utils.permissions.PermissionsActivity;
import com.jsmt.developer.utils.permissions.PermissionsChecker;
import com.jsmt.developer.utils.xutils3.MyCallBack;
import com.jsmt.developer.utils.xutils3.XUtil;

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
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_publish_buy)
public class PublishBuyFragment extends Base2Fragment implements View.OnClickListener, View.OnLongClickListener {

    private   BaseActivity baseActivity;
    @ViewInject(R.id.iv_qglx1)
    private ImageView iv_qglx1;
    @ViewInject(R.id.iv_qglx2)
    private ImageView iv_qglx2;

    @ViewInject(R.id.et_cpName)
    private EditText et_cpName;
    @ViewInject(R.id.et_cpms)
    private EditText et_cpms;
    @ViewInject(R.id.ll_cpfl)
    private LinearLayout ll_cpfl;
    @ViewInject(R.id.ll_qglx)
    private LinearLayout ll_qglx;
    @ViewInject(R.id.ll_jhTime)
    private LinearLayout ll_jhTime;
    @ViewInject(R.id.et_qgNum)
    private EditText et_qgNum;
    @ViewInject(R.id.publish_task_recyclerView)
    private RecyclerView recyclerView;
    private MyPublicTaskRecycleAdapter mTaskImgAdapter;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private List<Integer> imageRes = new ArrayList<>();
    private ArrayList<String> compressPaths = new ArrayList<>();
    private final int FROM_ALBUM_CODE = 102;// 调用相册更改背景图片的请求code

    private PermissionsChecker checker;

    private String compressImageFilePath;
    private String[] strPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private static final int REQUEST_CODEBBB = 100;
    private List<QiuGouXXBean.ResultBean.GoodsCateBean>goodsCateBeans=new ArrayList<>();
    private List<QiuGouXXBean.ResultBean.GoodsTimeBean> goodsTimeBeans=new ArrayList<>();
    private List<QiuGouXXBean.ResultBean.GoodsTypeBean> goodsTypeBeans=new ArrayList<>();
    private int type=0;
    @ViewInject(R.id.tv_cpfl)
    private TextView tv_cpfl;
    @ViewInject(R.id.tv_qglx)
    private TextView tv_qglx;
    @ViewInject(R.id.tv_jhTime)
    private TextView tv_jhTime;
    private int qgNum=0;
    private String goods_cate="";
    private String release_type="";
    private String attach_time="";
    @ViewInject(R.id.rl_close)
    private RelativeLayout rl_close;
    @ViewInject(R.id.tv_title)
    private  TextView tv_title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this, inflater, container);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseActivity= (BaseActivity) getActivity();
        checker = new PermissionsChecker(getActivity());

        initData();
        initView();
        upMainQGXXData();
    }
    private void initData(){
        rl_close.setVisibility(View.GONE);
        tv_title.setText(R.string.fabuqiug);

        imagePaths.add(R.drawable.addtupian + "");
        compressImageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WorksComing/Compress/";
        File folder = new File(compressImageFilePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    public void initView() {

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
        mTaskImgAdapter = new MyPublicTaskRecycleAdapter(getActivity(), imageRes, imagePaths, this, this);
        recyclerView.setAdapter(mTaskImgAdapter);

    }
    @Event(value = {R.id.rl_close,R.id.iv_qglx1, R.id.iv_qglx2,R.id.ll_cpfl, R.id.ll_qglx, R.id.ll_jhTime,R.id.tv_fbqg}, type = View.OnClickListener.class)
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.iv_qglx1:
                qgNum=1;
                iv_qglx1.setImageResource(R.drawable.xuanzhongf);
                iv_qglx2.setImageResource(R.drawable.weixuanz);
                et_qgNum.setEnabled(true);

                break;
            case R.id.iv_qglx2:
                qgNum=2;
                iv_qglx2.setImageResource(R.drawable.xuanzhongf);
                iv_qglx1.setImageResource(R.drawable.weixuanz);
                et_qgNum.setEnabled(false);

                break;
            case R.id.ll_cpfl:
                type=1;
                mPop=null;
                showPop(ll_cpfl);
                break;
            case R.id.ll_qglx:
                type=2;
                mPop=null;
                showPop(ll_qglx);
                break;
            case R.id.ll_jhTime:
                type=3;
                mPop=null;
                showPop(ll_jhTime);
                break;
            case R.id.tv_fbqg:
                if(BaseApplication.getInstance().userBean==null){
                    startActivity(new Intent(getActivity(),LoginActivity.class));
                }else {

                    String name=et_cpName.getText().toString();
                    String cpms=et_cpms.getText().toString();
                    if(name.equals("")){
                        CusToast.showToast("请填写产品名称");
                        return;
                    }
                    if(cpms.equals("")){
                        CusToast.showToast("请填写产品描述");
                        return;
                    }
                    if(goods_cate.equals("")){
                        CusToast.showToast("请选择采购商品类型");
                        return;
                    }
                    if(release_type.equals("")){
                        CusToast.showToast("请选择求购类型");
                        return;
                    }
                    if(attach_time.equals("")){
                        CusToast.showToast("请选择交货时间");
                        return;
                    }
                    if(qgNum==0){
                        CusToast.showToast("请选择求购数量");
                        return;
                    }
                    if(qgNum==1){
                        if(et_qgNum.getText().toString().equals("")){
                            CusToast.showToast("请输入求购数量");
                            return;
                        }
                    }
                    Log.i("===图片22--",strImage+"");

                    upSCQGXXData();
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.item_publishTask_image:
                Integer index = (Integer) v.getTag();

                if (index == imagePaths.size() - 1) {

                    if (imagePaths.size() >= 10) {
                        CusToast.showToast("最多选择9张图片");
                        return;
                    }

                    intent = new Intent(getActivity(), MultiImageSelectorActivity.class);
                    // 是否显示调用相机拍照
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
                    // 最大图片选择数量
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 10-imagePaths.size());

                    // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);

                    startActivityForResult(intent, FROM_ALBUM_CODE);
                } else {
                    ArrayList<String> paths = new ArrayList<>();
                    paths.addAll(imagePaths);
                    paths.remove(paths.get(paths.size() - 1));

                    intent = new Intent(getActivity(), ShowImageDetail.class);
                    intent.putStringArrayListExtra("paths", paths);
                    intent.putExtra("index", index);
                    startActivity(intent);
                }
                break;


            default:
                break;
        }
    }
    private void upSCQGXXData(){
        Map<String,Object> map=new HashMap<>();
        if(BaseApplication.getInstance().userBean==null){
            return;
        }
        map.put("token",BaseApplication.getInstance().userBean.getToken());
        map.put("goods_name",et_cpName.getText().toString());
        map.put("goods_desc",et_cpms.getText().toString());
        map.put("goods_cate",goods_cate);
        map.put("release_type",release_type);
        map.put("attach_time",attach_time);
        if(qgNum==1){
            map.put("goods_num",et_qgNum.getText().toString());
        }else  if(qgNum==2){
            map.put("release",2);
        }
        if(cartImage.size()>0){
            for (int i = 0; i < cartImage.size(); i++) {
                strImage +=cartImage.get(i)+"," ;
            }
        }

        if(!strImage.equals("")){
            map.put("goods_logo",strImage);
        }

        baseActivity.showDialog();
        XUtil.Post(URLConstant.MAIN_GQSC,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====求购发布===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    String msg = jsonObject.optString("msg");

                    if(res.equals("1")){
                        Gson gson=new Gson();
                        et_cpName.setText("");
                        et_cpms.setText("");
                        tv_cpfl.setText("");
                        tv_qglx.setText("");
                        tv_jhTime.setText("");
                        qgNum=0;
                        iv_qglx1.setImageResource(R.drawable.weixuanz);
                        iv_qglx2.setImageResource(R.drawable.weixuanz);
                        imagePaths.clear();
                        cartImage.clear();
                        strImage="";
                        imagePaths.add(R.drawable.addtupian + "");
                        mTaskImgAdapter.notifyDataSetChanged();
                        CusToast.showToast(msg);
                    }else if(res.equals("-1")) {
                        CusToast.showToast(msg);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

    private PopupWindow mPop;
    private View view;
    private RecyclerView recyclerView_qg;
    private TextView tv_xz_title;
    //显示弹窗
    private void showPop(View v) {
        initPop();
        if (mPop.isShowing())
            return;
        //设置弹窗底部位置
        mPop.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.6f;
        getActivity().getWindow().setAttributes(lp);
    }
    private void initPop() {
        if (mPop == null) {
            view = LayoutInflater.from(getActivity()).inflate(R.layout.main_qgxx_layout, null);
            mPop = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //点击弹窗外消失mPop
            mPop.setFocusable(true);
            mPop.setOutsideTouchable(true);
            //设置背景，才能使用动画效果
            mPop.setBackgroundDrawable(new BitmapDrawable());
            //设置动画
            mPop.setAnimationStyle(R.style.PopWindowAnim);
            //设置弹窗消失监听
            mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                    lp.alpha = 1f;
                    getActivity().getWindow().setAttributes(lp);
                }
            });
            ImageView iv_dialog_close = view.findViewById(R.id.iv_dialog_close);
            recyclerView_qg = view.findViewById(R.id.recyclerView_qg);
            recyclerView_qg.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            tv_xz_title=view.findViewById(R.id.tv_xz_title);
           if(type==1){
               tv_xz_title.setText(getResources().getText(R.string.chanpinfenlei));
               initSetdialog();
           }else if(type==2){
               tv_xz_title.setText(getResources().getText(R.string.qiugouleixing2));
               initSetdialog2();
           }else if(type==3){
               tv_xz_title.setText(getResources().getText(R.string.jiaohuoshijian));
               initSetdialog3();
           }
            iv_dialog_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPop.dismiss();
                }
            });

        }
    }
    private void initSetdialog(){
        BaseRVAdapter baseRVAdapter= new BaseRVAdapter(getActivity(),goodsCateBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsCateBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goods_cate=goodsCateBeans.get(position).getCat_name();
                        tv_cpfl.setText(goodsCateBeans.get(position).getCat_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter);

    }
    private void initSetdialog2(){
        BaseRVAdapter baseRVAdapter2= new BaseRVAdapter(getActivity(),goodsTypeBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsTypeBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        release_type=goodsTypeBeans.get(position).getCat_name();
                        tv_qglx.setText(goodsTypeBeans.get(position).getCat_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter2.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter2);
    }
    private void initSetdialog3(){
        BaseRVAdapter baseRVAdapter3= new BaseRVAdapter(getActivity(),goodsTimeBeans) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.fabuqiug_layout;
            }

            @Override
            public void onBind(BaseViewHolder holder, final int position) {
                holder.getTextView(R.id.tv_item).setText(goodsTimeBeans.get(position).getCat_name());
                holder.getTextView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        attach_time=goodsTimeBeans.get(position).getCat_name();
                        tv_jhTime.setText(goodsTimeBeans.get(position).getCat_name());
                        mPop.dismiss();
                    }
                });
            }

        };
        baseRVAdapter3.notifyDataSetChanged();
        recyclerView_qg.setAdapter(baseRVAdapter3);
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
        PermissionsActivity.startActivityForResult(getActivity(), REQUEST_CODEBBB, strPermissions);
    }
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
                        if (imagePaths.size() < 10) {
                            for (int i = 0; i < list.size(); i++) {
                                System.out.println("选中的路径：" + list.get(i));
                                String pic_path = list.get(i);
                                String targetPath = compressImageFilePath + Confirg.df.
                                        format(new Date()) + ".jpg";
                                //调用压缩图片的方法，返回压缩后的图片path
                                final String compressImage = BitnapUtils.compressImage(pic_path, targetPath, 40);
                                Log.i("==f3imgcc==", "onActivityResult: ===compressImage==压缩图片路径==" + compressImage);
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
                        mTaskImgAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }
    private String strImage="";
    final List<String> cartImage = new ArrayList<>();

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
                        Map<String,Object> map=new HashMap<>();
                        map.put("token", BaseApplication.getInstance().userBean.getToken());
                        if(imageDataBean.getResult().getImage_show().size()>=0){
                            map.put("head_pic", imageDataBean.getResult().getImage_show().get(0));
                            cartImage.add(imageDataBean.getResult().getImage_show().get(0));
                        }

//                        String str ="";
//                        if(storeListBeans!=null&&sStringtoreListBeans.size()>0){
//                            for(int shopAll=0;shopAll<storeListBeans.size();shopAll++){
//                                for (int all=0;all<storeListBeans.get(shopAll).getCartList().size();all++){
//                                    if(storeListBeans.get(shopAll).getCartList().get(all).getItemXZ().equals("1")){
//                                        cartId.add(Integer.parseInt(storeListBeans.get(shopAll).getCartList().get(all).getCart_id()));
//                                    }
//                                }
//                            }
//                            Collections.sort(cartId);
//                            for (int i = 0; i < cartId.size(); i++) {
//                                str +=cartId.get(i)+"," ;
//                            }
//                        }
//                        if(!str.equals("")){
//                            String cartid=str.substring(0,str.length()-1);
//                        upXGMessageData(map);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View view) {
        final Integer index = (Integer) view.getTag();
        System.out.println("index:===" + index);
        /**
         这里使用了 android.support.v7.app.AlertDialog.Builder
         可以直接在头部写 import android.support.v7.app.AlertDialog
         那么下面就可以写成 AlertDialog.Builder
         */
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setMessage("是否确认删除？");
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(imagePaths.size()>0){
                    imagePaths.remove(imagePaths.get(index));
                }
                if(compressPaths.size()>0){
                    compressPaths.remove(compressPaths.get(index));
                }
                if(cartImage.size()>0){
                    cartImage.remove(cartImage.get(index));
                }
                mTaskImgAdapter.notifyDataSetChanged();
            }
        });
        if (imagePaths.size() - 1 != index)
            builder.show();

        return true;
    }

    private void upMainQGXXData(){
        Map<String,Object> map=new HashMap<>();
        String yuyan= SharedPreferencesUtils.getString(getActivity(), BaseConstant.SPConstant.language, "");
        if(yuyan!=null){
            map.put("language",yuyan);
        }
        baseActivity.showDialog();
        XUtil.Post(URLConstant.MAIN_QGFBXGXX,map,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                baseActivity.closeDialog();
                Log.i("====求购相关选项===", result);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String res = jsonObject.optString("status");
                    if(res.equals("1")){
                        Gson gson=new Gson();
                        QiuGouXXBean qiuGouXXBean=gson.fromJson(result,QiuGouXXBean.class);
                        goodsCateBeans=qiuGouXXBean.getResult().getGoods_cate();
                        goodsTimeBeans=qiuGouXXBean.getResult().getGoods_time();
                        goodsTypeBeans=qiuGouXXBean.getResult().getGoods_type();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                baseActivity.closeDialog();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                baseActivity.closeDialog();
                ex.printStackTrace();
            }
        });
    }

}
