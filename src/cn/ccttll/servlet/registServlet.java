package cn.ccttll.servlet;

import cn.ccttll.bean.User;
import cn.ccttll.common.UploadUtils;
import cn.ccttll.service.MovieService;
import cn.ccttll.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class registServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void init() throws ServletException {
        super.init();
        userService=new UserService();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //数据表设置了用户名、电话号码、邮箱是唯一索引，重复将导致注册失败，这部分内容的判断以后再处理，现在默认用户不重复操作
        //首先获取表单 ，后台java再次校验，防止黑用户插入不符合规范的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String sex=req.getParameter("sex");



        String usernameRegex = "[a-zA-Z]{6,12}";
        //matches方法的含义是将获取过来的username和usernameRegex这个规则进行比对，如果满足要求则返回true，否则返回false
        boolean flag1 = username.matches(usernameRegex);
        //		密码只能为数字，长度至少为6位：[0-9]{6,}，\\d{6}
        String passRegex = "[0-9]{6,}";
        boolean flag2 = password.matches(passRegex);
        //		手机号校验：[1][3578]\\d{9}
        String phoneRegex ="[1][3578][0-9]{9}";
        boolean flag3 = phone.matches(phoneRegex);
        //		邮箱校验：[a-zA-Z_0-9]{3,}@([a-zA-Z]+|\\d+)(\\.[a-zA-Z]+)+
        String emialRegex ="[a-zA-Z_0-9]{3,}@([a-zA-Z]+|\\d+)(\\.[a-zA-Z]+)+";
        boolean flag4 = email.matches(emialRegex);
        //username、password、email、phone是必填项，必填项符合要求则插入数据，否则返回错误提示
        if(!(flag1&&flag2&&flag3&&flag4)){
            String msg="请按要求的格式输入";
            req.setAttribute("msg",msg);
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/register").forward(req,resp);
        }
        //封装用户数据
        User user=new User(username, password,phone,email,sex,null);
        //将用户数据添加到数据库中，如果成功，则跳转到登录页，失败则返回注册页
        if(userService.insertUser(user)){
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/login.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher(req.getContextPath()+"/WEB-INF/views/biz/regist.jsp").forward(req, resp);
        }

        /*
           //通过enctype="multipart/form-data"上传的表单数据，是二进制数据，常规方法无法获取值，需要用组件获取
            //这里使用fileupload的API来处理数据
            try {
                //定义一个Map集合来存储接收的数据
                Map<String,String> map=new HashMap<String,String>();
                //1、创建一个磁盘文件项工厂对象
                DiskFileItemFactory diskFileItemFactory=new DiskFileItemFactory();
                //2、创建一个核心解析类
                ServletFileUpload servletFileUpload=new ServletFileUpload(diskFileItemFactory);
                //3、解析request请求，返回的是List集合，List集合中存放的是FileItem对象
                List<FileItem> list=servletFileUpload.parseRequest(req);
                //定义一个List集合，用于保存复选框的兴趣爱好数据    因为兴趣项是多个对象，需要数组来存储
                List<String> hobbyList=new ArrayList<String>();
                //4、遍历集合，获得每个FileItem，判断是表单项还是文件上传项
                String url=null;
                for(FileItem fileItem:list) {
                    //如果都是普通表单项
                    if (fileItem.isFormField()) {
                        String name=fileItem.getFieldName();  //获得表单项的name属性的值
                        String value=fileItem.getString("utf-8");  //获得表单项的值
                    }
                    //如果是文件上传项
                    else {
                        //获得文件上传的名称：
                        String fileName=fileItem.getName();
                        if(fileName!=null && !"".equals(fileName)) {
                            //通过工具类获得唯一文件名；
                            String uuidFileName= UploadUtils.getUUIDFileName(fileName);
                            //获得文件上传的数据：
                            InputStream is=fileItem.getInputStream();
                            //定义文件存放的路径：
                            String path=this.getServletContext().getRealPath("/upImg");
                            //将输入流对接到输出流：
                            url=path+"\\"+uuidFileName;
                            OutputStream os=new FileOutputStream(url);
                            int len=0;
                            byte[] b=new byte[1024];
                            while((len=is.read(b))!=-1) {
                                os.write(b,0,len);
                            }
                            is.close();
                            os.close();
                        }
                    }
                }
                //封装用户数据,并插入数据库
                User user=new User();
                user.setUserName(map.get("username"));
                user.setUserPassword(map.get("password"));
                user.setUserSex(map.get("sex"));
                user.setUserEmail(map.get("email"));
                user.setUserPhone(map.get("phone"));
                user.setUserPhoto(map.get("upImg"));

            } catch (FileUploadException e) {
                e.printStackTrace();
         }
        */
    }

}
