package com.zhul.erp.domain.model.value;

/**
 * Created by yanglikai on 2023/12/18.
 */
public enum StatusEnum {

  DEFAULT(-1, "默认"),

  STATUS_0(0, "禁用"),

  STATUS_1(1, "启用");

  private int code;
  private String desc;

  private StatusEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String desc() {
    return this.desc;
  }

  public int code() {
    return this.code;
  }

  public static StatusEnum transform(int code) {
    for (StatusEnum value : StatusEnum.values()) {
      if (value.code == code) {
        return value;
      }
    }

    return StatusEnum.DEFAULT;
  }
}
