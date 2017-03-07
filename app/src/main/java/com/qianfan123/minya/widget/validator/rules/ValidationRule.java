package com.qianfan123.minya.widget.validator.rules;

import java.util.ArrayList;
import java.util.List;

import com.qianfan123.minya.widget.validator.ValidationError;

import android.widget.TextView;

/**
 * Created by neil on 2017/3/6.
 */

public class ValidationRule {
  private TextView field;
  private List<Rule> rules = new ArrayList<Rule>();

  public ValidationRule(TextView field, List<Rule> rules) {
    this.field = field;
    this.rules = rules;
  }

  public ValidationError validateField() {
    for (Rule rule : rules) {
      boolean validate = rule.validate(String.valueOf(field.getText()));
      if (!validate) {
        return new ValidationError(field, rule.getErrorMessage());
      }
    }
    return null;
  }
}
