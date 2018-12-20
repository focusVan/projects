package com.focustest.highquality;

/**
 * focus Create in 16:41 2018/12/6
 */
public class GetChildVarialbeTest {
    public static void main(String[] args) {
        new Derived();
    }
}

class Base {
    private int i = 2;
    public Base() {
        System.out.println(this.getClass());
        System.out.println(i);
        this.display();
    }
    public void display() {
        System.out.println(i);
    }
}

class Derived extends Base {
    private int i = 22;
    public Derived() {
        i = 222;
    }
    @Override
    public void display() {
        System.out.println(i);
    }
}
