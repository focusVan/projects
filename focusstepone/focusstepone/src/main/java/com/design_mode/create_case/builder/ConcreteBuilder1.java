package com.design_mode.create_case.builder;

import java.util.ArrayList;

/**
 * focus Create in 15:29 2019/4/12
 */
public class ConcreteBuilder1 extends Builder{
    private Product product = new ConcreteProduct1();
    @Override
    public void setPart(ArrayList<String> sequence) {
        this.product.setSequence(sequence);
    }

    @Override
    public Product buildProduct() {
        return this.product;
    }
}
