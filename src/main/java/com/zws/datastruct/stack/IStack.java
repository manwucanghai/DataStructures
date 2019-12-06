package com.zws.datastruct.stack;

/**
 * @author zhengws
 * @date 2019-10-19 15:25
 */
public interface IStack<E> {

    /**
     * 压入栈顶
     * @param e
     */
    void push(E e);

    /**
     * 弹出栈顶
     * @return
     */
    E pop();

    /**
     * 查看栈顶元素，但不进行出栈.
     * @return
     */
    E peek();

    /**
     * 栈当前深度
     * @return
     */
    int size();

    /**
     * 判断栈是否为空.
     * @return
     */
    boolean isEmpty();

    /**
     * 判断栈是否满
     * @return
     */
    boolean isFull();
}
