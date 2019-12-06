package com.zws.algorithm.dijkstra.graph;


import java.util.List;

/**
 * @author zhengws
 * @date 2019-11-17 16:49
 */
public interface IGraph<T> {

    /**
     * 插入图顶点
     *
     * @param v1     顶点1索引
     * @param v2     顶点2索引
     * @param weight 边的权重
     */
    void insertEdge(int v1, int v2, int weight);


    /**
     * 获取最短路径，从start节点开始到达end节点
     * @param start
     * @param end
     * @return
     */
    List<T> getShortestPath(T start, T end);
}
