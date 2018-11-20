package com.focustest.highquality;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * focus Create in 17:28 2018/8/21
 */
public class EqualsTest {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            EqualsTest demo = (EqualsTest)obj;
            if (demo.getName() == null || name == null) {
                return false;
            } else {
                return name.equalsIgnoreCase(demo.getName());
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).toHashCode();
    }
}
