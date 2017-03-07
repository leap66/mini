package com.leap.mini.network.model.auth;

import java.util.ArrayList;
import java.util.List;

import com.leap.mini.network.model.Ucn;

/**
 * Created by neil on 2017/3/7.
 */
public class UserSession {
  private String id;// 唯一标识
  private String name;// 姓名
  private String mobile;// 手机号
  private String gender;// 性别
  private String avatar;// 头像
  private Ucn role; // 职务
  private Ucn org; // 部门
  private boolean disabled = false;
  private List<Ucn> permissions = new ArrayList<Ucn>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Ucn getRole() {
    return role;
  }

  public void setRole(Ucn role) {
    this.role = role;
  }

  public Ucn getOrg() {
    return org;
  }

  public void setOrg(Ucn org) {
    this.org = org;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public List<Ucn> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Ucn> permissions) {
    this.permissions = permissions;
  }
}
