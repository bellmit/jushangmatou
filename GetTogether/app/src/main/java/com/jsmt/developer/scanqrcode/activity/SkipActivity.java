package com.jsmt.developer.scanqrcode.activity;

import android.text.TextUtils;
import android.widget.TextView;

import com.jsmt.developer.R;
import com.jsmt.developer.base.BaseActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_skip)

public class SkipActivity extends BaseActivity {
    @ViewInject(R.id.result)
    TextView mTextView;

//    private final static int SCANNIN_GREQUEST_CODE = 1;




    @Override
    protected void initData() {
        x.view().inject(this);
        String result = getIntent().getStringExtra("result");
        if(!TextUtils.isEmpty(result)){
            mTextView.setText(result);
        }

    }

    @Override
    protected void initView() {

    }


    /**
     * 显示扫描拍的图片
     */
//    private ImageView mImageView;

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_skip);
//
//        mTextView = (TextView) findViewById(R.id.result);
//        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
//
//        点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
//        扫描完了之后调到该界面
//        Button mButton = (Button) findViewById(R.id.button1);
//        mButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(SkipActivity.this, MipcaActivityCapture.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
//            }
//        });
//    }


    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case SCANNIN_GREQUEST_CODE:
//                if(resultCode == RESULT_OK){
//                    Bundle bundle = data.getExtras();
//                    //显示扫描到的内容
//                    mTextView.setText(bundle.getString("result"));
//                    //显示
////                    mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
//                }
//                break;
//        }
//    }

}
