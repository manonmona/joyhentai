package mona.joyhentai.service.impl;

import com.github.pagehelper.PageHelper;
import mona.joyhentai.dao.BooksMapper;
import mona.joyhentai.model.Books;
import mona.joyhentai.service.BooksService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 18:01
 */
public class BooksServiceImpl implements BooksService{

    SqlSessionFactory sqlSessionFactory;
    public BooksServiceImpl(){
        // 配置文件
        String resource = "mybatis.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);

            // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addBook(Books books) {
        return false;
    }

    @Override
    public boolean addBooks(List<Books> books) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
            int i = booksMapper.addBooks(books);
            session.commit();
            //关闭session
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBooksStatus(Books books) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
//            for (Integer id:ids) {
            int i = booksMapper.updateBooksStatus(books);
//            }
            session.commit();
            //关闭session
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Books> loadAllBooks() {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
            List<Books> booksList = booksMapper.selectAll();
            //关闭session
            session.close();
            return booksList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteBooks(List<Integer> ids) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
//            for (Integer id:ids) {
                int i = booksMapper.deleteBooks(ids);
//            }
            session.commit();
            //关闭session
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Books> loadBooksByName(Books books) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
            List<Books> booksList = booksMapper.loadBooksByName(books);
            //关闭session
            session.close();
            return booksList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addBuckup(Books books) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
            int i = booksMapper.addBuckup(books);
            session.commit();
            //关闭session
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Books> getNotDownSuccessBooks() {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //设置分页Limit 0,20
            PageHelper.startPage(1,350);
            //调用代理对象方法
            List<Books> booksList = booksMapper.loadNotDownSuccessBooks();
            //关闭session
            session.close();
            return booksList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateBooksDetails(List<Books> books) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksMapper booksMapper = session.getMapper(BooksMapper.class);
            //调用代理对象方法
            int i = booksMapper.updateBooksDetails(books);
            session.commit();
            //关闭session
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
