package com.design_mode.create_case.template_method;

/**
 * focus Create in 15:11 2019/4/12
 */
public abstract class AbstractClass {
    public abstract void method1();
    public abstract void method2();
    public abstract void method3();
    public void template(){
        method1();
        method2();
        method3();
    }
}
