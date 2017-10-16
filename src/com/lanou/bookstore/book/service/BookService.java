package com.lanou.bookstore.book.service;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface BookService {

    List<Book> findAll();

    List<Book> findByCategory(Category category);

    Book load(Book book);

    void saveBook(Book book);
}
