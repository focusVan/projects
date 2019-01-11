package com.focustest.highquality.datastructure;

import java.util.Arrays;

/**
 * focus Create in 16:26 2019/1/3
 */
public class ArrayStack<T> {
    private static int DEFAULT_SIZE = 10;
    private int size = 0;
    private Object[] elementData;
    private int capacity;
    private int capacityIncrement = 0;
    public ArrayStack() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    public ArrayStack(T element) {
        this();
        elementData[0] = element;
        size++;
    }

    public ArrayStack(T element, int initSize) {
        capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        size++;
    }

    public ArrayStack(T element, int initSize, int capacityIncrement) {
        this.capacity = initSize;
        this.capacityIncrement = capacityIncrement;
        elementData = new Object[capacity];
        elementData[0] = element;
    }

    public int length() {
        return size;
    }

    public void push(T element) {
        ensureCapacity(size + 1);
        elementData[size++] = element;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            if (capacityIncrement > 0) {
                while (capacity < minCapacity) {
                    capacity += capacityIncrement;
                }
            } else {
                while (capacity < minCapacity) {
                    capacity <<= 1;
                }
            }
        }
        elementData = Arrays.copyOf(elementData, capacity);
    }

    public T pop() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        T oldValue = (T)elementData[size - 1];
        elementData[--size] = null;
        return oldValue;
    }

    public T peek() {
        if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return (T)elementData[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(elementData, null);
        size = 0;
    }
}
