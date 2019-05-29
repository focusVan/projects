package com.design_mode.create_case.template_method;

/**
 * focus Create in 15:13 2019/4/12
 */
public class Client {
    public static void main(String[] args) {
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        class1.template();
        class2.template();
    }
}
