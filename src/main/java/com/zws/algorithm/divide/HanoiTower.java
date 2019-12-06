package com.zws.algorithm.divide;

/**
 * @author zhengws
 * @date 2019-11-16 15:04
 */
public class HanoiTower {

    /**
     * 定义三根汉诺塔柱子
     */
    private char A = 'A';
    private char B = 'B';
    private char C = 'C';

    private int moveCount;

    /**
     * 汉诺塔问题分解为一下两步骤
     * 先将 n 个碟子拆分为 1 个碟子及 n - 1 个碟子.
     * 1、1 个碟子
     *   直接从A柱子移动到C柱子
     * 2、n -1 个碟子
     *   2.1 将 n - 1 碟子先移动到B柱子
     *   2.2 将最后一个碟子从A柱子移动到C柱子
     *   2.3 将 n - 1 碟子作为下一次递归，目标从B柱子移动到C柱子
     * @param n 共 n 个碟子
     * @param a A 柱子
     * @param b B 柱子
     * @param c C 柱子
     */
    private void hanoi(int n, char a, char b, char c) {
        if (n == 1) {
            move(n, a, c);
        } else {
            hanoi(n - 1, a, c, b);
            move(n, a, c);
            hanoi(n - 1, b, a, c);
        }
    }

    private int hanoi(int n) {
        hanoi(n, A, B, C);
        return moveCount;
    }

    private void move(int n, char a, char b) {
        this.moveCount++;
        System.out.printf("第%s个碟子, %s --> %s", n, a, b);
        System.out.println();
    }

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        int count = hanoiTower.hanoi(3);
        //规律： 移动次数为 2^n - 1
        System.out.println("共移动次数为: " + count);

        /**
         * 输出：
         * 第1个碟子, A --> C
         * 第2个碟子, A --> B
         * 第1个碟子, C --> B
         * 第3个碟子, A --> C
         * 第1个碟子, B --> A
         * 第2个碟子, B --> C
         * 第1个碟子, A --> C
         * 共移动次数为: 7
         */
    }
}
