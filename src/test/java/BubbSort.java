import java.util.Arrays;
import java.util.Random;

public class BubbSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        boolean flag;
        for (int out = arr.length - 1; out > 0; out--) {
            flag = false;
            for (int in = 0; in < out; in++) {
                if (arr[in] > arr[in + 1]) {
                    swap(arr, in, in + 1);
                    if (!flag) {
                        flag = true;
                    }
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
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