package mona.joyhentai.service.impl;

import mona.joyhentai.dao.BooksPagesMapper;
import mona.joyhentai.model.BooksPages;
import mona.joyhentai.service.BooksPagesService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 23:10
 */
public class BooksPagesServiceImpl implements BooksPagesService {
    SqlSessionFactory sqlSessionFactory;
    public BooksPagesServiceImpl(){
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
    public boolean addBooksPage(BooksPages booksPages) {
        return false;
    }

    @Override
    public boolean addBooksPages(List<BooksPages> booksPages) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksPagesMapper booksPagesMapper = session.getMapper(BooksPagesMapper.class);
            //调用代理对象方法
            int i = booksPagesMapper.addBooksPages(booksPages);
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
    public boolean updateBooksPageStatus(BooksPages booksPages) {
        List<BooksPages> booksPagesList = new ArrayList<>();
        booksPagesList.add(booksPages);
        return this.updateBooksPageStatus(booksPagesList);
    }

    @Override
    public boolean updateBooksPageStatus(List<BooksPages> booksPagesList) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksPagesMapper booksPagesMapper = session.getMapper(BooksPagesMapper.class);
            //调用代理对象方法
            int i = booksPagesMapper.updateBooksPageStatus(booksPagesList);
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
    public List<BooksPages> getNotDownSuccessBooksPages(Integer bookId) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksPagesMapper booksPagesMapper = session.getMapper(BooksPagesMapper.class);
            //调用代理对象方法
            List<BooksPages> booksPagesList = booksPagesMapper.getNotDownSuccessBooksPages(bookId);
            //关闭session
            session.close();
            return booksPagesList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
