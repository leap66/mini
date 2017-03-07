/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * 
 * 项目名：	dpos-web
 * 文件名：	BVersionEntity.java
 * 模块说明：	
 * 修改历史：
 * 2016-5-27 - Gao JingYu - 创建。
 */
package com.leap.mini.network.model;

/**
 * @author Gao JingYu
 */
public class VersionedEntity extends Entity {

  private long version;

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }
}
