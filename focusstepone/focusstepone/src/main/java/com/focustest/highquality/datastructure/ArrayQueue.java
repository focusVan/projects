package com.focustest.highquality.datastructure;

import java.util.Arrays;

/**
 * focus Create in 17:14 2019/1/3
 */
public class ArrayQueue<T> {
    private static int DEFAULT_SIZE = 10;
    private int capacity;
    private Object[] elementData;
    private int front = 0;
    private int rear = 0;

    public ArrayQueue() {
        capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }
    public ArrayQueue(T element) {
        this();
        elementData[0] = element;
        rear++;
    }
    public ArrayQueue(T element, int initSize) {
        capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = element;
        rear++;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public int length() {
        return rear - front;
    }

    public void add(T element) {
        if (rear > capacity - 1) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        elementData[rear++] = element;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        T element = (T)elementData[front];
        elementData[front++] = null;
        return element;
    }

    public T element() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return (T)elementData[front];
    }

    public void clear() {
        Arrays.fill(elementData, null);
        rear = front = 0;
    }
}
