package com.ccx.common.entity;

/**
 * @program: com.ccx.common.entity
 * @description: 这是所有实体类的基类，其他实体类要继承它
 * @authhor: ChenChangxi
 * @create: 2019-09-28 15:56
 **/

public class BaseEntity {

    private int id;

    private String ping;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }
}
