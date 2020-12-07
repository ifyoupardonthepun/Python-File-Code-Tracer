// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #3
// CSE214
// R.04 James Finn

import java.util.*;
import java.io.*;

public class BlockStack {
    public static final int SPACE_COUNT = 4;

    /**
     * Searches for a keyword in a specific String
     *
     * @param line
     * the String name in which keyword is searched for
     * @return
     * returns a keyword if it is found
     */
    public static String keyWordSearch(String line) {
        String[] l = line.trim().split(" ");
        for (int i = 0; i < 6; i++) {
            if (Objects.equals(l[0], CodeBlock.BLOCK_TYPES[i])) {
                return l[0];
            }
        }
        return null;
    }
    /**
     * Opens the indicated file and traces through the code of the Python function contained within the file,
     * returning the Big-Oh order of complexity of the function. During operation, the stack trace should be printed to the
     * console as code blocks are pushed to/popped from the stack.
     *
     * @param filename
     *  The name of the python file to calculate the Big-Oh notation for.
     * @return
     * a Complexity object representing the total order of complexity of the Python code contained within the file.
     * @throws FileNotFoundException
     * If the user input a file that is not in a specified directory.
     */

    public static Complexity traceFile(String filename) throws FileNotFoundException {
        Stack<CodeBlock> stack = new Stack<>();
        Scanner stdin = new Scanner(new File(filename));
        String count = new String("1");
        String line;
        int indent;
        while (stdin.hasNextLine()) {
            line = stdin.nextLine();
            if(line.trim().length() != 0 && line.trim().charAt(0) != '#') {
                indent = line.indexOf(line.trim())/SPACE_COUNT;
                while (indent < stack.size()) {
                    if (indent == 0) {
                        stdin.close();
                        return stack.peek().getHighestSubComplexity();
                    }
                    else {
                        CodeBlock oldTop = stack.pop();
                        Complexity oldTopComplexity = new Complexity(oldTop.getBlockComplexity().getnPower() + oldTop.getHighestSubComplexity().getnPower(),
                                oldTop.getBlockComplexity().getLogPower() + oldTop.getHighestSubComplexity().getLogPower());
                        System.out.print("Leaving block " + oldTop.getName());
                        if(oldTopComplexity.getnPower() > stack.peek().getHighestSubComplexity().getnPower()) {
                            stack.peek().setHighestSubComplexity(oldTopComplexity);
                            System.out.print(", updating block " + stack.peek().getName() + ":");
                            System.out.println();
                        } else if(oldTopComplexity.getnPower() == stack.peek().getHighestSubComplexity().getnPower()) {
                            stack.peek().setHighestSubComplexity(oldTopComplexity);
                            System.out.print(", updating block " + stack.peek().getName() +":");
                            System.out.println();
                        } else if (oldTopComplexity.getLogPower() > stack.peek().getHighestSubComplexity().getLogPower()) {
                            stack.peek().setHighestSubComplexity(oldTopComplexity);
                            System.out.print(", updating block " + stack.peek().getName() +":");
                        } else {
                            System.out.printf(", nothing to update. %n");
                        }
                        System.out.printf("       %-15s%-20s%-30s%n%n", "BLOCK "+ stack.peek().getName()+":"
                                , "block complexity = "+ stack.peek().getBlockComplexity().toString() + "       "
                                , "highest sub-complexity = "+ stack.peek().getHighestSubComplexity().toString());
                    }
                }
                String [] l = line.trim().split(" ");
                if (keyWordSearch(line) != null) {
                    String keyWord = keyWordSearch(line);
                    String name;
                    if (indent == 0)
                        name = "1";
                    else {
                        if (indent == count.split("\\\\").length - 1) {
                            int j = Integer.parseInt(count);
                            name = count.substring(0, 0) +
                                    (j + 1);
                        }
                        else
                            name = stack.peek().getName() + ".1";
                    }
                    assert keyWord != null;
                    if (keyWord.equals("for")) {
                        if (line.contains("N:") && !line.contains("log_N:"))
                            stack.push(new CodeBlock(new Complexity(1,0), name));
                        else
                            stack.push(new CodeBlock(new Complexity(0,1), name));
                    }
                    else if (keyWord.equals("while")) {
                        stack.push(new CodeBlock(new Complexity(0,0), name));
                        stack.peek().setLoopVariable(l[1]);
                    }
                    else
                        stack.push(new CodeBlock(new Complexity(0,0), name));
                    System.out.printf("Entering block " + name + " '" + keyWord + "': %n");
                    System.out.printf("       BLOCK %s:       block complexity = " + stack.peek().getBlockComplexity()
                            +"      highest sub-complexity = " + stack.peek().getHighestSubComplexity() + "\n",stack.peek().getName());
                    System.out.println();
                }
                else if (stack.peek().getLoopVariable() != null && line.trim().split(" ")[0].equals(stack.peek().getLoopVariable())) {
                    if (line.equals("/="))
                        stack.peek().setBlockComplexity(new Complexity(0,1));
                    else {
                        stack.peek().setBlockComplexity(new Complexity(1, 0));
                    }
                    System.out.println("Found update statement, updating block " + stack.peek().getName() + ":");
                    System.out.printf("       BLOCK %s:       block complexity = " + stack.peek().getBlockComplexity().toString()
                            +"       highest sub-complexity = " + stack.peek().getHighestSubComplexity().toString() +
                            "%n",stack.peek().getName());
                    System.out.println();
                }

            }
        }
        while (stack.size() > 1) {
            CodeBlock oldTop = stack.pop();
            Complexity oldTopComplexity = new Complexity(oldTop.getBlockComplexity().getnPower() + oldTop.getHighestSubComplexity().getnPower(),
                    oldTop.getBlockComplexity().getLogPower() + oldTop.getHighestSubComplexity().getLogPower());
            System.out.print("Leaving block " + oldTop.getName());
            if (oldTopComplexity.getnPower() > stack.peek().getHighestSubComplexity().getnPower()) {
                stack.peek().setHighestSubComplexity(oldTopComplexity);
                System.out.print(", updating block " + stack.peek().getName() +":");
            } else {
                System.out.printf("Leaving block " + oldTop.getName() + ", nothing to update. %n"
                        + "       %-15s%-20s%-40s%n%n", "BLOCK " + stack.peek().getName() + ":", "block complexity = "
                        + stack.peek().getBlockComplexity().toString() + "        ", "highest sub-complexity = "
                        + stack.peek().getHighestSubComplexity().toString());
            }
        }
        stdin.close();
        return stack.pop().getHighestSubComplexity();
    }
}