package com.zhul.erp.domain.model.value;

/**
 * 用户类型
 * <p></p>
 * Created by yanglikai on 2023/01/04.
 */
public enum UserTypeEnum {

  DEFAULT(-1, "默认"),

  TYPE_1(1, "员工"),

  TYPE_2(2, "供应商");

  private int code;
  private String desc;

  private UserTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String desc() {
    return this.desc;
  }

  public int code() {
    return this.code;
  }

  public static UserTypeEnum transform(int code) {
    for (UserTypeEnum value : UserTypeEnum.values()) {
      if (value.code == code) {
        return value;
      }
    }

    return UserTypeEnum.DEFAULT;
  }
}
