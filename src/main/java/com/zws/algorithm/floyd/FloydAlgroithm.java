package com.zws.algorithm.floyd;

import java.util.Arrays;

/**
 * 使用弗洛伊德算法，求解最短路径问题(各个顶点间的最短路径)
 *
 * @author zhengws
 * @date 2019-11-21 17:55
 */
public class FloydAlgroithm {
    /**
     * 存放节点值信息
     */
    private char nodes[];


    /**
     * 节点个数
     */
    private int nodeSize;


    /**
     * 存放各个顶点间的最短路径值
     */
    private int[][] dis;


    /**
     * 存放中间顶点信息
     */
    private char[][] pre;


    public FloydAlgroithm(char[] nodes, int size, int[][] dis) {
        this.nodes = nodes;
        this.nodeSize = size;
        this.dis = dis;
        this.pre = new char[nodeSize][nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            char[] p = pre[i];
            Arrays.fill(p, nodes[i]);
        }
    }

    public void floyd() {
        // 变量保存两点间的距离
        // k为中间节点，i为起始节点，j为终点.
        int len = 0;
        for (int k = 0; k < nodeSize; k++) {
            for (int i = 0; i < nodeSize; i++) {
                if (dis[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for (int j = 0; j < nodeSize; j++) {
                    if (dis[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    len = dis[i][k] + dis[k][j];
                    if (dis[i][j] > len) {
                        /**
                         * 更新距离，及更新前驱节点.
                         */
                        dis[i][j] = len;
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }

        printDis();
        System.out.println("###################");
        printPath();
    }

    private void printDis() {
        for (int[] d : dis) {
            System.out.println(Arrays.toString(d));
        }
    }

    private void printPath() {
        StringBuilder sb;
        for (int i = 0; i < nodeSize; i++) {
            for (int j = 0; j < nodeSize; j++) {
                if (i == j) {
                    continue;
                }
                sb = new StringBuilder(nodes[i]).append(' ');
                showPath(sb, i, j);
                System.out.println(nodes[i] + "->" + nodes[j] + " ("+dis[i][j]+") :" + sb.toString());
            }
            System.out.println("------------------");
        }
    }

    private void showPath(StringBuilder sb, int i, int j) {
        char c = pre[i][j];
        int mid = j;
        if (c != nodes[j]){
            mid = getIndex(c);
            if (mid != -1){
                showPath(sb, i, mid);
            }
        }
        sb.append(nodes[j]).append(' ');
    }

    private int getIndex(char c) {
        for (int i = 0; i < nodeSize; i++) {
            if (nodes[i] == c){
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int size = nodes.length;
        int dis[][] = new int[size][size];
        for (int[] d : dis) {
            //1.将所有路径值初始化为最大值
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        //2.添加边，修改路径值
        dis[0][1] = 5;
        dis[1][0] = 5;
        dis[0][2] = 7;
        dis[2][0] = 7;
        dis[0][6] = 2;
        dis[6][0] = 2;
        dis[1][6] = 3;
        dis[6][1] = 3;
        dis[1][3] = 9;
        dis[3][1] = 9;
        dis[2][4] = 8;
        dis[4][2] = 8;
        dis[3][5] = 4;
        dis[5][3] = 4;
        dis[4][5] = 5;
        dis[5][4] = 5;
        dis[4][6] = 4;
        dis[6][4] = 4;
        dis[5][6] = 6;
        dis[6][5] = 6;
        //3.把自己到自己的路径值设置为0
        for (int i = 0; i < size; i++) {
            dis[i][i] = 0;
        }

        FloydAlgroithm floyd = new FloydAlgroithm(nodes, size, dis);
        floyd.floyd();

        /**
         * 输出如下：
         * [0, 5, 7, 12, 6, 8, 2]
         * [5, 0, 12, 9, 7, 9, 3]
         * [7, 12, 0, 17, 8, 13, 9]
         * [12, 9, 17, 0, 9, 4, 10]
         * [6, 7, 8, 9, 0, 5, 4]
         * [8, 9, 13, 4, 5, 0, 6]
         * [2, 3, 9, 10, 4, 6, 0]
         * ###################
         * A->B (5) : A B
         * A->C (7) : A C
         * A->D (12) : A G F D
         * A->E (6) : A G E
         * A->F (8) : A G F
         * A->G (2) : A G
         * ------------------
         * B->A (5) : B A
         * B->C (12) : B A C
         * B->D (9) : B D
         * B->E (7) : B G E
         * B->F (9) : B G F
         * B->G (3) : B G
         * ------------------
         * C->A (7) : C A
         * C->B (12) : C A B
         * C->D (17) : C E F D
         * C->E (8) : C E
         * C->F (13) : C E F
         * C->G (9) : C A G
         * ------------------
         * D->A (12) : D F G A
         * D->B (9) : D B
         * D->C (17) : D F E C
         * D->E (9) : D F E
         * D->F (4) : D F
         * D->G (10) : D F G
         * ------------------
         * E->A (6) : E G A
         * E->B (7) : E G B
         * E->C (8) : E C
         * E->D (9) : E F D
         * E->F (5) : E F
         * E->G (4) : E G
         * ------------------
         * F->A (8) : F G A
         * F->B (9) : F G B
         * F->C (13) : F E C
         * F->D (4) : F D
         * F->E (5) : F E
         * F->G (6) : F G
         * ------------------
         * G->A (2) : G A
         * G->B (3) : G B
         * G->C (9) : G A C
         * G->D (10) : G F D
         * G->E (4) : G E
         * G->F (6) : G F
         * ------------------
         */
    }
}
