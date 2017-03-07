package com.qianfan123.minya.network.http.bean;

/**
 * Created by NeilSpears on 2016/10/20.
 */

public class SortParam {
  private String property;
  private String direction;

  public SortParam() {

  }

  public SortParam(String property, String direction) {
    this.property = property;
    this.direction = direction;
  }

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}
