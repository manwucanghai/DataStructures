package com.zws.algorithm.mst;

import com.zws.algorithm.mst.graph.Edge;
import com.zws.algorithm.mst.graph.IGraph;
import com.zws.algorithm.mst.graph.LinkedGraph;
import com.zws.algorithm.mst.graph.Node;

import java.util.*;

/**
 * @author zhengws
 * @date 2019-11-18 16:10
 */
public class Kruska<T> implements IMinSpanTree<T> {

    private IGraph<T> graph;

    /**
     * 已经添加的节点
     */
    private Set<Integer> nodeKeys = new HashSet<>();

    /**
     * 显示路径
     */
    private List<String> path = new ArrayList<>();

    /**
     * 记录顶点个数
     */
    private int pointSize;

    /**
     * 存放当前节点的末尾节点所有，其中数组的索引代表当前节点的索引ID
     */
    private int[] ends;

    public Kruska(IGraph<T> graph) {
        this.graph = graph;
        this.pointSize = graph.getNodeSize();
        initEnds();
    }

    /**
     * 将终点数组初始化都为-1.
     */
    private void initEnds() {
        this.ends = new int[pointSize];
        for (int i = 0; i < pointSize; i++) {
            ends[i] = -1;
        }
    }


    @Override
    public List<String> getMstPath(T point) {
        PriorityQueue<Edge> queue = getEdegeQueue();
        Edge edge = null;
        while (nodeKeys.size() < pointSize && !queue.isEmpty()) {
            edge = queue.poll();
            if (edge.isCheck) {
                continue;
            }
            int start = getEnd(edge.start);
            int end = getEnd(edge.end);
            if (start != end) {
                //没有构成回路
                nodeKeys.add(edge.start);
                nodeKeys.add(edge.end);
                /**
                 * 注意: 这里是采用 ends[start] = end , 而不是 ends[edge.start] = end.
                 * 原因，确保每次获取终点都是最新的终点。
                 */
                ends[start] = end;
                path.add(graph.getNodeElement(edge.start) + "-(" + edge.weight + ")->" + graph.getNodeElement(edge.end));
            }
            graph.edgeChecked(edge);
        }
        return path;
    }


    /**
     * 对图的边进行排序
     * @return
     */
    private PriorityQueue<Edge> getEdegeQueue() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        int size = graph.getNodeSize();
        Node<?> node = null;
        Edge edge = null;
        for (int i = 0; i < size; i++) {
            node = graph.getNode(i);
            if (node != null) {
                edge = node.firstEdge;
                while (edge != null) {
                    queue.add(edge);
                    edge = edge.next;
                }
            }
        }
        return queue;
    }

    /**
     * 获取当前节点的末尾节点的索引值。
     *  如果获取的终点的值不为-1，则递归查找父节点的终点索引值.
     *
     * @param index
     * @return
     */
    private int getEnd(int index) {
        while (ends[index] != -1) {
            index = ends[index];
        }
        return index;
    }

    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E", "F", "G");
        IGraph<String> graph = new LinkedGraph<>(nodes);
        graph.insertEdge(0, 1, 5);
        graph.insertEdge(0, 2, 7);
        graph.insertEdge(0, 6, 2);
        graph.insertEdge(1, 6, 3);
        graph.insertEdge(1, 3, 9);
        graph.insertEdge(2, 4, 8);
        graph.insertEdge(3, 5, 4);
        graph.insertEdge(4, 5, 5);
        graph.insertEdge(4, 6, 4);
        graph.insertEdge(5, 6, 6);

        Kruska<String> kruska = new Kruska<>(graph);
        List<String> path = kruska.getMstPath(null);
        System.out.println(path);
        /**
         * 输出：
         * [A-(2)->G, B-(3)->G, E-(4)->G, D-(4)->F, E-(5)->F, A-(7)->C]
         */
    }
}
