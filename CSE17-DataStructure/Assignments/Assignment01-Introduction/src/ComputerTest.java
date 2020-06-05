import student.*;

// -------------------------------------------------------------------------
/**
 *  Unit tests for the {@link Computer} class.
 *
 *  @author  Yang Yi
 *  @version 2015.08.28)
 */
public class ComputerTest
    extends student.TestCase
{

    /**
     * Add a field to the test class to hold a Computer object to use in tests.
     */
    //~ Instance/static fields ...............................................
    Computer computer;


    //~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new ComputerTest object.
     */
    public ComputerTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        computer = new Computer("Core Duo", 2, 2.0);

    }


    // ----------------------------------------------------------
    /**
     * Create a method called testGetProcessor.
     */
    public void testGetProcessor()
    {
        assertEquals("Core Duo", computer.getProcessor());
    }

    // ----------------------------------------------------------
    /**
     * Create a method called testGetNumCores.
     */
    public void testGetNumCores() {
        assertEquals(2, computer.getNumCores());
    }

    // ----------------------------------------------------------
    /**
     * Create a method called testGetProcessorSpeed.
     */
    public void testGetProcessorSpeed() {
        assertEquals(2.0, computer.getProcessorSpeed(), 0.01);
    }

    // ----------------------------------------------------------
    /**
     * Create a method called testComputerPower.
     */
    public void testComputePower() {
        assertEquals(4.0, computer.computePower(), 0.01);
    }

    // ----------------------------------------------------------
    /**
     * Create a method called testToString.
     */
    public void testToString() {
        assertEquals("Core Duo, 2 cores at 2.0 GHz", computer.toString());
    }
}
