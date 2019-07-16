package cn.ccttll.service;

import cn.ccttll.bean.User;
import cn.ccttll.dao.UserDao;

public class UserService {
    private UserDao userDao;

    public UserService(){

        userDao=new UserDao();
    }

    /**
     * 添加用户到数据库中
     * @param user
     */

    public boolean insertUser(User user) {
       return userDao.insertUser(user);
    }

    /**
     * 登录验证
     * @param userName
     * @param userPassword
     * @return
     */
    public String login(String userName, String userPassword) {
        return userDao.login(userName,userPassword);
    }
}
