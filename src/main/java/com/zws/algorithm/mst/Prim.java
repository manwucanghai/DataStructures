package com.zws.algorithm.mst;

import com.zws.algorithm.mst.graph.Edge;
import com.zws.algorithm.mst.graph.IGraph;
import com.zws.algorithm.mst.graph.LinkedGraph;

import java.util.*;

/**
 * @author zhengws
 * @date 2019-11-17 16:44
 */
public class Prim<T> implements IMinSpanTree<T> {

    private IGraph<T> graph;

    /**
     * 已经添加的节点
     */
    private Set<Integer> nodeKeys = new HashSet<>();

    private List<String> path = new ArrayList<>();


    public Prim(IGraph<T> graph) {
        this.graph = graph;
    }

    @Override
    public List<String> getMstPath(T start) {
        int size = graph.getNodeSize();
        nodeKeys.add(graph.getNodeIndex(start));
        Edge minEdge = null;
        T end = null;
        while (nodeKeys.size() < size) {
            minEdge = null;
            for (int e : nodeKeys) {
                Edge edge = graph.getMinUnCheckEdge(e);
                /**
                 * 1. 判断边是否为null或者可构成回路的边
                 *    如果可构成回路，直接将该边置位已选中，避免下次再进行判断.
                 */
                if (edge == null || nodeKeys.contains(edge.end)) {
                    graph.edgeChecked(edge);
                    continue;
                }
                /**
                 * 2. 获取最小权重的边.
                 */
                if (minEdge == null) {
                    minEdge = edge;
                } else if (edge.weight < minEdge.weight) {
                    minEdge = edge;
                }
            }
            /**
             * 3. 如果获取的最小边不为null, 则将该边置位已选中，
             *    并将该边连接的另一个节点添加到nodeKeys，查找下一次最小边提供节点信息.
             */
            if (minEdge != null) {
                graph.edgeChecked(minEdge);
                nodeKeys.add(minEdge.end);
                start = graph.getNodeElement(minEdge.start);
                end = graph.getNodeElement(minEdge.end);
                path.add(start + " -(" + minEdge.weight + ")-> " + end);
            }
        }
        return path;
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

        Prim<String> prim = new Prim<>(graph);

        List<String> a = prim.getMstPath("A");
        System.out.println(a);

        /**
         * 输出：
         * [A -(2)-> G, G -(3)-> B, G -(4)-> E, E -(5)-> F, F -(4)-> D, A -(7)-> C]
         */
    }
}
