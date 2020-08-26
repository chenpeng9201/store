package com.itheima.dao.impl;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;
import com.itheima.utils.C3P0Utils;
import com.itheima.utils.JedisUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import redis.clients.jedis.Jedis;

import java.sql.SQLException;
import java.util.List;

public class CategoryImpl implements CategoryDao {
    QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
    //redis版本
    /*@Override
    public List<Category> findAll() throws SQLException {
        List<Category> categoryList = null;
        Jedis jedis = JedisUtils.getJedis();
        String category = jedis.get("category");
        if(category == null){
            //如果redis中不存在，则从mysql中查
            String sql = "select * from category";
            categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
            //再将查出来的结果存入redis中
            jedis.set("category", JSONArray.fromObject(categoryList).toString());
        }else{
            //redis中存在，则将字符串转换成集合
            categoryList = JSONArray.toList(JSONArray.fromObject(category), Category.class);
        }
        //关闭连接
        JedisUtils.close(jedis);
        return categoryList;
    }*/

    @Override
    public List<Category> findAll() throws SQLException {
        String sql = "select * from category";
        List<Category> categoryList = qr.query(sql, new BeanListHandler<Category>(Category.class));
        return categoryList;
    }



}
