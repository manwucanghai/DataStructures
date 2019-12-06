package com.zws.datastruct.tree.binarytree;

import java.util.NoSuchElementException;

/**
 * @author zhengws
 * @date 2019-11-02 10:15
 */
public class ThreadBinaryTree<E extends Comparable> implements IBinaryTree<E> {

    /**
     * 根节点
     */
    private Node<E> root;

    /**
     * 索引化前一个节点
     */
    private Node<E> preNode;

    private static class Node<E> {
        public E element;
        /**
         * 指向左子节点
         */
        public Node<E> left;

        /**
         * 是否进行线索化，默认为false
         */
        public boolean leftType;

        /**
         * 指向右子节点
         */
        public Node<E> right;

        /**
         * 是否进行线索化，默认为false
         */
        public boolean rightType;


        private Node(E element) {
            this.element = element;
        }
    }

    public ThreadBinaryTree() {
    }

    public ThreadBinaryTree(E element) {
        checkNullElement(element);
        this.root = new Node<>(element);
    }

    /**
     * 中序线索化二叉树.
     */
    public void infixThreadNode() {
        infixThreadNode(root);
    }

    private void infixThreadNode(Node<E> node) {
        if (node == null) {
            return;
        }
        //如果有左子节点，则先处理左子节点.
        infixThreadNode(node.left);

        if (node.left == null) {
            node.left = preNode;
            node.leftType = true;
        }

        if (preNode != null && preNode.right == null) {
            preNode.right = node;
            preNode.rightType = true;
        }
        preNode = node;

        infixThreadNode(node.right);
    }

    /**
     * 后序索引化二叉树
     */
    public void postThreadNode() {

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
        return submitAdd(root, e);
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
        if (checkRemoveRoot(e)) {
            return true;
        }
        if (root.element.compareTo(e) > 0) {
            return removeNode(root, root.left, e);
        } else {
            return removeNode(root, root.right, e);
        }
    }

    private boolean checkRemoveRoot(E e) {
        if (root.element == e) {
            if (root.left == null && root.right == root) {
                clear();
            } else if (root.right == null) {
                root = root.left;
            } else if (root.left == null) {
                root = root.right;
            } else {
                compareNextNode(root.left, root.right);
                root = root.right;
            }
            return true;
        }
        return false;
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
        boolean isLeft = preNode.element.compareTo(curNode.element) > 0;
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
        if (isLeft) {
            preNode.left = nextNode;
        } else {
            preNode.right = nextNode;
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

    }

    @Override
    public void postOrder() {
        checkEmptyTree();
        postOrderPrint(root);
    }

    private void postOrderPrint(Node<E> node) {

    }

    @Override
    public void infixOrder() {
        checkEmptyTree();
        infixOrderPrint(root);
    }

    private void infixOrderPrint(Node<E> node) {
        while (node != null) {
            //注意必须对node.leftType进行判断，否则会出现死循环问题
            while (!node.leftType) {
                node = node.left;
            }

            System.out.println(node.element);

            while (node.rightType) {
                System.out.println(node.right.element);
                node = node.right;
            }
            node = node.right;
        }
    }
}
