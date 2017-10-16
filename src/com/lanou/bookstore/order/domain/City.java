package com.lanou.bookstore.order.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 17/9/27.
 */
public class City implements Serializable {

    private String province;

    private List<String> city;

    public City() {

    }

    public City(String province, List<String> city) {
        this.province = province;
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<String> getCity() {
        return city;
    }

    public void setCity(List<String> city) {
        this.city = city;
    }
}
