package com.leap.mini.presentation.main;

import com.leap.mini.R;
import com.leap.mini.databinding.ActivityMainBinding;
import com.leap.mini.presentation.base.BaseActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

/**
 * 主界面
 * <p>
 * </> Created by weiyaling on 2017/3/7.
 */

public class MainActivity extends BaseActivity {
  private ActivityMainBinding binding;

  @Override
  protected void initComponent() {
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
  }

  @Override
  protected void loadData(Bundle savedInstanceState) {

  }
}
