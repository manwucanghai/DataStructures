package com.zws.datastruct.joseph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengws
 * @date 2019-10-19 09:23
 */
public class JosephLoop {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",2);
        map.put("c",3);
        map.put("d",4);
        map.put("e",5);

        JosephLoopLinked<String> linkedList = new JosephLoopLinked<String>(7);
        for (String key: map.keySet()){
            linkedList.add(key);
        }

        linkedList.josephLoopPrint(1, 3);

        /**
         * 输出结果:
         * ###### 当前链表中节点个数: 5 ######
         * ###### 移除前打印 ########
         * a
         * b
         * c
         * d
         * e
         * >>>> 出列人员: c
         * ###### 当前链表中节点个数: 4 ######
         * ###### 移除前打印 ########
         * d
         * e
         * a
         * b
         * >>>> 出列人员: a
         * ###### 当前链表中节点个数: 3 ######
         * ###### 移除前打印 ########
         * b
         * d
         * e
         * >>>> 出列人员: e
         * ###### 当前链表中节点个数: 2 ######
         * ###### 移除前打印 ########
         * b
         * d
         * >>>> 出列人员: b
         * ###### 当前链表中节点个数: 1 ######
         * ###### 移除前打印 ########
         * d
         * >>>> 出列人员: d
         */
    }
}
