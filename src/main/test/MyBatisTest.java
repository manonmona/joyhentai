import mona.joyhentai.dao.HostMapper;
import mona.joyhentai.model.Host;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author manonmona
 * @@Date 2021/10/11 17:37
 */
public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        // 配置文件
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        // 使用SqlSessionFactoryBuilder从xml配置文件中创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try {
            //获取session
            SqlSession session = sqlSessionFactory.openSession();
            //获取mapper接口的代理对象
            HostMapper hostMapper = session.getMapper(HostMapper.class);
            //调用代理对象方法
            Host host = hostMapper.selectByPrimaryKey(1);
            System.out.println(host);
            //关闭session
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
