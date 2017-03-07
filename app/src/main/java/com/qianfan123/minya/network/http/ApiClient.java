package com.qianfan123.minya.network.http;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qianfan123.minya.R;
import com.qianfan123.minya.network.http.exception.ApiException;
import com.qianfan123.minya.network.http.exception.TokenExpiredException;
import com.qianfan123.minya.util.IsEmpty;
import com.qianfan123.minya.util.NetworkUtil;

import android.content.Context;
import android.text.TextUtils;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wangcong on 2017/2/28.
 */

public class ApiClient {

  private static String token = "jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c3JOYSI6IkNjYyIsImV4cCI6MTQ4ODg1NjgwOCwic3BPdyI6ImRhNDY0MDI5LTRmNWYtNDZmYS04MjI5LTE4Zjk3YzczODdlMyIsInVzck1vIjoiMTMxMjIzMzIyMzMiLCJ1c3JJZCI6ImRhNDY0MDI5LTRmNWYtNDZmYS04MjI5LTE4Zjk3YzczODdlMyIsInBybXMiOlsi5ZWG5ZOBIiwi6L-b6LSnIiwi6ZSA5ZSuIiwi6ZSA5ZSu6YCA6LSnIiwi6ZSA5ZSu5pS55Lu3Iiwi5bqT5a2YIiwi5pS26JeP5aS5Iiwi6Kej5qy-Iiwi5pS25LuY5qy-Iiwi6YeH6LStIiwi5om55Y-RIl0sInNwSWQiOiJzMDFjMjg0NSIsImlhdCI6MTQ4ODc3MDQwOCwidXNyTHQiOjE0ODg3NzAzNzUwMDF9.8TKYkxFJtcN8a6tU1qGlXTvE-GlENQzW7cXmYtW5Jio; Domain=.1000sails.com; Path=/; HttpOnly";

  private static Context context;
  private static String baseUrl;
  private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

  public static void init(Context context, String baseUrl) {
    ApiClient.context = context;
    ApiClient.baseUrl = baseUrl;
  }

  private static Interceptor setUserCookie = new Interceptor() {
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
      Request request;
      if (!TextUtils.isEmpty(token)) {
        request = chain.request().newBuilder().addHeader("Cookie", token).build();
      } else {
        request = chain.request();
      }
      return chain.proceed(request);
    }
  };

  private static Interceptor getUserCookie = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      Request request = chain.request();
      Response response = chain.proceed(request);
      String cookie = response.headers().get("Set-Cookie");
      if (!IsEmpty.string(cookie) && cookie.contains("jwt=")) {
        // TokenMgr.updateUserToken(cookie);
      }
      return response;
    }
  };

  private static Interceptor requestErrorInterceptor = new Interceptor() {
    @Override
    public Response intercept(Chain chain) throws IOException {
      if (!NetworkUtil.isConnected(context)) {
        throw new ApiException(0, context.getString(R.string.newwork_err));
      }
      Request request = chain.request();
      Response response = chain.proceed(request);
      ApiException e = null;
      if (401 == response.code()) {
        throw new TokenExpiredException(401, context.getString(R.string.newwork_request_err_401));
      } else if (403 == response.code()) {
        e = new ApiException(403, context.getString(R.string.newwork_request_err_403));
      } else if (503 == response.code()) {
        e = new ApiException(503, context.getString(R.string.newwork_request_err_503));
      } else if (500 == response.code()) {
        e = new ApiException(500, context.getString(R.string.newwork_request_err_500));
      } else if (404 == response.code()) {
        e = new ApiException(404, context.getString(R.string.newwork_request_err_404));
      }
      if (e != null) {
        throw e;
      }

      return response;
    }
  };

  public static Retrofit instance() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okClient = new OkHttpClient.Builder().retryOnConnectionFailure(true)
        .addInterceptor(logging).addInterceptor(requestErrorInterceptor)
        .addInterceptor(setUserCookie).addInterceptor(getUserCookie)
        .connectTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(true)
        .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();

    Retrofit retrofit = new Retrofit.Builder().client(okClient).baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    return retrofit;
  }
}
