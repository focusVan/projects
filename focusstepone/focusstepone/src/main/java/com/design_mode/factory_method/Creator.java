package com.design_mode.factory_method;

/**
 * focus Create in 14:38 2019/4/12
 */
public abstract class Creator {
    public abstract <T extends Product> T createProduct(Class<T> clazz);
}
