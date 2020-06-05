import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link Node} class.
 *
 * @author Yang Yi
 * @version 2015.10.19
 */
public class NodeTest
    extends TestCase
{
    // ~ Fields ................................................................

    private Node<String> node1;
    private Node<String> node2;
    private Node<String> node3;
    private Node<String> node4;

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Create some new nodes for each test method.
     */
    public void setUp()
    {
        node1 = new Node<String>("node1");
        node2 = new Node<String>("node2");
        node3 = new Node<String>("node3");
        node4 = new Node<String>("node4");
    }


    // ----------------------------------------------------------
    /**
     * Create a test class called testJoin
     */
    public void testJoin()
    {
        Node<String> node = null;
        assertEquals(node1.join(node), node1);

        node1.join(node2);

        Exception thrown = null;
        try
        {
            node1.join(node3);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(
            "This node already has another node following it",
            thrown.getMessage());

        try
        {
            node4.join(node2);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertTrue(thrown instanceof IllegalStateException);
        assertEquals(
            "This node already has another node preceding it",
            thrown.getMessage());

    }


    // ----------------------------------------------------------
    /**
     * Create a test class called testSplit
     */
    public void testSplit()
    {
        assertEquals(node1.split(), null);
        node1.join(node2);
        assertEquals(node1.split(), node2);
    }
}
