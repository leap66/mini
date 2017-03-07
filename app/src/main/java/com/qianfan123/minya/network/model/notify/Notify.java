package com.qianfan123.minya.network.model.notify;

import com.qianfan123.minya.network.model.Entity;

/**
 * Created by neil on 2017/3/7.
 */

public class Notify extends Entity {
  private String epId;
  private String announcement;
  private String user;
  private boolean readed;

  public String getEpId() {
    return epId;
  }

  public void setEpId(String epId) {
    this.epId = epId;
  }

  public String getAnnouncement() {
    return announcement;
  }

  public void setAnnouncement(String announcement) {
    this.announcement = announcement;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public boolean isReaded() {
    return readed;
  }

  public void setReaded(boolean readed) {
    this.readed = readed;
  }
}
