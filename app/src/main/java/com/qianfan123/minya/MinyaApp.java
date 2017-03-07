package com.qianfan123.minya;

import com.qianfan123.minya.cmp.StorageMgr;
import com.qianfan123.minya.network.http.ApiClient;

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
