package com.qianfan123.minya.widget.validator.rules;

/**
 * Created by neil on 2017/3/6.
 */

public class PhoneNumberRule extends RegexRule {

  public PhoneNumberRule(String message) {
    super("^\\d{11}$", message);
  }
}
