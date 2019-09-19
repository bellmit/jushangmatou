package com.tem.gettogether.rongyun;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import com.tem.gettogether.R;
import com.tem.gettogether.activity.speechtranslation.SpeechTranslationActivity;
import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.rongyun
 * @ClassName: SpeechTranslationPlugin
 * @Author: csc
 * @CreateDate: 2019/9/19 10:35
 * @Description:
 */
public class SpeechTranslationPlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.drawable.rc_ic_phone_selector);
    }

    @Override
    public String obtainTitle(Context context) {
        return "语音";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        fragment.getActivity().startActivity(new Intent(fragment.getActivity(), SpeechTranslationActivity.class).putExtra("targetId",rongExtension.getTargetId()));
    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
