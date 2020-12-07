// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #3
// CSE214
// R.04 James Finn

public class CodeBlock {
    public static final String[] BLOCK_TYPES = {"def","for","while","if","else","elif"};
    public static final int DEF = 0;
    public static final int FOR = 1;
    public static final int WHILE = 2;
    public static final int IF = 3;
    public static final int ELIF = 4;
    public static final int ELSE = 5;
    private String name;
    private Complexity blockComplexity;
    private Complexity highestSubComplexity;
    private String loopVariable;

    /**
     *
     * @param b
     * The block complexity.
     * @param n
     * The name of the block complexity.
     */
    public CodeBlock(Complexity b, String n) {
        setBlockComplexity(b);
        highestSubComplexity = new Complexity(0,0);
        setName(n);
        loopVariable = null;
    }

    /**
     *
     * @return
     * the name of the block
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param n
     * the name of the block.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     *
     * @return
     * the complexity of the block.
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /**
     *
     * @param b
     * The complexity of the current block.
     */
    public void setBlockComplexity(Complexity b) {
        blockComplexity = b;
    }

    /**
     *
     * @return
     * the highest sub complexity of the CodeBlock
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /**
     *
     * @param h
     * The complexity of the highestSubComplexity set as an object.
     */
    public void setHighestSubComplexity(Complexity h) {
        highestSubComplexity = h;
    }

    /**
     *
     * @return
     * the loop variable of the CodeBlock.
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /**
     *
     * @param l
     * the variable used in the loop.
     */
    public void setLoopVariable(String l) {
        loopVariable = l;
    }
}