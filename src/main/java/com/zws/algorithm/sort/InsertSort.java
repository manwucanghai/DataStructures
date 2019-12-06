package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序算法
 * 规则水哦名：
 * 1、将数组划分成两部分，一部分是有序，一部分是无序
 * 2、依次遍历，读取字段后，往有序列表中插入相应位置
 *
 * @author zhengws
 * @date 2019-10-22 11:29
 */
public class InsertSort {

    public static void sort(int[] arr) {
        int length = arr.length;
        if (length < 1) {
            return;
        }
        //定义插入点
        int pos;
        //记录插入的值
        int value;
        for (int i = 1; i < length; i++) {
            pos = i;
            value = arr[i];
            for (int j = 0; j < i; j++) {
                if (arr[j] > value) {
                    pos = j;
                    break;
                }
            }
            if (pos != i) {
                //数组从第pos位置到第i-1个元素，往后移动一位
                System.arraycopy(arr, pos, arr, pos + 1, i - pos);
                arr[pos] = value;
            }
        }
    }


    public static void main(String[] args) {
//        int[] arr = new int[]{10, 7, 9, -1, -3, 4};
//        System.out.println("排序前: \n" + Arrays.toString(arr));
//        sort(arr);
//        System.out.println("排序后: \n" + Arrays.toString(arr));
        int[] arr = new int[30];
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            arr[i] = random.nextInt(300);
        }

        long startTime = System.currentTimeMillis();
        sort(arr);
        System.out.println("排序后: \n" + Arrays.toString(arr));
        System.out.println("total cost " + (System.currentTimeMillis() - startTime) + " ms");
//        // 8万个数字排序，花了 743 ms。
    }
}


