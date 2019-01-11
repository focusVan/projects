package com.focustest.highquality.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 10:20 2019/1/4
 */
public class ParentTree<E> {
    private class Node<T> {
        private T data;
        private int parent;
        public Node(){}
        public Node(T element, int parent) {
            this.data = data;
            this.parent = parent;
        }
    }

    private final int DEFAULT_TREE_SIZE = 100;
    private int treeSize = 0;
    //使用一个Node[]数组来记录树里的所有节点
    private Node<E>[] nodes;
    //记录节点数
    private int nodeNums;

    public ParentTree(){};
    //以指定根节点创建树
    public ParentTree(E element) {
        treeSize = DEFAULT_TREE_SIZE;
        nodes = new Node[treeSize];
        nodes[0] = new Node(element, -1);
        nodeNums++;
    }
    public ParentTree(E element, int treeSize) {
        this.treeSize = treeSize;
        nodes = new Node[treeSize];
        nodes[0] = new Node(element, -1);
        nodeNums++;
    }
    //为指定节点添加子节点
    public void addNode(E element, Node parent) {
        for (int i = 0; i < treeSize; i++) {
            //找到数组中第一个为null的元素，用于保存新节点
            if (nodes[i] == null) {
                nodes[i] = new Node(element, pos(parent));
                nodeNums++;
            }
        }
        throw new RuntimeException("tree is full");
    }
    //返回包含指定值的节点
    public int pos(Node node) {
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }
    public boolean isEmpty() {
        return nodes[0] == null;
    }
    //返回根节点
    public Node<E> root() {
        return nodes[0];
    }
    //返回指定节点（非根节点）的父节点
    public Node<E> parent(Node node) {
        if (node == nodes[0]) {
            return null;
        } else {
            return nodes[node.parent];
        }
    }
    //返回指定节点（非叶子节点）的所有子节点
    public List<Node<E>> children(Node node) {
        List<Node<E>> list = new ArrayList<>();
        for (int i = 0; i < treeSize; i++) {
            if (nodes[i] != null && nodes[i].parent == pos(node)) {
                list.add(nodes[i]);
            }
        }
        return list;
    }
    //返回该树的深度
    public int deep() {
        //用于记录节点的最大深度
        int max = 0;
        for (int i = 0; i < treeSize && nodes[i] != null; i++) {
            //初始化本节点的深度
            int def = 1;
            //m记录当前节点的父节点的位置
            int m = nodes[i].parent;
            //如果父节点存在
            while(m != -1 && nodes[m] != null) {
                //向上继续搜索父节点
                m = nodes[m].parent;
                def++;
            }
            if (max < def) {
                max = def;
            }
        }
        return max;
    }
}
