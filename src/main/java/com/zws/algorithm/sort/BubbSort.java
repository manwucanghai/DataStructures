package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡算法
 * 规则:
 * 1、从第0个元素开始，直到第 length - 1 - 0, 对比相邻两个元素的大小，如果满足条件(从大到小、从小到大)，则进行对调相邻两个元素位置, 第一轮对比下来，可以确定最大一个元素.
 * 2、因此第二轮比较，只需要从第0个开始比较，直到第 length - 1 - 1.
 * 3、因此第三轮比较，只需要从第0个开始比较，直到第 length - 1 - 2.
 *
 * @author zhengws
 * @date 2019-10-22 10:50
 */
public class BubbSort {

    public static void sort(int[] arr) {
        int length = arr.length;
        if (length < 1) {
            return;
        }
        int temp;
        boolean isSwitch = false;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    isSwitch = true;
                    //进行交换元素
                    swap(arr, j, j + 1);
                }
            }
            if (!isSwitch) {
                //说明上一轮检测，并没有任何元素进行交换，因此下一轮则无需进行比较
                break;
            }
            isSwitch = false; //将是否交换标识重置为false.
        }
    }

    public static void sort1(int[] arr) {
        boolean isSwitch = false;
        for (int out = arr.length - 1; out > 0; out--) {
            for (int in = 0; in < out; in++) {
                if (arr[in] > arr[in + 1]) {
                    swap(arr, in, in + 1);
                    //如果有元素进行交换，则置位true.
                    isSwitch = true;
                }
            }
            if (!isSwitch) {
                //如果上一轮没有进行任何元素交换，说明整个列表已经有序
                break;
            }
            isSwitch = false;
        }
    }

    private static void swap(int[] arr, int in, int out) {
        arr[in] = arr[in] + arr[out];
        arr[out] = arr[in] - arr[out];
        arr[in] = arr[in] - arr[out];
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
//        System.out.println("排序前: \n" + Arrays.toString(arr));
        sort1(arr);
//        System.out.println("排序后: \n" + Arrays.toString(arr));
        System.out.println("total cost " + (System.currentTimeMillis() - startTime) + " ms");
        // 8万个数字排序，花了 10472 ms。
    }
}
