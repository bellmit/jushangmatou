package com.jsmt.developer.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jsmt.developer.R;


public class ShowPopupWindow {

	public static Dialog unCancleableDialog(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
//		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);

		//x.image().bind(spaceshipImage,url,options);
//		Glide.with(context).load(R.drawable.loading).asGif().into(spaceshipImage);
		// // 加载动画
		// Animation hyperspaceJumpAnimation =
		// AnimationUtils.loadAnimation(context, R.anim.loading_animation);
		// // // 使用ImageView显示动画
		// spaceshipImage.startAnimation(hyperspaceJumpAnimation);

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(true);// 不可以用“返回键”取�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局

		loadingDialog.setCanceledOnTouchOutside(false);
		return loadingDialog;

	}


	public static Dialog mainCancleableDialog(Context context) {

		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
		// main.xml中的ImageView
//		ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);

		//x.image().bind(spaceshipImage,"",options);
		// // 加载动画
		// Animation hyperspaceJumpAnimation =
		// AnimationUtils.loadAnimation(context, R.anim.loading_animation);
		// // // 使用ImageView显示动画
		// spaceshipImage.startAnimation(hyperspaceJumpAnimation);

		Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog

		loadingDialog.setCancelable(true);// 不可以用“返回键”取�?
		loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局

		loadingDialog.setCanceledOnTouchOutside(false);
		return loadingDialog;

	}


}