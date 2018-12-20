package com.focustest.highquality;

/**
 * focus Create in 9:56 2018/12/7
 */
public class FieldAndMethodTest {
    public static void main(String[] args) {
        FieldBase base = new FieldBase();
        System.out.println(base.count);
        base.display();
        System.out.println();

        FieldDerived derived = new FieldDerived();
        System.out.println(derived.count);
        derived.display();
        System.out.println();

        FieldBase baseD = new FieldDerived();
        System.out.println(baseD.count);
        baseD.display();
        System.out.println();

        FieldBase baseDerived = derived;
        System.out.println(baseDerived.count);
        System.out.println(baseDerived.count == derived.count);
    }
}

class FieldBase {
    public int count = 2;
    public void display() {
        System.out.println(this.count);
    }
}

class FieldDerived extends FieldBase {
    int count = 20;
    @Override
    public void display() {
        System.out.println(this.count);
    }
}
