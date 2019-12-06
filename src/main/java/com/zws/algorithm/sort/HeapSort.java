package com.zws.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author zhengws
 * @date 2019-11-05 10:50
 */
public class HeapSort {

    public static void adjustHeap(int[] arr, int index, int length) {
        //先取出当前index元素
        int temp = arr[index];

        for (int i = 2 * index + 1; i < length; i = 2 * i + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                //左子节点小于右子节点,将i的指向右子节点的索引.
                i++;
            }
            //子节点的最大值大于父节点，则将父节点的值设置为子节点最的的值
            if (arr[i] > temp) {
                arr[index] = arr[i];
                //主要用于对调整后结果进行二次回归确认。
                index = i;
            } else {
                //之所以可以进行break，是因为是自下往上，自左往右进行调整的。
                //只要不满足交换条件，则说明底下子节点的堆都是符合要求的。
                break;
            }
            arr[index] = temp;
        }
    }

    public static void sort(int[] arr) {
        //1.构建大顶堆。自下往上，自左往右.
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            //如果i与j相等，则不能进行交换，否则会出现结果为0.
            return;
        }
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }


    public static void main(String[] args) {
        int[] arr = {9, 4, 2, 5, -1, 0, -30, 15, 12};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        /**
         * 输出：
         * [-30, -1, 0, 2, 4, 5, 9, 12, 15]
         */
    }
}
