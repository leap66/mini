package com.leap.mini.listener;

/**
 * Created by NeilSpears on 2016/11/3.
 */

public interface OnChangedListener<T> {
  void onChange(T oldValue, T newValue);
}
