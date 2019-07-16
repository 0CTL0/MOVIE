package cn.ccttll.dao;

import cn.ccttll.bean.User;
import cn.ccttll.common.ConnectionUtil;
import cn.ccttll.common.ConnectionUtil2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    /**
     * 验证用户登录是否正确，并返回用户名
     * @param userName
     * @param userPassword
     * @return
     */
    public String login(String userName, String userPassword) {
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="SELECT userName FROM users WHERE userName=? AND userPassword=?";
            //SELECT userName FROM users WHERE userName='ctl' AND userPassword='666666'
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,userName);
            pstmt.setString(2,userPassword);
            rs=pstmt.executeQuery();
            while (rs.next()) {
                String uName= rs.getString("userName");
                return  uName;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return null;
    }

    /**
     * 将注册的用户信息添加到用户表中
     * @param user
     */
    public boolean insertUser(User user) {
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="INSERT users(userName,userPassword,userPhone,userEmail,userSex,userPhoto) VALUES(?,?,?,?,?,?);";
//           INSERT users(userName,userPassword,userPhone,userEmail,userSex,userPhoto)
//            VALUES("ctl","666666","13570416750","643688809@qq.com","男","C:IMG");
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,user.getUserName());
            pstmt.setString(2,user.getUserPassword());
            pstmt.setString(3,user.getUserPhone());
            pstmt.setString(4,user.getUserEmail());
            pstmt.setString(5,user.getUserSex());
            pstmt.setString(6,user.getUserPhoto());
            int i=pstmt.executeUpdate();
            while (i>0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
        return false;
    }
}
