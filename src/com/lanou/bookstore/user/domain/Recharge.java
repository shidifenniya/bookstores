package com.lanou.bookstore.user.domain;


import java.io.Serializable;

/**
 * Created by dllo on 17/9/26.
 */
public class Recharge implements Serializable {

    private String rid;

    private String balance;

    private boolean state;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
