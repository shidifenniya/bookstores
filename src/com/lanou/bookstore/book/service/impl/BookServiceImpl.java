package com.lanou.bookstore.book.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.commons.CommonUtils;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAll() {

        return bookDao.findAll();

    }

    @Override
    public List<Book> findByCategory(Category category) {

        // 通过cname 查询 cid
        Category byCategory = bookDao.findByCategory(category.getCname());

        // 通过cid查询 booklist
        return bookDao.findByCid(byCategory.getCid());

    }

    @Override
    public Book load(Book book) {

        return bookDao.loadByBid(book.getBid());

    }

    @Override
    public void saveBook(Book book) {

        book.setBid(CommonUtils.uuid());

        bookDao.saveBook(book);

    }
}
