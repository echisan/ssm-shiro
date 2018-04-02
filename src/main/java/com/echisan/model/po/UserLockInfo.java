package com.echisan.model.po;

import java.util.Date;

public class UserLockInfo {
    private Long id;

    private Long targetUserId;

    private Long executeUserId;

    private Byte executeLockType;

    private Date executeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    public Long getExecuteUserId() {
        return executeUserId;
    }

    public void setExecuteUserId(Long executeUserId) {
        this.executeUserId = executeUserId;
    }

    public Byte getExecuteLockType() {
        return executeLockType;
    }

    public void setExecuteLockType(Byte executeLockType) {
        this.executeLockType = executeLockType;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}