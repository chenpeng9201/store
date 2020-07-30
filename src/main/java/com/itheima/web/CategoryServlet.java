package com.itheima.web;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {
    //BeanFactory获取业务层实现类对象
    private CategoryService categoryService = BeanFactory.newInstance(CategoryService.class);
    //返回所有分类
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws IOException {
        List<Category> categoryList = categoryService.findAll();
        Result rs = new Result(Result.SUCCESS,"查找成功",categoryList);
        response.getWriter().print(JSONObject.fromObject(rs));
    }
}
