package com.zws.algorithm.dijkstra.graph;

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

    /**
     * 判断终点是否已经有路径到达
     * 如果到达，则判断当前路径从值是否低于当前到达终点的路径值，如果低于才有必要进行子路径查询，否则没必要。
     */
    public boolean isCheck;

    /**
     * 记录上一个节点索引值,默认为-1
     */
    public int preIndex = -1;

    /**
     * 记录起始节点到当前节点最小的路径长度值。
     */
    public int pathWeight = -1;


    public Node(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", pathWeight=" + pathWeight +
                '}';
    }
}
