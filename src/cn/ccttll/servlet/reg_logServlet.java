package cn.ccttll.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class reg_logServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str=req.getParameter("page");
        if (Objects.equals("登录", str)) {
            //这里使用重定向不行？
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/login.jsp").forward(req, resp);
        }else if (Objects.equals("注册", str)) {
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/regist.jsp").forward(req, resp);
        }
    }
}
