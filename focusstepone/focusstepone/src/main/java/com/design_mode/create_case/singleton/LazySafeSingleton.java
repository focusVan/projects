package com.design_mode.create_case.singleton;

/**
 * focus Create in 11:19 2019/4/25
 * 定义一个私有内部类，在第一次用这个嵌套类时，会创建一个实例；
 * 而类型为LazySafeSingletonHolder类只有在LazeSafeSingleton.getInstance中调用
 */
public class LazySafeSingleton {
    private LazySafeSingleton(){}
    private static class LazeSafeSingletonHolder {
        private final static LazySafeSingleton instance = new LazySafeSingleton();
    }
    public static LazySafeSingleton getInstance() {
        return LazeSafeSingletonHolder.instance;
    }
}
