package com.zws.datastruct.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhengws
 * @date 2019-11-13 19:44
 */
public class ArrGraph<T> implements IGraph<T>{
    /**
     * 存放顶点列表
     */
    private List<T> node;

    /**
     * 顶点个数
     */
    private int nodeSize;

    /**
     * 边个数
     */
    private int edgeCount;

    /**
     * 存放图二维数组
     */
    private int[][] edges;

    /**
     * 标记节点是否被访问过。
     */
    private boolean[] isChecked;


    public ArrGraph(List<T> node) {
        this.node = node;
        this.nodeSize = node.size();
        this.edges = new int[nodeSize][nodeSize];
    }


    /**
     * 插入边
     *
     * @param v1
     * @param v2
     */
    public void insertEdge(int v1, int v2, int weight) {
        checkIndexRange(v1);
        checkIndexRange(v2);
        this.edges[v1][v2] = weight;
        this.edges[v2][v1] = weight;
        this.edgeCount++;
    }

    /**
     * 输出图
     */
    public void printGraph() {
        for (int[] nodes : this.edges) {
            System.out.println(Arrays.toString(nodes));
        }
    }

    /**
     * 获取边个数
     *
     * @return
     */
    public int edgeCount() {
        return this.edgeCount;
    }

    /**
     * 深度优先查找
     */
    public void dfs() {
        isChecked = new boolean[nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            if (!isChecked[i]) {
                dfs(i);
            }
        }
    }

    private void dfs(int v) {
        //1.先输出，并标记为已经访问过.
        System.out.print(node.get(v) + "-->");
        isChecked[v] = true;
        //2.查询该节点的第一个邻居节点.
        int w = getFirstNeighbor(v);
        //3.如果存在第一个邻居节点，
        while (w != -1) {
            if (!isChecked[w]) {
                //3.1 该邻居未被访问过，则进行递归访问该节点.
                dfs(w);
            } else {
                //3.2 该邻居节点被访问过，则说明该路径已经输出，进行查找下一个邻居节点.
                w = getNextNeighbor(v, w);
            }
        }
    }

    /**
     * 广度优先查找
     */
    public void bfs() {
        isChecked = new boolean[nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            if (!isChecked[i]) {
                bfs(i);
            }
        }
    }

    private void bfs(int v) {
        //1.先输出，并标记为已经访问过.
        if (!isChecked[v]) {
            System.out.print(node.get(v) + "-->");
            isChecked[v] = true;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(v);
        int w; //表示队列的头节点对应的下标
        int n; //表示邻居节点
        while (!linkedList.isEmpty()) {
            w = (Integer) linkedList.removeFirst();
            n = getFirstNeighbor(w);
            while (n != -1) {
                if (!isChecked[n]) {
                    System.out.print(node.get(n) + "-->");
                    isChecked[n] = true;
                    linkedList.addLast(n);
                }
                // 以w为前驱节点，找后面下一个邻居节点
                n = getNextNeighbor(w, n);
            }
        }
    }

    private int getNextNeighbor(int v, int w) {
        for (int i = w + 1; i < nodeSize; i++) {
            if (edges[v][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取当前节点的第一个邻居节点
     *
     * @param index
     * @return
     */
    private int getFirstNeighbor(int index) {
        for (int i = 0; i < nodeSize; i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= nodeSize) {
            throw new IllegalArgumentException("index out of range");
        }
    }
}
