package com.zws.algorithm.loadbalance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 这种按优先级队列来的，存在问题。。。
 * 平滑加权轮训算法
 * 需求:
 * 按服务器权重实现,轮训访问服务器地址
 * {
 * "A":5,
 * "B":3,
 * "C":2
 * }
 *
 * @author zhengws
 * @date 2020-02-23 16:52
 */
public class RoundRotation {

    public static void main(String[] args) {
        List<ServerWeight> weights = new ArrayList<>();
        weights.add(new ServerWeight("A", 5));
        weights.add(new ServerWeight("B", 1));
        weights.add(new ServerWeight("C", 1));
        int totalWeight = 0;

        PriorityQueue<ServerWeight> queue = new PriorityQueue<>();
        for (ServerWeight weight : weights) {
            totalWeight += weight.weight;
            queue.add(weight);
        }

        ServerWeight weight;
        for (int i = 0; i < 10; i++) {
            weight = queue.poll();
            weights.forEach(w -> {
                System.out.print(w.name + ": " + w.currentWeight + '\t');
            });
            System.out.println("==================");
            System.out.println(weight.name);
            weight.currentWeight -= totalWeight;
            addAll(weights);
            queue.add(weight);

        }
    }

    public static void addAll(List<ServerWeight> weights) {

        weights.forEach(ServerWeight::addWeight);
    }


    private static class ServerWeight implements Comparable<ServerWeight> {
        public String name;
        public Integer currentWeight;
        public Integer weight;

        public ServerWeight(String name, int weight) {
            this.name = name;
            this.currentWeight = weight;
            this.weight = weight;
        }

        public int compareTo(ServerWeight serverWeight) {
            return (serverWeight.currentWeight - currentWeight);
        }

        public void addWeight() {
            this.currentWeight += this.weight;
        }
    }
}
