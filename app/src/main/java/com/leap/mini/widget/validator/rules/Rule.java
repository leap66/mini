package com.leap.mini.widget.validator.rules;

/**
 * Created by neil on 2017/3/6.
 */

public interface Rule {

  boolean validate(String value);

  String getErrorMessage();
}
