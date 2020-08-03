package com.itheima.web;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends BaseServlet {
    //bean工厂获取业务层接口实现类
    ProductService ps = BeanFactory.newInstance(ProductService.class);

    void findNewAndHot(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //调用两个业务层方法获取数据
        List<Product> psNew = ps.findNew();//最新商品
        List<Product> psIsHot = ps.findIsHot();//最热商品

        List<Product>[] psarr = new List[]{psNew, psIsHot};
        Result result = new Result(Result.SUCCESS,"查找成功",psarr);
        response.getWriter().print(JSONObject.fromObject(result));
    }
}
