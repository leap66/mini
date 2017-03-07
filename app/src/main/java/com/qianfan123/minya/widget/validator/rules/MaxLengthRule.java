package com.qianfan123.minya.widget.validator.rules;

/**
 * Created by neil on 2017/3/6.
 */

public class MaxLengthRule implements Rule {
  private String message;
  private int length;

  public MaxLengthRule(int length, String message) {
    this.length = length;
    this.message = message;
  }

  @Override
  public boolean validate(String value) {
    if (value == null || value.length() == 0) {
      return false;
    }
    return value.length() <= length;
  }

  @Override
  public String getErrorMessage() {
    return message;
  }
}
