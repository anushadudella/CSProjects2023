import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main
{
    private  ArrayList<String> lettersGuessed = new ArrayList<String>();
    private  ArrayList<String> wordsList= new ArrayList<String>();
    private  ArrayList<String> lettersFound = new ArrayList<String>();
    private int correctGuesses = 0;
    private boolean guessed = false;
    
    public static void main (String[] args){
        Main wordle = new Main();
        wordle.playGame();
    }
    
    // Picks a random word from the word list
    // asks a user for a letter
    // @param: does not take any parameters
    public void playGame() {
        initializeWordList();
        Scanner input = new Scanner(System.in);
        int pos=(int)(Math.random()* wordsList.size());
        String word = wordsList.get(pos);

        initiliazeLettersFound(word);

        // Starting the for loop for the amount of original guesses (9 max).
        guessTheWord(input, word);

        // If user is unable to guess the word
        // print that they have not guessed the word
        // and what the word actually was
        if(!guessed){
            System.out.println("Oh no, you did not guess the word.");
            System.out.println("The actual word was: " + word);
        }
    }
   
    // @param: method takes in what the user inputted
    // and the random word that was generated from playGame()
    // this method does not return anything
    // instead, it prints whether the user has guessed or
    // has not guessed the word
    public void guessTheWord(Scanner input, String word) {
        for (int g = 0; g < 9; g++) {
            // Asks user to guess a letter.
            System.out.println("Please guess a letter");
            printLettersFound();
            String letter = input.nextLine().toLowerCase();

            // Checks if the character is in the word.
            // Initializing number of tries to guess the word.
            // Checks if it is equal or not to the position in the words.
            if (beenUsed(letter) == false) {
                for (int i = 0; i < word.length(); i++) {
                    if (letter.equals(word.substring(i, i + 1))){
                        lettersFound.set(i, letter);
                        correctGuesses++;
                    }
                }
                // If the user guesses 5 correct guesses
                // print that they have guessed the word
                lettersGuessed.add(letter);
                if (correctGuesses == word.length()) {
                    g = 9;
                    guessed = true;
                    System.out.println(word);
                    System.out.println("You guessed the word!");
                    break;
                }
            } else {
                System.out.println("You already guessed that letter.");
            }
        }
    }

    // Reading the file line by line
    // and initializes the word list by adding lines from list
    // for user
    // @param: this method does not take any paramters
    // since this is a void method, it does not return anything
    // instead, it reads the file if wordleList is imported
    // and adds words to the list if the list is not found 
    // prints that the list is not found as well
    private void initializeWordList(){
        try{
        Scanner scan = new Scanner(new FileReader("valid-wordle-words.txt"));
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            wordsList.add(line);
         }
        } catch(FileNotFoundException e){
            System.out.println("List not found");
            wordsList.add("water");
            wordsList.add("tranquility");
            wordsList.add("globe");
            wordsList.add("moon");
            wordsList.add("cow");
        }
    }


    // @param: this method does not take anything parameters
    // it does not print or return anything
    // this method initializes the letterFound arraylist to
    // all underscores at the beginning when the user guesses
    public void initiliazeLettersFound(String word){
        for(int numUnderScores = 0; numUnderScores < word.length(); numUnderScores++){
            lettersFound.add("_");
        }
    }

    // Prints the letters the user guessed correctly
    // in replace of the underscores 
    // @param: this method does not take anything parameters
    // since this is a void method, it does not return anything
    // instead, it prints out the letters guessed by the user
    private void printLettersFound(){
        for(int i = 0; i < lettersFound.size(); i++){
            System.out.print(lettersFound.get(i) + " ");
        }

        System.out.println("");
        printLettersGuessedList(lettersGuessed);
    }

    // BeenUsed method checks if the letter has already been guessed by the user.
    // @param: if let (string passed into method) equals to a string that has already 
    // been used, return true, else return false
    public boolean beenUsed(String let){
        for(int r= 0; r < lettersGuessed.size(); r++){
            if(let.equals(lettersGuessed.get(r))){
                return true;
            }
        }
        return false;
    }
    
    // @param: this method takes in an arraylist of type String
    // since it is a void method, it does not return anything
    // it only prints the letters that are guessed so far
    // (the letterGuess array)
    // Prints out the letters guessed by the user 
    // for every time the user guesses a letter
    private void printLettersGuessedList(ArrayList<String> aList){
        ArrayList<String> letterGuess = new ArrayList<String>();
        for(int j = 0; j < aList.size(); j++){
            letterGuess.add(aList.get(j));
        }
        System.out.print("Letters guessed so far: " + letterGuess);
        System.out.println(" ");
    }
}
