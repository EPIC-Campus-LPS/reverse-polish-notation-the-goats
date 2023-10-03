import java.util.Stack;
import java.util.Scanner;
import java.util.HashMap;

public class PIP {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println(infixToPostfix(input.nextLine()));

    }

    static String evaluatePostfix(String input) {



        return "";

    }

    /**
     *
     * Takes a mathematical expression in Infix notation, contained in a string, and outputs a string with that same expression in Postfix notation
     *
     * @param input
     * @return String postfix
     */
    static String infixToPostfix(String input) {

        boolean paren;

        Stack s = new Stack();
        String result = "";

        for (int i = 0; i < input.length(); i++) {

            //
            paren = false;
            for (int j = 0; j < input.length(); j++) {

                if (input.charAt(i) == '(') {

                    paren = true;
                    break;

                }

            }

            boolean b = (input.charAt(i) == '+'
                    || input.charAt(i) == '-'
                    || input.charAt(i) == '*'
                    || input.charAt(i) == '/'
                    || input.charAt(i) == '^'
                    || input.charAt(i) == '('
                    || input.charAt(i) == ')');

            if ((s.isEmpty() || s.peek().equals('(')) && b) {

                s.push(input.charAt(i));

            } else if (b){

                result += checkPrecedence(input.charAt(i),s,result, paren);

            } else {

                result += input.charAt(i);
                
            }

        }

        while(!s.isEmpty()){
            result += s.pop();
        }

        return result;

    }


    /**
     *
     * Takes an operator and manipulates it (by adding to output or stack) and returns when an operator should be added to the output
     *
     * @param oper
     * @param stack
     * @param output
     * @param paren
     * @return 1 or multiple string characters
     */
    static String checkPrecedence(char oper, Stack stack, String output, boolean paren) {

        //Hashmap for prescedence of operators
        HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();

        precedence.put('(',1);
        precedence.put('^',2);
        precedence.put('*',3);
        precedence.put('/',3);
        precedence.put('-',4);
        precedence.put('+',4);

        String out = "";
        if (paren) {
            //if there's an active open parenthesis
            if(oper == ')') {
                //if the operator is a closing paren, pop every operater until the opening paren into the output, then get rid of the paren
                while(!(stack.peek().equals('('))){

                    out += stack.pop();

                }

                stack.pop();

            }
            //otherwise if the next operator has less precedence than the top of the stack operator
            else if(precedence.get(oper) < precedence.get(stack.peek())) {

                // add the next input operator to the output
                out += oper;

                //while the stack of operators isn't empty and the input (next checked) operator's precedence is higher than the top of the stack,
                while(!stack.isEmpty() && precedence.get(oper) < precedence.get(stack.peek())) {

                    //pop all the lower precedence operators into the output
                    out += stack.pop();

                }

            } else if(precedence.get(oper) >= precedence.get(stack.peek())) {

                //otherwise, push the next operator onto the stack
                stack.push(oper);

            }

        } else {
            if(oper == ')') {
                //if the operator is a closing paren, pop every operater until the opening paren into the output, then get rid of the paren
                while (!(stack.isEmpty()) && !(stack.peek().equals('('))) {

                    out += stack.pop();

                }

                if (!stack.isEmpty()) stack.pop();

                //otherwise, there's not an active open parenthesis, so skip the check for closing parenthesis, and check if
                //if the next operator has less precedence than the top of the stack operator
            } else if (precedence.get(oper) < precedence.get(stack.peek())) {

                //push the next operator onto the stack
                out += stack.pop();

            } else if (precedence.get(oper) >= precedence.get(stack.peek())) {

                // otherwise, while the stack of operators isn't empty and the input (next checked) operator's precedence is higher than the top of the stack,
                // and pop all the lower precedence operators into the output
                out += oper;

                while (!stack.isEmpty() && precedence.get(oper) < precedence.get(stack.peek())) {

                    stack.push(oper);

                }

            }

        }


        return out;

    }


}