package com.zws.datastruct.graph;

/**
 * @author zhengws
 * @date 2019-11-16 07:29
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
     * 打印输出图
     */
    void printGraph();

    /**
     * 图的边个数
     *
     * @return
     */
    int edgeCount();

    /**
     * 深度优先遍历
     */
    void dfs();

    /**
     * 广度优先遍历
     */
    void bfs();
}
