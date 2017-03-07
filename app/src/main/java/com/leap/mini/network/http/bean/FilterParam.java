package com.leap.mini.network.http.bean;

/**
 * Created by NeilSpears on 2016/10/20.
 */

public class FilterParam {
  private String property;
  private Object value;

  public FilterParam() {
  }

  public FilterParam(String property, Object value) {
    this.property = property;
    this.value = value;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String toString() {
    return property + value.toString();
  }
}
