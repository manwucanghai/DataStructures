package com.zws.datastruct.stack.cal.reversepolish;

/**
 * @author zhengws
 * @date 2019-10-19 22:25
 */
public class PolishCalTest {
    public static void main(String[] args) {
        InfixToPostfixTransformV2 transform = new InfixToPostfixTransformV2();
        PolishCal cal = new PolishCal(transform);
        System.out.println("10-7*2*2-5+10/2 = "+ cal.execute("10-7*2*2-5+10/2"));
        System.out.println("#######################");
        System.out.println("14-(10+3*2)+1 = "+ cal.execute("14-(10+3*2)+1"));
        System.out.println("#######################");
        System.out.println("3-(4+(5*2))+21 = "+ cal.execute("3-(4+(5*2))+21"));
        System.out.println("#######################");
        System.out.println("23-(4+(5*2)) = "+ cal.execute("23-(4+(5*2))"));
        System.out.println("#######################");
        System.out.println("(2+23.4)*2-(4+(5*2)) = "+ cal.execute("(2 + 23.4) * 2 - (4 + (5 * 2))"));
        System.out.println("#######################");

        /**
         * 输出结果为:
         * 后缀表达式为: [10.0, 7.0, 2.0, *, 2.0, *, 5.0, -, 10.0, 2.0, /, +, -]
         * 10-7*2*2-5+10/2 = -18.0
         * #######################
         * 后缀表达式为: [14.0, 10.0, 3.0, 2.0, *, +, -, 1.0, +]
         * 14-(10+3*2)+1 = -1.0
         * #######################
         * 后缀表达式为: [3.0, 4.0, 5.0, 2.0, *, +, -, 21.0, +]
         * 3-(4+(5*2))+21 = 10.0
         * #######################
         * 后缀表达式为: [23.0, 4.0, 5.0, 2.0, *, +, -]
         * 23-(4+(5*2)) = 9.0
         * #######################
         * 后缀表达式为: [2.0, 23.0, +, 2.0, *, 4.0, 5.0, 2.0, *, +, -]
         * (2+23)*2-(4+(5*2)) = 36.0
         * #######################
         */
    }
}
