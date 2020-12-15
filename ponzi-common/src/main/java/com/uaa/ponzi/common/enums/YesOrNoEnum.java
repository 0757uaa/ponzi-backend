package com.uaa.ponzi.common.enums;

public enum YesOrNoEnum {

    NO(0, "否"),
    YES(1, "是");

    public final Integer type;
    public final String desc;

    YesOrNoEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
