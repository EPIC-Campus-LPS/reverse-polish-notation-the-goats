import java.util.Stack;
import java.util.Scanner;

public class PIP {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

    }

    static String evaluatePostfix(String input) {

        return "";

    }

    static String infixtoPostfix(String input) {

        Stack s = new Stack();
        String result = "";

        for (int i = 0; i < input.length(); i++) {

            if (s.empty()) {

                if (input.charAt(i) == '+') {

                    s.push(input.charAt(i));

                } else if (input.charAt(i) == '-') {

                    s.push(input.charAt(i));

                } else if (input.charAt(i) == '*') {

                    s.push(input.charAt(i));

                } else if (input.charAt(i) == '/') {

                    s.push(input.charAt(i));

                } else if (input.charAt(i) == '^') {

                    s.push(input.charAt(i));

                } else {

                    result += input.charAt(i);

                }

            } else {

                if (input.charAt(i) == '+') {



                } else if (input.charAt(i) == '-') {



                } else if (input.charAt(i) == '*') {



                } else if (input.charAt(i) == '/') {



                } else if (input.charAt(i) == '^') {



                } else {

                    result += input.charAt(i);

                }

            }

        }

        return "";

    }

    static void checkPrecedence(char oper) {



    }

}