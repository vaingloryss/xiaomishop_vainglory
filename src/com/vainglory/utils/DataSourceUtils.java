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
            log.info("Druid初始化失败.");
            e.printStackTrace();
        }
    }
    public static DruidDataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection(){
        Connection conn = threadLocal.get();
        if (conn==null){
            try {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    //开启事务
    public static void startTransaction() {
        Connection conn = getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //提交事务
    public static void commit(){
        Connection conn = getConnection();
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //回滚事务
    public static void rollback(){
        Connection conn = getConnection();
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(){
        Connection conn = getConnection();
        try {
            conn.close();
            threadLocal.remove();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
