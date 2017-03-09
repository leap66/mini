package com.leap.mini.presentation.auth;

import com.leap.mini.R;
import com.leap.mini.cmp.StorageMgr;
import com.leap.mini.databinding.DialogAuthResetPwdBinding;
import com.leap.mini.interactor.api_interface.auth.usecase.RegisterRequest;
import com.leap.mini.interactor.api_interface.auth.usecase.SendSmsCase;
import com.leap.mini.interactor.listener.TextChangedListener;
import com.leap.mini.interactor.network.subscriber.PureSubscriber;
import com.leap.mini.interactor.network.subscriber.Response;
import com.leap.mini.util.DialogUtil;
import com.leap.mini.util.IsEmpty;
import com.leap.mini.util.StringUtil;
import com.leap.mini.util.ToastUtil;
import com.leap.mini.widget.cleartextfield.validator.FieldValidateError;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * 修改密码Dialog
 * <p>
 * Created by weiyaling on 2016/12/2.
 */

public class ResetPwdDialog extends Dialog {
  private DialogAuthResetPwdBinding binding;

  public ResetPwdDialog(Context context, String phoneNum) {
    super(context);
    // 初始化组件
    initComponent(context, phoneNum);
    // 事件监听处理
    createEventHandler();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.setContentView(binding.getRoot());
  }

  /**
   * 初始化组件
   */
  private void initComponent(Context context, String phoneNum) {
    binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_auth_reset_pwd,
        null, false);
    binding.setPresenter(new Presenter());
    // 点击空白区域可以取消dialog
    this.setCanceledOnTouchOutside(true);
    // 点击back键可以取消dialog
    this.setCancelable(true);
    Window window = this.getWindow();
    // 让Dialog显示在屏幕的底部
    window.setGravity(Gravity.BOTTOM);
    // 设置窗口出现和窗口隐藏的动画
    window.getDecorView().setPadding(0, 0, 0, 0);// 设置窗口的padding值为0
    // 设置BottomDialog的宽高属性
    WindowManager.LayoutParams lp = window.getAttributes();
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    window.setAttributes(lp);
    String phone;
    if (!IsEmpty.string(phoneNum)) {
      phone = phoneNum;
    } else {
      phone = StorageMgr.get("phone", StorageMgr.LEVEL_GLOBAL);
    }
    if (!IsEmpty.string(phone)) {
      binding.phoneEt.setText(phone);
      binding.phoneEt.getClearEditText().setSelection(phone.length());
      binding.passwordEt.setFocusable(true);
      binding.passwordEt.setFocusableInTouchMode(true);
      binding.passwordEt.requestFocus();
      if (StringUtil.isMobileNO(binding.phoneEt.getText().toString())) {
        binding.sendCodeCt.setEnabled(true);
      } else {
        binding.sendCodeCt.setEnabled(false);
      }
    }
  }

  /**
   * 事件监听处理
   */
  private void createEventHandler() {
    binding.phoneEt.addEditTextChangedListener(new TextChangedListener() {

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (StringUtil.isMobileNO(s.toString())) {
          binding.sendCodeCt.setEnabled(true);
        } else {
          binding.sendCodeCt.setEnabled(false);
        }
      }
    });
    binding.codeEt.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
          new ResetPwdDialog.Presenter().onConfirm();
          return true;
        }
        return false;
      }
    });
  }

  /**
   * 主界面Presenter
   */
  public class Presenter {

    /**
     * 获取验证码
     */
    public void onSmsSend() {
      binding.codeEt.setFocusable(true);
      binding.codeEt.setFocusableInTouchMode(true);
      binding.codeEt.requestFocus();
      String mobile = binding.phoneEt.getText().toString().trim();
      if (StringUtil.isMobileNO(mobile)) {
        new SendSmsCase(mobile).execute(new PureSubscriber<Object>() {
          /**
           * 失败
           *
           * @param errorMsg
           *          错误信息
           * @param data
           *          data为null代表是网络错误
           */
          @Override
          public void onFailure(String errorMsg, Response<Object> data) {
            DialogUtil.showError(getContext(), errorMsg).show();
          }

          @Override
          public void onSuccess(Response data) {
            binding.sendCodeCt.startCount();
          }
        });
      } else {
        DialogUtil.showError(getContext(), getContext().getString(R.string.register_phone_hint))
            .show();
      }
    }

    public void onConfirm() {
      if (isValid()) {
        RegisterRequest param = getUserData();
        String code = binding.codeEt.getText().toString().trim();
      }
    }

    public void onCancel() {
      InputMethodManager im = (InputMethodManager) getContext()
          .getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(binding.phoneEt.getWindowToken(), 0);
      dismiss();
    }

  }

  /**
   * 判断输入数据是否有效 确认之前需要判断手机号，新密码，确认新密码是否为空和相同
   */
  private boolean isValid() {
    FieldValidateError phoneError = binding.phoneEt.validateEditText();
    FieldValidateError passwordError = binding.passwordEt.validateEditText();
    FieldValidateError repeatPasswordError = binding.repeatPasswordEt.validateEditText();
    FieldValidateError codeError = binding.codeEt.validateEditText();
    if (!IsEmpty.object(phoneError)) {
      ToastUtil.toastFailure(getContext(), phoneError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(passwordError)) {
      ToastUtil.toastFailure(getContext(), passwordError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(repeatPasswordError)) {
      ToastUtil.toastFailure(getContext(), repeatPasswordError.getErrorMessage());
      return false;
    } else if (!IsEmpty.object(codeError)) {
      ToastUtil.toastFailure(getContext(), codeError.getErrorMessage());
      return false;
    } else if (!binding.passwordEt.getText().toString()
        .equals(binding.repeatPasswordEt.getText().toString())) {
      ToastUtil.toastFailure(getContext(),
          getContext().getResources().getString(R.string.register_pwd_confirm_error));
      return false;
    } else {
      return true;
    }
  }

  /**
   * 获取用户数据
   */
  private RegisterRequest getUserData() {
    RegisterRequest param = new RegisterRequest();
    param.setMobile(binding.phoneEt.getText().toString().trim());
    param.setPassword(binding.passwordEt.getText().toString());
    return param;
  }

  /**
   * dialog dismiss时键盘不消失的问题
   * 
   * @param event
   * @return
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (isShowing() && shouldCloseOnTouch(getContext(), event)) {
      InputMethodManager im = (InputMethodManager) getContext()
          .getSystemService(Context.INPUT_METHOD_SERVICE);
      im.hideSoftInputFromWindow(binding.phoneEt.getWindowToken(), 0);
    }
    return super.onTouchEvent(event);
  }

  public boolean shouldCloseOnTouch(Context context, MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN && isOutOfBounds(context, event)
        && getWindow().peekDecorView() != null) {
      return true;
    }
    return false;
  }

  private boolean isOutOfBounds(Context context, MotionEvent event) {
    final int x = (int) event.getX();
    final int y = (int) event.getY();
    final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
    final View decorView = getWindow().getDecorView();
    return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
        || (y > (decorView.getHeight() + slop));
  }
}
