package mona.joyhentai.service.impl;

import mona.joyhentai.dao.BooksRepeatMapper;
import mona.joyhentai.model.BooksRepeat;
import mona.joyhentai.service.BooksRepeatService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author manonmona
 * @@Date 2021/10/12 0:18
 */
public class BooksRepeatServiceImpl implements BooksRepeatService {

    SqlSessionFactory sqlSessionFactory;
    public BooksRepeatServiceImpl(){
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
    public boolean addBooksRepeat(BooksRepeat booksRepeat) {
        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            BooksRepeatMapper booksRepeatMapper = session.getMapper(BooksRepeatMapper.class);
            //调用代理对象方法
            int i = booksRepeatMapper.insert(booksRepeat);
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
