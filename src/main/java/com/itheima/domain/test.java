package com.itheima.domain;

public class test {
    public static void main(String[] args) {

        /*String sxh = test.is_sxh(135);
        System.out.println(sxh);*/
        test.divisible();
    }
    public static String is_sxh (int num){
        String s = Integer.toString(num);
        int a = Integer.parseInt(s.substring(0,1));
        int b = Integer.parseInt(s.substring(1,2));
        int c = Integer.parseInt(s.substring(2,3));
        if(a*a*a+b*b*b+c*c*c==num)
            return num+"是水仙花数";
        return num+"不是水仙花数";

    }

    public static void divisible(){
        //求从1 开始第35个能被7和3整除的整数数
        int i = 1;
        int j = 1;
        while(true){
            if(i%7==0&&i%3==0){
                System.out.println(i+"是第"+j+"个可以被7和3整除的整数");
                if(j==35)
                    return;
                j++;
            }
            i++;

        }
    }
}
