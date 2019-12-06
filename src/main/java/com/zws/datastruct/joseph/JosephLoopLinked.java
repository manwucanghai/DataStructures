package com.zws.datastruct.joseph;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengws
 * @date 2019-10-19 09:23
 */
public class JosephLoopLinked<T> {

    private int maxSize;
    /**
     * size代表当前有效元素个数.
     */
    private AtomicInteger size;
    /**
     * first往前走一格 size自减
     */
    private Node<T> first;
    /**
     * last往前走一格, size自增
     */
    private Node<T> last;

    private static class Node<T> {
        public T item;
        public Node<T> next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    public JosephLoopLinked(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("maxSize must large 0");
        }
        this.maxSize = maxSize;
        this.size = new AtomicInteger(0);
    }


    public void add(T element) {
        checkNullElement(element);
        checkRoundLinkedFull();
        if (!checkAndInitFistNode(element)) {
            Node<T> node = new Node<T>(element);
            last.next = node;
            node.next = first;
            last = node;
        }
        this.size.getAndIncrement();
    }

    private boolean checkAndInitFistNode(T element) {
        if (first == null) {
            first = last = new Node<T>(element);
            first.next = first;
            return true;
        }
        return false;
    }

    public boolean removeNextNode(Node<T> node) {
        if (node == null) {
            return false;
        }
        Node<T> remove = node.next;
        if (first != last){
            checkRemoveNode(node, remove);
            node.next = remove.next;
        }else {
            //此节点为最后一个节点
            first = last = null;
        }
        this.size.decrementAndGet();
        return true;
    }

    private void checkRemoveNode(Node<T> node, Node<T> remove) {
        //针对移除节点为first或者last节点进行特殊处理。
        if (remove == first) {
            first = remove.next;
        } else if (remove == last) {
            last = node;
        }
    }

    public boolean removeFirst() {
        checkEmptyNode();
        return removeNextNode(last);
    }


    public int size() {
        return this.size.get();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == this.maxSize;
    }


    public void show() {
        if (isEmpty()) {
            return;
        }
        Node<T> node = first;
        while (true) {
            System.out.println(node.item);
            node = node.next;
            if (node == first) {
                break;
            }
        }
    }

    /**
     * 约瑟夫环出列打印.
     * 1. 将first，last 先移动 k-1个位置
     * 2.循环遍历进行以下步骤
     *      a. 让first，last指针同时移动 m-1
     *      b. 将当前first所指向的节点移除.
     *
     * @param k
     * @param m
     */
    public void josephLoopPrint(int k, int m) {
        checkEmptyNode();
        if (k <= 0 || m <= 0) {
            throw new IllegalArgumentException("输入参数不合法");
        }
        //1. 将first，last 先移动 k-1个位置
        moveIndex(k - 1);
        while (!isEmpty()) {
            System.out.println("###### 当前链表中节点个数: " + size() + " ######");
            System.out.println("###### 移除前打印 ########");
            show();
            //2. 让first，last指针同时移动 m-1
            moveIndex(m - 1);
            //3. 将当前first所指向的节点移除.
            System.out.println(">>>> 出列人员: " + first.item);
            removeFirst();
        }
    }

    private void moveIndex(int index) {
        for (int i = 0; i < index; i++) {
            first = first.next;
            last = last.next;
        }
    }

    private void checkEmptyNode() {
        if (isEmpty()) {
            throw new NoSuchElementException("round linked is empty.");
        }
    }

    private void checkNullElement(T element) {
        if (element == null) {
            throw new NullPointerException("element can't be null");
        }
    }

    private void checkRoundLinkedFull() {
        if (isFull()) {
            throw new RuntimeException("round linked is full");
        }
    }
}
