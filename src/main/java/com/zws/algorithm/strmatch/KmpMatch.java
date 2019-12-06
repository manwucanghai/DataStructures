package com.zws.algorithm.strmatch;

/**
 * @author zhengws
 * @date 2019-11-16 10:37
 */
public class KmpMatch {

    public int indexOf(String source, String partten) {
        // 1. 先获取模式串P的最长公共子序数组(next数组)
        int[] next = kmpNext(partten);

        int sourceLength = source.length();
        int parttenLength = partten.length();

        int index = -1;

        int i = 0;
        int m = 0;
        while (i < sourceLength && m < parttenLength) {
            //2. 如果两字符相等，则 i 与 m 都自增
            if (source.charAt(i) == partten.charAt(m)) {
                m++;
                i++;
            } else {
                //3. 如果不相等，如果 m 等于0，则 i 自增，如果 m 大于0，则 m = next[m - 1]
                if (m > 0) {
                    m = next[m - 1];
                } else {
                    i++;
                }
            }
            // 4. 如果 m 与 partten 长度相等，则说明已经匹配找到，则直接返回。
            if (m == parttenLength) {
                index = i - m;
                break;
            }
        }
        return index;
    }

    public int indexOfStr(String source, String partten) {
        // 1. 先获取模式串P的最长公共子序数组(next数组)
        int[] next = kmpNext(partten);

        int length = partten.length();

        int index = -1;
        for (int i = 0, m = 0; i < source.length(); i++) {
            if (source.charAt(i) == partten.charAt(m)) {
                m++;
            } else {
                if (m > 0) {
                    m = next[m-1];
                    i--;
                }
            }
            if (m == length) {
                //之所以需要 -1，因为前面匹配后m会自增
                index = i - (m - 1);
                break;
            }
        }
        return index;
    }

    private int[] kmpNext(String partten) {
        int[] next = new int[partten.length()];
        /**
         * i 代表源字符串，当前匹配位置.
         * m 代表模式串长度
         */
        for (int i = 1, m = 0; i < partten.length(); i++) {
            if (partten.charAt(i) == partten.charAt(m)) {
                m++;
            } else {
                m = 0;
            }
            next[i] = m;
        }
        return next;
    }

    public static void main(String[] args) {
        KmpMatch kmpMatch = new KmpMatch();
        String source = "BBC ABCDAB ABCDABCDABDE";
        String partten = "ABCDABD";
        int index = kmpMatch.indexOf(source, partten);
        System.out.println(index);
        System.out.println(source.substring(index, index + partten.length()));

        System.out.println("##################");
        index = kmpMatch.indexOfStr(source, partten);
        System.out.println(index);

        /**
         * 输出：
         * 15
         * ABCDABD
         * ##################
         * 15
         */
    }
}
