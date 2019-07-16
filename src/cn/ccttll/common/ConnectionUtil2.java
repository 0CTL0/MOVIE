package cn.ccttll.common;

import java.sql.*;

/**
 * 数据库公共类
 */
public final class ConnectionUtil2 {

    //数据库参数
    private static String url = "jdbc:mysql://localhost:3306/movie3?useUnicode=true&characterEncoding=UTF-8";
    //?useUnicode=true&characterEncoding=UTF-8
    private static String user = "root";
    private static String password = "";

    private ConnectionUtil2(){}

    //加载驱动
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Load Driver  Failure!");
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库连接
     * @return
     */
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            System.out.println("Connection  Failure!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 释放资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
