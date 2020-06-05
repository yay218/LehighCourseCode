import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Test cases for the Levenshtein distance algorithm.
 *
 * @author Yang Yi
 * @version 2015.09.22
 */
public class LevenshteinTests
    extends TestCase
{
    // ~ Public methods ..................................................

    // Add test methods here.
    // ----------------------------------------------------------
    /**
     * Create a method called testCase1
     */
    public void testCase1()
    {
        Levenshtein test1 = new Levenshtein("clap", "cram");
        assertEquals(2, test1.distance());
    }


    // ----------------------------------------------------------
    /**
     * Create a method called testCase2
     */
    public void testCase2()
    {
        Levenshtein test2 = new Levenshtein("mitt", "smitten");
        assertEquals(3, test2.distance());
    }


    // ----------------------------------------------------------
    /**
     * Create a method called testCase3
     */
    public void testCase3()
    {
        Levenshtein test3 = new Levenshtein("start", "cart");
        assertEquals(2, test3.distance());
    }


    // ----------------------------------------------------------
    /**
     * Create a method called testCase4
     */
    public void testCase4()
    {
        Levenshtein test4 =
            new Levenshtein("this is a long sentence", "this sentence is long");
        assertEquals(15, test4.distance());
    }
}
