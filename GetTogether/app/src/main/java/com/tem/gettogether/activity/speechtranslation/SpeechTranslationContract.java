package com.tem.gettogether.activity.speechtranslation;

import com.tem.gettogether.base.BaseView;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.speechtranslation
 * @ClassName: SpeechTranslationContract
 * @Author: csc
 * @CreateDate: 2019/9/19 14:42
 * @Description:
 */
public class SpeechTranslationContract {


    interface SpeechTranslationView extends BaseView {
        void getTranslationFromResult(String txt);
        void getTranslationToResult(String txt);
    }

    interface Presenter {
        void startRecord();
        void stopRecord(String fromType,String toType);
        void goToTranslation(String toType,String fromType,String txt);
        void goSendTranslationMsg(String fromMsg,String toMsg,String targetId);
    }

}
