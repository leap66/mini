package com.leap.mini.network.http.update;

public interface IUpdateListener {
  public static final int UPDATE_CODE_NEWEST = 0x1;
  public static final int UPDATE_CODE_FAIL = 0x2;
  public static final int UPDATE_CODE_IGNORE = 0x3;

  void onCancel(int type, String... args);
}
