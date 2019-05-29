package com.design_mode.create_case.prototype;

/**
 * focus Create in 16:05 2019/4/12
 */
public class Prototype implements Cloneable{
    @Override
    public Prototype clone() {
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        } catch (Exception e) {}
        return prototype;
    }
}
