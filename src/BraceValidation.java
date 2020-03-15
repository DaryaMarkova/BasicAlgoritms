import java.util.Stack;
import java.util.Scanner;

public class BraceValidation {
    public void runCheck() {
        Scanner scanner = new Scanner(System.in);
        String code = scanner.next();
        int position = check(code) + 1;
        System.out.println(position == 0 ? "Success" : position);
    }

    int check(String text) {
        Stack<Integer> stack = new Stack<>();
        int i = -1, index = -1;
        boolean ok = true;

        while (++i < text.length() && ok) {
            char current = text.charAt(i);

            if (isOpenBracket(current)) {
                stack.push(i);
            } else if (isCloseBracket(current)) {
                if (stack.isEmpty()) {
                    ok = false;
                } else {
                    char correspond = text.charAt(stack.pop());
                    ok = matches(correspond, current);
                }

            }
        }

        return (!ok) ? i - 1 : index < 0 && !stack.isEmpty() ? stack.pop() : index;
    }

    boolean matches(char openSymbol, char closeSymbol) {
        if (openSymbol == '(' && closeSymbol == ')')
            return true;
        if (openSymbol == '{' && closeSymbol == '}')
            return true;
        if (openSymbol == '[' && closeSymbol == ']')
            return true;

        return false;
    }

    boolean isOpenBracket(char symbol) {
        return symbol == '(' || symbol == '[' || symbol == '{';
    }
    boolean isCloseBracket(char symbol) {
        return symbol == ')' || symbol == ']' || symbol == '}';
    }
}
