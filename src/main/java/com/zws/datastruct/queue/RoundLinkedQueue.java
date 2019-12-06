package com.zws.datastruct.queue;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 采用linked实现环形队列
 *
 * @author zhengws
 * @date 2019-10-19 14:45
 */
public class RoundLinkedQueue<T> implements IQueue<T> {

    /**
     * 队列当前有效个数
     */
    private AtomicInteger size;

    /**
     * 队列最大个数.
     */
    private int maxSize;

    /**
     * 当前队列第一个节点，也就是将被取出的第一个点
     */
    private Node<T> first;

    /**
     * 当前队列有效节点的最后一个节点，也就是如果往队列加元素，则往该元素后面添加。
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

    public RoundLinkedQueue(int maxSize) {
        this.maxSize = maxSize;
        this.size = new AtomicInteger(0);
        first = last = new Node<T>();
        first.next = first;
    }

    public void add(T element) {
        checkNullElement(element);
        checkFull();
        if (first.item == null) {
            first.item = element;
        } else {
            Node<T> node = new Node<T>(element);
            last.next = node;
            node.next = first;
            last = node;
        }
        this.size.getAndIncrement();
    }

    private void checkFull() {
        if (isFull()) {
            throw new RuntimeException("queue is full");
        }
    }

    private void checkNullElement(T element) {
        if (element == null) {
            throw new NullPointerException("element can't be null");
        }
    }

    public T poll() {
        checkEmptyNode();
        T item = first.item;
        first = first.next;
        last.next = first;
        this.size.decrementAndGet();
        return item;
    }

    private void checkEmptyNode() {
        if (isEmpty()) {
            throw new NoSuchElementException("round queue is empty.");
        }
    }

    public int size() {
        return this.size.get();
    }

    public void show() {
        checkEmptyNode();
        Node<T> node = first;
        while (true) {
            System.out.println(node.item);
            node = node.next;
            if (node == first) {
                break;
            }
        }
    }

    public boolean isFull() {
        return size() == maxSize;
    }

    public boolean isEmpty() {
        return first == last;
    }

    public static void main(String[] args) {
        RoundLinkedQueue<String> queue = new RoundLinkedQueue<String>(3);
        queue.add("a");
        queue.add("b");
        queue.add("c");

        System.out.println("##############");
        System.out.println("size: " + queue.size());
        System.out.println(queue.poll());
        queue.add("d");
        System.out.println("size: " + queue.size());
        System.out.println(queue.poll());
        queue.add("e");
        System.out.println("size: " + queue.size());

        System.out.println("###############");
        System.out.println("show");
        queue.show();

        /**
         * 输出:
         * ##############
         * size: 3
         * a
         * size: 3
         * b
         * size: 3
         * ###############
         * show
         * c
         * d
         * e
         */
    }
}
