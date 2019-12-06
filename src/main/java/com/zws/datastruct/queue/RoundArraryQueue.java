package com.zws.datastruct.queue;

import java.lang.reflect.Array;

/**
 * @author zhengws
 * @date 2019-10-12 10:45
 */
public class RoundArraryQueue<T> implements IQueue<T> {
    private int maxSize;
    private int cur;
    private int last;
    private T[] queue;

    public RoundArraryQueue(Class clazz, int maxSize) {
        // 预留一个空间。
        this.maxSize = maxSize + 1;
        this.queue = (T[]) Array.newInstance(clazz, this.maxSize);
    }

    public void add(T element) {
        checkNull(element);
        if (isFull()) {
            throw new RuntimeException("queue full");
        }
        this.queue[last] = element;
        last = (last + 1) % maxSize;
    }

    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("queue empty");
        }
        T element = this.queue[cur];
        cur = (cur + 1) % maxSize;
        return element;
    }

    public int size() {
        return (last + maxSize - cur) % maxSize;
    }

    public void show() {
        if (isEmpty()){
            return;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            int index = (cur + i) % maxSize;
            System.out.println(queue[index]);
        }
    }

    public boolean isFull() {
        return (last + 1) % maxSize == cur;
    }

    public boolean isEmpty() {
        return cur == last;
    }

    private void checkNull(T element) {
        if (element == null) {
            throw new NullPointerException();
        }
    }

    public static void main(String[] args) {
        RoundArraryQueue<String> queue = new RoundArraryQueue<String>(String.class, 3);
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
         * 输出：
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
