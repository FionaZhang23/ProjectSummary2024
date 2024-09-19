import java.util.Scanner;

public class Lab7 {

    // Evaluate operators and single numbers and return numerical result.
    public static Integer EvaluatePostFix(String exp) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(c - '0'); // Convert char to int
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                }
            }
        }

        return stack.pop();
    }

    // A utility function to return the precedence of a given operator
    static int precedence(Character ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

        }
        return -1;
    }
    // convert a string infix to postfix based on the Algorithm
    public static String InfixToPostfix(String exp) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If it's a digit, add it to the output
            if (Character.isDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.empty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.empty() && stack.peek() == '(') {
                    stack.pop(); // Pop the '('
                } else {
                    return "Mismatched parentheses";
                }
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.empty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            } else {
                return "Invalid character detected";
            }
        }

        while (!stack.empty()) {
            if (stack.peek() == '(') {
                return "Mismatched parentheses";
            }
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Infix Expression: ");
        String InFixExpression = input.nextLine();

        String PostFixExpression = InfixToPostfix((InFixExpression));
        System.out.println(PostFixExpression);

        Integer result = EvaluatePostFix(PostFixExpression);
        System.out.println(result);

    }
}
