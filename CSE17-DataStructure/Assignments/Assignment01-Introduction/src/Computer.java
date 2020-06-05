
// -------------------------------------------------------------------------
/**
 *  This class is called Computer, and it has three fields.
 *  This class has a constructor and takes processor,
 *  numCores and processorSpeed as inputs.
 *
 *  @author Yang Yi
 *  @version Aug 28, 2015
 */

public class Computer
{
    private String processor;
    private int numCores;
    private double processorSpeed;

    // ----------------------------------------------------------
    /**
     * Create constructor.
     * @param processor is a String type
     * @param numCores is an integer type
     * @param processorSpeed is a double type
     */

    public Computer(String processor, int numCores, double processorSpeed) {
        this.processor = processor;
        this.numCores = numCores;
        this.processorSpeed = processorSpeed;
    }

    // ----------------------------------------------------------
    /**
     * Add accessor called getProcessor.
     * @return the value of processor
     */
    public String getProcessor() {
        return processor;
    }

    // ----------------------------------------------------------
    /**
     * Add mutator called setProcessor.
     * @param processor is a String type
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    // ----------------------------------------------------------
    /**
     * Add accessor called getNumCores.
     * @return the number of cores
     */
    public int getNumCores() {
        return numCores;
    }

    // ----------------------------------------------------------
    /**
     * Add mutator called setNumCores.
     * @param numCores is an integer type
     */
    public void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    // ----------------------------------------------------------
    /**
     * Add accessor called getProcessorSpeed.
     * @return the number of processor speed
     */
    public double getProcessorSpeed() {
        return processorSpeed;
    }

    // ----------------------------------------------------------
    /**
     * Add mutator called setProcessorSpeed.
     * @param processorSpeed is a double type
     */
    public void setProcessorSpeed(double processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    // ----------------------------------------------------------
    /**
     * Create a method called computerPower.
     * @return the final value, which is numCores multiply by processorSpeed
     */
    public double computePower() {
        return numCores * processorSpeed;
    }

    /**
     * Create a method called toString.
     * @return the value of processor, numCores and processorSpeed
     */
    public String toString() {
        return processor + ", " + numCores
            + " cores at " + processorSpeed + " GHz";
    }


}
