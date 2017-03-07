package com.qianfan123.minya.network.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wangcong on 2017/2/28.
 * <p>
 * </>
 */

public abstract class BaseUseCase<T> {

  private Observable observable;

  public void execute(final HttpSubscriber response) {
    observable = buildCase();
    if (observable == null)
      return;
    observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(response);
  }

  protected T createConnection() {
    return ApiClient.instance().create(getType());
  }

  protected T createUpdateConnection() {
    return UpdateClient.updateClient().create(getType());
  }

  protected abstract Observable buildCase();

  private Class<T> getType() {
    Class<T> entityClass = null;
    Type t = getClass().getGenericSuperclass();
    Type[] p = ((ParameterizedType) t).getActualTypeArguments();
    entityClass = (Class<T>) p[0];
    return entityClass;
  }
}
