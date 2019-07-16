package cn.ccttll.servlet;

import cn.ccttll.bean.Comment;
import cn.ccttll.bean.Movie;
import cn.ccttll.service.CommentService;
import cn.ccttll.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class detailServlet extends HttpServlet {

    private MovieService movieService;
    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        super.init();
        movieService=new MovieService();
        commentService=new CommentService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int movieId=Integer.parseInt(req.getParameter("movieId"));
        Movie movie=movieService.getMovieById(movieId);
        req.setAttribute("movie",movie);


        List<Comment> comments=commentService.getCommentsByMovieId(movieId);
        req.setAttribute("comments",comments);

        req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/detail.jsp").forward(req, resp);
    }
}
