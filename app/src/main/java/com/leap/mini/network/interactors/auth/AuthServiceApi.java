package com.leap.mini.network.interactors.auth;

import com.leap.mini.network.http.bean.Response;
import com.leap.mini.network.model.auth.UserSession;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by neil on 2017/3/7.
 */

public interface AuthServiceApi {

  /**
   * 登录
   * 
   * @param mobile
   * @param password
   * @return
   */
  @GET("app/auth/login")
  Observable<Response<UserSession>> login(@Query("mobile") String mobile,
      @Query("password") String password);

  /**
   * 注销
   * 
   * @param user
   *          用户ID
   * @return
   */
  @GET("app/auth/logout")
  Observable<Response<Void>> logout(@Path("epId") String epId, @Query("user") String user);
}
