package com.zws.datastruct.recursion;

/**
 * 八皇后问题求解
 * 在8 * 8 格的国际象棋上，摆放八个皇后，使其不能互相攻击
 * (任意两个皇后，不能同时处于同一行、同一列、同一斜线上)，
 * 求有多少种解法?
 *
 * 问题分析：
 * 1、从规则观察到，任意两个皇后不能处于同一行，因此可以采用一维数组，用一维数组下标索引代表这些皇后对应的行数，亦能确保任意两个皇后不在同一行上。
 * 2、该一维数组中存放的值，代表该皇后所处的列序号，也就是只需要确保该一维数组中元素的值能不能有重复，亦能确保任意两个皇后不在同一列上。
 * 3、新加入的皇后分别与已经存在的皇后对比，其行差绝对值 与 列差绝对值是否相等，如果相等就说明冲突，在同一个斜线上(满足等腰直角三角形定律)
 *
 * @author zhengws
 * @date 2019-10-20 12:41
 */
public class EightEmpress {
    /**
     * 定义国际象棋正方形边长
     */
    private int max = 8;

    /**
     * 存放解法结果数组.
     */
    private int[] result = new int[max];

    /**
     * 解法种数.
     */
    private int count;


    private int execCount;


    public int getResultCount() {
        check(0);
        return this.count;
    }

    /**
     * n 代表放置第N个皇后.
     * @param n
     */
    private void check(int n){
        if (n == max){
            showResult();
            this.count ++;
            return;
        }
        for (int i = 0; i < max; i++) {
            //先将当前第n个皇后的位置设置为i, 方便后面提取进行校验(这里的i 代表列序号)
             result[n] = i;
            if (judge(n)){
                //不冲突，即进行放第n+1个皇后.
                check(n + 1);
            }
            //如果冲突，就继续执行result[n] = i; 即将第n个皇后，放置本行的后一个位置.
        }
    }

    /**
     * n 代表放置第N个皇后.
     * result[n] == result[i] 说明存在列相同的皇后
     * Math.abs(n - i) == Math.abs(result[n] - result[i]) 说明满足在同一个斜线上.
     * 返回结果代表是否可进行添加。
     * @param n
     * @return
     */
    private boolean judge(int n) {
        this.execCount++;
        for (int i = 0; i < n; i++) {
            //判断如果冲突，则返回false
            if (result[n] == result[i] || Math.abs(n - i) == Math.abs(result[n] - result[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果顺序。
     */
    private void showResult(){
        for (int n : result){
            System.out.print(n + " ");
        }
        System.out.println();
        System.out.println("execCount = "+ execCount);
    }

    public static void main(String[] args) {
        EightEmpress eightEmpress = new EightEmpress();
        System.out.println("总共解法种数为: " + eightEmpress.getResultCount() + "种.");

        /**
         * 输出:
         * 总共解法种数为: 92种.
         */
    }
}
