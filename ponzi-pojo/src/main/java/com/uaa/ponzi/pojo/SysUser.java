package com.uaa.ponzi.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.uaa.ponzi.base.pojo.BaseEntity;
import com.uaa.ponzi.common.enums.SexEnum;
import lombok.Data;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

@Data
@TableName("sys_user")
public class SysUser extends BaseEntity {

    private String username;

    private String encryptPassword;

    private String dencryptPassword;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private SexEnum sex;

    private String salt;

    public SysUser() {
        super();
    }

    /**
     * 初始化密码
     * @param dencryptPassword 明文密码
     * @return
     */
    public static SysUser initPassword(String dencryptPassword) {
        SysUser sysUser =  new SysUser();
        sysUser.setDencryptPassword(dencryptPassword);
        sysUser.setSalt(new SecureRandomNumberGenerator().nextBytes().toHex());
        sysUser.setEncryptPassword(new Md5Hash(dencryptPassword, sysUser.getSalt(), 1024).toString());
        return sysUser;
    }
}
