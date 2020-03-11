import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);

    }

    private static void sort(int[] arr, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }

        int mid = (start + end) / 2;
        sort(arr, start, mid, temp);
        sort(arr, mid + 1, end, temp);
        merge(arr, start, mid, end, temp);
    }

    private static void merge(int[] arr, int start, int mid, int end, int[] temp) {
        int left = start;
        int right = mid + 1;
        int index = 0;
        while (left <= mid && right <= end) {
            if (arr[left] <= arr[right]) {
                temp[index++] = arr[left++];
            } else {
                temp[index++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = arr[left++];
        }

        while (right <= end) {
            temp[index++] = arr[right++];
        }


        for (int i = 0; i < index;) {
            arr[start++] = temp[i++];
        }
//        index = 0;
//        for (int i = start; i <= end; i++,index++) {
//            arr[i] = temp[index];
//        }
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