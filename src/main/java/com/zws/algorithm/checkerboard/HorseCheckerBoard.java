package com.zws.algorithm.checkerboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘算法，也称骑士周游
 *
 * @author zhengws
 * @date 2019-11-21 19:23
 */
public class HorseCheckerBoard {

    private int[][] checkerBorads;

    private int size;

    private int totalStep;

    private boolean isFinished;

    /**
     * 存放该位置是否被走过，0为未走过，1为走过
     */
    private boolean[] values;

    public HorseCheckerBoard(int size) {
        this.size = size;
        this.values = new boolean[size * size];
        this.checkerBorads = new int[size][size];
        this.totalStep = size * size;
    }

    public void horseChecker(Point point, int step) {
        //1. 记录当前步数，并将该节点标识为已访问。
        checkerBorads[point.x][point.y] = step;
        values[point.x * size + point.y] = true;

        //2. 获取当前节点的下一步可能走的节点列表
        List<Point> points = nexts(point);
        //采用贪心算法，对马踏棋盘算法进行优化。
        sortPoint(points);
        Point p;
        //3. 遍历下一步节点列表
        while (!points.isEmpty()) {
            p = points.remove(0);
            //3.1 如果该节点未被访问，则进行访问，并递归查找下一步节点信息.
            if (!values[p.x * size + p.y]) {
                horseChecker(p, step + 1);
            }
        }
        /**
         * 4. 判断当前棋盘是否已经走完
         *    step < totalStep 存在两种情况
         *    1、棋盘到目前为止仍没有走完
         *    2、棋盘已经走完过，此时在回溯的过程中
         */
        if (step < totalStep && !isFinished) {
            // 如果整个棋盘最终全部为零，则表示无解
            checkerBorads[point.x][point.y] = 0;
            values[point.x * size + point.y] = false;
        } else {
            isFinished = true;
        }

    }

    private void sortPoint(List<Point> points) {
        /**
         * 根据当前的这一步的所有下一步的选择数目, 进行非递减排序
         * 非递减排序，即从小到大排序，但是相同的依然保留，类似: [1, 2, 2, 3, 3]
         */
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = nexts(o1).size();
                int count2 = nexts(o2).size();
                return count1 - count2;
            }
        });
    }

    /**
     * 获取下一轮可选位置
     *
     * @return
     */
    private List<Point> nexts(Point curPoint) {
        List<Point> points = new ArrayList<>();
        Point point = null;
        if (curPoint.x - 2 >= 0 && curPoint.y - 1 >= 0) {
            point = new Point(curPoint.x - 2, curPoint.y - 1);
            points.add(point);
        }
        if (curPoint.x - 1 >= 0 && curPoint.y - 2 >= 0) {
            point = new Point(curPoint.x - 1, curPoint.y - 2);
            points.add(point);
        }
        if (curPoint.x + 1 < size && curPoint.y - 2 >= 0) {
            point = new Point(curPoint.x + 1, curPoint.y - 2);
            points.add(point);
        }
        if (curPoint.x + 2 < size && curPoint.y - 1 >= 0) {
            point = new Point(curPoint.x + 2, curPoint.y - 1);
            points.add(point);
        }

        if (curPoint.x - 2 >= 0 && curPoint.y + 1 < size) {
            point = new Point(curPoint.x - 2, curPoint.y + 1);
            points.add(point);
        }
        if (curPoint.x - 1 >= 0 && curPoint.y + 2 < size) {
            point = new Point(curPoint.x - 1, curPoint.y + 2);
            points.add(point);
        }
        if (curPoint.x + 1 < size && curPoint.y + 2 < size) {
            point = new Point(curPoint.x + 1, curPoint.y + 2);
            points.add(point);
        }
        if (curPoint.x + 2 < size && curPoint.y + 1 < size) {
            point = new Point(curPoint.x + 2, curPoint.y + 1);
            points.add(point);
        }
        return points;
    }

    public void printValues() {
        for (int[] v : checkerBorads) {
            System.out.println(Arrays.toString(v));
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        HorseCheckerBoard checkerBoard = new HorseCheckerBoard(8);
        checkerBoard.horseChecker(new Point(0, 0), 1);
        checkerBoard.printValues();

        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms ");
    }
}
