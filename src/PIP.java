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

        boolean paren = false;
        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == '(') {

                paren = true;
                break;

            }

        }

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

            if (s.isEmpty() && b) {

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

            //while the stack of operators isn't empty (which it shouldn't be because of the first if statement in main) and the input (next checked) operator's precedence is higher than the top of the stack,
            if(oper == ')') {

                while(!(stack.peek().equals('('))){

                    out += stack.pop();

                }

                stack.pop();

            }
            //if the next operator has less precedence than the top of the stack operator
            else if(precedence.get(oper) < precedence.get(stack.peek())) {

                out += oper;

                while(!stack.isEmpty() && precedence.get(oper) < precedence.get(stack.peek())) {

                    out += stack.pop();

                }

            } else if(precedence.get(oper) >= precedence.get(stack.peek())) {

                stack.push(oper);

            }

        } else {

            if (precedence.get(oper) < precedence.get(stack.peek())) {

                out += oper;

                while (!stack.isEmpty() && precedence.get(oper) < precedence.get(stack.peek())) {

                    stack.push(oper);

                }

            } else if (precedence.get(oper) >= precedence.get(stack.peek())) {

                out += stack.pop();

            }

        }


        return out;

    }


}