import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Enter desired magic square side length: ");
        //int n = scan.nextInt();
        //variable n is the size of the magicSquare that is recieved from the user input
        int n=0;
       try      {
        n=scan.nextInt();
         // If square length is divisible by, those are doubly even squares. 
        // we call doubly even method in this case. 
        if (n % 4 == 0 && n >= 4)
            doublyEvenSquare(n);
        // If square length is odd, these are odd squares and we call odd magic squares method. 
        else if (n % 2 == 1 && n > 2)
            oddMagicSquare(n);
        //If square length is not odd, negative or not divisible by 4, it's a user error. 
        else if (n <= 0 || n % 2 == 0)
            System.out.print("Input number must be a positive odd number or a positive number divisible by four");
        
        
        
        } catch(InputMismatchException e) {
          System.out.print("Input number must be a positive odd number or a positive number divisible by four");
         }
        

       
       
    }

    //method prints the magic square with proper allignment
    //@param: takes int[][] magicSq as the resultant magic Square and prints it to the console
    public static void printMagicSquare(int[][] magicSq) {
        for (int[] i: magicSq) {
            for (int j: i) {
                if (j < 10)
                    System.out.print(" "); // for alignment
                if (j < 100)
                    System.out.print(" "); // for alignment
                if (j < 1000)
                    System.out.print(" "); // for alignment
                if (j < 10000)
                    System.out.print(" "); // for alignment
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //@param: the oddSquareLength is the length of the odd magic square
    //computes the resultant magic square for odd lengths
    public static void oddMagicSquare(int oddSquareLength) {
        int[][] arr = new int[oddSquareLength][oddSquareLength];
        // Put first number in first row and middle column
        int row = 0;
        int col = oddSquareLength / 2;
        int currentRow, currentCol;
        for (int i = 1; i <= oddSquareLength * oddSquareLength; i++) {
            arr[row][col] = i;
            // Initialize current row and column values. We'll need it when a row and column are filled.
            currentRow = row;
            currentCol = col;
            // Move diagonally to right (next column) and top (Previous row)
            row -= 1;
            col += 1;
            // If row is out of bounds, put the data at bottom row.
            if (row == -1)
                row = oddSquareLength - 1;
            //If column is out of bounds, put the data in left most column
            if (col == oddSquareLength)
                col = 0;
            //If data is filled, put the next value beneath current value. Here we use current values
            if (arr[row][col] != 0) {
                row = currentRow + 1;
                col = currentCol;
            }
        }

        // print the square with alignment.
        printMagicSquare(arr);
        //prints the sum of the first and second diagnoal in the magic square 
        printFirstandSecondDiagonalSums(arr, oddSquareLength);
        //prints the sum of the random column and random row in the magic square 
        printSumRowsandCols(arr, oddSquareLength);
    }


    //@param: takes the length of a doublyEven magicSquare
    //the squareLength is divisible by four
    //method computes the resultant magic sqaure for a squareLength that is doubly even
    public static void doublyEvenSquare(int squareLength) {
        int[][] arr = new int[squareLength][squareLength];

        //Let's fill square with values
        // Example for square with length: 4, arr[0][0] = 4*0+0+1, arr[1][0]=4*1+0+1
        for (int i = 0; i < squareLength; i++)
            for (int j = 0; j < squareLength; j++)
                arr[i][j] = squareLength * i + j + 1;
        // Top Left corner.
        // For square of length: 4, arr[0][0] = 4*4+1-1 = 16
        for (int i = 0; i < squareLength / 4; i++)
            for (int j = 0; j < squareLength / 4; j++)
                arr[i][j] = (squareLength * squareLength + 1) - arr[i][j];
        //Top Right corner, Top row = Square length/4, Right column starts at 3/4
        // Same formula applies for magic square cells
        for (int i = 0; i < squareLength / 4; i++)
            for (int j = 3 * (squareLength / 4); j < squareLength; j++)
                arr[i][j] = (squareLength * squareLength + 1) - arr[i][j];

        //Bottom (From 3/4), left corner(First 1/4th)
        for (int i = 3 * squareLength / 4; i < squareLength; i++)
            for (int j = 0; j < squareLength / 4; j++)
                arr[i][j] = (squareLength * squareLength + 1) - arr[i][j];

        //Bottom(3/4) Right(3/4) corner.
        for (int i = 3 * squareLength / 4; i < squareLength; i++)
            for (int j = 3 * squareLength / 4; j < squareLength; j++)
                arr[i][j] = (squareLength * squareLength + 1) - arr[i][j];

        // Center of the square (from 1/4 to 3/4)
        for (int i = squareLength / 4; i < 3 * squareLength / 4; i++)
            for (int j = squareLength / 4; j < 3 * squareLength / 4; j++)
                arr[i][j] = (squareLength * squareLength + 1) - arr[i][j];

        // print the square with alignment.
        System.out.println();
        //prints the magic square
        printMagicSquare(arr);
        //prints the first and second diagonals in the magicSquare
        printFirstandSecondDiagonalSums(arr, squareLength);
        System.out.println();
        //prints the sum of the random row and random column in the magicSquare
        printSumRowsandCols(arr, squareLength);
    }

    //@param: takes a magicSquare 2d array and finds the two diagonals int this matrix 2D array
    //@param: int squareLength initializes the length of the magic square
    //method prints and computes the sum of the two diagnoals in the magic square
    public static void printFirstandSecondDiagonalSums(int[][] matrix,
        int squareLength) {
        int first = 0;
        int second = 0;
        /* We need to iterate only once for diagonal
           For first diagonal, index values are same. 
           For second diagonal, row will be i, column will be lengh - i - 1
         */
        System.out.println("Diagonals");
        for (int i = 0; i < squareLength; i++) {
            if (i < squareLength - 1)
                System.out.print(matrix[i][i] + " + ");

            else
                System.out.print(matrix[i][i] + " = ");
            first += matrix[i][i];
        }
        System.out.println(first);

        for (int k = 0; k < squareLength; k++) {
            if (k < squareLength - 1)
                System.out.print(matrix[k][squareLength - k - 1] + " + ");
            else
                System.out.print(matrix[k][squareLength - k - 1] + " = ");
            second += matrix[k][squareLength - k - 1];
        }
        System.out.println(second);
    }

    //@param: int[][] matr is the magicSquare for which the sum of rows and cols is computed
    //@param: int sideLength is the length od the magic square
    //method computes and prints the sum of random rows and random cols
    public static void printSumRowsandCols(int matr[][], int sideLength) {
        int randRow = (int)(Math.random() * sideLength - 1);
        int randCol = (int)(Math.random() * sideLength - 1);
        int rowSum = 0;
        int colSum = 0;
        System.out.println("Row " + randRow);
        for (int col = 0; col < sideLength; col++) {
            if (col < sideLength - 1)
                System.out.print(matr[randRow][col] + " + ");
            else
                System.out.print(matr[randRow][col] + " = ");
            rowSum += matr[randRow][col];
        }
        System.out.println(rowSum);
        System.out.println("Column " + randCol);
        for (int row = 0; row < sideLength; row++) {
            if (row < sideLength - 1)
                System.out.print(matr[row][randCol] + " + ");
            else
                System.out.print(matr[row][randCol] + " = ");
            colSum += matr[row][randCol];

        }
        System.out.println(colSum);
        System.out.println();
    }

}
