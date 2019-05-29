package com.design_mode.create_case.builder;

import java.util.ArrayList;

/**
 * focus Create in 15:28 2019/4/12
 */
public abstract class Builder {
    public abstract void setPart(ArrayList<String> sequence);
    public abstract Product buildProduct();
}
