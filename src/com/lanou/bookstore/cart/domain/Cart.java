package com.lanou.bookstore.cart.domain;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
public class Cart implements Serializable {

    private Map<String, CartItem> map;

    public Map<String, CartItem> getMap() {
        return map;
    }

    public void setMap(Map<String, CartItem> map) {
        this.map = map;
    }
}
