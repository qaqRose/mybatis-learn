package cache;

import data.AbstractData;
import domain.Book;
import mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;

/**
 * @author: QXQ
 * @time: 2020/7/26 14:55
 * @desc: cache测试类
 */
public class SimpleCacheTest extends AbstractData {

    private static SqlSessionFactory sqlSessionFactory;

    @Before
    public void setup() throws Exception {
        createDatabase();
//        final String resource = "config/mybatis-config.xml";
        final String resource = "config/mybatis-config-simple.xml";
        final Reader reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void getDataFromSqlSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book byId = mapper.getById(1);
        System.out.println(byId);
    }

    @Test
    public void testCacheInSqlSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book byId = mapper.getById(1);
        System.out.println(byId);
    }

    @Test
    public void testCacheBetweenSqlSession() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);

        BookMapper mapper = sqlSession1.getMapper(BookMapper.class);
        Book byId = mapper.getById(1);
        System.out.println(byId);

        sqlSession1.close();

        mapper = sqlSession2.getMapper(BookMapper.class);
        byId = mapper.getById(1);
        System.out.println(byId);

    }
}
