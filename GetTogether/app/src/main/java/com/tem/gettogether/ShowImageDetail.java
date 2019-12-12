package com.tem.gettogether;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.transition.Transition;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tem.gettogether.base.BaseActivity;
import com.tem.gettogether.utils.BitmapUtil;
import com.tem.gettogether.view.PhotoView;
import com.ybm.app.common.WindowToast.ToastTips;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;
import io.rong.imageloader.core.ImageLoader;


public class ShowImageDetail extends BaseActivity {

    private ViewPager mViewPager;
    private ImageDetailAdapter mPagerAdapter;
    private List<String> mImageInfos;
    private int index;
    private String isImageCanScale;
    public static final int DURATION = 300;
    private static final AccelerateDecelerateInterpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final String SCALE_WIDTH = "SCALE_WIDTH";
    private static final String SCALE_HEIGHT = "SCALE_HEIGHT";
    private static final String TRANSITION_X = "TRANSITION_X";
    private static final String TRANSITION_Y = "TRANSITION_Y";
    private ImageView mImageView;
    private FrameLayout mContainer;
//    private Palette mImagePalette;
    /**
     * 存储图片缩放比例和位移距离
     */
    private Bundle mScaleBundle = new Bundle();
    private Bundle mTransitionBundle = new Bundle();

    /**
     * 屏幕宽度和高度
     */
    private int mScreenWidth;
    private int mScreenHeight;

    /**
     * 图片资源 ID
     */
    private int mRescourceId;

    /**
     * 上一个界面图片的宽度和高度
     */
    private int mOriginWidth;
    private int mOriginHeight;

    /**
     * 上一个界面图片的位置信息
     */
    private Rect mRect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image_detail);

        index = getIntent().getIntExtra("index", -1);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mImageInfos = getIntent().getStringArrayListExtra("paths");
        isImageCanScale = getIntent().getStringExtra("isImageCanScale");

        System.out.println("mImageInfos:==" + mImageInfos.size());

        mPagerAdapter = new ImageDetailAdapter(this, mImageInfos);
        mViewPager.setAdapter(mPagerAdapter);
        if (index != -1) {
            mViewPager.setCurrentItem(index);
        }
        mViewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }


    private class ImageDetailAdapter extends PagerAdapter {

        private List<String> mData = new ArrayList<>();
        private Context mContext;


        public ImageDetailAdapter(Context context, List<String> data) {
            this.mContext = context;
            this.mData = data;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            final PhotoView view = new PhotoView(ShowImageDetail.this);

            if ("false".equals(isImageCanScale)) {

            } else {
                view.enable();
            }

            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            final String url;
            if (mData.get(position).contains("http")) {
                ImageLoader.getInstance().displayImage(mData.get(position), view);
                url = mData.get(position);
            } else {
                ImageLoader.getInstance().displayImage("file://" + mData.get(position), view);
                url = "file://" + mData.get(position);
            }
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowImageDetail.this.finish();
//                    runExitAnim(view);

                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setIcon(R.mipmap.ic_launcher).setTitle(R.string.save_picture)
                            .setMessage(R.string.save_the_picture).setPositiveButton(R.string.queding, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Glide.with(getContext()).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            saveImage(resource);
                                        }
                                    });
                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton(R.string.quxiao, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    builder.create().show();
                    return false;
                }
            });
            container.addView(view);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public String getItem(int position) {
            return mData.get(position);
        }
    }


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {

        }
    };


    private void saveImage(Bitmap image) {
        String saveImagePath = null;
        Random random = new Random();
        String imageFileName = "JPEG_" + "down" + random.nextInt(10) + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES) + "test");

        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            saveImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fout = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(saveImagePath);
            Toast.makeText(mContext, "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
//        return saveImagePath;
    }

    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

    /**
     * 初始化场景
     */
    private void initial() {
        // 获取上一个界面传入的信息
        mRect = getIntent().getSourceBounds();
//        mRescourceId = getIntent().getExtras().getInt(EXTRA_IMAGE);

        // 获取上一个界面中，图片的宽度和高度
        mOriginWidth = mRect.right - mRect.left;
        mOriginHeight = mRect.bottom - mRect.top;

        // 设置 ImageView 的位置，使其和上一个界面中图片的位置重合
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mOriginWidth, mOriginHeight);
        params.setMargins(mRect.left, mRect.top - getStatusBarHeight(), mRect.right, mRect.bottom);
        mImageView.setLayoutParams(params);

        // 设置 ImageView 的图片和缩放类型
        mImageView.setImageResource(mRescourceId);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        // 根据上一个界面传入的图片资源 ID，获取图片的 Bitmap 对象。
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(mRescourceId);
        Bitmap bitmap = bitmapDrawable.getBitmap();

        // 计算图片缩放比例和位移距离
        getBundleInfo(bitmap);

//        // 创建一个 Pallette 对象
//        mImagePalette = Palette.from(bitmap).generate();
//        // 使用 Palette 设置背景颜色
//        mContainer.setBackgroundColor( mImagePalette.getVibrantColor(ContextCompat.getColor(this, android.R.color.black)));
    }

    /**
     * 计算图片缩放比例，以及位移距离
     *
     * @param bitmap
     */
    private void getBundleInfo(Bitmap bitmap) {
        // 计算图片缩放比例，并存储在 bundle 中
        if (bitmap.getWidth() >= bitmap.getHeight()) {
            mScaleBundle.putFloat(SCALE_WIDTH, (float) mScreenWidth / mOriginWidth);
            mScaleBundle.putFloat(SCALE_HEIGHT, (float) bitmap.getHeight() / mOriginHeight);
        } else {
            mScaleBundle.putFloat(SCALE_WIDTH, (float) bitmap.getWidth() / mOriginWidth);
            mScaleBundle.putFloat(SCALE_HEIGHT, (float) mScreenHeight / mOriginHeight);
        }
        // 计算位移距离，并将数据存储到 bundle 中
        mTransitionBundle.putFloat(TRANSITION_X, mScreenWidth / 2 - (mRect.left + (mRect.right - mRect.left) / 2));
        mTransitionBundle.putFloat(TRANSITION_Y, mScreenHeight / 2 - (mRect.top + (mRect.bottom - mRect.top) / 2));
    }

    /**
     * 模拟退场动画
     */
    private void runExitAnim(View mImageView) {
        mImageView.animate()
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setDuration(DURATION)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y;
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return -1;
    }

}
