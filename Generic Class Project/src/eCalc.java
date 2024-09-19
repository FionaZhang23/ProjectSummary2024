import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class eCalc extends JFrame implements ActionListener {
    // create a frame
    static JFrame f;

    // create labels
    static JLabel infixLabel, postfixLabel, resultLabel;

    // create textfields
    static JTextField infix, postfix, result;

    // store operator and operands
    String infixExp, postfixExp, resultExp;

    // default constructor
    eCalc() {
        infixExp = postfixExp = resultExp = "";
    }

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
    static int precedence(Character ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

        }
        return -1;
    }

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

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if ((s.charAt(0) >= '0' && s.charAt(0) <= '9')) { // if the value is a number
            infixExp += s;
            infix.setText(infixExp);
        } else if (s.charAt(0) == '+' || s.charAt(0) == '-' || s.charAt(0) == '*' || s.charAt(0) == '/') { // operator
            // make sure not two operators in a row
            if (infixExp.charAt(infixExp.length() - 1) == '+' || infixExp.charAt(infixExp.length() - 1) == '-' ||
                    infixExp.charAt(infixExp.length() - 1) == '-' || infixExp.charAt(infixExp.length() - 1) == '/') {
                infixExp = infixExp.substring(0, infixExp.length() - 1); // remove last character for replace
            }
            infixExp += s;
            infix.setText(infixExp);
        } else if (s.charAt(0) == '(' || s.charAt(0) == ')') { // parentheses
            infixExp += s;
            infix.setText(infixExp);
        } else if (s.charAt(0) == 'C') { // clear everything
            // clear everything
            infixExp = postfixExp = resultExp = "";

            infix.setText(infixExp);
            postfix.setText(postfixExp);
            result.setText(resultExp);
        } else if (s.charAt(0) == '=') { // evaluate expression
            // set the textfields with the result of the calculations
            infix.setText(infixExp);

            // convert infix expression to postfix expression and show
            postfixExp = InfixToPostfix(infixExp);
            postfix.setText(postfixExp);

            // evalutate postfix expression and show result
            resultExp = Integer.toString(EvaluatePostFix(postfixExp));
            result.setText(resultExp);

        } else { // all other cases (for debugging)
            infix.setText("Error");
            postfix.setText("Error");
            result.setText("Error");
        }
    }

    // main function
    public static void main(String args[]) {
        // create a frame
        f = new JFrame("ePhone eCalc (TM)");

        try {
            // set look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

        // create a object of class
        eCalc c = new eCalc();

        // create label to display text
        infixLabel =   new JLabel("Infix:    ");
        infixLabel.setForeground(Color.yellow);
        postfixLabel = new JLabel("Postfix: ");
        postfixLabel.setForeground(Color.yellow);
        resultLabel =  new JLabel("Result:  ");
        resultLabel.setForeground(Color.yellow);

        // create textfields
        infix = new JTextField(20);
        postfix = new JTextField(20);
        result = new JTextField(20);

        // set the textfields to non editable
        infix.setEditable(false);
        postfix.setEditable(false);
        result.setEditable(false);

        // create number buttons and some operators
        JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, lp, rp, bcl, beq;

        // create number buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");

        // equals button
        beq = new JButton("=");

        // create operator buttons
        ba = new JButton("+");
        bs = new JButton("-");
        bd = new JButton("/");
        bm = new JButton("*");
        lp = new JButton("(");
        rp = new JButton(")");

        // clear button
        bcl = new JButton("C");

        // create a panel
        JPanel p = new JPanel();

        // add action listeners
        bm.addActionListener(c);
        bd.addActionListener(c);
        bs.addActionListener(c);
        ba.addActionListener(c);
        b9.addActionListener(c);
        b8.addActionListener(c);
        b7.addActionListener(c);
        b6.addActionListener(c);
        b5.addActionListener(c);
        b4.addActionListener(c);
        b3.addActionListener(c);
        b2.addActionListener(c);
        b1.addActionListener(c);
        b0.addActionListener(c);
        bcl.addActionListener(c);
        beq.addActionListener(c);
        lp.addActionListener(c);
        rp.addActionListener(c);

        // add elements to panel
        p.add(infixLabel);
        p.add(infix);
        p.add(postfixLabel);
        p.add(postfix);
        p.add(resultLabel);
        p.add(result);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(ba);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(bs);
        p.add(b7);
        p.add(b8);
        p.add(b9);
        p.add(bm);
        p.add(bcl);
        p.add(b0);
        p.add(beq);
        p.add(bd);
        p.add(lp);
        p.add(rp);

        // set Background of panel
        p.setBackground(Color.blue);

        // add panel to frame
        f.add(p);

        // set frame size and make it unresizable
        f.setSize(350, 290);
        f.setResizable(false);
        f.show();
    }
}
