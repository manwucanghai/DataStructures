package com.zws.datastruct.stack.cal;

/**
 * @author zhengws
 * @date 2019-10-19 16:35
 */
public class StringUtils {

    public static boolean isOperate(char c) {
        if (operatePriority(c) == -1) {
            return false;
        }
        return true;
    }

    public static boolean comparePriority(Character c1, Character c2) {
        if (c1.equals('(')) {
            return false;
        }
        return operatePriority(c1) >= operatePriority(c2);
    }

    public static int operatePriority(char c) {
        int priority = -1;
        switch (c) {
            case '+':
            case '-':
                priority = 0;
                break;
            case '*':
            case '/':
                priority = 1;
                break;
            case '(':
            case ')':
                priority = 2;
                break;
        }
        return priority;
    }

    public static Number cal(Number first, Number second, Character operType) {
        Number result = null;
        switch (operType) {
            case '+':
                result = first.doubleValue() + second.doubleValue();
                break;
            case '-':
                result = second.doubleValue() - first.doubleValue();
                break;
            case '*':
                result = first.doubleValue() * second.doubleValue();
                break;
            case '/':
                result = second.doubleValue() / first.doubleValue();
                break;
        }
        return result;
    }
}
