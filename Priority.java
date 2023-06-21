import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;

public class Main {
//Anusha Dudella and Sahiti Chintarlapalli
    public static void main(String[] args) {

        // Creates a priorityQueue of Words objects 
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a file name: ");
        String s = input.nextLine();
        PriorityQueue < Words > pq = new PriorityQueue < Words > ();

        try {
            // Creates a new fileReader and BufferedReader 
            // to read files the user inputs
            FileReader fr = new FileReader(s);
            BufferedReader br = new BufferedReader(fr);

            // if BufferedReader is not null
            // Makes an array and splits the file being read
            // into an array split by spaces
            if (br != null) {
                String line = br.readLine();
                // if the file is Empty
                // print out a message to the user
                // exit the program
                if (line == null) {
                    System.out.println("Empty file encountered.");
                    System.exit(0);
                } else {
                    String[] result = line.split(" ");
                    for (int i = 0; i < result.length; i++) {
                        // loops through the result array and adds 
                        // each of the new words into the priorityqueue
                        pq.add(new Words(result[i]));
                    }
                }

            }
        }
        // If file is invalid/not created
        // asks the user to enter a valid file
        catch (IOException e) {
            System.out.println("Please enter a valid file.");
        }

        // If the first element of the queue is not null
        // keep fetching the elements until the queue is empty
        while (pq.peek() != null)
            System.out.print(pq.poll().getWord() + " ");

    }
}

class Words implements Comparable < Words > {

    private String string1;

    Words(String string1) {
        this.string1 = string1;
    }

    // @param: does not take any parameters
    // @return: returns a word
    public String getWord() {
        return string1;
    }

    // @param: does not take any parameters
    // @return: returns the length of a string
    public int getLengthofString() {
        return string1.length();
    }

    // @param: takes a Words objects
    // @return: returns an int
    public int compareTo(Words a) {

        //if the object is shorter than a parameter object
        // returns -1 (a negative integer)
        if (this.getLengthofString() < a.getLengthofString()) {
            return -1;
        }
        // if the object is longer than a parameter object
        // returns 1 (a positive integer)
        else if (this.getLengthofString() > a.getLengthofString()) {
            return 1;
        }
        // if the object is equal in length to the paramter object
        // checks for the following conditions in order to order 
        // it lexographically
        else if (this.getLengthofString() == a.getLengthofString()) {
            // loops through the word 
            for (int i = 0; i < getWord().length(); i++) {
                // if the word at position of i is greater than the position of 
                // i at the parameter object
                // returns 1 (a positive integer)
                if (this.getWord().charAt(i) > a.getWord().charAt(i)) {
                    return 1;
                }
                // if the word at position of i is less than the position of 
                // i at the parameter object
                // returns -1 (a negative integer)
                else if (this.getWord().charAt(i) < a.getWord().charAt(i)) {
                    return -1;
                }
                // if the word at position of i is equal to the position of 
                // i at the parameter object
                // continues till the end of the word
                else if (this.getWord().charAt(i) == a.getWord().charAt(i)) {
                    continue;
                }
            }
        }
        // once it loops through the whole word 
        // and still cannot find any differing letters
        // returns 0
        return 0;
    }

}
