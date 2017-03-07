package com.qianfan123.minya.network.interactors.update.usecase;

import com.qianfan123.minya.network.http.BaseUseCase;
import com.qianfan123.minya.network.http.bean.Response;
import com.qianfan123.minya.network.interactors.update.UpdateServiceApi;

import rx.Observable;

public class InstallStatisticsCase extends BaseUseCase<UpdateServiceApi> {

  private String appToken;
  private String pid;
  private String appId;
  private String appKey;
  private String versionCode;

  /**
   * 检查新版本
   *
   * @param appToken
   * @param appKey
   *          时间撮
   * @param pid
   *          应用类型 android平台传1 iOS平台传2
   * @param appId
   *          应用内部标识
   * @param versionCode
   *          应用版本号
   * @return
   */
  public InstallStatisticsCase(String appToken, String appKey, String pid, String appId,
      String versionCode) {
    this.appToken = appToken;
    this.pid = pid;
    this.appKey = appKey;
    this.appId = appId;
    this.versionCode = versionCode;
  }

  @Override
  protected Observable<Response<String>> buildCase() {
    return createUpdateConnection().installStatistics(appToken, appKey, pid, appId, versionCode);
  }
}
