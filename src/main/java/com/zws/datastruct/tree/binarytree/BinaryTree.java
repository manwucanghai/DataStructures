package com.zws.datastruct.tree.binarytree;

import java.util.NoSuchElementException;

/**
 * @author zhengws
 * @date 2019-11-01 19:19
 */
public class BinaryTree<E extends Comparable> implements IBinaryTree<E> {

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
    }

    public BinaryTree() {
    }

    public BinaryTree(E element) {
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
}
