package com.leap.mini.network.interactors.notify;

import java.util.List;

import com.leap.mini.network.http.bean.QueryParam;
import com.leap.mini.network.http.bean.Response;
import com.leap.mini.network.model.notify.Notify;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by neil on 2017/3/7.
 */

public interface NotifyServiceApi {

  /**
   *
   * @param user
   *          用户ID
   * @param filter
   * @return
   */
  @POST("app/{epId}/notify/query")
  Observable<Response<List<Notify>>> query(@Path("epId") String epId, @Query("user") String user,
      @Body QueryParam filter);

  /**
   * 阅读指定消息
   * 
   * @param user
   * @param id
   * @return
   */
  @GET("app/{epId}/notify/read")
  Observable<Response<Notify>> read(@Path("epId") String epId, @Query("user") String user,
      @Query("id") String id);

  /**
   * 阅读所有消息
   * 
   * @param user
   * @return
   */
  @GET("app/{epId}/notify/read/all")
  Observable<Response<Void>> readAll(@Path("epId") String epId, @Query("user") String user);

}
