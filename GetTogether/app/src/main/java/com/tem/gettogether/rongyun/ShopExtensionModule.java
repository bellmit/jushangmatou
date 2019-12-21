package com.tem.gettogether.rongyun;

import android.content.Context;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BaseConstant;
import com.tem.gettogether.utils.SharedPreferencesUtils;

import java.util.List;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imlib.model.Conversation;

/**
 * Created by lt on 2019-04-17.
 */

public class ShopExtensionModule extends DefaultExtensionModule {
    private ShopPlugin myPlugin;
    private TranslationPlugin mTranslationPlugin;
    private SpeechTranslationPlugin mSpeechTranslationPlugin;
    private Context mContext;
    public ShopExtensionModule(Context context){
        mContext = context;
    }

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModuleList =  super.getPluginModules(conversationType);
        myPlugin=new ShopPlugin();
        mTranslationPlugin = new TranslationPlugin();
        mSpeechTranslationPlugin = new SpeechTranslationPlugin();
        if (SharedPreferencesUtils.getString(mContext, BaseConstant.SPConstant.ROLE_TYPE, "1").equals("0")) {
            pluginModuleList.add(myPlugin);
        }
        pluginModuleList.add(mTranslationPlugin);
        pluginModuleList.add(mSpeechTranslationPlugin);
        return pluginModuleList;
    }
    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}
