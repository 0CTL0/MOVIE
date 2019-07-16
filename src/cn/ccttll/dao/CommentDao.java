package cn.ccttll.dao;

import cn.ccttll.bean.Comment;
import cn.ccttll.bean.Movie;
import cn.ccttll.common.ConnectionUtil;
import cn.ccttll.common.ConnectionUtil2;
import cn.ccttll.common.SubCEStrUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * 获取某ID电影的全部评论
     * @param movieId
     * @return
     */
    public List<Comment> getCommentsByMovieId(int movieId){
            List<Comment> comments=new ArrayList<Comment>();
            try {
                conn= ConnectionUtil2.getConnection();
                String sql="SELECT * FROM comments  WHERE movieId =? ORDER BY commentId DESC;";
                //SELECT * FROM comments WHERE movieId=19329;
                pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1,movieId);
                rs=pstmt.executeQuery();
                while (rs.next()){
                    comments.add(new Comment(
                            rs.getInt("movieId"),
                            rs.getString("userName"),
                            rs.getString("comment"),
                            rs.getString("commentDataTime"),
                            rs.getString("userPhoto")
                    ));
                }
            } catch (SQLException e) {
                System.out.println("SELECT  DATABASE  Failure");
                comments=null;
                e.printStackTrace();
            } finally {
                ConnectionUtil.release(rs,pstmt,conn);
            }
            return comments;
    }


    /**
     * 将评论添加到评论表
     */
    public void subComment(Comment comment) {
        try {
            conn= ConnectionUtil2.getConnection();
            String sql="INSERT comments(movieId,userName,comment,commentDataTime,userPhoto) VALUES(?,?,?,?,?);";
//          INSERT comments(movieId,userName,comment,commentDataTime,userPhoto)
//            VALUES("1","ctl","好看","2017-8-9 12:00:00",NULL);
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1,comment.getMovieId());
            pstmt.setString(2,comment.getUserName());
            pstmt.setString(3,comment.getComment());
            pstmt.setString(4,comment.getCommentDataTime());
            pstmt.setString(5,comment.getUserPhoto());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionUtil.release(rs,pstmt,conn);
        }
    }




}
