package com.lanou.bookstore.category.dao;

import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface CategoryDao {

    List<Category> findCategory();

    void add(Category c);

    void delete(String cid);

    Admin adminLogin(Admin admin);

    Category findByCid(String cid);

    void editCategory(String cid, String cname);

}
