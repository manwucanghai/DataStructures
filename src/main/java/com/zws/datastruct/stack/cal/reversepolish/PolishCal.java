package com.zws.datastruct.stack.cal.reversepolish;

import com.zws.datastruct.stack.cal.StringUtils;

import java.util.List;
import java.util.Stack;

/**
 * @author zhengws
 * @date 2019-10-19 22:14
 */
public class PolishCal {
    private InfixToPostfixTransformV2 transform;

    private Stack<Object> calStack;

    public PolishCal(InfixToPostfixTransformV2 transform) {
        this.transform = transform;
        this.calStack = new Stack<Object>();
    }

    public Number execute(String expression) {
        transform.setExpression(expression);
        List<Object> lists = this.transform.transform();
        System.out.println("后缀表达式为: " + lists);
        for (int i = 0; i < lists.size(); i++) {
            Object o = lists.get(i);
            if (o instanceof Number) {
                calStack.push(o);
            } else {
                Number num1 = (Number) calStack.pop();
                Number num2 = (Number) calStack.pop();
                Number result = StringUtils.cal(num1, num2, (Character) o);
                calStack.push(result);
            }
        }
        return (Number) calStack.pop();
    }
}
