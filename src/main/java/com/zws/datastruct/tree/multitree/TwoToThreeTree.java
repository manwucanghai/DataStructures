package com.zws.datastruct.tree.multitree;

/**
 * 2-3 树
 *
 * @author zhengws
 * @date 2019-11-08 14:53
 */
public class TwoToThreeTree<E extends Comparable> implements IMultiTree<E> {

    private Node<E> root;

    private static class Node<E extends Comparable> {
        E min;
        E max;
        Node<E> preNode;
        Node<E> leftNode;
        Node<E> rightNode;
        Node<E> midNode;

        public Node(E e) {
            this.min = e;
        }

        //如果添加返回空，则直接添加完成，如果返回非空，则进行向上抽取一层。
        @SuppressWarnings("unchecked")
        E addNode(E e) {
            //最大值为空
            if (max == null) {
                if (min.compareTo(e) > 0) {
                    max = min;
                    min = e;
                } else {
                    max = e;
                }
                return null;
            }
            //存在最大最小值
            E mid;
            if (min.compareTo(e) > 0) {
                mid = min;
                min = e;
            } else if (max.compareTo(e) < 0) {
                mid = max;
                max = e;
            } else {
                mid = e;
            }
            return mid;
        }
    }

    @Override
    public boolean add(E e) {
        checkNullElement(e);
        if (root == null) {
            root = new Node<>(e);
            return true;
        }
        return submitAdd(root, e);
    }

    private boolean submitAdd(Node<E> node, E e) {
        try {
            if (node.min.compareTo(e) > 0 && node.leftNode != null) {
                submitAdd(node.leftNode, e);
            } else if (node.max.compareTo(e) < 0 && node.rightNode != null) {
                submitAdd(node.rightNode, e);
            } else {
                E element = node.addNode(e);
                if (element == null) {
                    return true;
                } else {
                    //向上抽取作为父节点.
                    upgradeNode(node, element);
                }
            }
            return true;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    /**
     * 向上抽取
     *
     * @param node
     * @param element
     */
    private void upgradeNode(Node<E> node, E element) {
        if (node.preNode == null) {
            Node<E> upNode = new Node<>(element);
            upNode.leftNode = node.leftNode;
            upNode.rightNode = node.rightNode;
            root = upNode;
            return;
        }
//        if (node.preNode.max == null){
//            //说明父节点是2-节点。
//            Node<E> minNode = new Node<>(node.min);
//            Node<E> maxNode = new Node<>(node.max);
//            if (element.compareTo(node.preNode.min) < 0){
//                node.preNode.leftNode = minNode;
//                node.preNode.midNode = maxNode;
//            }else {
//                node.preNode.midNode = minNode;
//                node.preNode.rightNode = maxNode;
//            }
//            node.preNode.addNode(element);
//        }else {
//            //父节点存在两个值，也就是3-节点.
//            E mid = node.preNode.addNode(element);
//            Node<E> minNode = new Node<>(node.preNode.min);
//            Node<E> maxNode = new Node<>(node.preNode.max);
//
//
//        }
    }


    @Override
    public boolean remove(E e) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    private void checkNullElement(E e) {
        if (e == null) {
            throw new NullPointerException("element can't be empty");
        }
    }
}
