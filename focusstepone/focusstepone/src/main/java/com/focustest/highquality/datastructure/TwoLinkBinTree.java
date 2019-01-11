package com.focustest.highquality.datastructure;

/**
 * focus Create in 15:53 2019/1/8
 */
public class TwoLinkBinTree<E> {
    class Node {
        private Node left;
        private Node right;
        private Object data;
        public Node(){}
        public Node(Object data) {
            this.data = data;
        }
        public Node(Object data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    private Node root;
    public TwoLinkBinTree(){};
    public TwoLinkBinTree(E root) {
        this.root = new Node(root);
    }

    public Node addNode(Node parent, E data, boolean isLeft) {
        if (parent == null) {
            throw new RuntimeException("error");
        }
        if (isLeft && parent.left != null) {
            throw new RuntimeException("left exists");
        }
        if (!isLeft && parent.right != null) {
            throw new RuntimeException("right exists");
        }
        Node node = new Node(data);
        if (isLeft) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        return node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node root() {
        return root;
    }

    public E parent(Node node) {
        //TODO
        return null;
    }

    public E leftChild(Node parent) {
        return parent.left == null ? null : (E)parent.left.data;
    }

    public E rightChild(Node parent) {
        return parent.right == null ? null : (E)parent.right.data;
    }

    public int deep() {
        return deep(root);
    }

    private int deep(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        } else {
            int leftDeep = deep(node.left);
            int rightDeep = deep(node.right);
            return leftDeep > rightDeep ? leftDeep + 1 : rightDeep + 1;
        }
    }
}
