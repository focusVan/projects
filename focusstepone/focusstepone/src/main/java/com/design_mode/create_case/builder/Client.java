package com.design_mode.create_case.builder;

import java.util.ArrayList;

/**
 * focus Create in 15:30 2019/4/12
 */
public class Client {
    public static void main(String[] args) {
        ArrayList<String> sequence = new ArrayList<>();
        sequence.add("method2");
        sequence.add("method1");
        Builder builder1 = new ConcreteBuilder1();
        builder1.setPart(sequence);
        builder1.buildProduct().template();

        Builder builder2 = new ConcreteBuilder2();
        builder2.setPart(sequence);
        builder2.buildProduct().template();
    }
}
