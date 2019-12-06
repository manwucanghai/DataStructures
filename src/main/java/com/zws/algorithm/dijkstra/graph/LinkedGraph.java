package com.zws.algorithm.dijkstra.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 使用迪杰斯特拉算法，求最短路径问题。
 * @author zhengws
 * @date 2019-11-20 14:09
 */
public class LinkedGraph<T> implements IGraph<T> {
    /**
     * 数组，存放顶点信息
     */
    private Node<?>[] nodes;

    private List<T> elements;

    private ConcurrentLinkedQueue<Node<?>> queue = new ConcurrentLinkedQueue<>();

    private List<T> path = new ArrayList<>();


    public LinkedGraph(List<T> nodes) {
        this.nodes = new Node<?>[nodes.size()];
        this.elements = nodes;
        for (int i = 0; i < nodes.size(); i++) {
            this.nodes[i] = new Node<>(nodes.get(i));
        }
    }

    public void insertEdge(int v1, int v2, int weight) {
        innerAddEdge(v1, v2, weight);
        innerAddEdge(v2, v1, weight);
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
     * 获取最短路径
     *
     * @param startElement
     * @param endElement
     * @return
     */
    @Override
    public List<T> getShortestPath(T startElement, T endElement) {
        /**
         * 1.获取开始节点及终止节点，对应数组下标的索引
         *
         */
        int start = getNodeIndex(startElement);
        int end = getNodeIndex(endElement);
        if (start == -1 || end == -1) {
            return Collections.emptyList();
        }

        /**
         * 2.根据下标获取开始节点node,
         *  2.1 将该节点的preIndex设置为自己
         *  2.2 将该节点的pathWeight设置为0
         *  2.3 将该节点添加到队列queue中，提供后续遍历
         */
        Node<?> node = getNode(start);
        node.preIndex = start;
        node.pathWeight = 0;
        queue.add(node);

        shortestPath(end);
//        printNodeInfo();
        addPath(path, end, start);
        return path;
    }

    private void printNodeInfo() {
        for (Node<?> node : nodes){
            System.out.println(node);
        }
    }


    private void addPath(List<T> path, int index, int start) {
        if (index != start) {
            addPath(path, getNode(index).preIndex, start);
        }
        path.add(getNodeElement(index));
    }

    private void shortestPath(int end) {
        Node<?> node;
        int preIndex;
        Node<?> endNode = getNode(end);
        /**
         * 3. 遍历queue中的顶点，直至queue中的顶点为空
         */
        while (!queue.isEmpty()) {
            node = queue.poll();
            preIndex = getNodeIndex(node);
            if (preIndex == -1) {
                continue;
            }
            /**
             * 4.先检测是否目标节点已经有路径达到
             *  4.1、如果没有, 则继续进行子节点路径查询.
             *  4.2、如果有，则先判断当前路径长度与已经到达路径的长度,如果当前路径较长，则无需进行子节点路径查询。
             */
            if (!endNode.isCheck || endNode.pathWeight > node.pathWeight){
                subNodePath(end, node, preIndex);
            }
        }

    }

    private void subNodePath(int end, Node<?> node, int preIndex) {
        Node<?> next;
        int weight;
        Edge edge = node.firstEdge;
        while (edge != null) {
            /**
             * 5. 获取当前节点到达起始节点的路径值 weight
             * 5.1 如果当前节点未被走过，则将当前节点的preIndex设置为前驱节点的索引，pathWeight设置为weight，并将该节点添加到queue中
             * 5.2 如果当前节点被走过，则判断当前节点的pathWeight 与 weight的值，
             *    5.2.1 如果weight比较小，则将当前节点的preIndex设置为前驱节点的索引，pathWeight设置为weight，并将该节点添加到queue中
             *    5.2.2 如果weight比较大，则进行下一个邻接节点遍历。
             */
            next = getNode(edge.end);
            weight = node.pathWeight + edge.weight;
            if (next.preIndex == -1 || next.pathWeight > weight) {
                next.preIndex = preIndex;
                next.pathWeight = weight;
                if (edge.end != end) {
                    queue.add(next);
                } else {
                    next.isCheck = true;
                }
            }
            edge = edge.next;
        }
    }


    private Node<?> getNode(int index) {
        return this.nodes[index];
    }

    /**
     * 根据顶点元素，获取该顶点对应数组下标索引值
     * @param element
     * @return
     */
    private int getNodeIndex(T element) {
        for (int i = 0; i < this.elements.size(); i++) {
            if (element.equals(this.elements.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据顶点信息，直接获取该顶点对应数组的下标索引值.
     * @param node
     * @return
     */
    private int getNodeIndex(Node<?> node) {
        for (int i = 0; i < this.nodes.length; i++) {
            if (this.nodes[i] == node) {
                return i;
            }
        }
        return -1;
    }

    private T getNodeElement(int index) {
        return this.elements.get(index);
    }


    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        IGraph<String> graph = new LinkedGraph<>(nodes);
        graph.insertEdge(0, 1, 5);
        graph.insertEdge(0, 2, 7);
        graph.insertEdge(0, 6, 5);
        graph.insertEdge(1, 6, 3);
        graph.insertEdge(1, 3, 9);
        graph.insertEdge(2, 4, 8);
        graph.insertEdge(3, 5, 4);
        graph.insertEdge(4, 5, 5);
        graph.insertEdge(4, 6, 4);
        graph.insertEdge(5, 6, 6);
//        graph.insertEdge(6, 2, 1);

        List<String> path = graph.getShortestPath("D", "C");
        System.out.println(path);

        /**
         * 输出：
         * [D, F, E, C]
         */
    }
}
