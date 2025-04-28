package com.zhul.erp.domain.model.value;

/**
 * 字典类型
 * <p></p>
 * Created by yanglikai on 2023/12/18.
 */
public enum DictTypeEnum {

  DEFAULT(-1, "默认"),

  DICT(1, "字典"),

  DICT_ITEM(2, "字典项");

  private int code;
  private String desc;

  private DictTypeEnum(int code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String desc() {
    return this.desc;
  }

  public int code() {
    return this.code;
  }

  public static DictTypeEnum transform(int code) {
    for (DictTypeEnum value : DictTypeEnum.values()) {
      if (value.code == code) {
        return value;
      }
    }

    return DictTypeEnum.DEFAULT;
  }
}
