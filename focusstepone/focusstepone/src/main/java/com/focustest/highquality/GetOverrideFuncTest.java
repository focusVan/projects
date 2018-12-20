package com.focustest.highquality;

/**
 * focus Create in 17:09 2018/12/6
 */
public class GetOverrideFuncTest extends TheAnimal{
    private String name;
    private double weight;
    public GetOverrideFuncTest(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getDesc() {
        return "name=" + name + " ,weight=" + weight;
    }

    public static void main(String[] args) {
        System.out.println(new GetOverrideFuncTest("wolf", 22));
    }
}

class TheAnimal {
    private String desc;
    public TheAnimal() {
        this.desc = getDesc();
    }
    public String getDesc() {
        return "Animal";
    }
    @Override
    public String toString() {
        return desc;
    }
}


