import java.util.Stack;
import java.util.Scanner;
import java.util.HashMap;

public class PIP {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println(infixtoPostfix(input.nextLine()));

    }

    static String evaluatePostfix(String input) {

        return "";

    }

    static String infixtoPostfix(String input) {

        Stack s = new Stack();
        String result = "";

        for (int i = 0; i < input.length(); i++) {

            if (s.empty() && (input.charAt(i) == '+'
                    || input.charAt(i) == '-'
                    || input.charAt(i) == '*'
                    || input.charAt(i) == '/'
                    || input.charAt(i) == '^')) {

                s.push(input.charAt(i));

            } else if (input.charAt(i) == '+'
                    || input.charAt(i) == '-'
                    || input.charAt(i) == '*'
                    || input.charAt(i) == '/'
                    || input.charAt(i) == '^'){

                result+=checkPrecedence(input.charAt(i),s,result);

            } else {

                result += input.charAt(i);

            }

        }

        return result;

    }


    static char checkPrecedence(char oper, Stack stack, String output) {

        HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();

        precedence.put('^',1);
        precedence.put('*',2);
        precedence.put('/',2);
        precedence.put('-',3);
        precedence.put('+',3);

        while(!stack.empty()) {
            if (precedence.get(oper) > precedence.get(stack.peek())) {
                stack.push(oper);
            } else {
                break;
            }

        }

        return (char) stack.pop();

    }

}