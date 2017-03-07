package com.leap.mini.network.interactors.user;

import com.leap.mini.network.http.bean.Response;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by neil on 2017/3/7.
 */

public interface UserServiceApi {

  /**
   * 
   * @param user
   * @return
   */
  @POST("app/user/avatar/update")
  @Multipart
  Observable<Response<String>> updateAvatar(@Path("epId") String epId, @Query("user") String user,
      @Part MultipartBody.Part avatar);

  /**
   * 修改密码
   * 
   * @param mobile
   * @param password
   * @param authCode
   * @return
   */
  @POST("app/user/password/change")
  Observable<Response<Void>> changeMobile(@Path("epId") String epId, @Query("mobile") String mobile,
      @Query(("password")) String password, @Query(("authCode")) String authCode);
}
