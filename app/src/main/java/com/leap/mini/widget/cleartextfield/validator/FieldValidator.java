package com.leap.mini.widget.cleartextfield.validator;

/**
 * Created by NeilSpears on 2016/11/5.
 */

public interface FieldValidator<T> {

  boolean isValid(T editText);

  String getError();

}
