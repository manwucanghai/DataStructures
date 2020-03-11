import java.util.Arrays;
import java.util.Random;

public class ShellSort{
	public static void  sort(int[] arr){
		if (arr.length <= 1) {
			return;
		}
		int index;
		for (int step = arr.length / 2; step > 0; step /= 2) {
			for (int i = step; i < arr.length; i++) {
				index = i;
				while(index - step >= 0 && arr[index - step] > arr[index]){
					swap(arr, index, index - step);
					index -= step;
				}
			}
		}

	}

	public static void swap(int[] arr, int a, int b){
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