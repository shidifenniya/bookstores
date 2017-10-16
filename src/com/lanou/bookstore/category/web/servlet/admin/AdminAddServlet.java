package com.lanou.bookstore.category.web.servlet.admin;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.utils.ImageResizer;
import com.lanou.commons.CommonUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
@WebServlet("/AdminAddServlet")
public class AdminAddServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    private ImageResizer imageResizer = new ImageResizer();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /*
        上传三步

        1. 得到工厂
        2. 通过工厂, 创建解析器
        3. 解析 request, 得到 FileItem 的集合
        4. 遍历 FileItem 集合, 调用相关API
        */

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

        ServletFileUpload sfu = new ServletFileUpload(diskFileItemFactory);

        Book book = new Book();

        try {

            List<FileItem> fileItems = sfu.parseRequest(request);



            FileItem fibname = fileItems.get(0);

            book.setBname(fibname.getString("UTF-8"));

            FileItem fiprice = fileItems.get(2);

            book.setPrice(fiprice.getString("UTF-8"));

            FileItem fiauthor = fileItems.get(3);

            book.setAuthor(fiauthor.getString("UTF-8"));

            FileItem ficid = fileItems.get(4);

            book.setCid(ficid.getString("UTF-8"));

            //取出图片

            FileItem fiimage = fileItems.get(1);

            String saveName = CommonUtils.uuid() + "_" + fiimage.getName();

            //获取项目名路径


            String rootName = this.getServletContext().getRealPath("/book_img/");

            String imageFile = rootName + saveName;

            //设置保存文件路径
            File file = new File(imageFile);

            //开始写入文件
            fiimage.write(file);


            //更改保存图片的宽高
            imageResizer.resizeImage(imageFile,imageFile,110,150);

            String imageUrl = "book_img/" + saveName;

            //保存进入book
            book.setImage(imageUrl);

            System.out.println(book);

            //将图书信息放入数据库
            bookService.saveBook(book);


            //调用findAll,并转发list.jsp
            List<Book> bookList = bookService.findAll();

            request.setAttribute("bookList",bookList);

            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);


        } catch (FileUploadException e) {

            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
    }
}