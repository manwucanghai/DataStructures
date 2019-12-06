package com.zws.datastruct.tree.binarytree;

/**
 * @author zhengws
 * @date 2019-11-02 09:21
 */
public class ArrBinaryTree {
    private int[] arr;


    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * 左子节点 2 * n + 1
     * 右子节点 2 * n + 2
     */
    public void preOrder() {
        checkEmptyArr();
        preOrderPrint(0);
    }

    private void preOrderPrint(int index) {
        if (checkIndexRange(index)) {
            System.out.println(arr[index]);
            //遍历左子节点
            preOrderPrint(2 * index + 1);
            //遍历右子节点
            preOrderPrint(2 * index + 2);
        }
    }

    private void checkEmptyArr() {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("arr is empty");
        }
    }

    private boolean checkIndexRange(int index) {
        if (index < 0 || index >= arr.length) {
            return false;
        }
        return true;
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        checkEmptyArr();
        postOrderPrint(0);
    }

    private void postOrderPrint(int index) {
        if (checkIndexRange(index)) {
            //遍历左子节点
            postOrderPrint(2 * index + 1);
            //遍历右子节点
            postOrderPrint(2 * index + 2);
            System.out.println(arr[index]);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        checkEmptyArr();
        infixOrderPrint(0);
    }

    private void infixOrderPrint(int index) {
        if (checkIndexRange(index)) {
            //遍历左子节点
            infixOrderPrint(2 * index + 1);
            System.out.println(arr[index]);
            //遍历右子节点
            infixOrderPrint(2 * index + 2);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree tree = new ArrBinaryTree(arr);
        System.out.println("#######preOrder########");
        tree.preOrder();
        System.out.println("#######postOrder########");
        tree.postOrder();
        System.out.println("#######infixOrder########");
        tree.infixOrder();

        /**
         * 输出:
         * #######preOrder########
         * 1
         * 2
         * 4
         * 5
         * 3
         * 6
         * 7
         * #######postOrder########
         * 4
         * 5
         * 2
         * 6
         * 7
         * 3
         * 1
         * #######infixOrder########
         * 4
         * 2
         * 5
         * 1
         * 6
         * 3
         * 7
         */
    }
}
