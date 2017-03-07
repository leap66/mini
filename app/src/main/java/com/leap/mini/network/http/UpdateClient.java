package com.leap.mini.network.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.leap.mini.network.http.exception.ApiException;
import com.leap.mini.util.IsEmpty;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dzq on 16/7/12.
 */
public class UpdateClient {
  private static String baseUrl;
  private static Retrofit updateClient;
  private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

  private static Interceptor requestErrorInterceptor = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Response response = chain.proceed(request);
      ApiException e = null;
      if (401 == response.code()) {
        // 使用EventBus通知跳转到登陆页面
//        EventBus.getDefault().post(new AuthEvent(AuthEvent.TOKEN_EXPIRED));
        e = new ApiException(401, "登录已过期,请重新登录!");
      } else if (403 == response.code()) {
        e = new ApiException(403, "禁止访问!");
      } else if (503 == response.code()) {
        e = new ApiException(503, "服务器升级中!");
      } else if (500 == response.code()) {
        e = new ApiException(500, "服务器内部错误!");
      } else if (404 == response.code()) {
        e = new ApiException(404, "链接错误");
      }
      if (!IsEmpty.object(e)) {
        throw e;
      }
      return response;
    }
  };

  // 未登陆之前使用该对象 没有Token
  public static Retrofit updateClient() {
    if (null != updateClient) {
      return updateClient;
    }
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okClient = new OkHttpClient.Builder().retryOnConnectionFailure(true)
        .addInterceptor(logging).connectTimeout(5, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(requestErrorInterceptor).build();
    updateClient = new Retrofit.Builder().baseUrl(baseUrl).client(okClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

    return updateClient;
  }

  public static void setBaseUrl(String baseUrl) {
    UpdateClient.baseUrl = baseUrl;
  }
}
