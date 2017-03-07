package com.leap.mini.widget.validator.rules;

/**
 * Created by neil on 2017/3/6.
 */

public class RequiredRule implements Rule {
  private String message;

  public RequiredRule(String message) {
    this.message = message;
  }

  @Override
  public boolean validate(String value) {
    return value != null && !value.isEmpty();
  }

  @Override
  public String getErrorMessage() {
    return message;
  }
}
