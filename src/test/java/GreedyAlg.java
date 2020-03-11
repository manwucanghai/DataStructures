import java.util.*;

public class GreedyAlg {

    // 所有需要覆盖的地区。
    private HashSet<String> allAreas = new HashSet<>();

    //广播台覆盖情况
    private HashMap<String, HashSet<String>> brocast;

    //最终结果选择的广播台。
    private HashSet<String> result = new HashSet<>();

    public GreedyAlg(HashMap<String, HashSet<String>> brocast) {
        brocast.forEach((k, v) -> {
            allAreas.addAll(v);
        });
        System.out.println(allAreas);
        this.brocast = brocast;
    }

    public HashSet<String> select() {
        String maxKey;
        int maxMatch;

        while (allAreas.size() > 0) {
            maxMatch = 0;
            maxKey = null;
            for (Map.Entry<String, HashSet<String>> entry : brocast.entrySet()) {
                if (result.contains(entry.getKey())) {
                    continue;
                }
                int matchNum = getMatchNum(entry.getValue());
                if (matchNum > maxMatch) {
                    maxKey = entry.getKey();
                    maxMatch = matchNum;
                }
            }
            if (maxKey != null) {
                allAreas.removeAll(brocast.get(maxKey));
                result.add(maxKey);
            } else {
                break;
            }
        }

        return result;
    }

    private int getMatchNum(HashSet<String> areas) {
        int matchNum = 0;
        for (String area : areas) {
            if (allAreas.contains(area)) {
                matchNum++;
            }
        }
        return matchNum;
    }


    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();
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
        HashSet<String> result = greedy.select();
        System.out.println(result);

        /**
         * 输出：
         * [K1, K2, K3, K5]
         */
    }

}