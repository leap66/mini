package com.leap.mini.network.http.update;

import com.qianfan123.minya.R;
import com.leap.mini.network.model.UpdateModel;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pub.devrel.easypermissions.AfterPermissionGranted;

public class ProgressDialog extends Dialog {
  private Context mContext;
  private View rootView;
  private ProgressBar progressBar;
  private TextView tvProgress;

  public ProgressDialog(Context context) {
    super(context);
    this.mContext = context;
    initViews();
  }

  public ProgressDialog(Context context, int themeResId) {
    super(context, themeResId);
    this.mContext = context;
    initViews();
  }

  private void initViews() {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setCanceledOnTouchOutside(false);
    rootView = LayoutInflater.from(mContext).inflate(R.layout.dialog_update_progress, null, false);

    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(UpdateMgr.getScreenWidth() * 11 / 15,
        RelativeLayout.LayoutParams.WRAP_CONTENT);
    setContentView(rootView, params);

    progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
    tvProgress = (TextView) rootView.findViewById(R.id.tv_progress);
  }

  @AfterPermissionGranted(11)
  public void downloadApk(final UpdateModel model) {
    final UpdateTask updateTask = new UpdateTask(mContext, model.position, progressBar, tvProgress,
        UpdateMgr.getInstance().getFilePath());
    updateTask.setSuccessListener(new UpdateTask.OnSuccessListener<String>() {
      @Override
      public void onSuccess(String data) {
        // installStatisticsRequest(model);
        dismiss();
        UpdateMgr.getInstance().installApk(data);
      }
    });
    updateTask.execute(model.downloadUrl);
  }

  // private void installStatisticsRequest(UpdateModel model) {
  // String time = String.valueOf(System.currentTimeMillis());
  // new
  // InstallStatisticsCase(TokenGenerator.generate(UpdateMgr.getInstance().getAppId(),
  // time),
  // time, "1", UpdateMgr.getInstance().getAppId(),
  // String.valueOf(model.versionCode.intValue()))
  // .execute(new PureSubscriber() {
  // @Override
  // public void onFailure(String errorMsg, Response response) {
  // }
  //
  // @Override
  // public void onSuccess(Response response) {
  // }
  // });
  // }

}
