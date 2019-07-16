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

public class searchServlet extends HttpServlet {
    private MovieService movieService;
    @Override
    public void init() throws ServletException {
        super.init();
        movieService=new MovieService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieName=req.getParameter("movieName");
        List<Movie> searchMovies=new ArrayList<Movie>();
        searchMovies=movieService.getMoviesByMovieName(movieName);
        int resultTotal=searchMovies.size();
        req.setAttribute("resultTotal",resultTotal);
        req.setAttribute("searchMovies",searchMovies);
        req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/search.jsp").forward(req,resp);
    }
}
