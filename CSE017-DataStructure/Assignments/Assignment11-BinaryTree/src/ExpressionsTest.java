import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Create a simple test class with just one test case
 *
 * @author Yang Yi
 * @version Nov 12, 2015
 */
public class ExpressionsTest
    extends TestCase
{
    /**
     *
     */
    Expressions test = new Expressions();

    // ----------------------------------------------------------
    /**
     * test main method
     */
    public void testMain()
    {

        Expressions.main(null);
        assertNotNull(test);
        // Check that some string was printed somewhere in the output
        assertTrue(
            systemOut().getHistory()
                .contains("(((a) -(b) ) *(((c) +(d) ) /(e) ) )"));
    }
}
