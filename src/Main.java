import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        int n = scanner.nextInt();

        int value;

        while (i++ < n) {
            String command = scanner.next();

            switch (command) {
                case "push":
                    value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    stack.pop();
                    break;
                case "max":
                    if (!stack.isEmpty())
                        System.out.println(stack.max());
                    break;
            }

        }
    }
}
