package com.zws.datastruct.tree.redblacktree;

/**
 * @author zhengws
 * @date 2020-03-02 23:20
 */
public interface IRedBlackTree<E extends Comparable<E>> {
    /**
     * 添加元素
     *
     * @param element
     * @return
     */
    boolean add(E element);

    /**
     * 移除元素
     *
     * @param element
     * @return
     */
    boolean remove(E element);

    /**
     * 判断树是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 将树清空
     */
    void clear();

    /**
     * 获取元素个数
     *
     * @return
     */
    int size();
}
