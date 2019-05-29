package com.design_mode.create_case.singleton;

/**
 * focus Create in 14:16 2019/4/12
 * 饿汉式，初始化静态的singleton创建一次
 * 缺点：没有lazy loading效果，从而降低了内存使用率
 */
public class Singleton {
    private Singleton(){}
    private static final Singleton singleton = new Singleton();
    public static Singleton getInstance() {
        return singleton;
    }
}
