package com.focustest.highquality.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 17:03 2019/1/8
 */
public class ThreeLinkBinTree<E> {
    class Node {
        private Object data;
        private Node left;
        private Node right;
        private Node parent;
        public Node() {}
        public Node(Object data) {
            this.data = data;
        }
        public Node(Object data, Node left, Node right, Node parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    private Node root;
    public ThreeLinkBinTree(){
        this.root = new Node();
    }
    public ThreeLinkBinTree(E data) {
        this.root = new Node(data);
    }

    public Node addNode(Node parent, E data, boolean isLeft) {
        if (parent == null) {
            throw new RuntimeException("error");
        }
        if (isLeft && parent.left != null) {
            throw new RuntimeException("left exsits");
        }
        if (!isLeft && parent.right != null) {
            throw new RuntimeException("right exsits");
        }
        Node node = new Node(data, null, null, parent);
        if (isLeft) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return node;
    }

    public E parent(Node node) {
        return (E)node.parent.data;
    }

    // 深度优先（先序遍历）
    public List<Node> preIterator() {
        return preIterator(root);
    }

    private List<Node> preIterator(Node node) {
        List<Node> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        //处理根节点
        list.add(node);
        if (node.left != null) {
            //递归处理左子树
            list.addAll(preIterator(node.left));
        }
        if (node.right != null) {
            list.addAll(preIterator(node.right));
        }
        return list;
    }

    //深度优先（中序遍历
    // ）
    public List<Node> inIterator() {
        return inIterator(root);
    }
    private List<Node> inIterator(Node node) {
        List<Node> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        if (node.left != null) {
            list.addAll(inIterator(node.left));
        }
        list.add(node);
        if (node.right != null) {
            list.addAll(inIterator(node.right));
        }
        return list;
    }
}
