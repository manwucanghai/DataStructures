package com.zws.datastruct.stack;

/**
 * @author zhengws
 * @date 2019-10-19 15:36
 */
public class StackTest {
    public static void main(String[] args) {
//        IStack<String> stack = new ArrayStack<String>(String.class,3);
        IStack<String> stack = new LinkedStack<String>(3);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println("peek 取出元素: " + stack.peek());
        System.out.println("当前栈大小为: " + stack.size());
        System.out.println("当前栈是否满: " + stack.isFull());
        System.out.println("当前栈是否为空: " + stack.isEmpty());
        System.out.println("pop 取出元素: " + stack.pop());
        System.out.println("pop 取出元素: " + stack.pop());
        System.out.println("pop 取出元素: " + stack.pop());

        System.out.println("############");
        System.out.println("当前栈大小为: " + stack.size());
        System.out.println("当前栈是否满: " + stack.isFull());
        System.out.println("当前栈是否为空: " + stack.isEmpty());
        stack.push("d");

        /**
         * 输出:
         * peek 取出元素: c
         * 当前栈大小为: 3
         * 当前栈是否满: true
         * 当前栈是否为空: false
         * pop 取出元素: c
         * pop 取出元素: b
         * pop 取出元素: a
         * ############
         * 当前栈大小为: 0
         * 当前栈是否满: false
         * 当前栈是否为空: true
         */
    }
}
