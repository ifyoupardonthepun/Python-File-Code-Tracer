// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #3
// CSE214
// R.04 James Finn

import java.util.*;
import java.io.*;

public class PythonTracer {

    // Beginning of the program
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Please enter a file name (or 'quit' to quit): ");
        String filename = stdin.nextLine();
        while (!filename.equalsIgnoreCase("QUIT")) {
            try {
                System.out.printf("Overall complexity of " + filename + ": " + BlockStack.traceFile(filename) + "%n %n");
            } catch (FileNotFoundException e) {
                System.out.println("That file cannot be found.");
            }
            System.out.print("Please enter a file name (or 'quit' to quit):");
            filename = stdin.nextLine();
        }
        System.out.print("Program terminating successfully...");
        stdin.close();
    }
}