package com.leap.mini.cmp;

import android.content.Context;

/**
 * Created by fhx on 16/11/10. 封装Session
 */
public class SessionMgr {
  // key
  private static String KEY_SESSION = "session";
  private static String KEY_LAST_MODIFIED = "session";
  private static String KEY_SHOP_DONAIN = "session";
  private static int UPDATE_PERIOD = 1;// 更新周期

  private static String lastModified;// 上次更新时间(时间撮)
  private static Context mContext;

  public static void init(Context context) {
    mContext = context;
    SessionMgr.lastModified = StorageMgr.get(KEY_LAST_MODIFIED, StorageMgr.LEVEL_GLOBAL);
  }

  // 清除
  public static void clear() {
    StorageMgr.set(KEY_SESSION, null, StorageMgr.LEVEL_GLOBAL);
    StorageMgr.set(KEY_SHOP_DONAIN, null, StorageMgr.LEVEL_GLOBAL);
  }

  // 更新用户（登录时）
  public static void updateUser() {
  }

  // 更新Session
  public static void updateSession() {

  }

  public static void updateLastModified() {
    SessionMgr.lastModified = String.valueOf(System.currentTimeMillis());
    StorageMgr.set(KEY_LAST_MODIFIED, SessionMgr.lastModified, StorageMgr.LEVEL_GLOBAL);
  }

}
