package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhengws
 * @date 2019-10-24 22:23
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, right, mid, temp);
        }
    }

    /**
     * 合并并进行排序。
     *
     * @param arr
     * @param left
     * @param right
     * @param mid
     * @param temp
     */
    public static void merge(int[] arr, int left, int right, int mid, int[] temp) {
        int l = left; //左边数组起点
        int r = mid + 1; //右边数组起点
        int t = 0; //temp的起始位置从0开始添加数据。
        //左右数组间，都还有元素存在，则对比元素，依次往temp数组中添加元素
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[r++];
            }
        }
        //说明其中一个数组已经取完毕，把剩下另外一组元素全部取出放置temp中
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }

        //将temp中的元素拷贝至原始数组中。
        t = 0;
        for (int i = left; i <= right; i++, t++) {
            arr[i] = temp[t];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{10, 7, 9, -1, 30, 4, -20, 101, 99, 27, 31, 11, -30};
        System.out.println("排序前: \n" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        System.out.println("排序后: \n" + Arrays.toString(arr));

        /**
         * 输出：
         * 排序前:
         * [10, 7, 9, -1, 30, 4, -20, 101, 99, 27, 31, 11, -30]
         * 排序后:
         * [-30, -20, -1, 4, 7, 9, 10, 11, 27, 30, 31, 99, 101]
         */
//        int[] arr = new int[8000000];
//        Random random = new Random();
//        for (int i = 0; i < 8000000; i++) {
//            arr[i] = random.nextInt(80000000);
//        }
//
//        long startTime = System.currentTimeMillis();
////        System.out.println("排序前: \n" + Arrays.toString(arr));
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
////        System.out.println("排序后: \n" + Arrays.toString(arr));
//        System.out.println("total cost " + (System.currentTimeMillis() - startTime) + " ms");

        //800w 花了 1205 ms。
    }
}
