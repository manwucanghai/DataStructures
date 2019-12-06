package com.zws.datastruct.tree.multitree;

/**
 * @author zhengws
 * @date 2019-11-08 14:54
 */
public interface IMultiTree<E> {
    /**
     * 添加元素
     * @param e
     * @return
     */
    boolean add(E e);

    /**
     * 删除元素
     * @param e
     * @return
     */
    boolean remove(E e);

    /**
     * 判断树是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 将树清空
     */
    void clear();
}
