package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhengws
 * @date 2019-10-22 19:38
 */
public class QuickSort {
    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int s = start;
        int e = end;
        int key = arr[start];
        while (s < e) {
            //注意：跟顺序有关系，如果从第一个位置作为基准位置，那需要先从后面开始查找。
            //从后面开始查找
            while (s < e && arr[e] > key) {
                e--;
            }
            //此时e索引所对应的值小于key,因此可以将e的值赋予当前s位置(也就是key所在位置)
            if (s < e) {
                arr[s] = arr[e];
                s++;
            }
            //从前面开始查找
            while (s < e && arr[s] < key) {
                s++;
            }
            //此时s索引对应的值大于key,因此将该值移动到e的位置.
            if (s < e) {
                arr[e] = arr[s];
                e--;
            }
        }
        arr[s] = key;
        //继续查找前面列表
        sort(arr, start, s - 1);
        //继续查找后面列表
        sort(arr, s + 1, end);
    }

    public static void sort1(int[] arr, int start, int end) {
        int s = start;
        int e = end;
        int value = arr[(start + end) / 2];
        while (s < e) {
            while (arr[e] > value) {
                e--;
            }
            while (arr[s] < value) {
                s++;
            }
            if (s >= e) {
                break;
            }
            swap(arr, s, e);
            if (arr[s] == value) {
                e--;
            }
            if (arr[e] == value) {
                s++;
            }
        }
        if (s == e) {
            s++;
            e--;
        }
        if (start < e) {
            sort1(arr, start, e);
        }
        if (end > s) {
            sort1(arr, s, end);
        }

    }



    private static void swap(int[] arr, int a, int b) {
        arr[a] += arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{10, 7, 9, -1, 30, 4, -20, 101, 99, 27, 31, 11, -30};
//        System.out.println("排序前: \n" + Arrays.toString(arr));
//        sort2(arr, 0, arr.length - 1);
//        System.out.println("排序后: \n" + Arrays.toString(arr));

        int[] arr = new int[8000000];
        Random random = new Random();
        for (int i = 0; i < 8000000; i++) {
            arr[i] = random.nextInt(80000000);
        }

        long startTime = System.currentTimeMillis();
//        System.out.println("排序前: \n" + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
//        sort1(arr, 0, arr.length - 1);
//        System.out.println("排序后: \n" + Arrays.toString(arr));
        System.out.println("total cost " + (System.currentTimeMillis() - startTime) + " ms");
        //80万共花了112ms
        //800万共花了919ms
    }
}
