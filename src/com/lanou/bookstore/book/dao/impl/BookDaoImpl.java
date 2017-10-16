package com.lanou.bookstore.book.dao.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookDaoImpl implements BookDao {

    private QueryRunner qr = new GxQueryRunner();

    @Override
    public List<Book> findAll() {

        String str = "select * from book where del=?";

        try {

            // 全表查询 返回集合 list

            return qr.query(str, new BeanListHandler<>(Book.class), false);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public Category findByCategory(String cname) {
        String str = "select * from category where cname=?";

        try {

            // 分类查询 返回集合 list

            return qr.query(str, new BeanHandler<>(Category.class),cname);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Book> findByCid(String cid) {

        String str = "select * from book where cid=? and del=?";

        try {

            // 分类查询 返回集合 list

            return qr.query(str, new BeanListHandler<>(Book.class),cid, false);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public Book loadByBid(String bid) {

        String str = "select * from book where bid=? and del=?";

        try {

            // 通过 bid 查找 book 并封装成 book 对象

            return qr.query(str, new BeanHandler<>(Book.class),bid, false);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }
    }

    @Override
    public void saveBook(Book book) {

        String str = "insert into book (bid,bname,price,author,image,cid,del) values(?,?,?,?,?,?,?)";

        Object[] obj = {book.getBid(),book.getBname(),book.getPrice(),book.getAuthor(),book.getImage(),book.getCid(),false};


        try {

            qr.update(str,obj);

        } catch (SQLException e) {

            throw  new  RuntimeException(e);

        }

    }

    @Override
    public void delBook(String bid) {

        String str = "UPDATE book SET del=? WHERE bid=?";

        try {

            qr.update(str,true,bid);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    @Override
    public void editBook(Book book) {

        String str = "UPDATE book SET bname=?,price=?,author=?,cid=? WHERE bid=?";

        Object[] obj = {book.getBname(),book.getPrice(),book.getAuthor(),book.getCid(),book.getBid()};

        try {

            qr.update(str,obj);

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }


}
