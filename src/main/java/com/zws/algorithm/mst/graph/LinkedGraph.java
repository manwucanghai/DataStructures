package com.zws.algorithm.mst.graph;

import java.util.List;

/**
 * @author zhengws
 * @date 2019-11-17 16:50
 */
public class LinkedGraph<T> implements IGraph<T> {

    /**
     * 数组，存放顶点信息
     */
    private Node<?>[] nodes;

    private List<T> elements;

    /**
     * 边个数
     */
    private int edgeCount;


    public LinkedGraph(List<T> nodes) {
        this.nodes = new Node<?>[nodes.size()];
        this.elements = nodes;
        for (int i = 0; i < nodes.size(); i++) {
            this.nodes[i] = new Node<>(nodes.get(i));
        }
    }

    /**
     * 添加边
     *
     * @param v1     顶点1索引
     * @param v2     顶点2索引
     * @param weight 边的权重
     */
    @Override
    public void insertEdge(int v1, int v2, int weight) {
        innerAddEdge(v1, v2, weight);
//        innerAddEdge(v2, v1, weight);
        edgeCount++;
    }

    private void innerAddEdge(int v1, int v2, int weight) {
        Node<?> node = this.nodes[v1];
        Edge edge = new Edge(v1, v2, weight);
        if (node.lastEdge == null) {
            node.firstEdge = node.lastEdge = edge;
        } else {
            node.lastEdge.next = edge;
        }
        node.lastEdge = edge;
    }

    /**
     * 获取顶点信息
     *
     * @param index
     * @return
     */
    @Override
    public Node<?> getNode(int index) {
        return this.nodes[index];
    }


    @Override
    public Edge getMinUnCheckEdge(int index) {
        Node<?> node = getNode(index);
        Edge edge = node.firstEdge;
        Edge minEdge = null;
        while (edge != null) {
            if (!edge.isCheck) {
                if (minEdge == null) {
                    minEdge = edge;
                } else if (edge.weight < minEdge.weight) {
                    minEdge = edge;
                }
            }
            edge = edge.next;
        }
        return minEdge;
    }

    @Override
    public int getNodeIndex(T element) {
        for (int i = 0; i < this.elements.size(); i++) {
            if (element.equals(this.elements.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T getNodeElement(int index) {
        return this.elements.get(index);
    }

    @Override
    public int getNodeSize() {
        return nodes.length;
    }

    @Override
    public void edgeChecked(Edge edge) {
        if (edge == null) {
            return;
        }
        edge.isCheck = true;
        Edge reverseEdge = getNode(edge.end).firstEdge;
        while (reverseEdge != null) {
            if (edge.start == reverseEdge.end && edge.end == reverseEdge.start) {
                reverseEdge.isCheck = true;
                break;
            }
            reverseEdge = reverseEdge.next;
        }
    }
}
