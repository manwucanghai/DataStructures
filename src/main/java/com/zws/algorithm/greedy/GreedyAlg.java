package com.zws.algorithm.greedy;

import java.util.*;

/**
 * 使用贪心算法，解决电台覆盖问题
 * <p>
 * 要求： 让所有电台都可以接收信号
 *
 * @author zhengws
 * @date 2019-11-17 11:09
 */
public class GreedyAlg {

    /**
     * 电台覆盖情况
     */
    private Map<String, HashSet<String>> broadcast;

    /**
     * 所有待覆盖的地区
     */
    private HashSet<String> allAreas = new HashSet<>();

    /**
     * 需要投放的电台
     */
    private List<String> realBroadCast = new ArrayList<>();


    public GreedyAlg(Map<String, HashSet<String>> broadcast) {
        this.broadcast = broadcast;
        //1.获取所有待覆盖地区
        broadcast.forEach((key, value) -> allAreas.addAll(value));
    }


    /**
     * 计算出真实需要投放的电台情况
     * 思路分析：
     *  1. 依次循环遍历广播台
     *      1.1 每一次遍历计算出覆盖剩余区域最多的电台(每次循环获取本次的最优解)
     *      1.2 将本次覆盖最多剩余的电台，加入到列表中，并从所有区域移除掉该电台所覆盖的地区
     *  2. 直到所有的区域都覆盖(也就是都被移除)，则说明已经得到最终解，返回列表中的所有电台。
     * @return
     */
    public List<String> realBroadcasts() {
        String maxKey = null;
        while (allAreas.size() > 0) {
            maxKey = null;
            int maxMatch = 0;
            for (String key : broadcast.keySet()) {
                if (realBroadCast.contains(key)) {
                    continue;
                }
                int count = getMatchArea(key);
                if (count > maxMatch) {
                    maxMatch = count;
                    maxKey = key;
                }
            }
            if (maxKey != null) {
                realBroadCast.add(maxKey);
                removeMatchArea(maxKey);
            } else {
                break;
            }
        }
        return realBroadCast;
    }

    private void removeMatchArea(String key) {
        broadcast.get(key).forEach(area -> allAreas.remove(area));
    }

    /**
     * 获取匹配区域个数.
     *
     * @param key
     * @return
     */
    private int getMatchArea(String key) {
        int num = 0;
        for (String area : broadcast.get(key)) {
            if (allAreas.contains(area)) {
                num++;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Map<String, HashSet<String>> broadcast = new HashMap<>();
        HashSet<String> k1 = new HashSet<>();
        k1.add("北京");
        k1.add("上海");
        k1.add("天津");
        broadcast.put("K1", k1);

        HashSet<String> k2 = new HashSet<>();
        k2.add("北京");
        k2.add("广州");
        k2.add("深圳");
        broadcast.put("K2", k2);

        HashSet<String> k3 = new HashSet<>();
        k3.add("成都");
        k3.add("上海");
        k3.add("杭州");
        broadcast.put("K3", k3);

        HashSet<String> k4 = new HashSet<>();
        k4.add("上海");
        k4.add("天津");
        broadcast.put("K4", k4);

        HashSet<String> k5 = new HashSet<>();
        k5.add("杭州");
        k5.add("大连");
        broadcast.put("K5", k5);

        GreedyAlg greedy = new GreedyAlg(broadcast);
        List<String> realBroadcasts = greedy.realBroadcasts();
        System.out.println(realBroadcasts);

        /**
         * 输出：
         * [K1, K2, K3, K5]
         */
    }
}
