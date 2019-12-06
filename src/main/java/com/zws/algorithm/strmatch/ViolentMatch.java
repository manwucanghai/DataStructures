package com.zws.algorithm.strmatch;

/**
 * 需求描述：
 * 有一个文本串S，和一个模式串P，现在要查找P在S中的位置，假设现在文本串S匹配到 i 索引位置, 模式串P匹配到 j 索引位置。
 * <p>
 * 思路分析：
 * 1. 若当前字符匹配成功，即S[i]=P[j] 时，i++, j++
 * 2. 若当前字符匹配失败，则 i = i - j + 1, j = 0
 *
 * @author zhengws
 * @date 2019-11-16 10:14
 */
public class ViolentMatch {

    public int indexOf(String source, String partten) {
        int i = 0;
        int j = 0;

        int sourceLength = source.length();
        int parttenLenght = partten.length();

        int index = -1;

        while (i < sourceLength && j < parttenLenght) {
            if (source.charAt(i) == partten.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            // 注意，这里j 已经进行自增了，因此只需要判断长度即可
            if (j == parttenLenght){
                index = i - j;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        ViolentMatch violentMatch = new ViolentMatch();
        int index = violentMatch.indexOf("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
        System.out.println(index); //15
    }
}
