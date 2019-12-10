package com.ccx.common.entity;

import java.math.BigInteger;
import java.util.Set;

/**
 * @program: com.ccx.framework.shiro.permissions
 * @description: 用户的权限信息实体类
 * @authhor: ChenChangxi
 * @create: 2019-12-05 00:19
 **/

public class PermissionEntity {

    private BigInteger permissionId;  //数据库中就是bigint

    private String permissionName;

    private String description;

    public BigInteger getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(BigInteger permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
