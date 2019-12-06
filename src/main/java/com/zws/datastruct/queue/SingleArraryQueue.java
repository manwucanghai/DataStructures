package com.zws.datastruct.queue;

import java.lang.reflect.Array;

/**
 * @author zhengws
 * @date 2019-10-12 09:07
 * 简单的基于数组队列，只能使用一次.
 */
public class SingleArraryQueue<T> implements IQueue<T> {
    /**
     * 定义队列最大长度
     */
    private int maxSize;
    private int cur;
    private int last;
    private T[] queue;

    public SingleArraryQueue(Class clazz, int maxSize) {
        this.maxSize = maxSize;
        this.queue = (T[]) Array.newInstance(clazz, maxSize);
    }


    public void add(T element) {
        checkNull(element);
        if (isFull()) {
            throw new RuntimeException("queue full");
        }
        queue[last] = element;
        last++;
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("queue empty");
        }
        T t = queue[cur];
        cur++;
        return t;
    }

    public int size() {
        return last - cur;
    }

    public void show() {
        for (int i = cur; i < last; i++) {
            System.out.println(queue[i]);
        }
    }

    public boolean isFull() {
        return last == maxSize;
    }

    public boolean isEmpty() {
        return cur == last;
    }

    private void checkNull(T element){
        if (element == null){
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) {
        SingleArraryQueue<String> queue = new SingleArraryQueue<String>(String.class,3);
        queue.add("a");
        queue.add("b");
        queue.add("c");
        System.out.println("size: " + queue.size());
        System.out.println(queue.poll());
        System.out.println("#########");
        System.out.println("size: " + queue.size());
        System.out.println(queue.poll());
        System.out.println("#########");
        System.out.println("size: " + queue.size());
        System.out.println(queue.poll());
        System.out.println("#########");
        queue.show();
    }
}
