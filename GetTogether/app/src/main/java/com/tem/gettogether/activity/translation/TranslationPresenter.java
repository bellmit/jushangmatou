package com.tem.gettogether.activity.translation;

import android.app.Activity;
import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.tem.gettogether.R;
import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.rongyun.CustomizeTranslationMessage;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;

import java.util.List;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.translation
 * @ClassName: TranslationPresenter
 * @Author: csc
 * @CreateDate: 2019/9/18 10:24
 * @Description:
 */
public class TranslationPresenter extends BasePresenter<TranslationContract.TranslationView> implements TranslationContract.Presenter{
    private TranslationModel model;
    private Context mContext;
    private Activity mActivity;

    public TranslationPresenter(Context context, Activity mActivity) {
        model = new TranslationModel();
        this.mContext = context;
        this.mActivity = mActivity;
    }

    /*
    * 翻译
    * */
    @Override
    public void goToTranslation(String fromType, String toType, String txt) {
        Language langFrom = LanguageUtils.getLangByName(fromType);
        Language langTo = LanguageUtils.getLangByName(toType);
        TranslateParameters tps = new TranslateParameters.Builder()
                .source("jushangmatou")
                .from(langFrom)
                .to(langTo)
                .build();
        Translator.getInstance(tps).lookup(txt, "requestId", new TranslateListener() {
            @Override
            public void onError(TranslateErrorCode translateErrorCode, String s) {

            }

            @Override
            public void onResult(Translate translate, String s, String s1) {
                if (translate.getTranslations() != null) {
                    mView.getTranslationResult(translate.getTranslations().get(0));
                }
            }

            @Override
            public void onResult(List<Translate> list, List<String> list1, List<TranslateErrorCode> list2, String s) {

            }

        });
    }


    /*
    * 发送
    * */
    @Override
    public void goSendTranslationMsg(String fromMsg,String toMsg,String targetId) {
        CustomizeTranslationMessage customizeMessage = new CustomizeTranslationMessage(fromMsg, toMsg);
        byte[] bvvv = customizeMessage.encode();
        CustomizeTranslationMessage richContentMessage = new CustomizeTranslationMessage(bvvv);
        io.rong.imlib.model.Message myMessage = io.rong.imlib.model.Message.obtain(targetId, Conversation.ConversationType.PRIVATE, richContentMessage);
        RongIM.getInstance().sendMessage(myMessage, null, null, new IRongCallback.ISendMessageCallback() {
            @Override
            public void onAttached(io.rong.imlib.model.Message message) {
                //消息本地数据库存储成功的回调
            }

            @Override
            public void onSuccess(io.rong.imlib.model.Message message) {
                Log.d("chenshichun", "======发送成功=====");

                //消息通过网络发送成功的回调
                CusToast.showToast(mContext.getText(R.string.message_successed));
                mActivity.finish();
            }

            @Override
            public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {
                //消息发送失败的回调
                Log.d("chenshichun", "======消息发送失败=====");
                CusToast.showToast(mContext.getText(R.string.message_failed));

            }
        });
    }

}
