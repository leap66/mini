/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * 
 * 项目名：	dpos-web
 * 文件名：	Entity.java
 * 模块说明：	
 * 修改历史：
 * 2016年5月21日 - linzhu - 创建。
 */
package com.leap.mini.network.model;

import java.io.Serializable;


/**
 * @author linzhu
 */
public class Entity implements Serializable{

  private String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
