import java.util.Iterator;
import java.util.NoSuchElementException;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests for the {@link ArrayStack} class.
 *
 * @author Yang Yi
 * @version 2015.11.02
 */
public class ArrayStackTest
    extends TestCase
{
    // ~ Instance/static variables .............................................

    private Stack<String>    stack;
    private Iterator<String> iterator;

    // ~ Constructor ...........................................................


    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public ArrayStackTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Creates a brand new, empty stack for each test method.
     */
    public void setUp()
    {
        stack = new ArrayStack<String>();
    }


    // ----------------------------------------------------------
    /**
     * Test the push() method.
     */
    public void testPush()
    {
        stack.push("hello");
        assertEquals(1, stack.size());
        assertEquals("hello", stack.top());

        stack.push("goodbye");
        assertEquals(2, stack.size());
        assertEquals("goodbye", stack.top());

        stack.push("hi");
    }


    // ----------------------------------------------------------
    /**
     * Test the pop() method.
     */
    public void testPop()
    {
        String word = "hello";
        stack.push(word);
        assertEquals(1, stack.size());

        stack.pop();

        // After removal, make sure the inserted object is no longer there.
        assertEquals(0, stack.size());
    }


    // ----------------------------------------------------------
    /**
     * Test the topAndPop() method.
     */
    public void testTopAndPop()
    {
        String word = "hello";
        stack.push(word);

        // Use assertSame() to ensure the specific object added is the
        // one returned here
        assertSame(word, stack.topAndPop());

        // After removal, make sure the inserted object is no longer there.
        assertEquals(0, stack.size());
    }


    // ----------------------------------------------------------
    /**
     * Test clear() with multiple values in the stack.
     */
    public void testRemove3()
    {
        String word1 = "hello";
        stack.push(word1);
        String word2 = "goodbye";
        stack.push(word2);
        assertEquals(2, stack.size());

        stack.clear();
        assertEquals(0, stack.size());
    }


    // ----------------------------------------------------------
    /**
     * create the test method for hasNext
     */
    public void testHasNext()
    {
        iterator = stack.iterator();
        assertFalse(iterator.hasNext());
        stack.push("hello");
        iterator = stack.iterator();
        assertTrue(iterator.hasNext());
    }


    // ----------------------------------------------------------
    /**
     * create the test method for next
     */
    public void testNext()
    {
        stack.push("hello");
        stack.push("goodbye");
        iterator = stack.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), "goodbye");
        iterator.next();
        assertFalse(iterator.hasNext());

        Exception occurred = null;
        try
        {
            iterator.next();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof NoSuchElementException);
        assertEquals(
            "There are no elements left to process",
            occurred.getMessage());
    }


    // ----------------------------------------------------------
    /**
     * create the test method for remove
     */
    public void testRemove()
    {
        stack.push("hello");
        stack.push("goodbye");
        iterator = stack.iterator();

        Exception occurred = null;
        try
        {
            iterator.remove();
        }
        catch (Exception exception)
        {
            occurred = exception;
        }
        assertNotNull(occurred);
        assertTrue(occurred instanceof IllegalStateException);

        iterator.next();
        iterator.remove();
        assertEquals(iterator.next(), "hello");
    }
}
