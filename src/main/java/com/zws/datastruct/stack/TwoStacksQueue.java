package com.zws.datastruct.stack;

import java.util.Stack;

/**
 * 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）
 *
 * @author zhengws
 * @date 2019-10-24 15:09
 */
public class TwoStacksQueue<E> {

    private Stack<E> pushStack;
    private Stack<E> popStack;

    public TwoStacksQueue() {
        this.pushStack = new Stack<>();
        this.popStack = new Stack<>();
    }

    public E add(E e) {
        checkNullElement(e);
        pushStack.push(e);
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return e;
    }

    public E poll() {
        checkAndSwap();
        return popStack.pop();
    }

    private void checkAndSwap() {
        if (popStack.isEmpty()) {
            if (pushStack.isEmpty()) {
                throw new RuntimeException("queue is empty.");
            }
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public E peek() {
        checkAndSwap();
        return popStack.peek();
    }

    private void checkNullElement(E e) {
        if (e == null) {
            throw new NullPointerException("添加的元素不能为空");
        }
    }


    public static void main(String[] args) {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        for (int i = 100; i > 0; i--) {
            queue.add(i);
            if (i % 2 == 0){
                System.out.println(queue.poll());
            }
        }
        System.out.println("###################");
        System.out.println("let me see see: " + queue.peek());
        System.out.println("**************");
        for (int i = 0; i < 50; i++) {
            System.out.println(queue.poll());
        }
    }
}
