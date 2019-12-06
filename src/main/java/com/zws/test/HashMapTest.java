package com.zws.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengws
 * @date 2019-11-26 17:10
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        map.put("d","d");
        map.put("e","e");

        int h;
        for (Map.Entry<String, String> entry : map.entrySet()){
            entry.setValue(entry.getValue() + "1");
            System.out.println((h = entry.getKey().hashCode()) ^ h>>>16);
        }

        System.out.println(map);

        /**
         * {a=a1, b=b1, c=c1, d=d1, e=e1}
         */

    }
}
