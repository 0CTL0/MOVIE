package cn.ccttll.service;


import cn.ccttll.bean.Movie;
import cn.ccttll.bean.User;
import cn.ccttll.dao.MovieDao;

import java.util.List;

public class MovieService {
  private MovieDao movieDao;

  public MovieService(){
      movieDao=new MovieDao();
  }

    /**
     * 获得分类页的数据
     * @param movieType
     * @return
     */
    public List<Movie> getMovie(String movieType){
        return movieDao.getMovie(movieType);
    }
    /**
     *获取分类页数据
     * @return
     */
   public List<Movie> getMovie(String movieType,int curentPage,int sum){
    return movieDao.getMovie(movieType,curentPage,sum);
   }

    /**
     * 通过电影ID获取电影
     * @param movieId
     * @return
     */
    public Movie getMovieById(int movieId){
       return movieDao.getMovieById(movieId);
    }

    /**
     * 获取分类页排行榜的数据
     * @param movieType
     * @return
     */
    public List<Movie> getMovieByMovieScore(String movieType){
        return movieDao.getMovieByMovieScore(movieType);
    }

    /**
     * 获取电影分类的总数
     * @param movieType
     * @return
     */
    public int countMovie(String movieType){
        return movieDao.countMovie(movieType);
    }

    /**
     * 模糊查询
     * @param movieName
     * @return
     */
    public List<Movie> getMoviesByMovieName(String movieName) {
        return movieDao.getMoviesByMovieNames(movieName);
    }


}