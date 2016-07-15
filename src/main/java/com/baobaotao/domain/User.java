package com.baobaotao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhouzilong on 2016/7/15.
 * 领域对象（Domain Object）
 * User（实体类）
 * 一般来说领域对象属于业务层，但他贯穿展现层、业务层和持久层，并最终被持久化到数据库中
 */
public class User implements Serializable {
    private int userId;
    private String userName;
    private String passWord;
    private int credits;
    private String lastIp;
    private Date lastVist;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastVist() {
        return lastVist;
    }

    public void setLastVist(Date lastVist) {
        this.lastVist = lastVist;
    }
}
