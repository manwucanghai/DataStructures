package com.zws.algorithm.sort;

/**
 * 基数排序
 * 1、先定义10个桶，每个桶大小为原数组个数(避免数组越界)
 * 2、查找出当前数组中，最大元素，并计算该元素共几位
 * 3、遍历当前数组，按照所属位数分别放入桶中，并依次从低数取出存入原始数组中
 *
 * 缺陷：
 * 无法对比负数。
 *
 * @author zhengws
 * @date 2019-10-25 17:10
 */
public class RadixSort {
    public static void sort(int[] arr) {
        //1、先定义10个桶，每个桶大小为原数组个数
        int[][] bucket = new int[10][arr.length];

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int maxLength = String.valueOf(max).length();
        int[] bucketSize = new int[10];
        int num = 0;
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                //将数据存放到对应的bucket中.
                num = arr[j] / n % 10;
                bucket[num][bucketSize[num]++] = arr[j];
            }
            //将桶元素按照低数开始取，全部取出至原数组中.
            int index = 0;
            for (int j = 0; j < bucketSize.length; j++) {
                if (bucketSize[j] == 0){
                    continue;
                }
                for (int k = 0; k < bucketSize[j] ; k++) {
                    arr[index++] = bucket[j][k];
                }
                //恢复至初始值0，否则会出问题.
                bucketSize[j] = 0;
            }
        }
    }
}
