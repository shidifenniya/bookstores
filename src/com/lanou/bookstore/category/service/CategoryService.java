package com.lanou.bookstore.category.service;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface CategoryService {

    List<Category> findCategory();

    void add(Category c);

    void delete(String cid) throws CategoryException;

    boolean login(Admin admin);

    Category findByCid(String cid);

    void edit(Category c);

    void del(String bid);

    void edit(Book book);
}
