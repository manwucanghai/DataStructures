package com.zws.algorithm.query;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengws
 * @date 2019-10-27 10:44
 */
public class BinarySearch implements ISearch {

    private int[] arr;

    public BinarySearch(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int search(int value) {
        return binarySearch(0, arr.length - 1, value);
    }

    private int binarySearch(int start, int end, int value) {
        System.out.println("binary search");
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] > value) {
            return binarySearch(start, mid - 1, value);
        } else if (arr[mid] < value) {
            return binarySearch(mid + 1, end, value);
        } else {
            //获取首次出现该元素的索引.
            while (mid >= 1 && arr[mid - 1] == value) {
                mid--;
            }
            return mid;
        }
    }

    @Override
    public List<Integer> serachAll(int value) {
        int length = arr.length;
        int index = binarySearch(0, length - 1, value);
        List<Integer> list = new ArrayList<>();
        if (index == -1) {
            return list;
        }
        list.add(index);
        while (true) {
            if (index >= length - 1 || arr[++index] != value) {
                break;
            }
            list.add(index);
        }
        return list;
    }
}
