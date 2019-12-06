package com.zws.datastruct.tree.huffman;

import java.util.PriorityQueue;

/**
 * @author zhengws
 * @date 2019-11-05 21:41
 */
public class HuffManTree {


    private class Node implements Comparable<Node> {
        Integer element;
        private Node left;
        private Node right;

        public Node(Integer element) {
            this.element = element;
        }

        @Override
        @SuppressWarnings("unchecked")
        public int compareTo(Node node) {
            return element.compareTo(node.element);
        }
    }

    public Node createHuffTree(Integer[] arr) {
        //1、从小到大进行排序.
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Integer element : arr) {
            queue.add(new Node(element));
        }

        Node left;
        Node right;
        while (queue.size() > 1) {
            //2、取出权值最小的两个节点,组成一棵新的二叉树，该二叉树根节点值为这两个节点权值之和。
            left = queue.poll();
            right = queue.poll();
            Node parent = new Node(left.element + right.element);
            parent.left = left;
            parent.right = right;
            //3、将这个根节点添加到队列中，进行参与排序。依次取出前两个最小元素进行构建新的二叉树，直至队列中只剩最后一个元素。
            queue.add(parent);
        }
        return queue.poll();
    }


    private void preOrderPrint(Node node) {
        System.out.println(node.element);
        if (node.left != null) {
            preOrderPrint(node.left);
        }
        if (node.right != null) {
            preOrderPrint(node.right);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {13, 7, 8, 3, 29, 6, 1};
        HuffManTree tree = new HuffManTree();
        Node root = tree.createHuffTree(arr);
        tree.preOrderPrint(root);

        /**
         * 输出结果：
         * 67
         * 29
         * 38
         * 15
         * 7
         * 8
         * 23
         * 10
         * 4
         * 1
         * 3
         * 6
         * 13
         */
    }
}
