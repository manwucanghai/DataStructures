package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序
 * 规则:
 * 1、定义两个临时变量，存储筛选结果的index，及对应的value
 * 2、依次从0开始遍历，查找满足条件的index及value，如果满足，则进行元素对调
 * 3、每次遍历后，起始位置加1
 *
 * @author zhengws
 * @date 2019-10-22 11:16
 */
public class SelectSort {

    public static void sort(int[] arr) {
        int length = arr.length;
        if (length < 1) {
            return;
        }
        int index;
        for (int i = 0; i < length - 1; i++) {
            index = i;
            for (int j = i + 1; j <= length - 1; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }
            //如果索引不同，则进行交换元素.
            if (index != i) {
                swap(arr, index, i);
            }
        }
    }

    public static void sort1(int[] arr){
        int index;
        for (int out = arr.length - 1; out > 0 ; out--) {
            index = out;
            for (int in = 0; in < out ; in++) {
                if (arr[in] > arr[out]){
                    index = in;
                }
            }
            if (index != out){
                swap(arr, out, index);
            }
        }
    }

    private static void swap(int[] arr, int a, int b) {
        arr[a] += arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{10, 7, 9, -1, -3, 4};
//        System.out.println("排序前: \n" + Arrays.toString(arr));
//        sort1(arr);
//        System.out.println("排序后: \n" + Arrays.toString(arr));

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }

        long startTime = System.currentTimeMillis();
        sort1(arr);
        System.out.println("total cost " + (System.currentTimeMillis() - startTime) + " ms");
//        // 8万个数字排序，花了 2762 ms。
    }
}
