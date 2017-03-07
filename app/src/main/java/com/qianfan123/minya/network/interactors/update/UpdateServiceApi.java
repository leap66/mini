package com.qianfan123.minya.network.interactors.update;

import com.qianfan123.minya.network.http.bean.Response;
import com.qianfan123.minya.network.model.UpdateModel;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dzq on 2016/11/1.
 */

public interface UpdateServiceApi {

  /**
   * 检查新版本
   *
   * @param appToken
   * @param pid
   *          应用类型 android平台传1 iOS平台传2
   * @param appId
   *          应用内部标识
   * @param versionCode
   *          应用版本号
   * @return
   */
  @GET("app/version/check")
  Observable<Response<UpdateModel>> checkVersion(@Query("appToken") String appToken,
                                                 @Query("appKey") String appKey, @Query("pid") String pid, @Query("appId") String appId,
                                                 @Query("versionCode") String versionCode);

  /**
   * 版本安装统计
   *
   * @param appToken
   * @param pid
   *          应用类型 android平台传1 iOS平台传2
   * @param appId
   *          应用内部标识
   * @param versionCode
   *          应用版本号
   * @return
   */
  @POST("app/version/install")
  Observable<Response<String>> installStatistics(@Query("appToken") String appToken,
                                                 @Query("appKey") String appKey, @Query("pid") String pid, @Query("appId") String appId,
                                                 @Query("versionCode") String versionCode);
}
