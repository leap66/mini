package com.qianfan123.minya.network.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by fuhuaxuan on 2016/11/18.
 */

public class UpdateModel implements Serializable {

  private static final long serialVersionUID = 2965065498031595999L;
  public boolean upgradable;
  public String updateMode;
  public String description;
  public String versionName;
  public BigDecimal versionCode;
  public String downloadUrl;
  public String md5;

  public boolean already;// 是否准备好
  public long position;// 下载位置
}