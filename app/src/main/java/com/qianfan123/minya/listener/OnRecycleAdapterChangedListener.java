package com.qianfan123.minya.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by NeilSpears on 2016/11/10.
 */

public class OnRecycleAdapterChangedListener extends RecyclerView.AdapterDataObserver {
  public void onAnyChanged() {
    // do nothing
  }

  @Override
  public void onChanged() {
    super.onChanged();
    onAnyChanged();
  }

  @Override
  public void onItemRangeChanged(int positionStart, int itemCount) {
    super.onItemRangeChanged(positionStart, itemCount);
    onAnyChanged();
  }

  @Override
  public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
    super.onItemRangeChanged(positionStart, itemCount, payload);
    onAnyChanged();
  }

  @Override
  public void onItemRangeInserted(int positionStart, int itemCount) {
    super.onItemRangeInserted(positionStart, itemCount);
    onAnyChanged();
  }

  @Override
  public void onItemRangeRemoved(int positionStart, int itemCount) {
    super.onItemRangeRemoved(positionStart, itemCount);
    onAnyChanged();
  }

  @Override
  public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
    super.onItemRangeMoved(fromPosition, toPosition, itemCount);
    onAnyChanged();
  }
}
