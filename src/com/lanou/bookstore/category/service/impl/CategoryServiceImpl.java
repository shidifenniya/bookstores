package com.lanou.bookstore.category.service.impl;


import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.dao.impl.CategoryDaoImpl;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.bookstore.category.service.CategoryService;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Category> findCategory() {

        return categoryDao.findCategory();

    }

    @Override
    public void add(Category c) {

        List<Category> category = categoryDao.findCategory();

        int size = category.size() + 1;

        c.setCid(String.valueOf(size));

        categoryDao.add(c);

    }

    @Override
    public void delete(String cid) throws CategoryException {

        List<Book> bookList = bookDao.findByCid(cid);

        if(!bookList.isEmpty()){

            throw new CategoryException("该分类下还有图书,不能删除分类");

        }

        categoryDao.delete(cid);

    }

    @Override
    public boolean login(Admin admin) {

        Admin login = categoryDao.adminLogin(admin);

        if(login != null){

            return true;

        }

        return false;
    }

    @Override
    public Category findByCid(String cid) {

        return categoryDao.findByCid(cid);

    }

    @Override
    public void edit(Category c) {

        categoryDao.editCategory(c.getCid(),c.getCname());

    }

    @Override
    public void del(String bid) {

        bookDao.delBook(bid);

    }

    @Override
    public void edit(Book book) {

        bookDao.editBook(book);

    }


}
