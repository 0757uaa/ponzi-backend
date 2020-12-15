package com.uaa.ponzi.base.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.uaa.ponzi.common.enums.DeletedEnum;
import com.uaa.ponzi.common.enums.DisabledEnum;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity extends IdEntity {

    @TableField(fill = FieldFill.INSERT)
    protected Date createTime;

    @TableField(fill = FieldFill.INSERT)
    protected DisabledEnum disabled;

    @TableField(fill = FieldFill.INSERT)
    protected DeletedEnum deleted;

    @TableField(fill = FieldFill.UPDATE)
    protected Date updateTime;

    protected Date disabledTime;

    @Version
    @TableField(fill = FieldFill.INSERT)
    protected Timestamp version;

}
