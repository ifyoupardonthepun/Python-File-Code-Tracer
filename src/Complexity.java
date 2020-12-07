// Shrey Shah
// ID: 112693183
// Shrey.Shah@stonybrook.edu
// Homework #3
// CSE214
// R.04 James Finn

public class Complexity {
    private int nPower;
    private int logPower;

    /**
     *
     * @param nPow
     * The power of n of the complexity.
     * @param logPow
     * The power of log of the complexity.
     */
    public Complexity(int nPow, int logPow){
        nPower = nPow;
        logPower = logPow;
    }

    /**
     *
     * @return
     *  the n Power of the complexity
     */
    public int getnPower(){
        return nPower;
    }

    /**
     * sets the nPower of the Complexity.
     *
     * @param n
     * The new nPower of the complexity.
     */
    public void setnPower(int n){
        this.nPower = n;
    }

    /**
     *
     * @return
     * the log Power of the complexity.
     */
    public int getLogPower(){
        return logPower;
    }

    /**
     * sets the logPower of the Complexity.
     *
     * @param l
     *  The new logPower of the complexity.
     */
    public void setLogPower(int l){
        this.logPower = l;
    }

    /**
     * prints human-readable Big-Oh notation
     *
     * @return
     * the string representation of the Big-O notation.
     */
    public String toString() {
        if(nPower == 0 && logPower == 0) {
            return "O(1)";
        }
        else if(nPower == 0) {
            return String.format("O(log(n)^%s)", logPower);
        }
        else if(logPower == 0) {
            return String.format("O(n^%s)", nPower);
        }
        return String.format("O(n^%s * log(n)^%s)", nPower, logPower);
    }
}