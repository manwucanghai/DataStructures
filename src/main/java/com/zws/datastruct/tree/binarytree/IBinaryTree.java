package com.zws.datastruct.tree.binarytree;

/**
 * @author zhengws
 * @date 2019-11-01 19:14
 */
public interface IBinaryTree<E> {

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

    /**
     * 前序遍历
     */
    void preOrder();

    /**
     * 后序遍历
     */
    void postOrder();

    /**
     * 中序遍历
     */
    void infixOrder();
}
