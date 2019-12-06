package com.zws.datastruct.stack.cal;

/**
 * @author zhengws
 * @date 2019-10-19 17:20
 */
public class CalStackTest {
    public static void main(String[] args) {
        CalByStack calByStack = new CalByStack();
        Number result1 = calByStack.execute("10-7*2*2-5+10/2");
        System.out.println("#######################");
        System.out.println("10-7*2*2-5+10/2 = " + result1.doubleValue());
        System.out.println("#######################");
        Number result2 = calByStack.execute("14-(10+3*2)+1");
        System.out.println("14-(10+3*2)+1 = " + result2.doubleValue());
        System.out.println("#######################");
        Number result3 = calByStack.execute("3-(4+(5*2))+21");
        System.out.println("3-(4+(5*2))+21 = " + result3.doubleValue());
        System.out.println("#######################");
        Number result4 = calByStack.execute("23-(4+(5*2))");
        System.out.println("23-(4+(5*2)) = " + result4.doubleValue());
        System.out.println("#######################");
        Number result5 = calByStack.execute("(2+23)*2-(4+(5*2))");
        System.out.println("(2+23)*2-(4+(5*2)) = " + result5.doubleValue());

        /**
         * 输出结果为:
         * #######################
         * 10-7*2*2-5+10/2 = -18.0
         * #######################
         * 14-(10+3*2)+1 = -1.0
         * #######################
         * 3-(4+(5*2))+21 = 10.0
         * #######################
         * 23-(4+(5*2)) = 9.0
         * #######################
         * (2+23)*2-(4+(5*2)) = 36.0
         */
    }
}
