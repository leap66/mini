package com.leap.mini.cmp;

import android.content.Context;
import android.content.SharedPreferences;

import com.leap.mini.util.GsonUtil;

public class StorageMgr {
  private static SharedPreferences storage;
  public static String LEVEL_USER = "user";// 用户级别（必需登录后使用）
  public static String LEVEL_GLOBAL = "global";// 全局级别

  public static void init(Context context) {
    storage = context.getSharedPreferences("qianfan123", Context.MODE_PRIVATE);
  }

  // 设置缓存信息
  public static void setStorage(String key, String value) {
    SharedPreferences.Editor editor = storage.edit();
    editor.putString(key, value);
    editor.apply(); // 先提交内存，再异步提交硬盘
    // editor.commit(); //同步提交内存及硬盘
  }

  /**
   * 获取对应key的缓存
   *
   * @param key
   *          键值
   * @param t
   *          缓存的类
   * @return
   * @throws Exception
   */
  public static <T> void set(String key, T t) throws RuntimeException {
    set(key, GsonUtil.toJson(t), StorageMgr.LEVEL_USER);
  }

  /**
   * 获取对应key的缓存
   *
   * @param key
   *          键值
   * @param t
   *          缓存的类
   * @param level
   *          缓存级别(用户，全局）
   * @return
   * @throws Exception
   */
  public static <T> void set(String key, T t, String level) throws RuntimeException {
    set(key, GsonUtil.toJson(t), level);
  }

  /**
   * 设置key对应缓存
   *
   * @param key
   *          键值
   * @param value
   *          字符串
   * @return
   * @throws Exception
   */
  public static void set(String key, String value) throws RuntimeException {
    set(key, value, StorageMgr.LEVEL_USER);
  }

  /**
   * 设置key对应缓存
   *
   * @param key
   *          键值
   * @param value
   *          字符串
   * @param level
   *          缓存级别(用户，全局）
   * @return
   * @throws Exception
   */
  public static void set(String key, String value, String level) throws RuntimeException {
//    BSessionUser user = SessionMgr.getUser();
//    BSessionShop shop = SessionMgr.getShop();
//    String k = "";
//    if (level.equals(StorageMgr.LEVEL_USER) || level.equals(StorageMgr.LEVEL_SHOP)) {
//      if (!IsEmpty.object(user)) {
//        k += user.getId();
//      } else {
//        throw new NullPointerException("用户为空");
//      }
//      k += "_";
//    }
//    if (level.equals(StorageMgr.LEVEL_SHOP)) {
//      if (!IsEmpty.object(shop)) {
//        k += shop.getId();
//      } else {
//        throw new NullPointerException("门店为空");
//      }
//      k += "_";
//    }
//    k += key;
//    setStorage(k, value);
  }

  // 获取缓存信息
  public static String getStorage(String key) {
    String value="";
    try {
      value=storage.getString(key, null);
    }catch (NullPointerException e){
//      throw e;
    }
    return value;
  }

  /**
   * 获取对应key的缓存
   *
   * @param key
   *          键值
   * @param c
   *          需要序列化的类
   * @return
   * @throws Exception
   */
  public static <T> T get(String key, Class<T> c) {
    String value = get(key, StorageMgr.LEVEL_USER);
    if (value == null) {
      return null;
    }
    return GsonUtil.parse(value, c);
  }

  /**
   * 获取对应key的缓存
   *
   * @param key
   *          键值
   * @param c
   *          需要序列化的类
   * @param level
   *          缓存级别(用户，门店，全局）
   * @return
   * @throws Exception
   */
  public static <T> T get(String key, Class<T> c, String level) {
    String value = get(key, level);
    if (value == null) {
      return null;
    }
    return GsonUtil.parse(value, c);
  }

  /**
   * 获取对应key的缓存
   *
   * @param key
   *          键值
   * @throws Exception
   */
  public static String get(String key) {
    return get(key, StorageMgr.LEVEL_USER);
  }

  /**
   * 获取对应key的缓存
   *
   * @param key
   *          键值
   * @param level
   *          缓存级别(用户，门店，全局）
   * @throws Exception
   */
  public static String get(String key, String level) {
//    BSessionUser user = SessionMgr.getUser();
//    BSessionShop shop = SessionMgr.getShop();
//    String k = "";
//    if (level.equals(StorageMgr.LEVEL_USER) || level.equals(StorageMgr.LEVEL_SHOP)) {
//      if (!IsEmpty.object(user)) {
//        k += user.getId();
//      }
//      k += "_";
//    }
//    if (level.equals(StorageMgr.LEVEL_SHOP)) {
//      if (!IsEmpty.object(shop)) {
//        k += shop.getId();
//      }
//      k += "_";
//    }
//    k += key;
//    return getStorage(k);
    return null;
  }
}