package com.uaa.ponzi.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uaa.ponzi.common.enums.DeletedEnum;
import com.uaa.ponzi.common.enums.DisabledEnum;
import com.uaa.ponzi.common.enums.SexEnum;
import lombok.Data;

import java.util.Date;

@Data
public class SysUserBo {
    String id;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private SexEnum sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    protected Date createTime;
    protected DisabledEnum disabled;
    protected DeletedEnum deleted;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    protected Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    protected Date disabledTime;
    public SysUserBo() {
        super();
    }

}
