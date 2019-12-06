package com.zws.datastruct.linkedlist;

import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengws
 * @date 2019-10-17 09:19
 */
public class SingleTrackLinkedList<T> implements ILinkedList<T> {
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

        public Node() {
        }

        public Node(T item) {
            this.item = item;
        }
    }

    public SingleTrackLinkedList() {
        head = last = new Node<T>();
        this.size = new AtomicInteger(0);
    }

    /**
     * 添加元素
     * 1、检测元素是否为空
     * 2、往链表最后一个节点添加元素，并自增节点元素个数.
     *
     * @param element
     */
    public void add(T element) {
        checkNullElement(element);
        Node<T> node = new Node<T>(element);
        last.next = node;
        last = node;
        size.getAndIncrement();
    }

    /**
     * 往链表特定元素插入指定值
     * 1、检测元素是否为空
     * 2、如果插入的是最后一个，则往链表末尾添加该元素
     * 3、获取该索引值的上一个节点
     * 4、将插入节点的next指向原来节点
     * 5、将上一个节点的next指向插入节点
     * 6、链表元素个数自增
     *
     * @param index
     * @param element
     */
    public void insert(int index, T element) {
        //如果插入的是最后一个，则相当于添加操作.
        checkNullElement(element);
        if (size() == index) {
            add(element);
            return;
        }
        checkElementIndex(index);
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        Node<T> node = new Node<T>(element);
        node.next = temp.next;
        temp.next = node;
        this.size.getAndIncrement();
    }

    /**
     * 移除链表中指定元素
     * 1、检测该元素是否为空
     * 2、查找链表中，该元素的上一个节点
     * 3、移除下一个节点(也就是元素所在的节点)
     *
     * @param element
     * @return
     */
    public boolean remove(T element) {
        checkNullElement(element);
        Node<T> temp = head;
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.item == element) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            return removeNextNode(temp);
        }
        return false;
    }


    /**
     * 移除链表中第一个节点
     * 1、判断链表是否为空，为空则抛异常
     * 2、传递head,移除head的下一个节点.
     *
     * @return
     */
    public boolean removeFirst() {
        checkEmptyNode();
        return removeNextNode(head);
    }

    /**
     * 移除最后一个节点
     *
     * @return
     */
    public boolean removeLast() {
        checkEmptyNode();
        //如果链表只剩一个元素，则移除最后一个元素，相当于移除第一个.
        int size = size();
        return removeIndex(size - 1);
    }

    /**
     * 移除指定索引的节点
     * 1、检测索引值范围是否合法
     * 2、获取该节点的前一个节点
     * 3、移除该节点
     * @param index
     * @return
     */
    public boolean removeIndex(int index) {
        checkElementIndex(index);
        Node<T> node;
        if (index == 0) {
            node = head;
        } else {
            node = getNode(index - 1);
        }
        return removeNextNode(node);
    }

    /**
     * 移除倒数第index个节点
     * 最后一个元素, lastIndex为0，也就是倒数第零个元素为最后一个.
     * 1、检测索引值范围是否合法
     * 2、获取正向索引
     * 3、移除第index(正向索引)节点
     * @param lastIndex
     * @return
     */
    public boolean removeLastIndexElement(int lastIndex) {
        checkElementIndex(lastIndex);
        int index = (size() - 1) - lastIndex;
        return removeIndex(index);
    }

    /**
     * 修改第index个节点元素
     * 1、检测索引值范围是否合法
     * 2、查找获取该节点
     * 3、修改该节点元素内容，返回旧的元素值
     * @param index
     * @param element
     * @return
     */
    public T set(int index, T element) {
        checkElementIndex(index);
        Node<T> node = getNode(index);
        T old = node.item;
        node.item = element;
        return old;
    }

    /**
     * 1、检测索引值范围是否合法
     * 2、查找获取该节点
     * @param index
     * @return
     */
    public T get(int index) {
        checkElementIndex(index);
        Node<T> temp = getNode(index);
        return temp.item;
    }

    public int size() {
        return this.size.get();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        if (isEmpty()) {
            return;
        }
        Node<T> cur = head;
        Node<T> next;
        while (cur != null) {
            next = cur.next;
            cur.next = null;
            cur = next;
        }
        last = head;
        this.size.set(0);
    }

    public void show() {
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp.item);
        }
    }

    public void reverseShow() {
        if (isEmpty()) {
            return;
        }
        Stack<Node<T>> stack = new Stack<Node<T>>();
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            stack.push(temp);
        }
        while (!stack.empty()) {
            temp = stack.pop();
            System.out.println(temp.item);
        }
    }

    public void reverse() {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (size() <= 1) {
            return;
        }
        Node<T> tempHead = new Node<T>();
        //将最后一个指向原始第一个节点.
        Node<T> cur = last = head.next;
        Node<T> next;
        while (cur != null) {
            //获取当前节点的下一个节点，不然底下cur.next = tempHead.next赋值后，
            // 就无法找到原始链表的下一个节点了。
            next = cur.next;
            cur.next = tempHead.next;
            tempHead.next = cur;
            cur = next;
        }
        //记得将新链表赋给旧的链表
        head.next = tempHead.next;
        tempHead.next = null;
    }

    public T lastIndexElement(int lastIndex) {
        //最后一个元素, lastIndex为0，也就是倒数第零个元素为最后一个.
        checkElementIndex(lastIndex);
        if (lastIndex == 0) {
            return findLastElement();
        }
        int index = size() - 1 - lastIndex;
        return get(index);
    }

    public T findLastElement() {
        checkEmptyNode();
        return last.item;
    }

    public T findFirstElement() {
        checkEmptyNode();
        return head.next.item;
    }

    private boolean removeNextNode(Node<T> node) {
        //判断node是否是倒数第二个.
        if (node.next == last) {
            last = node;
        }
        Node<T> remove = node.next;
        node.next = remove.next;
        this.size.decrementAndGet();
        remove.next = null;
        return true;
    }

    private Node<T> getNode(int index) {
        Node<T> temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp;
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
