public class Main {
    public static void main(String[] args) {
        HeapSort heap = new HeapSort();
        heap.add(1);
        heap.add(1);
        heap.add(5);
        heap.add(3);
        heap.add(2);
        heap.add(7);
        heap.remove();
        heap.remove();
        System.out.println(heap.peek());
    }
}
