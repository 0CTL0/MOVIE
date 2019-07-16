package cn.ccttll.common;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接池工具类
 */
public class ConnectionUtil {

    //连接的数据库、用户、密码
    public static String url = "jdbc:mysql://localhost:3306/movie3?useUnicode=true&characterEncoding=UTF-8";
    public  static String user = "root";
    public static String password = "";

    // 创建连接池:
    public static ComboPooledDataSource dataSource = new ComboPooledDataSource();
//方法体外无法执行设置参数的方法
//    public static ComboPooledDataSource dataSource=new ComboPooledDataSource();
//    dataSource.setDriverClass("com.mysql.jdbc.Driver");
//    dataSource.setJdbcUrl("jdbc:mysql:///jdbctest");
//    dataSource.setUser("root");
//    dataSource.setPassword("abc");
    /**
     * 获得数据库的连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn= null;
        try {
            // 设置连接池的参数:
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            dataSource.setJdbcUrl("url");
            dataSource.setUser("user");
            dataSource.setPassword("password");
            //设置最大连接数
            dataSource.setMaxPoolSize(20);
            //设置连接池初始化时的连接数
            dataSource.setInitialPoolSize(3);

            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 释放资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn){
            if(rs!= null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                rs = null;
            }
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                stmt = null;
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn = null;
            }
        }

}
