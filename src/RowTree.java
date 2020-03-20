import java.util.HashMap;
import java.util.Map;
/*
 * tree is presented as string:
 * 5 - count of nodes in the tree
 * 4 -1 4 1 1 - nodes where nodes[i] is parent of i
 */
class RowTree {
    private Map<Integer, Integer> tree = new HashMap<>(); // {node: parent}
    private Map<Integer, Integer> cache = new HashMap<>(); // {node: height}
    private int count;

    RowTree(int n, int[] row) {
        count = n;
        init(n, row);
    }

    private void init(int n, int[] row) {
        for (int i = 0; i < n; i++) {
            tree.put(i, row[i]);
        }
    }

    int getMaxHeight() {
        int maxHeight = 0;

        int height;
        for (int i = 0; i < count; i++) {
            if (cache.containsKey(i)) {
                height = cache.get(i);
            } else {
                height = traverse(i);
                cache.put(i, height);
                cache.put(tree.get(i), height - 1);
            }

            maxHeight = Math.max(maxHeight, height);
        }

        return maxHeight;
    }

    // from node to root
    private int traverse(int node) {
        int parent = node;
        int length = 1;

        while (parent >= 0) {
            parent = tree.get(parent);

            if (parent < 0)
                break;

            if (cache.containsKey(parent)) {
                length += cache.get(parent);
                break;
            } else {
                ++length;
            }
        }

        return length;
    }
}
