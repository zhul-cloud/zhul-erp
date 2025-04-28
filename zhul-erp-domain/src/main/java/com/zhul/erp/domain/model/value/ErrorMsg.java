package com.zhul.erp.domain.model.value;

/**
 * Created by yanglikai on 2023/12/18.
 */
public enum ErrorMsg {

  D0001("D0001", "角色名称[%s]已存在"),

  D0002("D0002", "用户名[%s]已存在"),

  D0003("D0003", "该用户不存在"),

  D0004("D0004", "用户名不存在"),

  D0005("D0005", "登录密码不正确"),

  D0006("D0006", "旧密码不正确"),

  D0007("D0007", "token已失效，请退出重新登录"),

  D0008("D0008", "用户未登录，请登录后再进行操作"),

  D0009("D0009", "角色不存在"),
  ;

  private String code;
  private String msg;

  private ErrorMsg(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String msg() {
    return this.msg;
  }

  public String code() {
    return this.code;
  }
}
