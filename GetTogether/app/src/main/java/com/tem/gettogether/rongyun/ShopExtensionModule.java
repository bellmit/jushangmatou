package com.tem.gettogether.rongyun;

import java.util.List;

import io.rong.common.RLog;
import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.manager.InternalModuleManager;
import io.rong.imkit.plugin.CombineLocationPlugin;
import io.rong.imkit.plugin.DefaultLocationPlugin;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.model.Conversation;

/**
 * Created by lt on 2019-04-17.
 */

public class ShopExtensionModule extends DefaultExtensionModule {
    private ShopPlugin myPlugin;

    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModuleList =  super.getPluginModules(conversationType);
        myPlugin=new ShopPlugin();
        pluginModuleList.add(myPlugin);

        return pluginModuleList;
    }
    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        return super.getEmoticonTabs();
    }
}
