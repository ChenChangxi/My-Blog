package com.ccx.common.entity;

import java.util.Set;

/**
 * @program: com.ccx.framework.shiro.permissions
 * @description: 用户的权限信息实体类
 * @authhor: ChenChangxi
 * @create: 2019-12-05 00:19
 **/

public class PermissionEntity {

    private String permissionId;

    private String permissionName;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
