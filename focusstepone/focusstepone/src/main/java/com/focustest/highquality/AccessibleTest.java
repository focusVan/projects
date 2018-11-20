package com.focustest.highquality;

import java.lang.reflect.Method;

/**
 * focus Create in 16:56 2018/8/23
 */
public class AccessibleTest {
    public static void main(String[] args) throws Exception {
        //反射获得方法
        Method method = Temp.class.getMethod("doStuff");
        //打印出Accessible的值
        System.out.println("Accessible: " + method.isAccessible());
        //执行方法
        method.invoke(new Temp());
    }
}

class Temp {
    public void doStuff() {
        System.out.println("Do Stuff");
    }
}
