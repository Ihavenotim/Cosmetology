package com.cqjtu.cosmetology.entity;

import java.io.Serializable;

/**
 * (Business)实体类
 *
 * @author makejava
 * @since 2023-03-09 14:46:14
 */
public class Business implements Serializable {
    private static final long serialVersionUID = 341399724418405780L;

    private Long id;
    /**
     * 商家名称
     */
    private String busname;
    /**
     * 商家地址
     */
    private String busaddress;
    /**
     * 商家介绍
     */
    private String busexplain;
    /**
     * 商家电话
     */
    private String bustell;
    /**
     * 服务时间
     */
    private String servertime;

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public String getBusaddress() {
        return busaddress;
    }

    public void setBusaddress(String busaddress) {
        this.busaddress = busaddress;
    }

    public String getBusexplain() {
        return busexplain;
    }

    public void setBusexplain(String busexplain) {
        this.busexplain = busexplain;
    }

    public String getBustell() {
        return bustell;
    }

    public void setBustell(String bustell) {
        this.bustell = bustell;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

}

