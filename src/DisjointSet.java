import java.util.Arrays;
import java.util.Scanner;

public class DisjointSet {
    private int capacity;
    private int[] parent;
    private int[] rank;
    private int[] sizes;

    int max = Integer.MIN_VALUE;

    public DisjointSet(int capacity, int[] sizes) {
        this.capacity = capacity;
        this.sizes = Arrays.copyOfRange(sizes, 0, sizes.length);

        parent = new int[capacity];
        rank = new int[capacity];
        init();
    }

    private void init() {
        for (int i = 0; i < capacity; i++) {
            makeSet(i);

            if (rank[i] > max) {
                max = rank[i];
            }
        }
    }

    private void makeSet(int i) {
        parent[i] = i;
        rank[i] = sizes[i];
    }

    public int union(int i, int j) {
        int iId = find(i), jId = find(j);

        if (iId == jId) {
            return iId;
        }

        if (rank[iId] > rank[jId]) {
            parent[jId] = iId;
            rank[iId] += rank[jId];
            return iId;
        }

        parent[iId] = jId;
        rank[jId] += rank[iId];
        return jId;

    }

    public int find(int i) {
        if (i != parent[i]) {
           parent[i] = find(parent[i]);
        }

        return parent[i];
    }

    public int getRank(int i) {
      return rank[i];
    }

    public int getMax() {
        return max;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int setCapacity = scanner.nextInt();
        int countOfOperations = scanner.nextInt();

        int[] sizes = new int[setCapacity];
        int[][] operations = new int[countOfOperations][];

        for (int i = 0; i < setCapacity; i++) {
            sizes[i] = scanner.nextInt();
        }

        for (int i = 0; i < countOfOperations; i++) {
            operations[i] = new int[]{scanner.nextInt() - 1, scanner.nextInt() - 1};
        }

        DisjointSet set = new DisjointSet(setCapacity, sizes);
        StringBuilder output = new StringBuilder();
        int maxSize = set.getMax();

        for (int[] operation: operations) {
           int destination = operation[0];
           int source = operation[1];

           int destParent = set.find(destination);
           int sourceParent = set.find(source);

           if (destParent == sourceParent) {
               maxSize = Math.max(maxSize, set.getRank(destParent));
           } else {
               int commonParent = set.union(destParent, sourceParent);
               maxSize = Math.max(maxSize, set.getRank(commonParent));
           }

           output.append(maxSize).append("\n");
        }

        System.out.println(output);
    }
}
