package com.zws.datastruct.stack;

import java.util.Stack;

/**
 * 【题目】
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * <p>
 * 【要求】
 * 1.pop、push、getMin操作的时间复杂度都是O (1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 *
 * @author zhengws
 * @date 2019-10-24 14:35
 */
public class GetMinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public Integer push(Integer e) {
        checkNullElement(e);
        if (stackMin.isEmpty()) {
            stackMin.push(e);
        } else {
            if (stackMin.peek().compareTo(e) >= 0) {
                //说明当前栈顶的大于等于压入的值
                stackMin.push(e);
            }
        }
        return stackData.push(e);
    }

    public Integer pop() {
        checkEmptyStack(stackData);
        Integer pop = stackData.pop();
        Integer curMin = getMin();
        if (pop.equals(curMin)) {
            stackMin.pop();
        }
        return pop;
    }

    public Integer getMin() {
        checkEmptyStack(stackMin);
        return stackMin.peek();
    }

    private void checkEmptyStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("stack is empty.");
        }
    }

    private void checkNullElement(Integer e) {
        if (e == null) {
            throw new NullPointerException("元素不能为空");
        }
    }


    public static void main(String[] args) {
        GetMinStack stack = new GetMinStack();
        stack.push(10);
        stack.push(9);
        stack.push(30);
        stack.push(5);
        stack.push(21);
        stack.push(1);

        System.out.println("当前最小元素为: " + stack.getMin());
        System.out.println("pop: "+stack.pop());
        System.out.println("当前最小元素为: " + stack.getMin());

        System.out.println("pop: "+stack.pop());
        System.out.println("当前最小元素为: " + stack.getMin());

        System.out.println("pop: "+stack.pop());
        System.out.println("当前最小元素为: " + stack.getMin());

        System.out.println("pop: "+stack.pop());
        System.out.println("当前最小元素为: " + stack.getMin());

        System.out.println("pop: "+stack.pop());
        System.out.println("当前最小元素为: " + stack.getMin());

        System.out.println("pop: "+stack.pop());

        /**
         * 输出：
         * 当前最小元素为: 1
         * pop: 1
         * 当前最小元素为: 5
         * pop: 21
         * 当前最小元素为: 5
         * pop: 5
         * 当前最小元素为: 9
         * pop: 30
         * 当前最小元素为: 9
         * pop: 9
         * 当前最小元素为: 10
         * pop: 10
         */
    }
}
