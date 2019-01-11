package com.focustest.highquality.datastructure;

/**
 * focus Create in 16:48 2019/1/3
 */
public class LinkedStack<T> {
    private class Node {
        private Node next;
        private T data;
        public Node(){}
        public Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }
    }

    private Node top;
    private int size;

    public LinkedStack() {}
    public LinkedStack(T element) {
        top = new Node(null, element);
        size++;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T element) {
        top = new Node(top, element);
        size++;
    }

    public T pop() {
        if (this.isEmpty()) {
            return null;
        }
        Node oldNode = top;
        top = oldNode.next;
        oldNode.next = null;
        size--;
        return oldNode.data;
    }

    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return top.data;
    }

    public void clear() {
        top = null;
        size = 0;
    }
}
