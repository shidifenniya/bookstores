package com.lanou.bookstore.category.dao.impl;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class CategoryDaoImpl implements CategoryDao {

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public List<Category> findCategory() {

        String str = "select * from category";

        try {

            return qr.query(str, new BeanListHandler<>(Category.class));

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void add(Category c) {

        String str = "insert into category (cid, cname) values (?, ?)";

        try {

            qr.update(str, c.getCid(), c.getCname());

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void delete(String cid) {

        String str = "delete from category where cid=?";

        try {

            qr.update(str, cid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public Admin adminLogin(Admin admin) {

        String str = "select * from admin where username=? and password=?";

        try {

            return qr.query(str, new BeanHandler<>(Admin.class), admin.getUsername(), admin.getPassword());

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public Category findByCid(String cid) {

        String str = "select * from category where cid=?";

        try {

            return qr.query(str, new BeanHandler<>(Category.class), cid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public void editCategory(String cid, String cname) {

        String str = "update category set cname=? where cid=?";

        try {

            qr.update(str,cname,cid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
