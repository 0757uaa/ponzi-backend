package com.uaa.ponzi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeletedEnum {
    YES(Boolean.TRUE, "已删除"),
    NO(Boolean.FALSE, "使用中");

    @EnumValue
    @JsonValue
    private final Boolean value;
    private final String desc;

    DeletedEnum(Boolean value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Boolean getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
