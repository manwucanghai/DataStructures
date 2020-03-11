/**
 * @author zhengws
 * @date 2020-03-11 14:17
 */
public class KmpMatch {

    private static int indexOf(String source, String pattern) {
        int[] next = getKmpNext(pattern);

        int index = -1;

        int sourceLength = source.length();
        int patternLength = pattern.length();

        for (int s = 0, p = 0; s < sourceLength; s++) {
            if (source.charAt(s) == pattern.charAt(p)) {
                p++;
            } else {
                if (p > 0) {
                    p = next[p - 1];
                    s--;
                }
            }
            if (p == patternLength) {
                index = s - (p - 1);
                break;
            }
        }
        return index;
    }

    private static int[] getKmpNext(String pattern) {
        int[] next = new int[pattern.length()];

        for (int s = 1, p = 0; s < pattern.length(); s++) {
            if (pattern.charAt(s) == pattern.charAt(p)) {
                p++;
            } else {
                p = 0;
            }
            next[s] = p;
        }
        return next;
    }

    public static void main(String[] args) {
        int index = indexOf("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
        System.out.println(index); //15
    }
}
