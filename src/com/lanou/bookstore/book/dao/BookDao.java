package com.lanou.bookstore.book.dao;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface BookDao {

    List<Book> findAll();

    Category findByCategory(String cname);

    List<Book> findByCid(String cid);

    Book loadByBid(String bid);

    void saveBook(Book book);

    void delBook(String bid);

    void editBook(Book book);

}
