package com.itheima.web;

import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet{
    //使用bean工厂获取业务接口实现类
    UserService userService = BeanFactory.newInstance(UserService.class);
    public void loginOut(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /**
         * 客户端的退出登录
         * 销毁session对象
         * 销毁客户端cookie
         */
        //session失效
        request.getSession().invalidate();
        //使用null覆盖之前的cookie信息
        Cookie cookie = new Cookie("username",null);
        //设置cookie参数
        cookie.setMaxAge(0);//立即销毁
        cookie.setPath(request.getContextPath());
        //设置cookie携带的域名
        cookie.setDomain("itheima331.com");
        response.addCookie(cookie);
        //设置响应结果集
        Result result = new Result(Result.SUCCESS,"退出成功");
        response.getWriter().print(JSONObject.fromObject(result));

    }

    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
        /**
         * 客户端发起AJAX请求,表单数据提交到服务器Servlet
         * Servlet接收客户端请求数据并传递到业务层
         * 调用业务层方法获取返回值
         * 业务层调用持久层方法
         * Servlet将登录结果封装成对象,返回JSON数据
         * 登录成功保存session
         * 用户名姓名保存在cookie中,回写浏览器
         * 客户端判断JSON数据,跳转页面
         * 客户端在页面顶部显示登录的用户名
         **/
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用业务层方法获取用户
        User user = userService.login(username, password);
        //判断user对象
        if(user!=null){
            //查询到数据，用户存在，登录成功
            //将user对象存储到session中
            request.getSession().setAttribute("user",user);
            //创建cookie对象，保存用户名
            Cookie cookie = new Cookie("username",username);
            cookie.setMaxAge(60*10);
            cookie.setPath(request.getContextPath());
            //设置cookie携带的域名
            cookie.setDomain("itheima331.com");
            response.addCookie(cookie);
            //设置响应结果集对象
            Result result = new Result(Result.SUCCESS,"登录成功");
            response.getWriter().print(JSONObject.fromObject(result));

        }else{
            Result result = new Result(Result.FAILS,"登录失败,请检查用户名或密码");
            response.getWriter().print(JSONObject.fromObject(result));
        }

    }
    //用户注册功能
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取页面传过来的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        //封装javaBean
        try {
            BeanUtils.populate(user,parameterMap);
        }  catch (Exception e) {
            e.printStackTrace();
        }
        user.setState(1);
        user.setUid(UUIDUtils.getUUID());
        userService.register(user);
        Result result = new Result(1,"注册成功");
        //将结果对象封装成json
        response.getWriter().print(JSONObject.fromObject(result));
    }
}
