package com.zws.datastruct.stack;

import java.util.Stack;

/**
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 将这个栈转置后，从栈顶到栈底为1、2、3、4、5，也就是实现栈中元素的逆序。
 * 只能用递归函数来实现，不能用其他数据结构。
 *
 * @author zhengws
 * @date 2019-10-24 15:28
 */
public class ReverseStack<E> {
    private Stack<E> stack;

    public ReverseStack() {
        this.stack = new Stack<>();
    }

    public E push(E e) {
        return stack.push(e);
    }

    public E pop() {
        return stack.pop();
    }

    public void reverse() {
        if (stack.isEmpty()) {
            return;
        }
        //取出最后一个元素，并最后压入，也就是栈顶弹出是，这个最后元素就会被弹出。
        E last = getAndRemoveLast();
        if (!stack.isEmpty()) {
            reverse();
        }
        stack.push(last);
    }

    private E getAndRemoveLast() {
        E result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            E last = getAndRemoveLast();
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        ReverseStack<Integer> stack = new ReverseStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.reverse();
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
    }
}
