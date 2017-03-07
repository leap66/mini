package com.leap.mini.network.http.exception;

import java.io.IOException;

/**
 * 网络异常实体类
 * <p>
 * </> Created by weiyaling on 2017/2/23.
 */

public class ApiException extends IOException {

  private int code;

  public ApiException(int code, String message) {
    super(message);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
