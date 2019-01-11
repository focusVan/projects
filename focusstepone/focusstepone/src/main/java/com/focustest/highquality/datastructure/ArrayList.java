package com.focustest.highquality.datastructure;

import java.util.Arrays;
import java.util.List;

/**
 * focus Create in 14:10 2018/12/29
 */
public class ArrayList<T> {
    private final int DEFAULT_SIZE = 16;
    List list = new java.util.ArrayList();
    //保存数组的长度
    private int capacity;
    //定义一个数组用于保存顺序线性表的元素
    private Object[] elementDatas;
    //保存顺序表中元素的当前个数
    private int size = 0;
    //以默认长度创建空的顺序表
    public ArrayList() {
        capacity = DEFAULT_SIZE;
        elementDatas = new Object[capacity];
    }
    //以一个初始化元素创建顺序线性表
    public ArrayList(T element) {
        this();
        elementDatas[0] = element;
        size++;
    }
    //以指定长度的数组来创建顺序线性表
    public ArrayList(T element, int initSize) {
        capacity = 1;
        //把capacity设为大于initSize的最小的2的n次方
        while (capacity < initSize) {
            capacity <<= 1;
        }
        elementDatas = new Object[capacity];
        elementDatas[0] = element;
        size++;
    }
    //获取顺序线性表的大小
    public int length() {
        return size;
    }
    //获取顺序线性表中索引为i处的元素
    public T get(int i) {
        if (i < 0 || i > size - 1) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return (T)elementDatas[i];
    }
    //查找顺序表中指定元素的索引
    public int locate(T element) {
        for (int i = 0; i < size; i++) {
            if (elementDatas[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
    //像顺序表的指定位置插入一个元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        ensureCapacity(size + 1);
        //将指定索引处的所有元素向后移动一格
        System.arraycopy(elementDatas, index, elementDatas, index + 1, size - index);
        elementDatas[index] = element;
        size++;
    }
    //在线性表的开始处添加一个元素
    public void add(T element) {
        insert(element, size);
    }
    //保证数组长度大于元素个数
    private void ensureCapacity(int minCapacity) {
        //如果数组原长度小于目前所需长度
        if (minCapacity > capacity) {
            //不断地将capacity扩大2倍，直到capacity > minCapacity
            while(capacity < minCapacity) {
                capacity <<= 1;
            }
            elementDatas = Arrays.copyOf(elementDatas, capacity);
        }
    }
    //删除线性表中指定索引处的元素
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        T oldValue = (T)elementDatas[index];
        //需要移动的元素个数
        int numMoved = size - 1 - index;
        if (numMoved > 0) {
            System.arraycopy(elementDatas, index + 1, elementDatas, index, numMoved);
        }
        //清空最后一个元素
        elementDatas[--size] = null;
        return oldValue;
    }
    //删除线性表中最后一个元素
    public T remove() {
        return delete(size - 1);
    }
    //判断线性表是否为空表
    public boolean empty() {
        return size == 0;
    }
    //清空线性表
    public void clear() {
        //将底层数组所有元素赋为null
        Arrays.fill(elementDatas, null);
        size = 0;
    }
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder stringBuilder = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                stringBuilder.append(elementDatas[i].toString()).append(", ");
            }
            int len = stringBuilder.length();
            return stringBuilder.delete(len - 2, len).append("]").toString();
        }
    }
}
