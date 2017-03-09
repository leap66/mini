package com.leap.mini;

import com.blankj.utilcode.utils.Utils;
import com.leap.mini.cmp.SessionMgr;
import com.leap.mini.cmp.StorageMgr;
import com.leap.mini.cmp.TokenMgr;
import com.leap.mini.interactor.network.ApiClient;

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
    SessionMgr.init(this);
    TokenMgr.init();
    Utils.init(this);
  }
}
