package data;

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: QXQ
 * @time: 2020/7/26 14:33
 * @desc: 仿mybatis官方测试类构造数据
 */
public abstract class AbstractData {

    public static final String DATABASE_PROPERTIES = "derby.properties";
    public static final String SCHEMA_SQL = "derby-schema.sql";
    public static final String INIT_SQL = "derby-data-init.sql";

    /**
     * 从 resource 资源路径获取 数据配置
     * 创建一个数据源（无池化）
     * @param resource
     * @return
     * @throws IOException
     */
    public static UnpooledDataSource createUnpooledDataSource(String resource) throws IOException {
        Properties props = Resources.getResourceAsProperties(resource);
        UnpooledDataSource ds = new UnpooledDataSource();
        ds.setDriver(props.getProperty("driver"));
        ds.setUrl(props.getProperty("url"));
        ds.setUsername(props.getProperty("username"));
        ds.setPassword(props.getProperty("password"));
        return ds;
    }

    /**
     * 运行 sql 脚本
     * 通过读取resource路径的文件
     * @param ds
     * @param resource
     * @throws IOException
     * @throws SQLException
     */
    public static void runScript(DataSource ds, String resource) throws IOException, SQLException {
        Connection connection = ds.getConnection();
        try {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setAutoCommit(true);
            runner.setStopOnError(false);
            runner.setLogWriter(null);
            runner.setErrorLogWriter(null);

            Reader reader = Resources.getResourceAsReader(resource);
            try {
                runner.runScript(reader);
            } finally {
                reader.close();
            }
        } finally {
            connection.close();
        }
    }

    /**
     * 创建数据库并初始化（derby内置数据库）
     * @return
     * @throws IOException
     * @throws SQLException
     */
    public static DataSource createDatabase() throws IOException, SQLException {
        DataSource ds = createUnpooledDataSource(DATABASE_PROPERTIES);
        runScript(ds, SCHEMA_SQL);
        runScript(ds, INIT_SQL);
        return ds;
    }
}
