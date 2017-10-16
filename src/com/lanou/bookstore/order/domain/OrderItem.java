package com.lanou.bookstore.order.domain;

import com.lanou.bookstore.book.domain.Book;

/**
 * Created by dllo on 17/9/22.
 */
public class OrderItem {

    private String iid;

    private int count;

    private String subtotal;

    private String oid;

    private String bid;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String Oid) {
        this.oid = Oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
