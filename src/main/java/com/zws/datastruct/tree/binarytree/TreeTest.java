package com.zws.datastruct.tree.binarytree;

import java.util.Random;

/**
 * @author zhengws
 * @date 2019-11-01 20:13
 */
public class TreeTest {
    public static void main(String[] args) {
        IBinaryTree<Integer> tree = new BinaryTree<>();
//        Random random = new Random();
//        long start = System.currentTimeMillis();
//        for (int i = 1; i < 1000000; i++) {
//            tree.add(random.nextInt(1000000));
//        }
//        tree.add(66666);
//
//        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms");
//        /**
//         * cost 656 ms
//         */
//
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

//        System.out.println("########preOrder#########");
//        tree.preOrder();
//        System.out.println("########postOrder#########");
//        tree.postOrder();
//        System.out.println("#########infixOrder########");
//        tree.infixOrder();

        System.out.println("###########");
        tree.preOrder();
        tree.remove(20);
        System.out.println("###########");
        tree.preOrder();
        System.out.println("###########");
        tree.remove(5);
        tree.preOrder();

        System.out.println(tree.remove(9));
        System.out.println(tree.remove(30));
        System.out.println(tree.remove(15));
        System.out.println(tree.remove(6));
        System.out.println(tree.remove(25));
        System.out.println(tree.remove(7));
        System.out.println(tree.remove(10));

        System.out.println("###########");
        System.out.println(tree.isEmpty());

        System.out.println(tree.remove(2));

        System.out.println("###########");
        System.out.println(tree.isEmpty());
//
//        /**
//         * 输出：
//         * ########preOrder#########
//         * 5
//         * 2
//         * 7
//         * 6
//         * 8
//         * ########postOrder#########
//         * 2
//         * 6
//         * 8
//         * 7
//         * 5
//         * #########infixOrder########
//         * 2
//         * 5
//         * 6
//         * 7
//         * 8
//         */


    }
}
