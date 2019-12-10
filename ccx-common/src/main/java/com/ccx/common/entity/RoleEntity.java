package com.ccx.common.entity;

import java.math.BigInteger;
import java.util.Set;

/**
 * @program: com.ccx.framework.shiro.role
 * @description: 用户的角色信息实体类
 * @authhor: ChenChangxi
 * @create: 2019-12-05 00:20
 **/

public class RoleEntity {

    private BigInteger roleId;  //数据库中就是bigint

    private String roleName;

    private String description;

    private Set<PermissionEntity> permissions;

    public BigInteger getRoleId() {
        return roleId;
    }

    public void setRoleId(BigInteger roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}
