package com.qianfan123.minya.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by NeilSpears on 2016/11/14.
 */

public abstract class TextChangedListener implements TextWatcher {

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public abstract void onTextChanged(CharSequence s, int start, int before, int count);

  @Override
  public void afterTextChanged(Editable s) {
  }
}
