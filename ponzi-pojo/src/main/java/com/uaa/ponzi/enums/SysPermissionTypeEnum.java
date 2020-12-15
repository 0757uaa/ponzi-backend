package com.uaa.ponzi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SysPermissionTypeEnum {

    URL(0, "URL"),
    BUTTON(1, "按钮");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String desc;

    SysPermissionTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
