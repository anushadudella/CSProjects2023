import java.io.*;
import java.util.Arrays.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Fill in the blanks!");
        String[] bitwiseOps = { "<<", "|",  "&", "^" };

        // Generates 2 operand and an operator and evaluates the
        // end result of operation, so we can present to the user
        while (true) {
            int randomNumber = (int)(Math.random() * 10);
            int randomArrayNum = (int)(Math.random() * 4);
            int randomNumber2 = (int)(Math.random() * 10);
            int blankPos = (int)(Math.random() * 4);
            int result = 0;
            String op = bitwiseOps[randomArrayNum];
            result = evaluate(op, randomNumber, randomNumber2);

            // Generates 4 different blank position in different problem formulation
            try {
                printTheQuestion(blankPos,randomNumber,op,randomNumber2,result);
            } catch (Exception e) {
                // checks if the input is valid and informs the user
                System.out.println("Invalid input. Please try again.");
            }
            System.out.println();

            // Checks if user wants another problem, if yes continues to generate one
            // else exits the program
            System.out.println("Do you want another problem? Type Y for yes and N for no");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("Y")) {
                System.out.println();
                continue;
            } else if(answer.equalsIgnoreCase("N")){
                System.exit(0);
            }
            else{
                continue;
            }
        }
    }

    //@param: takes a position, a random number, an operator,
    // another random number, & a result
    // "printthequestion()" presents the question based on the
    // randomly generated "blankPos" value that indicates which
    // of the operand1,2, operator or result is shown to be
    // filled by the end user
    private static void printTheQuestion(int blankPos, int randomNumber, String op, int randomNumber2, int result) {
        if (blankPos == 0) {
            System.out.println("_" + " " + op + " " + randomNumber2 + " == " + result);
            display(Integer.toString(randomNumber),blankPos,randomNumber,op, randomNumber2,result);
        }
        if (blankPos == 1) {
            System.out.println(randomNumber + " " + "_" + " " + randomNumber2 + " == " + result);
            display(op,blankPos,randomNumber,op, randomNumber2,result);
        }
        if (blankPos == 2) {
            System.out.println(randomNumber + " " + op+ " " + "_" + " == " + result);
            display(Integer.toString(randomNumber2),blankPos,randomNumber,op, randomNumber2,result);
        }
        if (blankPos == 3) {
            System.out.println(randomNumber + " " + op + " " + randomNumber2 + " == " + "_");
            display(Integer.toString(result),blankPos,randomNumber,op, randomNumber2,result);
        }
    }

    //@param: input operands/result/blankpos as a parameter
    // compares the random input generated with the
    // user input and prints whether it correct or not
    // It also reevaluates the operation with user inputted value
    // and checks if the answer "could" be correct or not
    public static void display(String blankValue,int blankPos, int rand1, String op, int rand2, int result ) {
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        int reevalresult = 0;
        if (userInput.equals(blankValue))
            System.out.println("That works! " + " (Possible Answer:" + blankValue + ")");
        else if (blankPos == 0) {
            reevalresult = evaluate(op, Integer.parseInt(userInput), rand2);
            if (reevalresult == result)
                System.out.println("That works! " + " (Possible Answer:" + userInput + ")");
            else
                System.out.println("That doesn't work. " + " (Possible Answer:" + blankValue + ")");
        } else if (blankPos == 1) {
            reevalresult = evaluate(userInput, rand1, rand2);
            if (reevalresult == result)
                System.out.println("That works! " + " (Possible Answer:" + userInput + ")");
            else
                System.out.println("That doesn't work. " + " (Possible Answer:" + blankValue + ")");
        } else if (blankPos == 2) {
            reevalresult = evaluate(op, rand1, Integer.parseInt(userInput));
            if (reevalresult == result)
                System.out.println("That works! " + " (Possible Answer:" + userInput + ")");
            else
                System.out.println("That doesn't work. " + " (Possible Answer:" + blankValue + ")");
        } else if (blankPos == 3) {
            reevalresult = evaluate(op, Integer.parseInt(blankValue), rand2);
            if (reevalresult == result)
                System.out.println("That works! " + " (Possible Answer:" + userInput + ")");
            else
                System.out.println("That doesn't work. " + " (Possible Answer:" + blankValue + ")");
        } else {
            System.out.println("That doesn't work. " + " (Possible Answer:" + blankValue + ")");
        }
    }

    // @param: takes two numbers and a operator
    // @return: returns the result of the operation
    // indicated by "op" done
    public static int evaluate(String op, int i, int j) {
        int result = 0;
        if (op.equals("^"))
            result = i ^ j;
        if (op.equals("|"))
            result = i | j;
        if (op.equals("&"))
            result = i & j;
        if (op.equals("<<"))
            result = i << j;
        return result;
    }

}
