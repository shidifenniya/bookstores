package com.lanou.bookstore.user.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/25.
 */
public class Balance implements Serializable{

    private String uid;

    private String balance;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
