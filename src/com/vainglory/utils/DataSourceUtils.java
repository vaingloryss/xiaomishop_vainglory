package com.vainglory.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class DataSourceUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadLocal;

    static {
        log.info("初始化Druid.");
        threadLocal = new InheritableThreadLocal<>();
        Properties properties = new Properties();
        try(InputStream inputStream = DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties")){
            properties.load(inputStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            log.info("Druid初始化成功.");
        }catch (Exception e){
            log.error("Druid初始化失败："+e.getMessage());
        }
    }
    public static DruidDataSource getDataSource(){
        log.info("获取数据源.");
        return dataSource;
    }

    public static Connection getConnection(){
        log.info("获取数据库连接.");
        Connection conn = threadLocal.get();
        if (conn==null){
            try {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
                log.info("数据库连接获取成功.");
            } catch (SQLException e) {
                log.error("数据库连接获取失败："+e.getMessage());
            }
        }
        return conn;
    }

    //开启事务
    public static void startTransaction() {
        log.info("开启事务.");
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
            log.info("事务开启成功.");
        } catch (SQLException e) {
            log.error("事务开启失败："+e.getMessage());
        }
    }

    //提交事务
    public static void commit(){
        log.info("提交事务.");
        Connection conn = getConnection();
        try {
            conn.commit();
            log.info("事务提交成功.");
        } catch (SQLException e) {
            log.error("事务提交失败："+e.getMessage());
        }
    }
    //回滚事务
    public static void rollback(){
        log.info("回滚事务.");
        Connection conn = getConnection();
        try {
            conn.rollback();
            log.info("事务回滚成功.");
        } catch (SQLException e) {
            log.error("事务回滚失败："+e.getMessage());
        }
    }
    public static void close(){
        log.info("关闭连接.");
        Connection conn = getConnection();
        try {
            conn.close();
            threadLocal.remove();
            log.info("连接关闭成功.");
        } catch (SQLException e) {
            log.error("连接关闭失败："+e.getMessage());
        }
    }
}
