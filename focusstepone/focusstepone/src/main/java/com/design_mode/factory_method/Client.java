package com.design_mode.factory_method;

/**
 * focus Create in 14:45 2019/4/12
 */
public class Client {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        product.method1();
    }
}
