package com.tem.gettogether.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bugtags.library.Bugtags;
import com.tem.gettogether.R;
import com.tem.gettogether.dialog.CommonDialogLayout;
import com.tem.gettogether.dialog.ShowAlertDialog;
import com.tem.gettogether.utils.AppManager;
import com.tem.gettogether.utils.SharedPreferencesUtils;
import com.tem.gettogether.utils.language.LanguageUtil;
import com.ybm.app.common.WindowToast.ToastTips;

import org.xutils.x;

import java.util.Locale;

import io.rong.imlib.RongIMClient;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Created by litao
 * 注释:base activity_distributor_rz controller
 * Created by 2019/1/11.
 */

public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {
    public Context mContext;
    public boolean isFullScreen = false;

//    public abstract int setLayoutId();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        x.view().inject(this);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.white));
        if (isFullScreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mContext = this;
        initAppLanguage();
        initData();
        initView();
    }

    public BaseActivity getContext() {
        return this;
    }

    public void showProgress() {
        showProgress("加载中", true, true);
    }

    public void showProgress(String msg) {
        showProgress(msg, true, true);
    }

    private AlertDialog dialog;
    private CommonDialogLayout dialogView;
    private Animatable loadingDrawable;

    //取消对话框
    public void dismissProgress() {
        if (dialog != null) {
            try {
                dialog.dismiss();
                if (loadingDrawable != null) {
                    loadingDrawable.stop();
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                dialog = null;
            }
        }
    }

    /**
     * 语言切换
     *
     * @param newBase
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.attachBaseContext(newBase));
    }

    protected Dialog dialog2;

    public void showDialog() {

        if (null == dialog2) {
            dialog2 = ShowAlertDialog.loadingDialog(this);
        }
        dialog2.show();

    }

    public void closeDialog() {
        if (null != dialog2) {
            dialog2.dismiss();
        }
        //    hideProgress();

    }

    public static void log(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }

    //显示加载对话框
    public void showProgress(String msg, boolean showMsg, final boolean cancelable) {

        if (dialog == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
            builder.setCancelable(false);
            dialogView = new CommonDialogLayout(this);
            ImageView iv = dialogView.getImageView();
            if (iv == null) {
                iv = new ImageView(this);
            }
            iv.setImageResource(R.drawable.loading);
            loadingDrawable = (AnimationDrawable) iv.getDrawable();
            dialog = builder.create();
            if (dialog != null) {
                dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_APP_SWITCH || keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_MENU || keyCode == KeyEvent.KEYCODE_BACKSLASH) {
                            return !cancelable;
                        }
                        return false;
                    }
                });
            }
        } else {
            if (dialog.isShowing() || this.isFinishing() || dialog.getOwnerActivity() == null || dialog.getOwnerActivity() != this) {
                try {
                    dialog.dismiss();
                    if (loadingDrawable != null) {
                        loadingDrawable.stop();
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    dialog = null;
                    showProgress(msg, showMsg, cancelable);
                }
            }
        }
        if (TextUtils.isEmpty(msg)) {
            showMsg = false;
        }
//        showMsg = false;//强制不显示文字
        dialogView.getTextView().setVisibility(showMsg ? View.VISIBLE : View.GONE);
        if (showMsg) {
            dialogView.setMsg(msg);
        }

        dialog.setCanceledOnTouchOutside(cancelable);
        try {
            dialog.show();
            loadingDrawable.start();
            dialog.getWindow().setContentView(dialogView);
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            params.width = dp2px(this, 100);
            params.height = dp2px(this, 100);
            params.dimAmount = 0.01f;
            dialog.getWindow().setAttributes(params);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            dialog = null;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 跳转activity
     *
     * @param clazz
     */
    public void gotoAtivity(Class clazz) {
        gotoAtivity(clazz, null);
    }

    /**
     * 跳转activity
     *
     * @param clazz
     * @param bundle
     */
    public void gotoAtivity(Class clazz, Bundle bundle) {
        Intent it = new Intent(this, clazz);
        if (bundle != null) {
            it.putExtra("bundle", bundle);
            it.putExtras(bundle);
        }
        startActivity(it);
    }

    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) (mContext)).getWindowManager().getDefaultDisplay().getMetrics(dm);//display = getWindowManager().getDefaultDisplay();display.getMetrics(dm)（把屏幕尺寸信息赋值给DisplayMetrics dm）;
        int height = dm.heightPixels;
        return height;
    }

    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) (mContext)).getWindowManager().getDefaultDisplay().getMetrics(dm);//display = getWindowManager().getDefaultDisplay();display.getMetrics(dm)（把屏幕尺寸信息赋值给DisplayMetrics dm）;
        int width = dm.widthPixels;
        return width;
    }

    protected abstract void initData();

    protected abstract void initView();


    @Override
    protected void onStart() {
        super.onStart();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        displayMetrics.scaledDensity = displayMetrics.density;
    }

    @Override
    protected void onPause() {
        Bugtags.onPause(this);
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.unbind(this);

        dismissProgress();

    }

    @Override
    public void finish() {
        dismissProgress();
        super.finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        /**
         * 设置纵向
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        Bugtags.onResume(this);
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onClick(View v) {


    }

    /**
     * 跳转到其他页面
     */
    public void jumpActivity(Class<? extends Activity> activity, Bundle bundle, boolean isfinish) {

        Intent intent = new Intent();
        intent.setClass(mContext, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (isfinish) {
            this.finish();
        }
        startActivity(intent);


    }


    /**
     * 通过requestCode跳转到指定的页面
     *
     * @param activity
     * @param bundle
     * @param requestCode
     */
    public void jumpActivityForResult(Class<? extends Activity> activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mContext, activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        startActivityForResult(intent, requestCode);
    }

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * 点击空白位置 隐藏软键盘
     */
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            this.finish();
        }
        return true;
    }

    public void initAppLanguage() {
        if (SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "").equals("en")) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            Configuration config = getResources().getConfiguration();
            // 应用用户选择语言
            config.locale = Locale.ENGLISH;
            getResources().updateConfiguration(config, dm);
        } else if (SharedPreferencesUtils.getString(this, BaseConstant.SPConstant.language, "").equals("ara")) {
            DisplayMetrics dm = getResources().getDisplayMetrics();
            Configuration config = getResources().getConfiguration();
            // 应用用户选择语言
            config.locale = new Locale("ar");
            getResources().updateConfiguration(config, dm);
        }else{
            DisplayMetrics dm = getResources().getDisplayMetrics();
            Configuration config = getResources().getConfiguration();
            // 应用用户选择语言
            config.locale = Locale.CHINESE;
            getResources().updateConfiguration(config, dm);
        }
    }

}
