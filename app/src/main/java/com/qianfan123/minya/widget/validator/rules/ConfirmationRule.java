package com.qianfan123.minya.widget.validator.rules;

import android.widget.TextView;

/**
 * Created by neil on 2017/3/6.
 */

public class ConfirmationRule implements Rule {
  private String message;
  private TextView field;

  public ConfirmationRule(TextView field, String message) {
    this.field = field;
    this.message = message;
  }

  @Override
  public boolean validate(String value) {
    return field.getText().equals(value);
  }

  @Override
  public String getErrorMessage() {
    return message;
  }
}
