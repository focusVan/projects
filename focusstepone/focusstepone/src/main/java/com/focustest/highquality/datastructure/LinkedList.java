package com.focustest.highquality.datastructure;

/**
 * focus Create in 14:54 2018/12/29
 */
public class LinkedList<T> {
    //定义一个内部类Node,Node实例代表链表的节点
    private class Node {
        private Node prev;
        private Node next;
        private T data;
        public Node(){}
        //全参构造器
        public Node(Node prev, Node next, T data) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
    //保存该链表的头结点
    private Node head;
    private Node tail;
    private int size = 0;
    //创建空链表
    public LinkedList() {
        head = null;
        tail = null;
    }
    //以指定元素来创建链表，链表只有一个元素
    public LinkedList(T element) {
        head = new Node(null, null, element);
        tail = head;
        size++;
    }
    //返回链表的长度
    public int length() {
        return size;
    }
    //获取链表中索引为index的元素
    public T get(int index) {
        Node node = getNodeByIndex(index);
        if (node != null) {
            return node.data;
        }
        return null;
    }
    //根据index获取指定位置的节点
    private Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        if (index <= size/2) {
            //从head开始遍历
            Node current = head;
            for (int i = 0; i < index/2 && current != null; i++, current = current.next) {
                if (i == index) {
                    return current;
                }
            }
        } else {
            Node current = tail;
            for (int i = size - 1; i > size/2 && current != null; i--, current = current.prev) {
                if (i == index) {
                    return current;
                }
            }
        }
        return null;
    }
    //查找链式线性表指定元素的索引
    public int locate(T element) {
        Node current = head;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }
    //向线性链式表指定位置插入一个元素
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        //如果还是空表
        if (head == null) {
            add(element);
        } else {
            //当index为0时，表示在链表头处插入
            if (index == 0) {
                addAtHead(element);
            } else {
                //获取插入点的前一个节点
                Node prev = getNodeByIndex(index - 1);
                Node next = prev.next;
                Node node = new Node(prev, next, element);
                prev.next = node;
                if (next != null) {
                    next.prev = node;
                }
                size++;
            }
        }

    }
    //尾插法为链表添加节点
    public void add(T element) {
        //如果该链表还是空链表
        if (head == null) {
            head = new Node(null, null, element);
            tail = head;
        } else {
            Node newNode = new Node(null, tail, element);
            tail.next = newNode;
            //以新节点作为尾节点
            tail = newNode;
        }
        size++;
    }
    //采用头插法为链表添加新节点
    public void addAtHead(T element) {
        Node node = new Node(null, head, element);
        if (size == 0) {
            tail = head = node;
        }
        size++;
    }
    //删除链式线性表中指定索引处的元素
    public T delete(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("out of bounds");
        }
        Node del = null;
        //如果被删除的是head
        if (index == 0) {
            del = head;
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            del = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            del = getNodeByIndex(index);
            Node prev = del.prev;
            Node next = del.next;
            prev.next = next;
            next.prev = prev;
        }
        del.next = null;
        del.prev = null;
        size--;
        return del.data;
    }
    public T remove() {
        return delete(size - 1);
    }
    public boolean empty() {
        return size == 0;
    }
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
