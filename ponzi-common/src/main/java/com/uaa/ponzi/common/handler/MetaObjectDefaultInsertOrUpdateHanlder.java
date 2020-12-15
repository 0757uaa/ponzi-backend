package com.uaa.ponzi.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.uaa.ponzi.common.enums.DeletedEnum;
import com.uaa.ponzi.common.enums.DisabledEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

// 针对对象在进行新增、修改操作时自动设置特定字段的默认数据
@Slf4j
@Component
public class MetaObjectDefaultInsertOrUpdateHanlder implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("插入数据前设置默认数据");
        Date now = new Date();
        this.strictInsertFill(metaObject, "createTime", Date.class, now);
        this.strictInsertFill(metaObject, "disabled", DisabledEnum.class, DisabledEnum.NO);
        this.strictInsertFill(metaObject, "deleted", DeletedEnum.class, DeletedEnum.NO);
        this.strictInsertFill(metaObject, "version", Timestamp.class, new Timestamp(now.getTime()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("更新数据前设置默认数据");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}
