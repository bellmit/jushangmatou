package com.tem.gettogether.rongyun;

import java.util.List;

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
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModuleList =  super.getPluginModules(conversationType);
        myPlugin=new ShopPlugin();
        mTranslationPlugin = new TranslationPlugin();
        mSpeechTranslationPlugin = new SpeechTranslationPlugin();
        pluginModuleList.add(myPlugin);
        pluginModuleList.add(mTranslationPlugin);
        pluginModuleList.add(mSpeechTranslationPlugin);
        return pluginModuleList;
    }
    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}
