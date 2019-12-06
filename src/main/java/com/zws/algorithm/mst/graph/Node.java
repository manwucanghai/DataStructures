package com.zws.algorithm.mst.graph;

/**
 * @author zhengws
 * @date 2019-11-17 16:50
 */
public class Node<T> {
    /**
     * 存放顶点信息
     */
    public T item;

    /**
     * 第一条边信息，也就是链表的第一个元素
     */
    public Edge firstEdge;

    /**
     * 最后一条边，主要方便添加边是无需进行遍历链表，即可进行添加操作
     */
    public Edge lastEdge;


    public Node(T item) {
        this.item = item;
    }
}
