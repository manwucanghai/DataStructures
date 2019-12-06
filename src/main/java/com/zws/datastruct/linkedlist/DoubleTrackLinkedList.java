package com.zws.datastruct.linkedlist;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengws
 * @date 2019-10-18 14:48
 */
public class DoubleTrackLinkedList<T> implements ILinkedList<T> {

    /**
     * 当前链表个数
     */
    private AtomicInteger size;
    /**
     * 头节点
     */
    private Node<T> head;
    /**
     * 最后一个节点.
     */
    private Node<T> last;

    private static class Node<T> {
        private T item;
        public Node<T> next;
        public Node<T> previous;

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    public DoubleTrackLinkedList() {
        head = last = new Node<T>();
        this.size  = new AtomicInteger(0);
    }

    public void add(T element) {
        checkNullElement(element);
        Node<T> node = new Node<T>(element);
        last.next = node;
        node.previous = last;
        last = node;
        size.getAndIncrement();
    }

    public void insert(int index, T element) {
        checkNullElement(element);
        //如果插入的是最后一个，则相对于添加操作.
        if (size() == index) {
            add(element);
            return;
        }
        checkElementIndex(index);
        Node<T> old = getNode(index);
        Node<T> node = new Node<T>(element);
        node.previous = old.previous;
        node.previous.next = node;
        node.next = old;
        old.previous = node;
        size.getAndIncrement();
    }

    public boolean remove(T element) {
        checkNullElement(element);
        boolean flag = false;
        Node<T> node = head;
        while (node != null){
            if (node.item == element){
                flag = true;
                break;
            }
            node = node.next;
        }
        if (flag){
            return removeNode(node);
        }
        return false;
    }

    private boolean removeNode(Node<T> node) {
        node.previous.next = node.next;
        if (node.next != null){
            node.next.previous = node.previous;
        }
        if (node == last){
            last = node.previous;
        }
        node.previous = node.next = null;
        size.decrementAndGet();
        return true;
    }

    public boolean removeFirst() {
        checkEmptyNode();
        return removeNode(head.next);
    }

    public boolean removeLast() {
        checkEmptyNode();
        return removeNode(last);
    }

    public boolean removeIndex(int index) {
        Node<T> node = getNode(index);
        return removeNode(node);
    }

    public boolean removeLastIndexElement(int index) {
        int idx = (size() - 1) - index;
        return removeIndex(idx);
    }

    public T set(int index, T element) {
        Node<T> node = getNode(index);
        T old = node.item;
        node.item = element;
        return old;
    }

    public T get(int index) {
        Node<T> node = getNode(index);
        return node.item;
    }

    public int size() {
        return this.size.get();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        if (isEmpty()){
            return;
        }
        Node<T> temp;
        while (last != head){
            last.next = null;
            temp = last;
            last = last.previous;
            temp.previous = null;
        }
        last.next = null;
        size.set(0);
    }

    public void show() {
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp.item);
        }
    }

    public void reverseShow() {
        Node<T> temp = last;
        while (temp != head){
            System.out.println(temp.item);
            temp = temp.previous;
        }
    }

    public void reverse() {
        if (size() <= 1){
            return;
        }
        Node<T> node = last = head.next;
        Node<T> tempHead = new Node<T>();
        Node<T> next;
        while (node != null){
            next = tempHead.next;
            tempHead.next = node;
            if (next != null){
                next.previous = node;
            }
            node.previous = tempHead;
            node = node.next;
            tempHead.next.next = next;
        }
        head.next = tempHead.next;
        tempHead.next = null;
    }

    public T lastIndexElement(int index) {
        checkElementIndex(index);
        int idx = (size() - 1) - index;
        return get(idx);
    }

    public T findLastElement() {
        checkEmptyNode();
        return last.item;
    }

    public T findFirstElement() {
        checkEmptyNode();
        return head.next.item;
    }

    private Node<T> getNode(int index){
        checkElementIndex(index);
        Node<T> node = head;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node;
    }
    private void checkEmptyNode() {
        if (isEmpty()) {
            throw new NoSuchElementException("linked queue is empty.");
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
}
