package cn.ccttll.servlet;

import cn.ccttll.bean.Comment;
import cn.ccttll.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class commentServlet extends HttpServlet {
    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        commentService=new CommentService();
    }
    @Override

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session值，已登录则获取数据插入数据库，否则跳转登录
        HttpSession session = req.getSession();
        String userName=(String)session.getAttribute("userName");
        if(null != userName && (!"".equals(userName))){
            int movieId=Integer.parseInt(req.getParameter("movieId"));
            String comcontent=req.getParameter("input-comment");
            //获取当前系统时间
            LocalDateTime ldt = LocalDateTime.now();
            //定义格式，不显示毫秒
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //转换为字符串
            String commentDataTime = ldt.format(dtf);
            //封装数据
            Comment comment=new Comment(movieId,userName,comcontent,commentDataTime,null);
            //插入评论并刷新当前页面
            commentService.subComment(comment);

           req.getRequestDispatcher(req.getContextPath()+"/home.do").forward(req, resp);
           //为什么死循环，我表单只提交了一次而已
           //req.getRequestDispatcher(req.getContextPath()+"/detail.do").forward(req, resp);
        }else {
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/login.jsp").forward(req, resp);
        }


    }


}
