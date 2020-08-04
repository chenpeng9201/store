package com.itheima.web;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends BaseServlet {
    //bean工厂获取业务层接口实现类
    ProductService ps = BeanFactory.newInstance(ProductService.class);

    void findNewAndHot(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //调用两个业务层方法获取数据
        List<Product> psNew = ps.findNew();//最新商品
        List<Product> psIsHot = ps.findIsHot();//最热商品

        /**
         * data:2020.08.04
         * author:chenp
         * desc:改用hashmap方式存放响应数据
         * */
        /*List<Product>[] psarr = new List[]{psNew, psIsHot};*/
        Map<String,List<Product>> productmap = new HashMap<>();
        productmap.put("new",psNew);
        productmap.put("hot",psIsHot);
        Result result = new Result(Result.SUCCESS,"查找成功",productmap);
        response.getWriter().print(JSONObject.fromObject(result));

    }

    void findById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String pid = request.getParameter("pid");
        Product pinfo = ps.pinfo(pid);
        Result result = new Result(Result.SUCCESS,"查找成功",pinfo);
        response.getWriter().print(JSONObject.fromObject(result));
    }
}
