package com.zws.datastruct.arr;

import java.util.*;

/**
 * @author zhengws
 * @date 2019-10-24 17:46
 */
public class Solution {
    public static int leastBricks(List<List<Integer>> wall) {

        Map<Integer, Integer> resultMap = new HashMap<>();
        int sum = 0;
        int rows = 0;
        for (List<Integer> list : wall) {
            sum = 0;
            rows++;
            for (int i = 0; i < list.size() - 1; i++) {
                sum += list.get(i);
                if (resultMap.containsKey(sum)) {
                    resultMap.put(sum, resultMap.get(sum) + 1);
                } else {
                    resultMap.put(sum, 1);
                }
            }
        }
        int max = 0;
        for (Integer value : resultMap.values()) {
            if (value > max) {
                max = value;
            }
        }
        return rows - max;
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = new ArrayList<>();
        wall.add(Arrays.asList(1, 2, 2, 1));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 2));
        wall.add(Arrays.asList(2, 4));
        wall.add(Arrays.asList(3, 1, 2));
        wall.add(Arrays.asList(1, 3, 1, 1));
        System.out.println(leastBricks(wall));


    }
}
