package com.focustest.highquality;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * focus Create in 18:17 2018/8/23
 */
public class DecoratorPatternTest {
    public static void main(String[] args) {
        Animal jeffy = new Rat();
        //添加飞行能力
        jeffy = new DecoratorAnimal(jeffy, FlyFeature.class);
        //添加钻地能力
        jeffy = new DecoratorAnimal(jeffy, DigFeature.class);
        jeffy.doStuff();
    }
}

interface Animal {
    public void doStuff();
}

class Rat implements Animal {
    @Override
    public void doStuff() {
        System.out.println("rat do stuff");
    }
}

interface Feature {
    public void load();
}

class FlyFeature implements Feature {
    @Override
    public void load() {
        System.out.println("fly feature...");
    }
}

class DigFeature implements Feature {
    @Override
    public void load() {
        System.out.println("dig feature...");
    }
}


class DecoratorAnimal implements Animal {
    //被包装的动物
    private Animal animal;
    //使用哪一个包装器
    private Class<? extends Feature> clz;
    public DecoratorAnimal(Animal animal, Class<? extends Feature> clz) {
        this.animal = animal;
        this.clz = clz;
    }

    @Override
    public void doStuff() {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            //具体的包装行为
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object obj = null;
                //设置包装条件
                if (Modifier.isPublic(method.getModifiers())) {
                    obj = method.invoke(clz.newInstance(), args);
                }
                animal.doStuff();
                return obj;
            }
        };

        Feature proxy = (Feature) Proxy.newProxyInstance(animal.getClass().getClassLoader(), clz.getInterfaces(), invocationHandler);
        proxy.load();
    }
}