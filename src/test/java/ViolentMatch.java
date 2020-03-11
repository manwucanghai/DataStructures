/**
 * @author zhengws
 * @date 2020-03-11 14:06
 */
public class ViolentMatch {


    public static void main(String[] args) {
        ViolentMatch violentMatch = new ViolentMatch();
        int index = violentMatch.indexOf("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
        System.out.println(index); //15
    }

    private int indexOf(String source, String pattern) {
        int sourceLength = source.length();
        int patternLength = pattern.length();


        int index = -1;
        int s = 0;
        int p = 0;
        while (s < sourceLength && p < patternLength) {
            if (source.charAt(s) == pattern.charAt(p)) {
                s++;
                p++;
            } else {
                s = s - p + 1;
                p = 0;
            }

            if (p == patternLength) {
                index = s - p;
                break;
            }
        }
        return index;
    }
}
