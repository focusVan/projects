package com.design_mode.factory_method;

/**
 * focus Create in 14:43 2019/4/12
 */
public class ConcreteCreator extends Creator{

    @Override
    public <T extends Product> T createProduct(Class<T> clazz) {
        Product product = null;
        try {
            product = (Product)Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {
            System.out.println(e);
        }
        return (T)product;
    }
}
