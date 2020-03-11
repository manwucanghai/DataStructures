package com.zws.test;

/**
 * @author zhengws
 * @date 2019-10-26 07:23
 */
public class BinTest {
    public static void main(String[] args) throws Exception{
        printBin(Integer.MAX_VALUE);
//        long start = System.currentTimeMillis();
//        getTotalNum(100);
//        int num = getOneNum(100);
//        System.out.println(num);
//        Thread.sleep(1000000);
//        System.out.println(System.currentTimeMillis() - start);
//        11111111 11111111 11111111 1111111
    }



    private static void getTotalNum(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += getOneNum(i);
        }
        System.out.println(n + ": " + count);
    }

    private static int getOneNum(int n) {
        int count = 0;
        int result;
        do {
            result = n % 10;
            if ((result ^ 14) == 15) {
                printBin(n);
                count++;
            }
            n /= 10;
        } while (n > 0);
        return count;
    }

    private static void printBin(int n) {
        System.out.println(n + ": " + Integer.toBinaryString(n));
    }
}
