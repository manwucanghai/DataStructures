package com.zws.datastruct.linkedlist;

/**
 * @author zhengws
 * @date 2019-10-17 10:34
 */
public class LinkedListTest {
    public static void main(String[] args) {
        ILinkedList<String> linkedList = new SingleTrackLinkedList<String>();
//        ILinkedList<String> linkedList = new DoubleTrackLinkedList<String>();
        testRemoveLast(linkedList);

//        testRemoveFirst(linkedList);

//        testRemoveLastIndex(linkedList);

//        testRemoveIndex(linkedList);

//        testRemoveElement(linkedList);

//        testGetIndex(linkedList);

//        testSetElement(linkedList);

//        insertTest(linkedList);

//        testFindLastElement(linkedList);

//        reverseTest(linkedList);

//        reverseShowTest(linkedList);

//        clearTest(linkedList);

    }

    private static void testRemoveFirst(ILinkedList<String> linkedList) {
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.show();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("######## remove last test #######");
        linkedList.removeFirst();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findFirstElement());
        linkedList.removeFirst();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findFirstElement());
        linkedList.removeFirst();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findFirstElement());
        linkedList.removeFirst();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findFirstElement());
        linkedList.removeFirst();
        System.out.println("链表当前节点个数: " + linkedList.size());
        linkedList.show();
    }

    private static void clearTest(ILinkedList<String> linkedList) {
        System.out.println("####### clear test ##############");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("####### clear 前 ##############");
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.show();
        linkedList.clear();
        System.out.println("####### clear 后 ##############");
        System.out.println("链表当前节点个数: " + linkedList.size());
//        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.show();
    }

    private static void reverseShowTest(ILinkedList<String> linkedList) {
        System.out.println("######reverse show test #############");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("###### reverse show 前 #############");
        linkedList.show();
        System.out.println("####### reverse show print ############");
        linkedList.reverseShow();
        System.out.println("###### reverse show 后 #############");
        linkedList.show();
    }

    private static void reverseTest(ILinkedList<String> linkedList) {
        System.out.println("######reverse test #############");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("######reverse 前 #############");
        linkedList.show();
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        System.out.println("######reverse 后 #############");
        linkedList.reverse();
        linkedList.show();
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
    }

    private static void insertTest(ILinkedList<String> linkedList) {
        System.out.println("######## insert  test #########");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("######### insert 前 ###########");
        linkedList.show();
        System.out.println("######### insert 后 ###########");
        linkedList.insert(0, "insert0");
        linkedList.insert(4, "insert4");
        linkedList.insert(8, "insert8");
        System.out.println("链表当前节点个数: " + linkedList.size());
        linkedList.show();
    }

    private static void testFindLastElement(ILinkedList<String> linkedList) {
        System.out.println("######查询倒数第index个,最后一个代表倒数第0个.#########");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("######### show ###########");
        linkedList.show();
        System.out.println("######### find lastIndexElement ############");
        System.out.println(linkedList.lastIndexElement(0));
        System.out.println(linkedList.lastIndexElement(3));
        System.out.println(linkedList.lastIndexElement(5));


        System.out.println("链表当前节点个数: " + linkedList.size());
    }

    private static void testSetElement(ILinkedList<String> linkedList) {
        System.out.println("######## set element test #######");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        linkedList.set(0, "aa");
        linkedList.set(4, "ee");
        linkedList.set(5, "ff");
        linkedList.show();
    }

    private static void testGetIndex(ILinkedList<String> linkedList) {
        System.out.println("######## get index #######");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(3));
        System.out.println(linkedList.get(5));
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.show();
    }

    private static void testRemoveElement(ILinkedList<String> linkedList) {
        System.out.println("######## remove element test #######");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("########## 移除前 ############");
        linkedList.show();
        System.out.println("########## 移除后 ############");
        linkedList.remove("a");
        linkedList.remove("f");
        linkedList.remove("c");
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.show();
    }

    private static void testRemoveIndex(ILinkedList<String> linkedList) {
        System.out.println("######## remove index #######");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.add("f");
        System.out.println("########## 移除前 ############");
        linkedList.show();
        System.out.println("########## 移除后 ############");
        linkedList.removeIndex(0);
        linkedList.removeIndex(2);
        linkedList.removeIndex(3);
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.show();
    }

    private static void testRemoveLastIndex(ILinkedList<String> linkedList) {
        System.out.println("######## remove last index #######");
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        System.out.println("########## 移除前 ############");
        linkedList.show();
        linkedList.removeLastIndexElement(0);
        linkedList.removeLastIndexElement(3);
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.show();
    }

    private static void testRemoveLast(ILinkedList<String> linkedList) {
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        linkedList.show();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("######## remove last test #######");
        linkedList.removeLast();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.removeLast();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.removeLast();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.removeLast();
        System.out.println("链表当前节点个数: " + linkedList.size());
        System.out.println("当前最后一个元素为: " + linkedList.findLastElement());
        linkedList.removeLast();
        System.out.println("链表当前节点个数: " + linkedList.size());
        linkedList.show();
    }
}
