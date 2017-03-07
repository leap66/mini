package com.leap.mini.listener;

/**
 * Created by chailin on 2016/11/11.
 */
public interface OnConfirmListener<V, T> {
  void onConfirm(V view, T data);
}
