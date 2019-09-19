package com.tem.gettogether.activity.speechtranslation;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tem.gettogether.base.BasePresenter;
import com.tem.gettogether.rongyun.CustomizeTranslationMessage;
import com.tem.gettogether.utils.AuditRecorderConfiguration;
import com.tem.gettogether.utils.ExtAudioRecorder;
import com.tem.gettogether.utils.FileUtils;
import com.youdao.sdk.app.EncryptHelper;
import com.youdao.sdk.app.Language;
import com.youdao.sdk.app.LanguageUtils;
import com.youdao.sdk.common.Constants;
import com.youdao.sdk.ydonlinetranslate.SpeechTranslate;
import com.youdao.sdk.ydonlinetranslate.SpeechTranslateParameters;
import com.youdao.sdk.ydonlinetranslate.Translator;
import com.youdao.sdk.ydtranslate.Translate;
import com.youdao.sdk.ydtranslate.TranslateErrorCode;
import com.youdao.sdk.ydtranslate.TranslateListener;
import com.youdao.sdk.ydtranslate.TranslateParameters;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cc.duduhuo.custoast.CusToast;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * @ProjectName: GetTogether
 * @Package: com.tem.gettogether.activity.speechtranslation
 * @ClassName: SpeechTranslationPresenter
 * @Author: csc
 * @CreateDate: 2019/9/19 14:42
 * @Description:
 */
public class SpeechTranslationPresenter extends BasePresenter<SpeechTranslationContract.SpeechTranslationView> implements SpeechTranslationContract.Presenter {
    private Context mContext;
    private Activity mActivity;
    private File audioFile;
    Handler handler = new Handler();
    ExtAudioRecorder recorder;
    SpeechTranslateParameters tps;

    public SpeechTranslationPresenter(Context context, Activity mActivity) {
        this.mContext = context;
        this.mActivity = mActivity;
    }

    @Override
    public void startRecord() {
        try {
            audioFile = File.createTempFile("record_", ".wav");
            AuditRecorderConfiguration configuration = new AuditRecorderConfiguration.Builder()
                    .recorderListener(listener)
                    .handler(handler)
                    .rate(Constants.RATE_16000)
                    .uncompressed(true)
                    .builder();

            recorder = new ExtAudioRecorder(configuration);
            recorder.setOutputFile(audioFile.getAbsolutePath());
            recorder.prepare();
            recorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stopRecord(final String fromType, final String toType) {
        try {
            int time = recorder.stop();
            if (time > 0) {
                if (audioFile != null) {// 后面做翻译处理
                    Log.d("chenshichun", "=====audioFile======" + audioFile.getAbsolutePath());
                    recognize(audioFile.getAbsolutePath(),fromType,toType);
                }
            }
            recorder.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void goSendTranslationMsg(String fromMsg, String toMsg, String targetId) {
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
                CusToast.showToast("发送成功");
                mActivity.finish();
            }

            @Override
            public void onError(io.rong.imlib.model.Message message, RongIMClient.ErrorCode errorCode) {
                //消息发送失败的回调
                Log.d("chenshichun", "======消息发送失败=====");
            }
        });
    }

    /*
     * 语音识别
     * */
    public void recognize(final String path,final String fromType, final String toType) {
        if (TextUtils.isEmpty(path)) {
            Toast.makeText(mContext, "请录音或选择音频文件", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        try {
//            resultText.setText("正在识别，请稍等....");
            mView.showLoading();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    startRecognize(path,fromType,toType);
                }
            }).start();
        } catch (Exception e) {
        }
    }


    private void startRecognize(String filePath, final String fromType, final String toType) {
        byte[] datas = null;
        try {
            datas = FileUtils.getContent(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String bases64 = EncryptHelper.getBase64(datas);


        Language langFrom = LanguageUtils.getLangByName(fromType);

        Language langTo = LanguageUtils.getLangByName(toType);

        //输入和输出音频格式都为wav格式
        tps = new SpeechTranslateParameters.Builder().source("youdaovoicetranslate")
                .from(langFrom).to(langTo)
                .rate(Constants.RATE_16000)//输入音频码率，支持8000,16000
                .voice(Constants.VOICE_NEW)//输出声音，支持美式女生、美式男生、英式女生、英式男生
                .timeout(100000)//超时时间
                .build();

        SpeechTranslate.getInstance(tps).lookup(bases64, "requestId",
                new TranslateListener() {
                    @Override
                    public void onResult(final Translate result, String input, String requestId) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                goToTranslation(fromType, toType, result.getQuery());
                                mView.getTranslationFromResult(result.getQuery());
                                Log.d("chenshichun", "===========" + result.getQuery());
                            }
                        });
                    }

                    @Override
                    public void onError(final TranslateErrorCode error, String requestId) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("chenshichun", "===========" + "翻译失败" + error.toString());
                            }
                        });
                    }

                    @Override
                    public void onResult(List<Translate> results, List<String> inputs, List<TranslateErrorCode> errors, String requestId) {

                    }
                });


    }

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
                    mView.getTranslationToResult(translate.getTranslations().get(0));
                }
            }

            @Override
            public void onResult(List<Translate> list, List<String> list1, List<TranslateErrorCode> list2, String s) {

            }

        });
    }


    /**
     * 录音失败的提示
     */
    ExtAudioRecorder.RecorderListener listener = new ExtAudioRecorder.RecorderListener() {
        @Override
        public void recordFailed(int failRecorder) {
            if (failRecorder == 0) {
                Toast.makeText(mContext, "录音失败，可能是没有给权限", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "发生了未知错误", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
