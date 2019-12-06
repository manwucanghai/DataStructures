package com.zws.datastruct.linkedlist;

import com.zws.datastruct.linkedlist.ILinkedList;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengws
 * @date 2019-10-19 09:23
 */
public class RoundSingleLinkedList<T> implements ILinkedList<T> {

    private int maxSize;
    /**
     * size代表当前有效元素个数.
     */
    private AtomicInteger size;
    /**
     * first往前走一格 size自减
     */
    private Node<T> first;
    /**
     * last往前走一格, size自增
     */
    private Node<T> last;

    /**
     * 删除节点后，缓存节点，用于下一次使用
     */
    private ConcurrentLinkedQueue<Node<T>> rmNodeCache;

    private static class Node<T> {
        public T item;
        public Node<T> next;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    public RoundSingleLinkedList(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("maxSize must large 0");
        }
        this.maxSize = maxSize;
        this.size = new AtomicInteger(0);
        this.rmNodeCache = new ConcurrentLinkedQueue<Node<T>>();
    }


    public void add(T element) {
        checkNullElement(element);
        checkRoundLinkedFull();
        if (!checkAndInitFistNode(element)) {
            Node<T> node = getCacheNode();
            if (node == null) {
                node = new Node<T>(element);
            }
            last.next = node;
            node.next = first;
            last = node;
        }
        this.size.getAndIncrement();
    }

    private boolean checkAndInitFistNode(T element) {
        if (first == null) {
            first = last = new Node<T>(element);
            first.next = first;
            return true;
        }
        return false;
    }

    private Node<T> getCacheNode() {
        if (this.rmNodeCache.isEmpty()) {
            return null;
        }
        return rmNodeCache.poll();
    }

    private void addCacheNode(Node<T> node) {
        node.next = null;
        node.item = null;
        rmNodeCache.add(node);
    }

    public void insert(int index, T element) {

    }

    public boolean remove(T element) {
        checkEmptyNode();
        Node<T> node = last;
        int size = size();
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (node.next.item == element) {
                flag = true;
                break;
            }
        }
        if (flag) {
            return removeNextNode(node);
        }
        return false;
    }

    public boolean removeNextNode(Node<T> node) {
        if (node == null) {
            return false;
        }
        if (size() == 1) {
            clear();
            return true;
        }
        Node<T> remove = node.next;
        checkRemoveNode(node, remove);
        node.next = remove.next;
        addCacheNode(remove);
        this.size.decrementAndGet();
        return true;
    }

    private void checkRemoveNode(Node<T> node, Node<T> remove) {
        //针对移除节点为first或者last节点进行特殊处理。
        if (remove == first) {
            first = remove.next;
        } else if (remove == last) {
            last = node;
        }
    }

    public boolean removeFirst() {
        checkEmptyNode();
        return removeNextNode(last);
    }

    public boolean removeLast() {
        checkEmptyNode();
        int size = size();
        return removeIndex(size - 1);
    }

    public boolean removeIndex(int index) {
        checkEmptyNode();
        int preIndex = (index - 1 + maxSize) % maxSize;
        Node<T> node = getNode(preIndex);
        return removeNextNode(node);
    }

    public boolean removeLastIndexElement(int index) {
        return false;
    }

    public T set(int index, T element) {
        return null;
    }

    public T get(int index) {
        return null;
    }

    private Node<T> getNode(int index) {
        checkElementIndex(index);
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public int size() {
        return this.size.get();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == this.maxSize;
    }

    public void clear() {
        Node<T> node = first;
        Node<T> next;
        while (node != null) {
            next = node.next;
            node.next = null;
            node.item = null;
            node = next;
        }
        this.size.set(0);
        first = last = null;
        rmNodeCache.clear();
    }

    public void show() {
        if (isEmpty()) {
            return;
        }
        Node<T> node = first;
        while (true) {
            System.out.println(node.item);
            node = node.next;
            if (node == first){
                break;
            }
        }
    }

    /**
     * 约瑟夫环出列打印.
     * @param k
     * @param m
     */
    public void josephLoopPrint(int k, int m){
        checkEmptyNode();
        checkElementIndex(k);
        //1. 将first，last 先移动 k-1个位置
        moveIndex(k - 1);
        while (!isEmpty()){
            //2. 让first，last指针同时移动 m-1
            moveIndex(m - 1);
            //3. 将当前first所指向的节点移除.
            System.out.println("出列人员: " + first.item);
            removeFirst();
        }
    }

    private void moveIndex(int index){
        for (int i = 0; i < index; i++) {
            first = first.next;
            last = last.next;
        }
    }

    public void reverseShow() {

    }

    public void reverse() {

    }

    public T lastIndexElement(int index) {
        return null;
    }

    public T findLastElement() {
        return null;
    }

    public T findFirstElement() {
        return null;
    }

    private void checkEmptyNode() {
        if (isEmpty()) {
            throw new NoSuchElementException("round linked is empty.");
        }
    }

    private void checkNullElement(T element) {
        if (element == null) {
            throw new NullPointerException("element can't be null");
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new NoSuchElementException("The index element does not exist");
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < this.size.get();
    }

    private void checkRoundLinkedFull() {
        if (isFull()) {
            throw new RuntimeException("round linked is full");
        }
    }
}
