import java.util.Stack;
import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
	    // String input which is subject to change
	    System.out.println("Please input a string: ");
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		String bracket = "(<[{}]>)";
		String closing = ")>}]";
        String opening = "(<{[";
        //Creates the String stack 
		Stack <String> myStack = new Stack <String>();
		if(input.length() > 1){
		for(int i = 0; i < input.length(); i++){
		    //checks if the input contains a special character "(<[{}]>)"
		    if(bracket.contains(input.substring(i,i+1))){
		        //checks if the input contains an open bracket
		        //if the input does contain an open bracket
		        //push the substring on the stack
		      if(opening.contains(input.substring(i,i+1))){
		         myStack.push(input.substring(i,i+1));
		         }
		         //if the input contains a closed bracket
		         // make a string pair with the popped substring (whatever was pushed earlier)
		         // as well as the current input substring
		        else if(closing.contains(input.substring(i,i+1))){
		            //if the stack size is = to 0
		            // prints invalid
		            if(myStack.size() == 0){
		              System.out.println("Invalid");
		              System.exit(0); 
		            }
		            else{
		          String popped = myStack.pop();
		          String pair = popped + input.substring(i,i+1);
		            
		          if(isValidPairBracket(pair)){
		              continue;
		          }
		      
		          //if the pair is invalid 
		          // (does not contain a proper open and close bracket)
		          // print out invalid and exit out of the loop
		          else{
		              System.out.println("Invalid");
		              System.exit(0);
		          }
		            }
		        }
		    } // if else 
		}// for loop ending
		if(myStack.size() != 0){
		   System.out.println("Invalid");
	       System.exit(0); 
		}
		else{
		   System.out.println("Valid");
	       System.exit(0); 
		}
      }
	else{
	   System.out.println("Invalid");
	   System.exit(0);
    	}
	}
    //@param: takes the string pair 
    //@return: returns either true or false
     //check if the created pair (closed and open) equals a pair with an
     //open or closed bracket (pair1, pair2, pair3, pair4) 
     //if the pair does equal one of the pairs, return true else return false
    public static boolean isValidPairBracket(String pair){
        String pair1 = "[]", pair2 = "()", pair3 = "<>", pair4 = "{}";
        if(pair.equals(pair1) || pair.equals(pair2) || pair.equals(pair3) || pair.equals(pair4)){
            return true;
    }
    return false;
    }
}
