package com.zws.datastruct.tree.binarytree;

/**
 * @author zhengws
 * @date 2019-11-02 19:51
 */
public class ThreadTreeTest {
    public static void main(String[] args) {
        ThreadBinaryTree<Integer> tree = new ThreadBinaryTree<>();
        tree.add(5);
        tree.add(2);
        tree.add(7);
        tree.add(6);
        tree.add(20);
        tree.add(30);
        tree.add(10);
        tree.add(25);
        tree.add(15);
        tree.add(9);

        System.out.println("##################");
        tree.infixThreadNode();
        tree.infixOrder();

        /**
         * 输出：
         * 2
         * 5
         * 6
         * 7
         * 9
         * 10
         * 15
         * 20
         * 25
         * 30
         */
    }
}
