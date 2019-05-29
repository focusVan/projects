package com.design_mode.create_case.prototype;

import java.util.ArrayList;

/**
 * focus Create in 16:11 2019/4/12
 */
public class DeepPrototype implements Cloneable{
    private ArrayList<String> list = new ArrayList<>();
    @Override
    public DeepPrototype clone() {
        DeepPrototype deepPrototype = null;
        try {
            deepPrototype = (DeepPrototype)super.clone();
            deepPrototype.list = (ArrayList<String>)this.list.clone();
        } catch (Exception e){}
        return deepPrototype;
    }
}
