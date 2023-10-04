import java.util.Stack;
import java.util.Queue;
import java.util.Scanner;
import java.util.HashMap;

public class PIP {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println(infixToPostfix(input.nextLine()));
        //a+b*c+(d*e+f)*g

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

                result += checkPrecedence(input.charAt(i),s);

            } else {

                result += input.charAt(i);
                
            }

        }

        while(!s.isEmpty()){
            result += s.pop();
        }

        System.out.println(s);

        return result;

    }


    /**
     *
     * Takes an operator and manipulates it (by adding to output or stack) and returns when an operator should be added to the output
     *
     * @param oper
     * @param stack
     * @return 1 or multiple string characters
     */
    static String checkPrecedence(char oper, Stack stack) {

        //Hashmap for prescedence of operators
        HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();

        precedence.put('(',5);
        precedence.put('^',4);
        precedence.put('*',3);
        precedence.put('/',3);
        precedence.put('-',2);
        precedence.put('+',2);


        String out = "";
        //if there's an active open parenthesis
        if(oper == ')') {
            //if the operator is a closing paren, pop every operater until the opening paren into the output, then get rid of the paren
            while(!(stack.peek().equals('('))){

                out += stack.pop();

            }

            stack.pop();

        } else if(precedence.get(stack.peek()) >= precedence.get(oper)) { //otherwise if the next operator has more precedence than the top of the stack operator

            while(!stack.isEmpty() && precedence.get(stack.peek()) >= precedence.get(oper)) {

                //pop all the higher precedence operators into the output
                out += stack.pop();

            }

            stack.push(oper);


        } else if(precedence.get(stack.peek()) < precedence.get(oper)) {

            //otherwise, push the next operator onto the stack
            stack.push(oper);
        }


        return out;

    }


}