package com.zws.datastruct.linkedlist;

/**
 * @author zhengws
 * @date 2019-10-17 09:20
 * 链表
 */
public interface ILinkedList<T> {
    /**
     * 添加元素
     * @param element
     * @return
     */
    void add(T element);

    /**
     * 往第index索引插入元素
     * @param index
     * @param element
     */
    void insert(int index, T element);

    /**
     * 移除节点
     * @param element
     * @return
     */
    boolean remove(T element);

    /**
     * 移除第一个元素.
     * @return
     */
    boolean removeFirst();

    /**
     * 移除最后一个元素.
     * @return
     */
    boolean removeLast();

    /**
     * 移除第index个
     * @param index
     * @return
     */
    boolean removeIndex(int index);

    /**
     * 移除倒数第index个元素.
     * @param index
     * @return
     */
    boolean removeLastIndexElement(int index);

    /**
     * 替换指定索引节点的元素，并返回旧值，如果旧值为空，则返回null.
     * @param index
     * @param element
     * @return
     */
    T set(int index, T element);

    /**
     * 获取第index个元素.
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 获取链表节点个数
     * @return
     */
    int size();

    /**
     * 判断链表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空链表
     */
    void clear();

    /**
     * 显示所有节点.
     */
    void show();

    /**
     * 反向打印，注意不改变原有链表的顺序。
     */
    void reverseShow();

    /**
     * 链表反转。
     */
    void reverse();

    /**
     * 获取倒数第index个元素，不存在则返回空。
     * @param index
     * @return
     */
    T lastIndexElement(int index);

    /**
     * 查找最后一个元素.
     * @return
     */
    T findLastElement();

    /**
     * 查找第一个元素.
     * @return
     */
    T findFirstElement();
}
