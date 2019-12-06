package com.zws.datastruct.queue;

/**
 * @author zhengws
 * @date 2019-10-12 09:09
 * 实现先进先出队列
 */
public interface IQueue<T> {

    /**
     * 往队列中添加元素
     * @param element
     */
    void add(T element);

    /**
     * 从队列中取元素
     * @return
     */
    T poll();

    /**
     * 队列当前的有效个数
     * @return
     */
    int size();

    /**
     * 打印队列中元素信息
     */
    void show();

    /**
     * 判断队列是否满
     * @return
     */
    boolean isFull();

    /**
     * 判断队列是否为空
     * @return
     */
    boolean isEmpty();
}
