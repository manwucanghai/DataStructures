package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 希尔排序算法
 *
 * @author zhengws
 * @date 2019-10-22 11:29
 */
public class ShellSort {
    public static void sort(int[] arr) {
        int length = arr.length;
        if (length < 1) {
            return;
        }
        int index;
        for (int step = length / 2; step > 0; step /= 2) {
            for (int i = step; i < length; i++) {
                index = i;
                while (index - step >= 0 && arr[index - step] > arr[index]) {
                    swap(arr, index, index - step);
                    //往前探测检查一次，如果满足条件，则下一次依旧会往前探测一次。
                    index -= step;
                }
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
//        sort(arr);
//        System.out.println("排序后: \n" + Arrays.toString(arr));

        int[] arr = new int[800000];
        Random random = new Random();
        for (int i = 0; i < 800000; i++) {
            arr[i] = random.nextInt(8000000);
        }

        long startTime = System.currentTimeMillis();
        sort(arr);
        System.out.println("total cost " + (System.currentTimeMillis() - startTime) + " ms");
       //80万共花了215ms。
    }
}
