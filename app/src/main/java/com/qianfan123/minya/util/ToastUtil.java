package com.qianfan123.minya.util;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.qianfan123.minya.R;
import com.qianfan123.minya.databinding.ItemToastBinding;

/**
 * Created by dzq on 2016/10/19.
 */

public class ToastUtil {

  public static void toastHint(Context context, int stringResId) {
    String s = context.getResources().getString(stringResId);
    toastHint(context, s);
  }

  public static void toastHint(Context context, String text) {
    Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER, 0, 0);
    toast.show();
  }

  public static void toastFailure(Context context, int stringResId) {
    String text = context.getResources().getString(stringResId);
    toast(context, text, R.mipmap.ic_toast_warning);
  }

  public static void toastFailure(Context context, String text) {
    toast(context, text, R.mipmap.ic_toast_warning);
  }

  public static void toastSuccess(Context context, String text) {
    toast(context, text, R.mipmap.ic_toast_success);
  }

  public static void toastSuccess(Context context, int stringResId) {
    String text = context.getResources().getString(stringResId);
    toast(context, text, R.mipmap.ic_toast_success);
  }

  private static void toast(Context context, String text, int imageResource) {
    ItemToastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
        R.layout.item_toast, null, false);
    Toast toast = new Toast(context);
    toast.setDuration(Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.CENTER, 0, 0);
    binding.imageIv.setImageResource(imageResource);
    binding.titleTv.setText(text);
    toast.setView(binding.getRoot());
    toast.show();
  }
}
