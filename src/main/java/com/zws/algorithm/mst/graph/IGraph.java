package com.zws.algorithm.mst.graph;

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
     * 获取顶点信息
     *
     * @param index
     * @return
     */
    Node<?> getNode(int index);

    /**
     * 获取该节点未选中的最小边
     *
     * @param index
     * @return
     */
    Edge getMinUnCheckEdge(int index);

    /**
     * 获取元素存储数组的索引
     *
     * @param element
     * @return
     */
    int getNodeIndex(T element);

    /**
     * 根据数组索引获取节点元素
     *
     * @param index
     * @return
     */
    T getNodeElement(int index);

    /**
     * 获取顶点个数
     *
     * @return
     */
    int getNodeSize();

    /**
     * 设置该边已经被选中, 双向都得置位选中状态.
     *
     * @param edge
     */
    void edgeChecked(Edge edge);
}
