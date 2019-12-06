package com.zws.datastruct.recursion;

/**
 * @author zhengws
 * @date 2019-10-20 10:37
 */
public class MiGong {
    /**
     * 行个数
     */
    private int rows;

    /**
     * 列个数
     */
    private int cols;

    /**
     * 挡板位置.使用n行，2列的二维数组.
     */
    private int[][] flap;

    /**
     * 设置出口位置行号
     */
    private int existRow;

    /**
     * 设置出口位置列号
     */
    private int existCol;

    /**
     * 存放位置信息.
     */
    private int[][] map;

    public MiGong(int rows, int cols, int[][] flap, int existRow, int existCol) {
        this.rows = rows;
        this.cols = cols;
        this.flap = flap;
        this.existRow = existRow;
        this.existCol = existCol;
        initMap();
    }

    /**
     * 退出位置标识为-1, 挡板标识为1, 走过标识为2，死角标识为3.
     */
    private void initMap() {
        map = new int[rows][cols];
        for (int[] row : flap) {
            map[row[0]][row[1]] = 1;
        }
        for (int i = 0; i < rows; i++) {
            map[i][0] = 1;
            map[i][cols - 1] = 1;
        }
        for (int i = 0; i < cols; i++) {
            map[0][i] = 1;
            map[rows - 1][i] = 1;
        }
        map[existRow][existCol] = -1;
    }

    /**
     * 寻找顺序 下 -> 右 -> 上 -> 左
     * @param row
     * @param col
     * @return
     */
//    public boolean setWay(int row, int col) {
//        if (map[existRow][existCol] == 2) {
//            return true;
//        }
//        if (map[row][col] <= 0) {
//            map[row][col] = 2;
//            if (setWay(row + 1, col)) { //往下走
//                return true;
//            } else if (setWay(row, col + 1)) { //往右走
//                return true;
//            } else if (setWay(row - 1, col)) { //往上走
//                return true;
//            } else if (setWay(row, col - 1)) { //往左走
//                return true;
//            } else {
//                map[row][col] = 3;
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }

    /**
     * 寻找顺序 右 -> 下 -> 左 -> 上
     * @param row
     * @param col
     * @return
     */
    public boolean setWay(int row, int col) {
        if (map[existRow][existCol] == 2) {
            return true;
        }
        if (map[row][col] <= 0) {
            map[row][col] = 2;
            if (setWay(row, col + 1)) { //往右走
                return true;
            } else if (setWay(row + 1, col)) { //往下走
                return true;
            } else if (setWay(row, col - 1)) { //往左走
                return true;
            } else if (setWay(row - 1, col)) { //往上走
                return true;
            } else {
                map[row][col] = 3;
                return false;
            }
        } else {
            return false;
        }
    }

    public void showMap() {
        for (int[] row : map) {
            for (int n : row) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] flap = new int[][]{{3, 1}, {3, 2}};
        MiGong miGong = new MiGong(8, 7, flap, 6, 5);
        miGong.showMap();
        System.out.println("########################");
        boolean flag = miGong.setWay(1, 1);
        System.out.println("寻找是否成功? " + flag);
        miGong.showMap();

        /**
         * 输出结果:
         * 1 1 1 1 1 1 1
         * 1 0 0 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 1 1 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 0 0 0 0 0 1
         * 1 0 0 0 0 -1 1
         * 1 1 1 1 1 1 1
         * ########################
         * 寻找是否成功? true
         * 1 1 1 1 1 1 1
         * 1 2 2 2 2 2 1
         * 1 0 0 0 0 2 1
         * 1 1 1 0 0 2 1
         * 1 0 0 0 0 2 1
         * 1 0 0 0 0 2 1
         * 1 0 0 0 0 2 1
         * 1 1 1 1 1 1 1
         */
    }
}
