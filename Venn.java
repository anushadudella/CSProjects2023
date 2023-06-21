import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        try {
            // Creates a new fileReader 
            FileReader file = new FileReader("venn.dat");
            BufferedReader br = new BufferedReader(file);
            // Reads the first line of the file
            // (this should give the # of test cases)
            int testCases = Integer.parseInt(br.readLine());
            int loop = testCases / 2;
            String line1 = "";
            String line2 = "";
            
            // if the testCases is 0
            // program should print out that there are no 
            // test cases found and exit out of the program
            if(testCases == 0){
                System.out.println("No test cases found.");
                System.exit(0);
            }
            
            // While looping through the # of test cases
            // reads 2 lines at a time 
            // adds each of the words to an arrayList
            // and calls the createSet method 
            // description of the createSet method is below
            for (int i = 0; i <= loop; i++) {
                line1 = br.readLine();
                line2 = br.readLine();
                String[] sArray1 = line1.split(" ");
                String[] sArray2 = line2.split(" ");
                ArrayList < String > strList = new ArrayList < String > (Arrays.asList(sArray1));
                ArrayList < String > strList2 = new ArrayList < String > (Arrays.asList(sArray2));

                createSet(strList, strList2);

            }
            // if the venn.dat is not created
            // prints that an exception has occured 
            // and exits the program
        } catch (Exception e) {
            System.out.println("Exception occured.");
            e.printStackTrace();
            System.exit(0);
        }

    }

    //@param: takes two arrayLists (a set, b set)
    //@return: void method so returns nothing
    public static void createSet(ArrayList < String > AList, ArrayList < String > BList) {

        //Creates the sets for Only A, Only B, and Both
        Set ASet = new TreeSet(AList);
        Set BSet = new TreeSet(BList);
        Set onlyBSet = new TreeSet();
        Set onlyASet = new TreeSet();
        Set abSet = new TreeSet();

        // Loops through set ASet
        // if bSet contains the same String
        // adds to the set with Both
        // else, it adds to onlyA
        for (Object e: ASet) {
            if (BSet.contains((String) e))
                abSet.add((String) e);
            else
                onlyASet.add((String) e);
        }

        // Loops through set BSet
        // if ASet contains the same String
        // adds to the set with Both
        // else, it adds to onlyB
        for (Object e: BSet) {
            if (ASet.contains((String) e))
                abSet.add((String) e);
            else
                onlyBSet.add((String) e);
        }
        
        //Prints ONLY set A
        //Prints ONLY set B
        //Prints both sets
        System.out.println("A: " + onlyASet);
        System.out.println("B: " + onlyBSet);
        System.out.println("BOTH: " + abSet);
        System.out.println();

    }

}
