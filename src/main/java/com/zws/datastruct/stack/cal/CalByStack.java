package com.zws.datastruct.stack.cal;

import com.zws.datastruct.stack.IStack;
import com.zws.datastruct.stack.LinkedStack;

import java.util.NoSuchElementException;

/**
 * @author zhengws
 * @date 2019-10-19 16:34
 */
public class CalByStack {

    /**
     * 数栈
     */
    private IStack<Number> numStack;

    /**
     * 操作栈
     */
    private IStack<Character> operStack;

    /**
     * 当前读到表达式位置游标.
     */
    private int index;


    public CalByStack() {
        this.numStack = new LinkedStack<Number>(10);
        this.operStack = new LinkedStack<Character>(10);
    }

    public Number execute(String expression) {
        checkExpression(expression);
        pushCalStack(expression);
        //对栈中数据进行取出计算.
        while (!operStack.isEmpty()) {
            popAndCal();
        }
        //最终留在数栈的最后一个元素，则为最终结果.
        return numStack.pop();
    }

    /**
     * 压栈，待计算，中间过程比较优先级，可进行优先计算。
     * @param expression
     */
    private void pushCalStack(String expression) {
        //必须将index重置为0，否则如果进行多次计算的话，会出问题。
        this.index = 0;
        int length = expression.length();
        String temp = "";
        while (index < length) {
            char cur = expression.substring(index, ++index).charAt(0);
            if (StringUtils.isOperate(cur)) {
                modifyOperStack(cur);
            } else {
                temp += cur;
                if (index + 1 <= length) {
                    //如果下一位依然是数字，则进行继续取.
                    char next = expression.substring(index, index + 1).charAt(0);
                    if (!StringUtils.isOperate(next)) {
                        continue;
                    }
                }
                pushNumStack(temp);
                temp = "";
            }
        }
    }

    private void pushNumStack(String temp) {
        numStack.push(Integer.parseInt(temp));
    }

    private void modifyOperStack(char c) {
        if (operStack.isEmpty() || c == '(') {
            operStack.push(c);
            return;
        }
        if (c == ')'){
            //开始计算括号内表达式，直到遇到'('
            calInnerOper();
            return;
        }
        int curPriviority = StringUtils.operatePriority(c);
        comparePriviority(curPriviority);
        operStack.push(c);
    }

    private void calInnerOper() {
        while (operStack.peek() != '('){
            popAndCal();
        }
        //将'('弹出，否则会出现不对等情况.
        operStack.pop();
    }

    /**
     * 比较操作优先级
     * 注意：如果top的优先级更高或相等，则先进行计算再压入.
     * (相等也必须先计算，否则弹栈是从后面弹出，计算顺序会乱掉.)
     *
     * @param curPriviority
     */
    private void comparePriviority(int curPriviority) {
        int topPriviority;
        try {
            Character top = operStack.peek();
            if (top.equals('(')){
                return;
            }
            topPriviority = StringUtils.operatePriority(top);
        } catch (NoSuchElementException e) {
            //当取不到上一个操作时，退出比较.
            return;
        }

        if (topPriviority >= curPriviority) {
            //栈顶优先级大于等于当前的操作优先级,则进行弹两个num栈，进行计算，后压入栈
            popAndCal();
            comparePriviority(curPriviority);
        }
    }

    private void popAndCal() {
        Character operType = operStack.pop();
        Number first = numStack.pop();
        Number second = numStack.pop();
        Number result = StringUtils.cal(first, second, operType);
        if (result == null) {
            throw new RuntimeException("计算出错");
        }
        numStack.push(result);
    }


    private void checkExpression(String expression) {
        if (expression == null || "".equals(expression)) {
            throw new IllegalArgumentException("输入参数有误");
        }
    }
}
