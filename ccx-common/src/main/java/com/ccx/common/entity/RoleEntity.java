package com.ccx.common.entity;

import java.util.Set;

/**
 * @program: com.ccx.framework.shiro.role
 * @description: 用户的角色信息实体类
 * @authhor: ChenChangxi
 * @create: 2019-12-05 00:20
 **/

public class RoleEntity {

    private String roleId;

    private String roleName;

    private Set<PermissionEntity> permissions;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }
}
