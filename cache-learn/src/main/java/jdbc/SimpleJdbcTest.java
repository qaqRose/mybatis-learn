package jdbc;

import data.AbstractData;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

/**
 * @author: QXQ
 * @time: 2020/7/26 19:22
 * @desc: jdbc简单使用测试
 */
public class SimpleJdbcTest extends AbstractData {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL= "jdbc:derby:test_db;create=true";
    private static final String NAME = "";
    private static final String PASSWORD = "";
    private static final String QUERY_SQL = "SELECT * FROM book WHERE id = ?";

    /**
     * 创建并初始化一个内置数据库
     * @throws Exception
     */
    @Before
    public void setup() throws Exception {
        createDatabase();
    }

    @Test
    public void jdbc() throws ClassNotFoundException, SQLException, IOException {
        // 1. 加载驱动
        Class.forName(DRIVER);
        //2. 获得数据库的连接
        Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        //3. 创建一个 PreparedStatement
        PreparedStatement statement = connection.prepareStatement(QUERY_SQL);
        // 4. 设置参数
        statement.setString(1, "1");
        // 5. 执行sql语句
        ResultSet resultSet = statement.executeQuery();
        // 6. 从ResultSet获取结果
        while(resultSet.next()) {
            System.out.println("id: " + resultSet.getInt("id"));
            System.out.println("name: " + resultSet.getString("name"));
            System.out.println("typeId: " + resultSet.getInt("type_id"));
        }
    }
}
