package com.zws.datastruct.arr;

import java.util.ArrayList;

/**
 * @author zhengws
 * @date 2019-10-22 09:35
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("start");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);

        ArrayList<String> list1 = new ArrayList<>();
        list1.add("d");
        list1.add("e");
        list1.add("f");

        list.addAll(1, list1);

        System.out.println(list);
    }
}
