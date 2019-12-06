package com.zws.algorithm.query;

/**
 * @author zhengws
 * @date 2019-10-27 10:47
 */
public class SearchTest {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 44, 55, 78, 106, 110, 110, 124, 129, 141, 148, 148, 148, 153, 161, 166, 175, 175, 180, 188, 190, 238, 243, 258, 263, 267, 271, 274, 287, 287};

//        ISearch search = new BinarySearch(arr);
        ISearch search = new InsertSearch(arr);

        System.out.println(search.search(0));
        System.out.println(search.serachAll(0));
        /**
         * 输出：
         * 29
         * [29, 30]
         */
    }
}
