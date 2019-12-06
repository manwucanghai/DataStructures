package com.zws.datastruct.linkedlist;

/**
 * @author zhengws
 * @date 2019-10-19 09:32
 */
public class RoundSingleLinkedListTest {
    public static void main(String[] args) {
        RoundSingleLinkedList<String> linkedList = new RoundSingleLinkedList<String>(4);
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        System.out.println("当前链表中个数为: " + linkedList.size());
        System.out.println("############ 链表打印 ###########");
        linkedList.show();

        linkedList.removeIndex(3);
        System.out.println("当前链表中个数为: " + linkedList.size());
        System.out.println("############ 链表打印 ###########");
        linkedList.show();

        linkedList.clear();
        System.out.println("当前链表中个数为: " + linkedList.size());
        System.out.println("############ 链表打印 ###########");
        linkedList.show();
    }
}
