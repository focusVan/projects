package com.focustest.highquality.datastructure;

/**
 * focus Create in 18:02 2019/1/3
 */
public class LinkedQueue<T> {
    private class Node {
        private Node next;
        private T data;
        public Node() {}
        public Node(T element, Node next) {
            this.data = element;
            this.next = next;
        }
    }

    private Node front;
    private Node rear;
    private int size = 0;

    public LinkedQueue() {
        front = rear = null;
    }
    public LinkedQueue(T element) {
        front = rear = new Node(element, null);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int length() {
        return size;
    }

    public void add(T element) {
        if (isEmpty()) {
            rear = front = new Node(element, front);
        } else {
            Node newNode = new Node(element, null);
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public T remove() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        Node oldNode = front;
        front = oldNode.next;
        oldNode.next = null;
        size--;
        return oldNode.data;
    }

    public T element() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        return front.data;
    }

    public void clear() {
        front = rear = null;
        size = 0;
    }
}
