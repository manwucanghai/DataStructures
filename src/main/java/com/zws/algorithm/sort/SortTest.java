package com.zws.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhengws
 * @date 2019-10-25 17:02
 */
public class SortTest {
    public static void main(String[] args) {
//        int[] arr = new int[]{10, 7, 9, -1, 30, 4, -20, 101, 99, 27, 31, 11, -30};
//        int[] arr = new int[]{53, 3, 542, 748, 14, 214};
//        System.out.println("排序前: \n" + Arrays.toString(arr));
//        RadixSort.sort(arr);
//        System.out.println("排序后: \n" + Arrays.toString(arr));

        /**
         * 输出：
         * 排序前:
         * [53, 3, 542, 748, 14, 214]
         * 排序后:
         * [3, 14, 53, 214, 542, 748]
         */

        long start = System.currentTimeMillis();
        System.out.println(countDigitOne(824883294));

        System.out.println("cost " + (System.currentTimeMillis() - start));
    }


    public static int countDigitOne(int n) {
        int length;
        int count = 0;
        for(int i = n ; i >= 1; i--){
            length = String.valueOf(i).length();
            for(int j = 0, num = 1; j < length; j++, num *= 10){
                if(i / num % 10 == 1){
                    count++;
                }
            }
        }
        return count;
    }
}
