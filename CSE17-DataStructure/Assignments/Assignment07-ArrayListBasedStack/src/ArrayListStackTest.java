import java.util.EmptyStackException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link ArrayListStack} class.
 *
 * @author Yang Yi
 * @version 2015.10.14
 */
public class ArrayListStackTest
    extends TestCase
{
    // ~ Instance/static variables .............................................

    private ArrayListStack<String> stack;

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        stack = new ArrayListStack<String>();
    }


    // ----------------------------------------------------------
    /**
     * test push method
     */
    public void testPush()
    {
        stack.push("number1");
        assertEquals("number1", stack.top());
        stack.push("number2");
        stack.push("number3");
        assertEquals("number3", stack.top());
    }


    // ----------------------------------------------------------
    /**
     * test pop method
     */
    public void testPop()
    {
        Exception thrown = null;
        try
        {
            stack.pop();
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);

        stack.push("number1");
        stack.pop();
        assertEquals(true, stack.isEmpty());
        stack.push("number2");
        stack.push("number3");
        stack.pop();
        assertEquals("number2", stack.top());
    }


    // ----------------------------------------------------------
    /**
     * test top method
     */
    public void testTop()
    {
        Exception thrown = null;
        try
        {
            stack.top();
        }
        catch (Exception exception)
        {
            thrown = exception;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof EmptyStackException);

        assertEquals(true, stack.isEmpty());
        stack.push("number1");
        assertEquals("number1", stack.top());
        assertEquals(false, stack.isEmpty());
    }


    // ----------------------------------------------------------
    /**
     * test size method
     */
    public void testSize()
    {
        assertEquals(0, stack.size());
        stack.push("number1");
        assertEquals(1, stack.size());
        stack.push("number2");
        assertEquals(2, stack.size());
    }


    // ----------------------------------------------------------
    /**
     * test isEmpty method
     */
    public void testIsEmpty()
    {
        assertEquals(true, stack.isEmpty());
        stack.push("number1");
        assertEquals(false, stack.isEmpty());
    }
}
