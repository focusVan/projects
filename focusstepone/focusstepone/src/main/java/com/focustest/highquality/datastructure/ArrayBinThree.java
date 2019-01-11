package com.focustest.highquality.datastructure;

/**
 * focus Create in 15:39 2019/1/8
 */
public class ArrayBinThree<T> {
    //使用数组来记录该树的所有节点
    private Object[] datas;
    private final int DEFAULT_DEEP = 8;
    private int deep;
    private int arraySize;
    //以默认深度创建二叉树
    public ArrayBinThree() {
        this.deep = DEFAULT_DEEP;
        this.arraySize = (2 << DEFAULT_DEEP - 1) - 1;
        datas = new Object[arraySize];
    }
    //以指定深度创建二叉树
    public ArrayBinThree(int deep) {
        this.deep = deep;
        this.arraySize = (2 << deep - 1) - 1;
        datas = new Object[arraySize];
    }
    //以指定深度，指定根节点创建二叉树
    public ArrayBinThree(int deep, T data) {
        this(deep);
        datas[0] = data;
    }
    // TODO
}
