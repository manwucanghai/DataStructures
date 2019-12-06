package com.zws.datastruct.stack;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengws
 * @date 2019-10-19 15:44
 */
public class LinkedStack<E> implements IStack<E> {

    /**
     * 栈最大深度
     */
    private int maxSize;

    /**
     * 栈当前大小
     */
    private AtomicInteger size;

    /**
     * 当前栈顶节点
     */
    private Node<E> top;

    private static class Node<E>{
        public E item;
        public Node<E> previous;

        public Node() {
        }

        public Node(E item) {
            this.item = item;
        }
    }

    public LinkedStack(int maxSize) {
        this.maxSize = maxSize;
        this.size = new AtomicInteger(0);
    }

    public void push(E e) {
        checkNullElement(e);
        checkStackFull();
        Node<E> node = new Node<E>(e);
        node.previous = top;
        top = node;
        this.size.getAndIncrement();
    }

    public E pop() {
        checkStackEmpty();
        E item = top.item;
        top = top.previous;
        this.size.decrementAndGet();
        return item;
    }


    public E peek() {
        checkStackEmpty();
        return top.item;
    }

    public int size() {
        return this.size.get();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public boolean isFull() {
        return size() == maxSize;
    }

    private void checkStackEmpty() {
        if (isEmpty()){
            throw new NoSuchElementException("stack is empty");
        }
    }

    private void checkNullElement(E e) {
        if (e == null){
            throw new NullPointerException("add element cat't be empty");
        }
    }

    private void checkStackFull() {
        if (isFull()){
            throw new RuntimeException("stack is full");
        }
    }
}
