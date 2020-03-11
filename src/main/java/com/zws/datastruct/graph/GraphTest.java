package com.zws.datastruct.graph;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhengws
 * @date 2019-11-16 08:01
 */
public class GraphTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        IGraph<Integer> arrGraph = new LinkedGraph<>(list);
//        IGraph<Integer> arrGraph = new ArrGraph<>(list);
        arrGraph.insertEdge(0, 1, 1);
        arrGraph.insertEdge(0, 2, 1);
        arrGraph.insertEdge(1, 3, 1);
        arrGraph.insertEdge(1, 4, 1);
        arrGraph.insertEdge(3, 7, 1);
        arrGraph.insertEdge(4, 7, 1);
        arrGraph.insertEdge(2, 5, 1);
        arrGraph.insertEdge(2, 6, 1);
        arrGraph.insertEdge(5, 6, 1);

//        arrGraph.printGraph();

        System.out.println("深度度优先遍历");
        arrGraph.dfs();
        System.out.println();
        System.out.println("广度优先遍历");
        arrGraph.bfs();

        /**
         * [0, 1, 1, 0, 0, 0, 0, 0]
         * [1, 0, 0, 1, 1, 0, 0, 0]
         * [1, 0, 0, 0, 0, 1, 1, 0]
         * [0, 1, 0, 0, 0, 0, 0, 1]
         * [0, 1, 0, 0, 0, 0, 0, 1]
         * [0, 0, 1, 0, 0, 0, 1, 0]
         * [0, 0, 1, 0, 0, 1, 0, 0]
         * [0, 0, 0, 1, 1, 0, 0, 0]
         * 深度度优先遍历
         * 1-->2-->4-->8-->5-->3-->6-->7-->
         * 广度优先遍历
         * 1-->2-->3-->4-->5-->6-->7-->8-->
         */
    }
}
