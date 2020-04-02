import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] array = new int[n];
        int i = 0, count = 0;

        while (i < n) {
            array[i++] = scanner.nextInt();
        }

        StringBuilder log = new StringBuilder();
        BinaryHeap heap = new BinaryHeap(array);
        count = heap.buildHeapFromArray(log);
        System.out.println(count);
        System.out.println(log);
    }
}
