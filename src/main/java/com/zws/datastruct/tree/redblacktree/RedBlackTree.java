package com.zws.datastruct.tree.redblacktree;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 红黑树
 *
 * @author zhengws
 * @date 2020-03-02 23:09
 */
public class RedBlackTree<E extends Comparable<E>> implements IRedBlackTree<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    /**
     * 统计元素个数
     */
    private AtomicInteger size = new AtomicInteger(0);

    /**
     * 根节点.
     */
    private Node<E> root;

    private static class Node<E extends Comparable<E>> {
        public E element;
        public Node<E> parent;
        public Node<E> left;
        public Node<E> right;
        public boolean color;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public Node(E element, Node<E> parent, boolean color) {
            this.element = element;
            this.parent = parent;
            this.color = color;
        }


        /**
         * 是否是叶子节点
         *
         * @return
         */
        public boolean isLeaf() {
            return left == null && right == null;
        }

        /**
         * 是否是父节点的左子节点
         *
         * @return
         */
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 是否是父节点的右子节点
         *
         * @return
         */
        public boolean isRightChild() {
            return parent != null && this == parent.left;
        }

        /**
         * 是否有双子节点
         *
         * @return
         */
        public boolean hasDoubleChild() {
            return left != null && right != null;
        }

        /**
         * 获取兄弟节点
         *
         * @return
         */
        public Node<E> brotherNode() {
            if (isLeftChild()) {
                return parent.right;
            } else if (isRightChild()) {
                return parent.left;
            } else {
                return null;
            }
        }

    }

    @Override
    public boolean add(E element) {
        checkNullElement(element);
        Node<E> node;
        if (root == null) {
            node = new Node<>(element, null, BLACK);
            root = node;
        } else {
            node = submitAddNode(element, root);
        }
        afterAddNodeAccess(node);
        this.size.incrementAndGet();
        return true;
    }

    private void afterAddNodeAccess(Node<E> node) {
        /**
         * 1.如果该节点为根节点，或者所添加的父节点为BLACK,则无需进行任何操作
         */
        Node<E> parent = node.parent;
        if (node == root || parent == null || isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.brotherNode();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) {
            /**
             * 2.如果叔父节点为红色
             * 将父节点和叔父节点颜色修改为BLACK
             * 将grand节点颜色修改为红色，并当做新添加进行递归处理
             */
            black(parent);
            black(uncle);
            afterAddNodeAccess(red(grand));
        } else {
            /**
             * 3.叔父节点不为红色，也就是黑色
             */
            if (node.isLeftChild()) {
                if (parent.isLeftChild()) {
                    //LL: grand 右旋 （父节点染成BLACK, grand染成 RED）
                    black(parent);
                    red(grand);
                    rightRotateNode(grand);

                } else {
                    //LR: 自己染成黑色，grand染成红色, parent 左旋, grand右旋
                    black(node);
                    red(grand);
                    leftRotateNode(parent);
                    rightRotateNode(grand);
                }
            } else {
                if (node.parent.isRightChild()) {
                    //RR: grand 左旋 （父节点染成BLACK, grand染成 RED）
                    black(parent);
                    red(grand);
                    leftRotateNode(grand);

                } else {
                    //RL 自己染成黑色，grand染成红色, parent 右旋, grand左旋
                    black(node);
                    red(grand);
                    rightRotateNode(parent);
                    leftRotateNode(grand);
                }
            }
        }
    }


    private Node<E> submitAddNode(E element, Node<E> parent) {
        final E parentValue = parent.element;
        if (parentValue.equals(element)) {
            return parent;
        }
        Node<E> node;
        if (parentValue.compareTo(element) > 0) {
            //往左子节点查找
            if (parent.left == null) {
                node = new Node<>(element, parent);
                parent.left = node;
            } else {
                return submitAddNode(element, parent.left);
            }
        } else {
            //往右子节点查找
            if (parent.right == null) {
                node = new Node<>(element, parent);
                parent.right = node;
            } else {
                return submitAddNode(element, parent.right);
            }
        }
        return node;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    private void checkNullElement(E element) {
        if (element == null) {
            throw new NullPointerException("element can't be null");
        }
    }


    private Node<E> leftRotateNode(Node<E> node) {
        /**
         * 右深度大于左深度
         * 1. 将当前节点的右子节点，指向当前右子节点的左子节点.
         * 2. 将当前节点的右子节点的左子节点, 指向当前节点.
         * 3. 将当前的root节点，指向当前的右子节点.
         *
         * 注意: 为何不能直接使用node = rightNode, 而是需要进行return后进行赋值操作?
         * 原因分析:
         * 每调用一个方法，则会创建一个栈帧，存放本地变量表，而node 作为参数传入，也是存放到该方法的栈帧的本地变量表中
         * 如果对其进行赋值，当该方法调用结束后，该栈帧会被自动回收，对于上层调用者来说是无效赋值的。
         */
        Node<E> rightNode = node.right;

        if (node.isRightChild()) {
            node.parent.right = rightNode;
        } else if (node.isLeftChild()) {
            node.parent.left = rightNode;
        }
        rightNode.parent = node.parent;

        node.right = rightNode.left;
        rightNode.left = node;
        node.parent = rightNode;

        return rightNode;
    }

    private Node<E> rightRotateNode(Node<E> node) {
        /**
         * 左深度大于右深度
         * 1. 将当前节点的左子节点，指向当前左子节点的右子节点.
         * 2. 将当前节点的左子节点的右子节点, 指向当前节点.
         * 3. 当前节点的父节点为 当前节点的左子节点.
         * 4. 将当前的root节点，指向当前的左子节点.
         */
        Node<E> leftNode = node.left;

        if (node.isRightChild()) {
            node.parent.right = leftNode;
        } else if (node.isLeftChild()) {
            node.parent.left = leftNode;
        }
        leftNode.parent = node.parent;

        node.left = leftNode.right;
        leftNode.right = node;
        node.parent = leftNode;
        return leftNode;
    }

    public Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    public Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    public Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    public boolean colorOf(Node<E> node) {
        return (node == null) ? BLACK : node.color;
    }

    public boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    public boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }


    public static void main(String[] args) {
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        redBlackTree.add(1);
        redBlackTree.add(2);
        redBlackTree.add(3);
        redBlackTree.add(4);
        redBlackTree.add(5);
        redBlackTree.add(6);
        redBlackTree.add(7);
        redBlackTree.add(8);
        redBlackTree.add(9);
        System.out.println(redBlackTree);
    }
}
