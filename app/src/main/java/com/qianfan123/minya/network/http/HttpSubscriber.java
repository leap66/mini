package com.qianfan123.minya.network.http;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import com.qianfan123.minya.network.http.bean.ResponseBean;
import com.qianfan123.minya.network.http.exception.ApiException;
import com.qianfan123.minya.network.http.exception.TokenExpiredException;
import com.qianfan123.minya.util.DialogUtil;
import com.qianfan123.minya.widget.sweetAlert.SweetAlertDialog;

import android.content.Context;

/**
 * Created by wangcong on 2017/3/6.
 * <p>
 * </>
 */

public abstract class HttpSubscriber<T> extends rx.Subscriber<ResponseBean<T>> {

  private Context context;
  private SweetAlertDialog dialog;

  public HttpSubscriber() {
  }

  public HttpSubscriber(Context context) {
    this.context = context;
    dialog = DialogUtil.getProgressDialog(context);
  }

  @Override
  public void onCompleted() {
  }

  @Override
  public void onError(Throwable throwable) {
    if (dialog != null && context != null)
      dialog.dismiss();
    onFailure(parseException(throwable), null);
  }

  @Override
  public void onStart() {
    super.onStart();
    if (dialog != null && context != null)
      dialog.show();
  }

  @Override
  public void onNext(ResponseBean<T> t) {
    if (dialog != null && context != null)
      dialog.dismiss();
    if (t == null) {
      onSuccess(null, null);
      return;
    }
    if (t.isSuccess()) {
      onSuccess(t.getData(), t);
    } else {
      if (t.getMessage() != null) {
        onFailure(t.getMessage().get(0), t);
      } else {
        onFailure(null, t);
      }
    }

  }

  public abstract void onFailure(String errorMsg, ResponseBean<T> response);

  public abstract void onSuccess(T data, ResponseBean<T> response);

  private String parseException(Throwable throwable) {
    String errorMessage;
    if (throwable instanceof SocketTimeoutException) {
      errorMessage = "网络链接超时,请稍后重试!";
    } else if (throwable instanceof ConnectException) {
      errorMessage = "网络链接失败,请检查网络设置!";
    } else if (throwable instanceof JSONException) {
      errorMessage = "数据解析失败,请稍候重试!";
    } else if (throwable instanceof ApiException) {
      errorMessage = throwable.getMessage();
    } else if (throwable instanceof TokenExpiredException) {
      errorMessage = throwable.getMessage();
      EventBus.getDefault().post(new AuthEvent(AuthEvent.TOKEN_EXPIRED));
    } else {
      errorMessage = "未知错误!";
    }
    return errorMessage;
  }

}
