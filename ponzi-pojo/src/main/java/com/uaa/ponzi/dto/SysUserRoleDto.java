package com.uaa.ponzi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uaa.ponzi.common.enums.SexEnum;
import lombok.Data;

import java.util.List;

@Data
public class SysUserRoleDto {

    private String id;

    private String username;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private SexEnum sex;

    private List<SysRoleDto> roleDtoList;

}
