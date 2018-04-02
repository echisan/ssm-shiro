package com.echisan.util;

/**
 * @author E73AN
 */


public enum LockStatus {
    // 0表示无封禁状态 即表示解封 枚举表示unlock
    UNLOCK(0,"解封"),
    // 1表示封禁
    LOCK(1,"封禁");

    int mCode;
    String mDescription;

    LockStatus(int mCode, String description) {
        this.mCode = mCode;
        this.mDescription = description;
    }


    @Override
    public String toString() {
        return "LockStatus{" +
                "mCode=" + mCode +
                ", description='" + mDescription + '\'' +
                '}';
    }
}
