package com.leap.mini;

import com.leap.mini.cmp.StorageMgr;
import com.leap.mini.network.http.ApiClient;
import com.qianfan123.minya.BuildConfig;

import android.support.multidex.MultiDexApplication;

/**
 * Created by wangcong on 2017/3/1.
 */

public class MinyaApp extends MultiDexApplication {

  private static MinyaApp instance;

  public static MinyaApp getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    ApiClient.init(this, BuildConfig.SERVER_URL);
    StorageMgr.init(this);
  }
}
