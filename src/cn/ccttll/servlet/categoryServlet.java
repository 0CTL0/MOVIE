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
import java.util.Objects;

public class categoryServlet extends HttpServlet {
    private MovieService movieService;

    @Override
    public void init() throws ServletException {
        super.init();
        movieService=new MovieService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //分类的电影数据

        String movieType=req.getParameter("movieType");
        String pageStr = req.getParameter("page");//当前页码
        int page=1;  //页码默认值
        if (null != pageStr && (!"".equals(pageStr))) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        List<Movie> categoryMovies =movieService.getMovie(movieType,page,15);
        int count=movieService.countMovie(movieType);  //分类电影总数
        int last=count%15==0?(count/15):((count/15)+1);


        //GET：如果页面传递的参数安全性不是很重要，采用URL拼接传递更方便，虚拟路径太麻烦
        //获取Servelt的虚拟路径
//        String pathName = req.getServletPath();
//        if (Objects.equals("/dzCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("动作", page, 15);
//            count=movieService.countMovie("动作");
//        } else if (Objects.equals("/aqCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("爱情", page, 15);
//            count=movieService.countMovie("爱情");
//        } else if (Objects.equals("/xjCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("喜剧", page, 15);
//            count=movieService.countMovie("喜剧");
//        } else if (Objects.equals("/dmCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("动漫", page, 15);
//            count=movieService.countMovie("动漫");
//        } else if (Objects.equals("/jqCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("剧情", page, 15);
//            count=movieService.countMovie("剧情");
//        } else if (Objects.equals("/llCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("伦理", page, 15);
//            count=movieService.countMovie("伦理");
//        } else if (Objects.equals("/khCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("科幻", page, 15);
//            count=movieService.countMovie("科幻");
//        } else if (Objects.equals("/jlCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("纪录", page, 15);
//            count=movieService.countMovie("记录");
//         } else if (Objects.equals("/jpCategory.do", pathName)) {
//            categoryMovies = movieService.getMovie("街拍", page, 15);
//            count=movieService.countMovie("街拍");
//        }


        req.setAttribute("last",last);
        req.setAttribute("page",page);
        req.setAttribute("movieType",movieType);
        req.setAttribute("categoryMovies", categoryMovies);

        //获取分类排行榜的数据

        List<Movie> scoreMovies=movieService.getMovieByMovieScore(movieType);
        req.setAttribute("scoreMovies",scoreMovies);
        req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/category.jsp").forward(req, resp);



    }



}
