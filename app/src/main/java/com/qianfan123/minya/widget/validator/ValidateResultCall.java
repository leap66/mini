package com.qianfan123.minya.widget.validator;

/**
 * Created by neil on 2017/3/6.
 */
public interface ValidateResultCall {

  void onSuccess();

  void onFailure(String message);
}
