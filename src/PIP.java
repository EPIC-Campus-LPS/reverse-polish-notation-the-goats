import java.util.Stack;
import java.util.HashMap;

/**
 * Class managing Postfix converting and solving
 *
 * @author Nathan Hoehndorf, Nicholas Un
 * @version 1.0, 9/25/23
 * @see Stack HashMap
 */
public class PIP {
    /**
     * Takes a Postfix expression and returns the answer
     *
     * @param input input Postfix String read from input
     * @return integer answer
     */
    public static int evaluatePostfix(String input) {

        input = input.replaceAll(" ", "");

        Stack s = new Stack();

        int countNum = 0;
        int var;
        char inC;
        boolean b;
        int ops = 0; int nums = 0;

        for(int i = 0; i < input.length(); i++){

            input.charAt(i);

            b = (input.charAt(i) == '+'
                    || input.charAt(i) == '-'
                    || input.charAt(i) == '*'
                    || input.charAt(i) == '/'
                    || input.charAt(i) == '^');

            if (b){
                ops += 1;
            }
            else if (!(input.charAt(i) == '(' || input.charAt(i) == ')')){
                nums += 1;
            }
        }

        if(!(ops+1 == nums)){
            throw new IllegalArgumentException("Invalid Postfix Exception");
        }

        for (int i = 0; i < input.length(); i++) {

            inC = input.charAt(i);

             b = (inC == '+'
                     || inC == '-'
                     || inC == '*'
                     || inC == '/');

            if (!b){
                countNum++;
                s.add(inC);
            }

            if (countNum >= 2 && b) {

                if (inC == '+') {

                    s.push(Integer.parseInt(String.valueOf(s.pop())) + Integer.parseInt(String.valueOf(s.pop())));

                } else if (inC == '-') {

                    var = Integer.parseInt(String.valueOf(s.pop()));

                    s.push(Integer.parseInt(String.valueOf(s.pop())) - var);

                } else if (inC == '*') {

                    s.push(Integer.parseInt(String.valueOf(s.pop())) * Integer.parseInt(String.valueOf(s.pop())));

                } else {

                    var = Integer.parseInt(String.valueOf(s.pop()));

                    s.push(Integer.parseInt(String.valueOf(s.pop())) / var);

                }

                countNum--;

            }else if(b){
                countNum = 0;
            }

        }


        var = (int)s.pop();

        return var;

    }

    /**
     *
     * Takes a mathematical expression in Infix notation, contained in a string, and outputs a string with that same expression in Postfix notation
     *
     * @param input
     * @return String postfix
     */
    public static String infixToPostfix(String input) {
        input = input.replaceAll(" ", "");
        Stack s = new Stack();
        String result = "";
        int ops = 0;
        int nums = 0;

        int open = 0;
        int close = 0;

        for(int i = 0; i < input.length(); i++){

            if (input.charAt(i) == '(') open++;
            if (input.charAt(i) == ')') close++;

            input.charAt(i);

            boolean b = (input.charAt(i) == '+'
                    || input.charAt(i) == '-'
                    || input.charAt(i) == '*'
                    || input.charAt(i) == '/'
                    || input.charAt(i) == '^');

            if (b){
                ops += 1;
            }
            else if (!(input.charAt(i) == '(' || input.charAt(i) == ')')){
                nums += 1;
            }
        }

        if (open != close) {
            throw new IllegalArgumentException("Invalid Infix Exception");
        }

        if(!(ops+1 == nums)){
            throw new IllegalArgumentException("Invalid Infix Exception");
        }

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

        result = result.replaceAll(" ","");
        String withSpaces = "";

        for(int i = 0; i < result.length(); i++){
            withSpaces += (result.substring(i,i+1) + " ");
        }

        return withSpaces;

    }


    /**
     *
     * Takes an operator and manipulates it (by adding to output or stack) and returns when an operator should be added to the output
     *
     * @param oper
     * @param stack
     * @return 1 or multiple string characters
     */
    public static String checkPrecedence(char oper, Stack stack) {

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

            while(!stack.isEmpty() && precedence.get(stack.peek()) >= precedence.get(oper) && !stack.peek().equals('(')) {

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