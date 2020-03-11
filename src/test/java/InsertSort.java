import java.util.Arrays;
import java.util.Random;

public class InsertSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int value;
        int pos;
        for (int e = 1; e < arr.length; e++) {
            value = arr[e];
            pos = e;
            for (int s = 0; s < e; s++) {
                if (arr[s] > arr[e]) {
                    pos = s;
                    break;
                }
            }
            if (pos != e) {
                System.arraycopy(arr, pos, arr, pos + 1, e - pos);
                arr[pos] = value;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[20];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            arr[i] = random.nextInt(300);
        }
        System.out.println("排序前：" + Arrays.toString(arr));
        sort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }
}