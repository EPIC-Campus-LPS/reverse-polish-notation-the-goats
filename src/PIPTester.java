import java.util.Scanner;
import java.util.Scanner.*;
public class PIPTester {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Infix to Postfix: ");
        String inToPost = input.nextLine();
        System.out.println(PIP.infixToPostfix(inToPost));
        System.out.println("Evaluate Postfix: ");
        String postSolved = input.nextLine();
        System.out.println(PIP.evaluatePostfix(postSolved));
    }
}
