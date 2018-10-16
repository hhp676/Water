package com.hongguaninfo.hgdf.core.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @ClassName: ConnectionPool
 * @Description: 连接池工具类
 * @author henry
 * @date 2014-2-10 下午6:37:59
 * 
 */
public class ConnectionPool extends Thread {

    private static ConnectionPool connectionPool = new ConnectionPool();
    private BlockingQueue<Connection> connectionQueue = new ArrayBlockingQueue<Connection>(64);
    private Lock lock = new ReentrantLock();
    private boolean poolActive = true;

    private String dbDriver = "";
    private String dbUrl = "";
    private String dbUserName = "";
    private String dbPwd = "";
    private int poolSize = 10;

    private String connectionCheckDesc1 = "";
    private String connectionCheckDesc2 = "";
    private String connectionCheckDesc3 = "";
    private String connectionCheckDesc4 = "";

    private boolean initPoolSuccess = false;
    private boolean poolCheckRunning = false;

    public static ConnectionPool getInstance() {
        return connectionPool;
    }

    // 只允许初始化成功后，启动一次
    public boolean startService() {
        if (this.initPoolSuccess && poolCheckRunning == false) {
            this.start();
            this.poolCheckRunning = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean stopService() {
        this.poolActive = false;
        return true;
    }

    public void run() {
        while (this.poolActive) {
            try {
                // 检查数据库连接
                for (int i = 0; i < this.connectionQueue.size(); i++) {
                    Connection conn = this.connectionQueue.poll(1, TimeUnit.SECONDS);
                    if (conn != null) {
                        try {
                            // 检查数据库连接
                            conn.setAutoCommit(false);
                            conn.commit();
                            conn.setAutoCommit(true);
                            this.connectionQueue.offer(conn);
                        } catch (Exception e) {
                            // 数据库连接异常
                            this.connectionCheckDesc1 = "Time:" + System.currentTimeMillis() + ",connectionCheckDesc1:"
                                    + e.getMessage();
                        }
                    }
                }

                // 数据库连接数小于阀值,则批量创建10个数据库连接
                if (this.connectionQueue.size() < 10) {
                    this.connectionCheckDesc2 = "Time:" + System.currentTimeMillis()
                            + ",connectionCheckDesc2:connectionQueue.size() = " + connectionQueue.size();
                    try {
                        for (int i = 0; i < 10; i++) {
                            Connection connection = this.createConnection(this.dbDriver, this.dbUrl, this.dbUserName,
                                    this.dbPwd);
                            boolean isAdded = this.connectionQueue.offer(connection);
                            if (!isAdded) {
                                if (connection != null && !connection.isClosed()) {
                                    connection.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        this.connectionCheckDesc3 = "Time:" + System.currentTimeMillis() + ",connectionCheckDesc3:"
                                + e.getMessage();
                    }
                }

                Thread.sleep(60 * 1000);

            } catch (Exception e) {
                this.connectionCheckDesc4 = "Time:" + System.currentTimeMillis() + ",connectionCheckDesc4:"
                        + e.getMessage();
            }
        }
    }

    public void initParameter(String dbDriver, String dbUrl, String dbUserName, String dbPwd, int poolSize) {
        this.dbDriver = dbDriver;
        this.dbUrl = dbUrl;
        this.dbUserName = dbUserName;
        this.dbPwd = dbPwd;
        this.poolSize = poolSize;
    }

    // 创建连接
    private Connection createConnection(String driver, String url, String userName, String pwd) throws Exception {
        try {
            this.lock.lock();
            if (driver == null || url == null || userName == null || pwd == null) {
                throw new Exception("connection parameter is null.");
            }

            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, userName, pwd);
            return conn;
        } finally {
            this.lock.unlock();
        }
    }

    // 初始化数据库连接
    public void initConnection() throws Exception {
        // 清除原有队列中数据库连接，清除不完全
        if (this.connectionQueue != null) {
            for (int i = 0; i < this.connectionQueue.size(); i++) {
                Connection conn = this.connectionQueue.poll(1, TimeUnit.SECONDS);
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            }
        }

        // 初始化数据库连接
        for (int i = 0; i < this.poolSize; i++) {
            Connection connection = this.createConnection(this.dbDriver, this.dbUrl, this.dbUserName, this.dbPwd);
            this.connectionQueue.offer(connection);
        }

        this.initPoolSuccess = true;
    }

    // 取得连接
    public Connection getConnection() throws Exception {
        Connection connection = null;
        connection = this.connectionQueue.poll(1, TimeUnit.SECONDS);

        // 没有获取到连接
        if (connection == null) {
            connection = this.createConnection(this.dbDriver, this.dbUrl, this.dbUserName, this.dbPwd);
        }

        // 连接已经关闭
        if (connection != null && connection.isClosed()) {
            connection = this.createConnection(this.dbDriver, this.dbUrl, this.dbUserName, this.dbPwd);
        }

        return connection;
    }

    // 将连接放入池中
    public void putConnection(Connection connection) throws Exception {
        if (connection != null) {
            if (!connection.isClosed()) {
                this.connectionQueue.offer(connection);
            }
        }
    }

    public int getCurrentPoolSize() {
        return this.connectionQueue.size();
    }

    public boolean isPoolCheckRunning() {
        return this.poolCheckRunning;
    }

    public String getCheckDesc() {
        return connectionCheckDesc1 + connectionCheckDesc2 + connectionCheckDesc3 + connectionCheckDesc4;
    }
}
