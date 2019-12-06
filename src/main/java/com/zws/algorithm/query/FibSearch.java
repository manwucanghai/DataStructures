package com.zws.algorithm.query;

import java.util.List;

/**
 * @author zhengws
 * @date 2019-10-27 12:18
 */
public class FibSearch implements ISearch {
    private int[] arr;

    public FibSearch(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int search(int value) {
        return 0;
    }


    @Override
    public List<Integer> serachAll(int value) {
        return null;
    }

    private int[] getFib(int max) {
        int[] fib = new int[max];
        fib[0] = 1;
        fib[1] = 2;
        for (int i = 3; i < max; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
}
