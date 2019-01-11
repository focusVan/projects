package com.focustest.highquality.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 17:10 2019/1/7
 */
public class ChildChainTree<E> {
    private static class SonNode {
        //记录当前节点的位置
        private int pos;
        private SonNode next;
        public SonNode(int pos, SonNode next) {
            this.pos = pos;
            this.next = next;
        }
    }

    public static class Node<T> {
        private T data;
        //记录第一个子节点
        private SonNode first;
        public Node(T data) {
            this.data = data;
            this.first = null;
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    //记录所有节点
    private Node<E>[] nodes;
    //记录节点数
    private int nodeNums;
    //以指定根节点创建树
    public ChildChainTree(E data) {
        this.treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node<>(data);
        nodeNums++;
    }
    //为指定节点添加子节点
    public void addNode(Node parent, E data) {
        for (int i = 0; i < treeSize; i++) {
            //找到数组中第一个为null的元素，该元素保存新节点
            if (nodes[i] == null) {
                nodes[i] = new Node(data);
                if (parent.first == null) {
                    parent.first = new SonNode(i, null);
                } else {
                    SonNode next  = parent.first;
                    while (next.next != null) {
                        next = next.next;
                    }
                    next.next = new SonNode(i, null);
                }
            }
            nodeNums++;
            return;
        }
        throw new RuntimeException("tree has full");
    }
    //判断树是否为空
    public boolean isEmpty() {
        //根节点是否为null
        return nodes[0] == null;
    }
    //返回根节点
    public Node<E> root() {
        return nodes[0];
    }
    //返回指定节点（非叶子节点）的所有子节点
    public List<Node<E>> children(Node parent) {
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == parent) {
                if (nodes[i].first != null) {
                    SonNode sonNode = nodes[i].first;
                    while (sonNode != null) {
                        list.add(nodes[sonNode.pos]);
                        sonNode = sonNode.next;
                    }
                }
            }
        }
        return list;
    }
    //返回指定节点（非叶子节点）的第index个子节点
    public Node<E> child(Node parent, int index) {
        SonNode sonNode = parent.first;
        for (int i = 0; sonNode.next != null; i++) {
            if (index == i) {
                return nodes[sonNode.pos];
            }
        }
        return null;
    }
    //返回树的深度
    public int deep() {
        int max = 1;
        for (int i = 0; i < treeSize; i++) {
            int deep = 1;
            SonNode sonNode = nodes[i].first;
            while (sonNode.next != null) {
                deep++;
                sonNode = sonNode.next;
            }
            if (max < deep) {
                max = deep;
            }
        }
        return max;
    }

    //递归：每课数的深度为其所有子树的最大深度+1
    private int deep(Node node) {
        if (node.first == null) {
            return 1;
        } else {
            //记录其所有子树的最大深度
            int max = 0;
            SonNode sonNode = node.first;
            while (sonNode != null) {
                //获取以其子节点为根的子树的深度
                int tmp = deep(nodes[sonNode.pos]);
                if (tmp > max) {
                    max = tmp;
                }
                sonNode = sonNode.next;
            }
            return max + 1;
        }
    }
}
