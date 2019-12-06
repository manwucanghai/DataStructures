package com.zws.datastruct.stack;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * 基于数组实现栈功能。
 *
 * @author zhengws
 * @date 2019-10-19 15:28
 */
public class ArrayStack<E> implements IStack<E> {
    /**
     * 栈最大深度
     */
    private int maxSize;

    /**
     * 栈顶索引
     */
    private int top;

    /**
     * 存放栈数据。
     */
    private E[] data;

    public ArrayStack(Class<?> clazz, int maxSize) {
        this.maxSize = maxSize;
        this.top = -1;
        this.data = (E[]) Array.newInstance(clazz, this.maxSize);
    }

    public void push(E e) {
        checkNullElement(e);
        checkStackFull();
        top++;
        data[top] = e;
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

    public E pop() {
        checkStackEmpty();
        return data[top--];
    }

    private void checkStackEmpty() {
        if (isEmpty()){
            throw new NoSuchElementException("stack is empty");
        }
    }

    public E peek() {
        checkStackEmpty();
        return data[top];
    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return size() == maxSize;
    }
}
