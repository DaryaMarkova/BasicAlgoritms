import java.util.Scanner;


public class DisjointSetPure {
    int capacity;
    int[] parent;
    int[] rank;

    public DisjointSetPure(int capacity) {
        this.capacity = capacity;
        parent = new int[capacity];
        rank = new int[capacity];
        init();
    }

    private void init() {
        for (int i = 0; i < capacity; i++) {
            makeSet(i);
        }
    }

    private void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }

    public void shallowUnion(int i, int j) {
       parent[j] = parent[i];
    }

    public boolean equalParents(int i, int j) {
        return parent[i] == parent[j];
    }

    public void union(int i, int j) {
        int iId = find(i), jId = find(j);

        if (iId == jId) {
            return;
        }

        if (rank[iId] > rank[jId]) {
            parent[jId] = iId;
        } else {
            parent[iId] = jId;

            if (rank[iId] == rank[jId]) {
                rank[jId] += 1;
            }
        }
    }

    public Integer find(int i) {
        if (i != parent[i]) {
            parent[i] = find(parent[i]);
        }

        return parent[i];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int p = scanner.nextInt(), n = scanner.nextInt();
        int source, target;
        DisjointSetPure set = new DisjointSetPure(size);

        for (int i = 0; i < p; i++) {
            source = scanner.nextInt() - 1;
            target = scanner.nextInt() - 1;

            set.shallowUnion(source, target);
        }

        for (int i = 0; i < n; i++) {
            source = scanner.nextInt() - 1;
            target = scanner.nextInt() - 1;

            if (set.equalParents(source, target)) {
                System.out.println(0);
                return;
            }
        }

        System.out.println(1);
    }
}
