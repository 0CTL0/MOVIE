package cn.ccttll.servlet;

import cn.ccttll.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService=new UserService();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName=req.getParameter("username");
        String userPassword=req.getParameter("password");
        String uName=userService.login(userName,userPassword);
        //通过登录验证，创建session,跳转到首页
        if(uName!=null && !"".equals(uName)){
            HttpSession session=req.getSession();
            session.setAttribute("userName",uName);
            req.getRequestDispatcher(req.getContextPath()+"/home.do").forward(req, resp);
        }
        else {
            req.setAttribute("msg","你输入的用户名或者密码不正确！");
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/home.jsp").forward(req, resp);
        }
    }
}
