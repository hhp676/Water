package com.hongguaninfo.hgdf.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

public class JdbcConnection {

    private static final Log LOG = LogFactory.getLog(JdbcConnection.class);

    private String driver;
    private String url;
    private String username;
    private String password;

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement prestmt = null;

    private static JdbcConnection jdbcConnection = null;

    /**
     * 构造方法,只调用init方法,此方法不需要提供属性文件路径，会自动在类加载路径下搜寻database.properties文件，默认即src目录下
     * 。
     */
    public JdbcConnection() {
        init();
    }

    public static JdbcConnection getInstance() {
        if (jdbcConnection == null) {
            jdbcConnection = new JdbcConnection();
        }
        return jdbcConnection;
    }

    /**
     * 实例化方法，指定数据库名字，该类会得到属性配置文件里面配置的参数。
     * 
     * @param databaseName
     *            数据库名
     * @throws Exception
     */
    private void init() {
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            LOG.error("init fail!", e);
            return;
        }
    }

    /**
     * getConnection()方法会返回一个连接上构造此类时制定的数据库的Connection.
     * 
     * @return 目标连接数据库的连接
     */
    public Connection getConnection() {
        init();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            LOG.error("exeUpdate fail!", e);
        }
        if (conn == null) {
            LOG.debug("警告: DriverManager.getConnection() 获得数据库链接失败.\r\n\r\n链接类型:" + driver + "\r\n链接位置:" + url
                    + "\r\n用户/密码" + username + "/" + password);
        }
        return conn;
    }

    /**
     * 功能：执行查询语句 executeQuery()方法执行查询语句并且返回一个ResultSet，封装了执行结果。
     * 
     * @param sql
     *            想要执行的sql语句
     * @param update
     *            是否返回允许更新的ResultSet
     * @param objects
     *            此sql语句需要处理的预编译参量
     * @return rs 查询结果过对应的ResultSet
     * 
     */
    public ResultSet exeQuery(String sql, boolean update, Object... objects) throws SQLException {
        conn = getConnection();
        if (update) {
            prestmt = conn.prepareStatement(sql, ResultSet.CONCUR_UPDATABLE, ResultSet.TYPE_SCROLL_INSENSITIVE);
        } else {
            prestmt = conn.prepareStatement(sql);
        }
        for (int i = 0; i < objects.length; i++) {
            prestmt.setObject(i + 1, objects[i]);
        }
        rs = prestmt.executeQuery();
        return rs;
    }

    /**
     * 功能：执行查询语句 executeQuery()方法执行查询语句并且返回一个ResultSet，封装了执行结果。
     * 
     * @param sql
     *            要执行的查询语句，此语句是预编译语句。
     * @return 执行给定查询语句后返回封装了执行结果的ResultSet<br>
     *         注意：此ResultSet是可滚动但通常不受 ResultSet 底层数据更改影响,并且不可更新的。
     */
    public ResultSet exeQuery(String sql) {
        try {
            conn = getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            LOG.error("exeQuery fail!", ex);
        }
        return rs;
    }

    /**
     * 功能:执行更新操作 executeUpdate()方法执行更新语句，并且返回一个int值，包含给定查询所生成数据的 ResultSet 对象
     * 
     * @param sql
     *            要执行的查询语句，此语句是预编译语句
     * @param objects
     *            此sql语句需要处理的预编译参量
     * @return result 包含给定sql语句操作的数据条数，如果执行不成功，则返回0
     */
    public int exeUpdate(String sql, Object... objects) {
        int result;
        try {
            conn = getConnection();
            prestmt = conn.prepareStatement(sql, ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            for (int i = 0; i < objects.length; i++) {
                prestmt.setObject(i + 1, objects[i]);
            }
            result = prestmt.executeUpdate();
        } catch (SQLException e) {
            LOG.error("exeUpdate fail!", e);
            result = 0;
        }
        return result;

    }

    /**
     * 功能:执行更新操作 executeUpdate()方法执行更新语句，并且返回一个int值，包含给定查询所生成数据的 ResultSet 对象
     * 
     * @return result 包含给定sql语句操作的数据条数，如果执行不成功，则返回0
     */
    public int exeUpdate(String sql) {
        int result = 0;
        try {
            conn = getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            result = stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            result = 0;
        }
        return result;
    }

    /**
     * 功能:关闭数据库的连接 close()方法关闭所使用的资源.
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (prestmt != null) {
                prestmt.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            LOG.error("close fail!", e);
        }
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
