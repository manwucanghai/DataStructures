package com.zws.datastruct.stack.cal.reversepolish;

import com.zws.datastruct.stack.cal.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式
 *
 * @author zhengws
 * @date 2019-10-19 20:55
 */
public class InfixToPostfixTransform {

    /**
     * 用户输入中缀表达式
     */
    private String expression;

    /**
     * 标记栈，也就是临时栈，存储符号
     */
    private Stack<Character> markStack;

    /**
     * 结果栈，存储最终结果的，包括数字及符号
     */
    private Stack<Object> resultStack;

    public InfixToPostfixTransform() {
        this.markStack = new Stack<Character>();
        this.resultStack = new Stack<Object>();
    }

    public InfixToPostfixTransform(String expression) {
        this.expression = expression;
        this.markStack = new Stack<Character>();
        this.resultStack = new Stack<Object>();
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * 执行转换
     *
     * @return
     */
    public List<Object> transform() {
        checkExpression();
        genrateResultStack();
        Stack<Object> tempStack = new Stack<Object>();
        while (!resultStack.isEmpty()){
            tempStack.push(resultStack.pop());
        }

        List<Object> result = new ArrayList<Object>();
        while (!tempStack.isEmpty()){
            result.add(tempStack.pop());
        }
        return result;
    }

    /**
     * 将中缀表达式转换成后缀表达式，存放于resutlStack.
     */
    private void genrateResultStack() {
        List<String> strings = expressSplit();
        for (String s : strings) {
            if (s.matches("(\\d+)")) {
                //数字
                resultStack.push(Double.valueOf(s));
            } else {
                //符号
                markStackModify(s);
            }
        }
        while (!markStack.isEmpty()) {
            popMarkAndPushResult();
        }
    }

    /**
     * 针对符号操作栈处理.
     * 1、如果操作栈为空，或者操作符为(,则直接压入操作栈
     * 2、如果操作符为),则弹出操作并压入到结果栈，直到遇到操作符(,代表一对小括号结束, 并将末尾(弹出栈.
     * 3、其他情况，对比操作符与操作栈栈顶操作的优先级，如果优先级低于栈顶，则先操作栈的栈顶弹出并添加到结果栈中，再将该操作符入操作栈。
     * @param s
     */
    private void markStackModify(String s) {
        char c = s.charAt(0);
        if (markStack.isEmpty() || c == '(') {
            markStack.push(c);
        } else if (c == ')') {
            while (!markStack.isEmpty() && markStack.peek() != '(') {
                popMarkAndPushResult();
            }
            //将'('弹出.
            markStack.pop();
        } else {
            if (StringUtils.comparePriority(markStack.peek(), c)) {
                popMarkAndPushResult();
            }
            markStack.push(c);
        }
    }

    /**
     * 将操作栈的栈顶弹出，并压入到结果栈中.
     */
    private void popMarkAndPushResult() {
        Character pop = markStack.pop();
        resultStack.push(pop);
    }

    /**
     * 表达式拆分
     *
     * @return
     */
    private List<String> expressSplit() {
        //正数及小数点.
        String[] operts = expression.split("\\d+");
        List<String> list = new ArrayList<String>();
        int length;
        for (String op : operts) {
            int index = expression.indexOf(op);
            if (index != 0) {
                list.add(expression.substring(0, index));
            }
            //将多符号的进行拆分
            length = op.length();
            for (int i = 0; i < length; ) {
                list.add(op.substring(i, ++i));
            }
            expression = expression.substring(index + length);
        }
        if (expression.length() > 0){
            list.add(expression);
        }
        return list;
    }

    private void checkExpression() {
        if (expression == null || "".equals(expression)) {
            throw new IllegalArgumentException("表达式不能为空.");
        }
    }
}
