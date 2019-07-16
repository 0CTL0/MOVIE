package cn.ccttll.servlet;

import cn.ccttll.bean.Movie;
import cn.ccttll.service.MovieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class homeServlet extends HttpServlet {

    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        super.init();
        movieService=new MovieService();
    }



    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取首页数据的值并重定向跳转到首页
        List<Movie> dzMovies=new ArrayList<Movie>();
        List<Movie> xjMovies=new ArrayList<Movie>();
        List<Movie> aqMovies=new ArrayList<Movie>();
        dzMovies=movieService.getMovie("动作片");
        xjMovies=movieService.getMovie("喜剧片");
        aqMovies=movieService.getMovie("爱情片");
        req.setAttribute("dzMovies",dzMovies);
        req.setAttribute("xjMovies",xjMovies);
        req.setAttribute("aqMovies",aqMovies);
        req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/home.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        movieService=null;
    }
}
