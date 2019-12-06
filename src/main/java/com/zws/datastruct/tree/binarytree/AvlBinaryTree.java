package com.zws.datastruct.tree.binarytree;

import java.util.NoSuchElementException;

/**
 * @author zhengws
 * @date 2019-11-01 19:19
 */
public class AvlBinaryTree<E extends Comparable> implements IBinaryTree<E> {

    private Node<E> root;

    private static class Node<E> {
        public E element;
        /**
         * 指向左子节点
         */
        public Node<E> left;
        /**
         * 指向右子节点
         */
        public Node<E> right;

        private Node(E element) {
            this.element = element;
        }

        public int leftLength() {
            if (left == null) {
                return 0;
            } else {
                return left.height();
            }
        }

        public int rightLength() {
            if (right == null) {
                return 0;
            } else {
                return right.height();
            }
        }

        /**
         * 获取当前节点最大高度.
         *
         * @return
         */
        public int height() {
            // +1 是计算当前节点。
            return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
        }
    }

    public AvlBinaryTree() {
    }

    public AvlBinaryTree(E element) {
        checkNullElement(element);
        this.root = new Node<>(element);
    }

    private void checkNullElement(E element) {
        if (element == null) {
            throw new NullPointerException("element can't be null");
        }
    }

    @Override
    public boolean add(E e) {
        checkNullElement(e);
        if (root == null) {
            this.root = new Node<>(e);
            return true;
        }
        boolean isSuccess = submitAdd(root, e);
        if (isSuccess) {
            AvlNode();
        }
        return isSuccess;
    }

    /**
     * 双旋转
     */
    private void AvlNode() {
        int leftLength = root.leftLength();
        int rightLength = root.rightLength();
        if (Math.abs(rightLength - leftLength) <= 1) {
            return;
        }
        if (leftLength > rightLength) {
            //如果根节点的左子树的右子树的高度, 大于根节点的左子树的左子树的高度
            if (root.left != null && root.left.rightLength() > root.left.leftLength()){
                root.left = leftRotateNode(root.left);
            }
            //右旋
            root = rightRotateNode(root);
        } else {
            // 如果根节点的的右子树的左子树的高度, 大于根节点的右子树的右子树的高度
            if (root.right != null && root.right.leftLength() > root.right.rightLength()){
                root.right = rightRotateNode(root.right);
            }
            //左旋
            root = leftRotateNode(root);
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
         *  每调用一个方法，则会创建一个栈帧，存放本地变量表，而node 作为参数传入，也是存放到该方法的栈帧的本地变量表中
         *  如果对其进行赋值，当该方法调用结束后，该栈帧会被自动回收，对于上层调用者来说是无效赋值的。
         */
//        Node<E> curNode = node;
        Node<E> rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        return rightNode;
    }

    private Node<E> rightRotateNode(Node<E> node) {
        /**
         * 左深度大于右深度
         * 1. 将当前节点的左子节点，指向当前左子节点的右子节点.
         * 2. 将当前节点的左子节点的右子节点, 指向当前节点.
         * 3. 将当前的root节点，指向当前的左子节点.
         */
//        Node<E> curNode = node;
        Node<E> leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        return leftNode;
    }

    @SuppressWarnings("unchecked")
    private boolean submitAdd(Node<E> node, E e) {
        try {
            if (e.compareTo(node.element) > 0) {
                //比当前节点大
                if (node.right != null) {
                    submitAdd(node.right, e);
                } else {
                    node.right = new Node<>(e);
                }
            } else {
                //比当前节点小
                if (node.left != null) {
                    submitAdd(node.left, e);
                } else {
                    node.left = new Node<>(e);
                }
            }
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(E e) {
        checkNullElement(e);
        return removeNode(null, root, e);
    }


    @SuppressWarnings("unchecked")
    private boolean removeNode(Node<E> preNode, Node<E> curNode, E e) {
        if (curNode == null) {
            return false;
        }
        if (curNode.element == e) {
            //找到节点
            compareLeftRightNode(preNode, curNode);
            return true;
        }
        if (curNode.element.compareTo(e) > 0) {
            return removeNode(curNode, curNode.left, e);
        } else {
            return removeNode(curNode, curNode.right, e);
        }
    }


    @SuppressWarnings("unchecked")
    private void compareLeftRightNode(Node<E> preNode, Node<E> curNode) {
        boolean isLeft = false;
        if (preNode != null) {
            isLeft = preNode.element.compareTo(curNode.element) > 0;
        }
        if (curNode.right == null && curNode.left == null) {
            //左右两边都为空
            dragNode(preNode, isLeft, null);
        } else if (curNode.right == null) {
            //右为空，则说明左不为空
            dragNode(preNode, isLeft, curNode.left);
        } else if (curNode.left == null) {
            //左为空，则说明右不为空
            dragNode(preNode, isLeft, curNode.right);
        } else {
            //都不为空.
            compareNextNode(curNode.left, curNode.right);
            dragNode(preNode, isLeft, curNode.right);
        }
    }

    private void compareNextNode(Node<E> left, Node<E> right) {
        if (right.left != null) {
            compareNextNode(left, right.left);
        } else {
            right.left = left;
        }
    }

    private void dragNode(Node<E> preNode, boolean isLeft, Node<E> nextNode) {
        if (preNode == null) {
            //说明删除的是根节点.
            root = nextNode;
        } else {
            if (isLeft) {
                preNode.left = nextNode;
            } else {
                preNode.right = nextNode;
            }
        }
    }


    @Override
    public boolean isEmpty() {
        return root == null;
    }

    private void checkEmptyTree() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty tree");
        }
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public void preOrder() {
        checkEmptyTree();
        preOrderPrint(root);
    }

    private void preOrderPrint(Node<E> node) {
        System.out.println(node.element);
        if (node.left != null) {
            preOrderPrint(node.left);
        }
        if (node.right != null) {
            preOrderPrint(node.right);
        }
    }

    @Override
    public void postOrder() {
        checkEmptyTree();
        postOrderPrint(root);
    }

    private void postOrderPrint(Node<E> node) {
        if (node.left != null) {
            postOrderPrint(node.left);
        }
        if (node.right != null) {
            postOrderPrint(node.right);
        }
        System.out.println(node.element);
    }

    @Override
    public void infixOrder() {
        checkEmptyTree();
        infixOrderPrint(root);
    }

    private void infixOrderPrint(Node<E> node) {
        if (node.left != null) {
            infixOrderPrint(node.left);
        }
        System.out.println(node.element);
        if (node.right != null) {
            infixOrderPrint(node.right);
        }
    }


    public static void main(String[] args) {
        IBinaryTree<Integer> tree = new AvlBinaryTree<>();
//        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr = {10, 11, 7, 6, 8, 9};
        for (int i : arr){
            tree.add(i);
        }
        tree.preOrder();

        /**
         * 验证示例:
         * 8
         * 7
         * 6
         * 10
         * 9
         * 11
         */
    }
}
