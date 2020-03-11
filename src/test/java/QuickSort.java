import java.util.Arrays;
import java.util.Random;

public class QuickSort{
	public static void sort(int[] arr){
		if (arr.length <= 1) {
			return;
		}
		sort(arr, 0, arr.length-1);

	}

	private static void sort(int arr[], int start, int end){
		if (start >= end) {
			return;
		}
		int value = arr[start];

		int left = start;
		int right = end;

		while(right > left){
			while(right > left && arr[right] > value){
				right--;
			}

			if (right > left) {
				arr[left] = arr[right];
				left++;
			}

			while(right > left && arr[left] < value){
				left++;
			}

			if (right > left) {
				arr[right] = arr[left];
				right--;
			}
		}

		arr[left] = value;

		sort(arr, start, left-1);
		sort(arr, left + 1, end);
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