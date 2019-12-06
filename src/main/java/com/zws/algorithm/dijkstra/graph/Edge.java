package com.zws.algorithm.dijkstra.graph;

/**
 * 边信息
 * @author zhengws
 * @date 2019-11-17 16:55
 */
public class Edge implements Comparable<Edge>{
    /**
     * 连接的起始顶点的索引值
     */
    public int start;

    /**
     * 连接的终止顶点的索引值
     */
    public int end;

    /**
     * 边的权重
     */
    public int weight;

    /**
     * 是否被选中
     */
    public boolean isCheck;

    /**
     * 下一个边(针对于同一个节点，主要用于构建链表.)
     */
    public Edge next;


    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}
