import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class BinaryTree {
    class Node {
        Node left, right;
        int value;

        public Node(int value) {
            this.value = value;
        }

        public void insert(int _value) {
            if (_value <= value) {
                if (left == null) {
                    left = new Node(_value);
                } else {
                    left.insert(_value);
                }
            } else {
                if (right == null) {
                    right = new Node(_value);
                } else {
                    right.insert(_value);
                }
            }
        }

        public Node findNode(int searchValue) {
            if (value == searchValue) {
                return this;
            } else if (searchValue < value) {
                if (left == null) {
                    return null;
                } else {
                    return left.findNode(searchValue);
                }
            } else {
                if (right == null) {
                    return null;
                } else {
                    return right.findNode(searchValue);
                }
            }
        }

        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }

            System.out.println(value);

            if (right != null) {
                right.printInOrder();
            }
        }

        public int maxDepth(Node start) {
            if (start == null) {
                return 0;
            }

            return Math.max(maxDepth(start.left), maxDepth(start.right)) + 1;
        }

        public void printMatrix() {
            int depth = maxDepth(this);
            int width = (int)Math.pow(2, depth) + 1;

            String[][] treeMatrix = new String[depth][width];

            for(int i = 0; i < depth; i++) {
                for(int j=0; j < width; j++) {
                    treeMatrix[i][j] = "";
                }
            }

            fullMatrixIn(this, treeMatrix, 0, width / 2, width / 4);

            for(int i = 0; i < depth; i++) {
                for(int j=0; j < width; j++) {
                    System.out.print(treeMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }

        private void fullMatrixIn(Node top, String[][] matrix, int row, int col, int delta) {
            matrix[row][col] =  String.valueOf(top.value);

            if (top.left != null) {
                fullMatrixIn(top.left, matrix, row + 1, col - delta, delta / 2);
            }

            if (top.right != null) {
                fullMatrixIn(top.right, matrix, row + 1, col + delta, delta / 2);
            }
        }
    }

    Node top;

    public void insert(int value) {
        if (this.top == null) {
            this.top = new Node(value);
        } else {
            this.top.insert(value);
        }
    }

    public void sort() {
        this.top.printInOrder();
    }

    public Node findNode(int searchValue) {
        return this.top.findNode(searchValue);
    }

    private Node minimum(Node start) {
        if (start.left == null) {
            return start;
        }
        return minimum(start.left);
    }

    public Node delete(Node start, int deletedValue) {
        if (start == null) {
            return start;
        }

        if (deletedValue < start.value) {
            start.left = delete(start.left, deletedValue);
        } else if (deletedValue > start.value) {
            start.right = delete(start.right, deletedValue);
        } else if (start.left != null && start.right != null) {
            // оба потомка не равны нулю, ищем минимальное значение в правом поддереве
            start.value = minimum(start.right).value;
            start.right = delete(start.right, start.value);
        } else {
            if (start.left != null)
                start = start.left;
            else
                start = start.right;
        }

        return start;
    }

    //check if tree is a binary search tree
    // is less than max
    // is greater then min
    public boolean isBST(Node root, int min, int max) {
        if (root == null) {
            return true;
        }

        if (root.value <= min || root.value > max) {
            return false;
        }

        return isBST(root.left, min, root.value) && isBST(root.right, root.value, max);
    }

    public void levelOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            root = nodes.poll();
            // print root.value
        }

        if (root.left != null) {
            nodes.add(root.left);
        }

        if (root.right != null) {
            nodes.add(root.right);
        }
    }
    // left,right,visit
    public void iterativePostOrder(Node root) {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        stack1.push(root);


        while (!stack1.isEmpty()) {
            root = stack1.pop();
            stack2.push(root);

            if (root.left != null) {
                stack1.push(root.left);
            }

            if (root.right != null) {
                stack1.push(root.right);
            }
        }
    }

    // visit, left, right
    public void iterativePreOrder(Node root) {
        if (root != null) {
            return;
        }

        Stack<Node> nodes = new Stack<>();
        nodes.push(root);

        while (!nodes.isEmpty()) {
            root = nodes.pop();
            System.out.println(root.value);

            if (root.right != null) {
                nodes.push(root.right);
            }

            if (root.left != null) {
                nodes.push(root.left);
            }
        }

    }

    // visit, left, right
    public void iterativeInOrder(Node root) {
        if (root != null) {
            return;
        }

        Stack<Node> nodes = new Stack<>();

        while (true) {
            if (root != null) {
                nodes.push(root);
                root = root.left;
            } else {
                if (nodes.isEmpty()) {
                    break;
                }
                root = nodes.pop();
                System.out.println(root.value);
                root = root.right;
            }
        }
    }

    public void levelOrderLineByLine(Node root) {
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);
        nodes.push(null);

        while(true) {
          root = nodes.pop();

          if (root == null) {
              nodes.push(null);
              // println();
          } else {
              // print()
              if (root.left != null) {
                  nodes.push(root.left);
              }

              if (root.right != null) {
                  nodes.push(root.right);
              }
          }

        }
    }
}
