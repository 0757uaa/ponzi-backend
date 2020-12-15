package com.uaa.ponzi.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum SexEnum implements IEnum<Integer> {

    SECRET(0, "不公开"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer value;
    private String desc;

    SexEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return super.toString();
//        return String.valueOf(this.value);
    }
}
