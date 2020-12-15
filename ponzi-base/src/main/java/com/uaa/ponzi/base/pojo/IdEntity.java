package com.uaa.ponzi.base.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public abstract class IdEntity implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    protected String id;

}
