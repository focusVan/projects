package com.design_mode.create_case.builder;

import java.util.ArrayList;

/**
 * focus Create in 15:27 2019/4/12
 */
public abstract class Product {
    private ArrayList<String> sequence = new ArrayList<>();
    public abstract void method1();
    public abstract void method2();
    public void template() {
        for (String action : sequence) {
            if (action.equals("method1")) {
                method1();
            }
            if (action.equals("method2")) {
                method2();;
            }
        }
    }
    public void setSequence(ArrayList<String> list) {
        this.sequence = list;
    }
}
