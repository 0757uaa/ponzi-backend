package com.uaa.ponzi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DisabledEnum {
    YES(Boolean.TRUE, "已禁用"),
    NO(Boolean.FALSE, "已启用");

    @EnumValue
    @JsonValue
    private final Boolean value;
    private final String desc;

    DisabledEnum(Boolean value, String desc) {
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
