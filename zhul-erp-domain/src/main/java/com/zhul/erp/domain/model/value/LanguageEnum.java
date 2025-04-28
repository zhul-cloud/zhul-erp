package com.zhul.erp.domain.model.value;

import lombok.Getter;

@Getter
public enum LanguageEnum {


    CN(1, "zh-CN"),
    US(2, "en-US"),
    ;

    private final Integer code;
    private final String desc;


    LanguageEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String desc() {
        return this.desc;
    }

    public int code() {
        return this.code;
    }

    public static LanguageEnum transform(int code) {
        for (LanguageEnum value : LanguageEnum.values()) {
            if (value.code == code) {
                return value;
            }
        }

        return LanguageEnum.US;
    }
}
