package com.zws.algorithm.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 采用动态规范算法，求解背包问题
 *
 * @author zhengws
 * @date 2019-11-16 16:33
 */
public class Knapsack {

    /**
     * 存放特定背包容量下，不同物品组合的最优解二维数组
     */
    private int values[][];

    /**
     * 最优组合方式
     */
    private int associations[][];

    /**
     * 存放商品名称
     */
    private List<Good> goods;

    /**
     * @param capacity 背包容量
     * @param goods    物品权重及价值映射信息
     */
    public Knapsack(int capacity, List<Good> goods) {
        int size = goods.size();
        this.values = new int[size][capacity];
        this.associations = new int[size][capacity];
        this.goods = goods;
    }

    public void storeGoods() {
        for (int m = 0; m < values[0].length; m++) {
            for (int n = 0; n < values.length; n++) {
                int weight = goods.get(n).weight;
                if (weight > m + 1) {
                    //1.物品权重大于背包容量，依旧保持上前一个最大值
                    values[n][m] = getPreValue(n, m);
                } else if (weight == m + 1) {
                    //2.物品权重等于背包容量，比较当前物品价值与前一个值，取最大值
                    values[n][m] = getMaxValue(goods.get(n).value, n, m);
                } else {
                    /**
                     * 3.物品权重小于背包容量
                     *  3.1 对比当前物品的最佳组合，前一个值，取最大值.
                     */
                    int assocation = getCurAssocation(n, m);
                    values[n][m] = getMaxValue(assocation, n, m);
                }
            }
        }
        showStoreAssocation();
    }

    /**
     * 显示组合方式.
     */
    private void showStoreAssocation() {
        for (int[] arr : values) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("##############");
        for (int[] arr : associations) {
            System.out.println(Arrays.toString(arr));
        }

        System.out.println("##############");
        Good good = null;
        int i = associations.length - 1;
        int j = associations[0].length - 1;

        System.out.print("放入: ");
        while (i >= 0 && j >= 0){
            if (associations[i][j] == 1){
                good = goods.get(i);
                System.out.printf("%s ", good.name);
                j = j - good.weight;
            }
            i--;
        }
    }

    /**
     * 获取当前组合与上一次组合的最大值
     *
     * @param value
     * @param n
     * @param m
     * @return
     */
    private int getMaxValue(int value, int n, int m) {
        int preValue = getPreValue(n, m);
        if (value > preValue) {
            //记录选中物品的路径。
            associations[n][m] = 1;
            return value;
        }
        return preValue;
    }

    private int getCurAssocation(int row, int col) {
        // 剩余容量数
        int w = col - goods.get(row).weight;
        int value = goods.get(row).value;
        // 需要判断，当前行是否是第一行, 如果是第一行，则返回该物品的价值
        if (row == 0) {
            return value;
        }
        return value + values[row - 1][w];
    }

    private int getPreValue(int row, int col) {
        if (row == 0) {
            return 0;
        } else {
            return values[row - 1][col];
        }
    }


    private static class Good {
        private int weight;
        private int value;
        private String name;

        public Good(int weight, int value, String name) {
            this.weight = weight;
            this.value = value;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        List<Good> goods = new ArrayList<>();
        goods.add(new Good(1, 1500, "吉他"));
        goods.add(new Good(4, 3000, "音响"));
        goods.add(new Good(3, 2000, "电脑"));
        Knapsack knapsack = new Knapsack(4, goods);
        knapsack.storeGoods();

        /**
         * 输出:
         * [1500, 1500, 1500, 1500]
         * [1500, 1500, 1500, 3000]
         * [1500, 1500, 2000, 3500]
         * ##############
         * [1, 1, 1, 1]
         * [0, 0, 0, 1]
         * [0, 0, 1, 1]
         * ##############
         * 放入: 电脑 吉他
         */
    }
}
