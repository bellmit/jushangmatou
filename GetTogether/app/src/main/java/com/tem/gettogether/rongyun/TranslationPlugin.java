package com.tem.gettogether.rongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.tem.gettogether.R;
import com.tem.gettogether.activity.translation.TranslationActivity;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.rongyun
 * @ClassName: TranslationPlugin
 * @Author: csc
 * @CreateDate: 2019/9/18 9:49
 * @Description:
 */
public class TranslationPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.translation_icon);
    }

    @Override
    public String obtainTitle(Context context) {
        return context.getString(R.string.fanyi);
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        Log.d("chenshichun","=====翻译======");
        fragment.getActivity().startActivity(new Intent(fragment.getActivity(), TranslationActivity.class).putExtra("targetId",rongExtension.getTargetId()));
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
