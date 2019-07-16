package cn.ccttll.service;

import cn.ccttll.bean.Comment;
import cn.ccttll.dao.CommentDao;

import java.util.List;

public class CommentService {

    private CommentDao commentDao;
    public CommentService(){
        commentDao=new CommentDao();
    }

    public List<Comment> getCommentsByMovieId(int movieId){
        return  commentDao.getCommentsByMovieId(movieId);
    }


    /**
     * 提交评论到数据库
     * @param comment
     */
    public void subComment(Comment comment) {
        commentDao.subComment(comment);
    }
}
