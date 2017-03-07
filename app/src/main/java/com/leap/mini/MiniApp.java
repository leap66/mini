package com.leap.mini;

import com.leap.mini.cmp.StorageMgr;
import com.leap.mini.network.http.ApiClient;

import android.support.multidex.MultiDexApplication;

/**
 * 复合型 Application
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class MiniApp extends MultiDexApplication {

  private static MiniApp instance;

  public static MiniApp getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    ApiClient.init(this, BuildConfig.SERVER_URL);
    StorageMgr.init(this);
  }
}
