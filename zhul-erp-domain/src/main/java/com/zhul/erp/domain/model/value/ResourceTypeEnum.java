package com.zhul.erp.domain.model.value;

/**
 * 资源类型
 * <p></p>
 * Created by yanglikai on 2023/12/18.
 */
public enum ResourceTypeEnum {

  DEFAULT(-1, "默认"),

  TYPE_1(1, "目录"),

  TYPE_2(2, "菜单"),

  TYPE_3(3, "按钮");

  private int code;
  private String desc;

  private ResourceTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String desc() {
    return this.desc;
  }

  public int code() {
    return this.code;
  }

  public static ResourceTypeEnum transform(int code) {
    for (ResourceTypeEnum value : ResourceTypeEnum.values()) {
      if (value.code == code) {
        return value;
      }
    }

    return ResourceTypeEnum.DEFAULT;
  }
}
