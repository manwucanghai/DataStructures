package com.zws.datastruct.graph;

import java.util.List;

/**
 * 采用链表的形式存储图
 *
 * @author zhengws
 * @date 2019-11-16 07:28
 */
public class LinkedGraph<T> implements IGraph<T> {

    /**
     * 数组，存放顶点信息
     */
    private Node<?>[] nodes;

    /**
     * 边个数
     */
    private int edgeCount;


    private static class Node<T> {

        /**
         * 节点存放的元素
         */
        T item;

        /**
         * 是否被访问过
         */
        boolean isChecked;

        /**
         * 第一个边信息
         */
        NodeSide firstSide;

        /**
         * 该节点的最后一个边信息。
         */
        NodeSide lastSide;

        public Node(T item) {
            this.item = item;
        }
    }

    private static class NodeSide {
        /**
         * 对应数组的下标
         */
        int num;
        /**
         * 指向下一个邻接节点.
         */
        NodeSide next;
        /**
         * 边的权重
         */
        int weight;

        public NodeSide(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    /**
     * 初始化节点信息.
     */
    public LinkedGraph(List<T> nodes) {
        this.nodes = new Node<?>[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            this.nodes[i] = new Node<>(nodes.get(i));
        }
    }

    @Override
    public void insertEdge(int v1, int v2, int weight) {
        Node<?> node = this.nodes[v1];
        NodeSide nodeSide = new NodeSide(v2, weight);
        if (node.lastSide == null) {
            node.lastSide = nodeSide;
            node.firstSide = nodeSide;
        } else {
            node.lastSide.next = nodeSide;
        }
        node.lastSide = nodeSide;
        edgeCount++;
    }

    @Override
    public void printGraph() {
        for (Node<?> node : nodes) {
            NodeSide side = node.firstSide;
            while (side != null) {
                System.out.print(nodes[side.num].item);
                side = side.next;
            }
            System.out.println();
        }
    }

    @Override
    public int edgeCount() {
        return edgeCount;
    }

    @Override
    public void dfs() {
        clearCheck();
        for (Node<?> node : nodes) {
            dfs(node);
        }
    }

    private void dfs(Node<?> node) {
        /**
         * 注意，判断是否访问过必须在这个位置，而不是在上层for循环判断
         * 因为，一个节点可以从不同路径访问到，如果该节点已经被访问过了，
         * 则说明该节点的邻接节点已经全部遍历完毕。
         */
        if (node.isChecked){
            return;
        }
        checkedAndPrint(node);
        NodeSide side = node.firstSide;
        while (side != null) {
            dfs(nodes[side.num]);
            side = side.next;
        }
    }

    /**
     * 广度优先遍历
     */
    @Override
    public void bfs() {
        clearCheck();
        for (Node<?> node : nodes) {
            checkedAndPrint(node);
            NodeSide side = node.firstSide;
            while (side != null) {
                Node<?> nextNode = nodes[side.num];
                checkedAndPrint(nextNode);
                side = side.next;
            }
        }
    }

    private void checkedAndPrint(Node<?> node) {
        if (!node.isChecked) {
            System.out.print(node.item + "==>");
            node.isChecked = true;
        }
    }

    private void clearCheck(){
        for (Node<?> node : nodes) {
            node.isChecked = false;
        }
    }
}
