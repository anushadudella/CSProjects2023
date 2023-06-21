import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
// Do-while at line 34 and 52 
// Switch at line 27
// By: Anusha Dudella and Sahiti Chintarlapalli

public class Main {

    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static String newChars = "";
    private static FileWriter fw = null;
    private static Scanner input = null;

    public static void main(String[] args) throws IOException {

        // Asks the user if they want to encrypt or decrypt a file by letter E or D
        // Asks the user for a keyphrase in order to encode/decode the characters
        // Encodes or decodes the file
        System.out.print("[E]ncrypt or [D]ecrypt: ");
        input = new Scanner(System.in);
        String choice = input.nextLine();
        
        // Switch is incorporated here
        switch (choice.toUpperCase()) {
            case "E" :
                String fileToEncode = "";
                File encodeFile = null;

                // if the file to encode does not exist
                // keep asking the user to input a file
                do {
                    System.out.print("Please enter a valid file to encode: ");
                    fileToEncode = input.nextLine();
                    encodeFile = new File(fileToEncode);
                } while (!encodeFile.exists());
                System.out.println("Keyphrase: ");
                String keyPhrase = input.nextLine();
                newChars = generateNewChars(keyPhrase);
                encodeTheFile(fileToEncode);
                break;

            case "D" :
                // if the file to decode does not exist
                // keep asking the user to input a file
                String fileToDecode = "";
                File decodeFile = null;

                do {
                    System.out.print("Please enter a valid file to decode: ");
                    fileToDecode = input.nextLine();
                    decodeFile = new File(fileToDecode);
                } while (!decodeFile.exists());
                System.out.println("Keyphrase: ");
                keyPhrase = input.nextLine();
                newChars = generateNewChars(keyPhrase);
                decodeTheFile(fileToDecode);
                break;
        }
    }

    // @param: takes the fileToEncode as a string
    // @return: void
    // encoding of the given file and write the encoded text to a file
    // and also output it to the terminal
    private static void encodeTheFile(String fileToEncode) throws IOException {
        FileReader fr = new FileReader(fileToEncode); 
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        StringBuilder opString = new StringBuilder();

        // get the output encoded file name from the input file
        // this is for creating the new file for encoded text, uses the dot position to check for file extension
        // (this file is also used for decoding)
       String encodedFile = "";
       int dotPos = fileToEncode.indexOf(".");
       if(dotPos < 0)
        encodedFile = fileToEncode + ".ctxt";
       else
        encodedFile = fileToEncode.substring(0,dotPos) + ".ctxt";

        fw = new FileWriter(encodedFile);
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); ++i) {
                opString = encodeWithNewChars(line.substring(i, i + 1).toLowerCase(Locale.ROOT),opString);
            }
            fw.write("\n");
            opString.append("\n");
        }
        fw.close();
        fr.close();
        System.out.println("Ciphertext file created: " + encodedFile);
        System.out.println("\nOUTPUT");
        System.out.println(opString.toString());
    }

    // @param: takes the fileToDecode string as a parameter
    // @return: void
    // decoding of the given file and write the decoded text to a file
    // catch IO Exception: if the files is not there, throws an exception
    private static void decodeTheFile(String fileToDecode) throws IOException {
        FileReader fr = new FileReader(fileToDecode);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String decodedFile = "decodedFile.txt";
        fw = new FileWriter(decodedFile);
        StringBuilder opString = new StringBuilder();
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); ++i) {
               opString = decodeWithNewChars(line.substring(i, i + 1), opString);
            }
            fw.write("\n");
            opString.append("\n");
        }
        fw.close();
        fr.close();
        System.out.println("Decoded file created: " + decodedFile);
        System.out.println("\nOUTPUT");
        System.out.println(opString.toString());
    }

    // @param: takes in the String keyPhrase
    // @return: returns the new characters - encoded alphabets using the keyphrase
    // loops through the keyphrase to remove any duplicate characters
    // then checks if the non-duplicate keyphrase letters with the actual alphabet
    // in order to build the new characters 
    private static String generateNewChars(String keyPhrase) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < keyPhrase.length(); i++) {
            String chr = keyPhrase.substring(i, i + 1);
            if (!(s.toString().contains(chr))) {
                s.append(chr);
            }
        }
        ArrayList<String> letters = new ArrayList<String>();
        StringBuilder d = new StringBuilder();

        for (int i = 0; i < alphabet.length(); i++) {
            if (!(s.toString().contains(alphabet.substring(i, i + 1)))) {
                d.append(alphabet.substring(i, i + 1));
            }
        }
        // concatenates the non-duplicate keyphrase and the rest of the alphabet
        newChars = s.toString() + d.toString();
        return newChars;
    }

    // @param: takes string s (letter from textToEncode file), stringbuilder to build the encoded String
    // @return: returns the encoded string
    // if s is in the alphabet, it finds the position of s in alphabet
    // and the locates that character in that position in the new chars
    private static StringBuilder encodeWithNewChars(String s, StringBuilder sb) throws IOException{
        if (alphabet.contains(s)) {
            for (int i = 0; i < 26; i++) {
                if (alphabet.substring(i, i + 1).equals(s)) {
                        fw.write((newChars.substring(i, i + 1)));
                        sb.append(newChars.substring(i, i + 1));
                        break;
                    }
                 }
        } else {
            fw.write(s);
            sb.append(s);
        }
        return sb;
    }

    // @param: takes string s (letter from textToEncode file)
    // @return: returns the decoded string
    // if s is in the new chars, it finds the position of s in new chars
    // and the locates that character in that position in the alphabet
    // if s is an non-alphabet character, it does not replace it with anything
    private static StringBuilder decodeWithNewChars(String s, StringBuilder sb) throws IOException {
        if (newChars.contains(s)) {
            for (int i = 0; i < 26; i++) {
                if (newChars.substring(i, i + 1).equals(s)) {
                    fw.write((alphabet.substring(i, i + 1)));
                    sb.append(alphabet.substring(i, i + 1));
                    break;
                }
            }
        } else {
            fw.write(s);
            sb.append(s);
        }
        return sb;
    }

}
