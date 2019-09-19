package com.tem.gettogether.activity.translation;

import com.tem.gettogether.base.BaseView;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.translation
 * @ClassName: TranslationContract
 * @Author: csc
 * @CreateDate: 2019/9/18 10:23
 * @Description:
 */
public class TranslationContract {

    interface TranslationView extends BaseView {
        void getTranslationResult(String txt);
    }

    interface Presenter {
        void goToTranslation(String fromType, String toType, String txt);

        void goSendTranslationMsg(String fromMsg, String toMsg, String targetId);
    }
}
