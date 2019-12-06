package com.zws.algorithm.dynamic;

import java.util.Arrays;

/**
 * 我们把两个字符串的相似度定义为：将一个字符串转换成另外一个字符串时需要付出的代价。
 * 转换可以采用插入、删除和替换三种编辑方式，因此转换的代价就是对字符串的编辑次数。
 * 以字符串"SNOWY"和"SUNNY"为例:
 * <p>
 * 转换方法1：
 * S - N O W Y
 * S U N N - Y
 * 转换代价Cost = 3 （插入U、替换O、删除W）
 * <p>
 * 转换方法2：
 * - S N O W - Y
 * S U N - - N Y
 * 转换代价Cost = 5 （插入S、替换S、删除O、删除W、插入N
 *
 * @author zhengws
 * @date 2019-11-23 09:12
 */
public class StringEdit {


    public StringEdit() {
    }


        public int editDistance(char[] src, char[] dest) {
        int srcLength = src.length;
        int destLength = dest.length;

        int distance[][] = new int[srcLength+1][destLength+1];

        for (int i = 0; i <= srcLength; i++) {
            distance[i][0] = i;
        }

        for (int j = 0; j <= destLength; j++) {
            distance[0][j] = j;
        }

        int insert = 0;
        int delete = 0;
        int replace = 0;
        for (int i = 1; i <= srcLength; i++) {
            for (int j = 1; j <= destLength; j++) {
                if (src[i - 1] == dest[j - 1]) {
                    //说明匹配，无需操作
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    insert = distance[i][j - 1] + 1; //插入
                    delete = distance[i - 1][j] + 1; //删除
                    replace = distance[i - 1][j - 1] + 1; //替换
                    distance[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }
        }
        printDistance(distance, src, dest);
        return distance[srcLength][destLength];
    }

    private void printDistance(int[][] distance, char[] src, char[] dest) {
        System.out.println("  " + Arrays.toString(src));
        int num = 0;
        for (int[] d : distance){
            if (num < dest.length){
                System.out.print(dest[num++] + " :");
            }
            System.out.println(Arrays.toString(d));
        }
    }


    public static void main(String[] args) {
        StringEdit stringEdit = new StringEdit();
        String src = "SNOWY";
        String dest = "SNAWY";
        int count = stringEdit.editDistance(src.toCharArray(), dest.toCharArray());
        System.out.println(count);
    }
}
