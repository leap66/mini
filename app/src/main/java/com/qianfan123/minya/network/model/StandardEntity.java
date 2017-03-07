package com.qianfan123.minya.network.model;

import java.util.Date;

/**
 * Created by neil on 2016/5/26.
 */
public class StandardEntity extends VersionedEntity {

  private Date created;
  private Ucn creator;
  private Date lastModified;
  private Ucn lastModifier;

  public Ucn getCreator() {
    return creator;
  }

  public void setCreator(Ucn creator) {
    this.creator = creator;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public Ucn getLastModifier() {
    return lastModifier;
  }

  public void setLastModifier(Ucn lastmodifier) {
    this.lastModifier = lastmodifier;
  }
}
