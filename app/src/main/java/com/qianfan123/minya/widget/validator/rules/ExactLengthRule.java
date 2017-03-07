package com.qianfan123.minya.widget.validator.rules;

/**
 * Created by neil on 2017/3/6.
 */

public class ExactLengthRule implements Rule {
  private String message = "Must be at most 16 characters long";
  private int length;

  public ExactLengthRule(int length, String message) {
    this.length = length;
    this.message = message;
  }

  @Override
  public boolean validate(String value) {
    if (value == null && length == 0) {
      return true;
    } else if (value != null) {
      return value.length() == length;
    } else {
      return false;
    }

  }

  @Override
  public String getErrorMessage() {
    return message;
  }
}
