package com.itheima.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
    /*
    *购物车：
    * 购物项(多个)
    * 总金额
    */
    private Map<String,CartItem> itemMap = new HashMap<>();
    private double totalprice;

    public Collection getCartItem() {
        return itemMap.values();
    }

    public void setCartItem(Map<String, CartItem> cartItem) {
        this.itemMap = cartItem;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public void addCart(CartItem cartItem){
        String pid = cartItem.getProduct().getPid();
        if(itemMap.containsKey(pid)){
            //如果存在此商品,则把数量增加即可
            int count = itemMap.get(pid).getCount();
            count += cartItem.getCount();
            itemMap.get(pid).setCount(count);
        }else{
            //如果不存在此商品,则把购物项加到购物车中
            itemMap.put(cartItem.getProduct().getPid(),cartItem);
        }
        //重新计算购物车总价格
        totalprice += cartItem.getSubtotal();
    }
}
