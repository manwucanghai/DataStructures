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
public class InfixToPostfixTransformV2 {

    /**
     * 用户输入中缀表达式
     */
    private String expression;

    /**
     * 标记栈，也就是临时栈，存储符号
     */
    private Stack<Character> markStack = new Stack<Character>();
    ;

    /**
     * 后缀表达式结果列表，存储最终结果的，包括数字及符号
     */
    private List<Object> resultList = new ArrayList<Object>();

    public InfixToPostfixTransformV2() {
    }

    public InfixToPostfixTransformV2(String expression) {
        this.expression = expression;
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
        this.resultList.clear();
        checkExpression();
        genrateResultStack();
        return resultList;
    }

    /**
     * 将中缀表达式转换成后缀表达式，存放于resutlStack.
     */
    private void genrateResultStack() {
        List<Object> strings = expressSplit();
        for (Object s : strings) {
            if (s instanceof Double) {
                //数字
                resultList.add(s);
            } else {
                //符号
                markStackModify((Character) s);
            }
        }
        while (!markStack.isEmpty()) {
            popMarkAndPushResult();
        }
    }

    /**
     * 针对符号操作栈处理.
     * 1、如果操作栈为空，或者操作符为(,则直接压入操作栈
     * 2、如果操作符为),则弹出操作并添加到结果列表中，直到遇到操作符(,代表一对小括号结束, 并将末尾(弹出栈.
     * 3、其他情况，对比操作符与操作栈栈顶操作的优先级，如果优先级低于栈顶，则先操作栈的栈顶弹出并添加到结果列表中，再将该操作符入操作栈。
     *
     * @param c
     */
    private void markStackModify(Character c) {
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
     * 将操作栈的栈顶弹出，并添加到结果列表中.
     */
    private void popMarkAndPushResult() {
        Character pop = markStack.pop();
        resultList.add(pop);
    }

    /**
     * 表达式拆分
     *
     * @return
     */
    private List<Object> expressSplit() {
        List<Object> list = new ArrayList<Object>();
        int index = 0;
        String temp = "";
        int length = expression.length();
        while (index < length) {
            char c = expression.substring(index, ++index).charAt(0);
            if (isNumber(c)) {
                temp = "";
                temp += c;
                while (index < length - 1) {
                    c = expression.substring(index, index + 1).charAt(0);
                    if (isNumber(c)) {
                        temp += c;
                        index++;
                        continue;
                    }
                    break;
                }
                list.add(Double.valueOf(temp));
            } else {
                list.add(c);
            }
        }
        return list;
    }

    private boolean isNumber(char c) {
        boolean res = (c >= 48 && c <= 57) || c == 46;
        return res;
    }

    private void checkExpression() {
        if (expression == null || "".equals(expression.trim())) {
            throw new IllegalArgumentException("表达式不能为空.");
        }
        //将多余的空格替换成"",兼容空白符(空格、制表符、换页符等)
        expression = expression.replaceAll("\\s+", "");
    }
}
