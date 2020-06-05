import java.util.EmptyStackException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link LinkedStack} class.
 *
 * @author Yang Yi
 * @version 2015.10.19
 */
public class LinkedStackTest
    extends TestCase
{
    // ~ Fields ................................................................

    private LinkedStack<String> stack;

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        stack = new LinkedStack<String>();
    }


    // ----------------------------------------------------------
    /**
     * Create a test method called testPush
     */
    public void testPush()
    {
        stack.push(null);
        stack.push("number1");
        assertEquals(stack.peek(), "number1");
        stack.push("number2");
        assertEquals(stack.peek(), "number2");
    }


    // ----------------------------------------------------------
    /**
     * Create a test method called testPop
     */
    public void testPop()
    {
        Exception thrown = null;

        try
        {
            stack.pop();
        }
        catch (EmptyStackException e)
        {
            thrown = e;
        }

        assertNotNull(thrown);

        stack.push("number1");
        stack.push("number2");
        stack.pop();
        assertEquals(stack.peek(), "number1");

    }


    // ----------------------------------------------------------
    /**
     * Create a test method called testPeek
     */
    public void testPeek()
    {
        Exception thrown = null;

        try
        {
            stack.peek();
        }
        catch (EmptyStackException e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        stack.push("number1");
        assertEquals(stack.peek(), "number1");
    }


    // ----------------------------------------------------------
    /**
     * Create a test method called testIsEmpty
     */
    public void testIsEmpty()
    {
        assertTrue(stack.isEmpty());
        stack.push("number1");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }
}
