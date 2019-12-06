package com.zws.datastruct.hashtable;

import java.util.Iterator;

/**
 * @author zhengws
 * @date 2019-10-29 18:17
 */
public class HashTableTest {
    public static void main(String[] args) {
        HashTable<String, String> table = new HashTable<>();
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 10 ; i++) {
            table.put(""+i, ""+i);
        }
        System.out.println("tabel size is: " + table.size());
        System.out.println("get 1 = " + table.get("1"));
        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms");

        Iterator<String> iterator = table.getIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        /**
         * 输出：
         * tabel size is: 10
         * get 1 = 1
         * cost 0 ms
         * 1
         * 10
         * 9
         * 8
         * 7
         * 6
         * 5
         * 4
         * 3
         * 2
         */
    }
}
