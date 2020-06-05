package cs2114;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  This is Lab08DequeTest class to test all the methods in Lab08Deque
 *
 *  @author Yang Yi
 *  @version 2015.10.26
 */
public class Lab08DequeTest extends TestCase
{
    private Lab08Deque<String> deque;

 // ----------------------------------------------------------
    /**
     * Create a method called setUp
     */
    public void setUp() {
        deque = new Lab08Deque<String>();
    }

    // ----------------------------------------------------------
    /**
     * test enqueueAtFront
     */
    public void testEnqueueAtFront() {
        deque.enqueueAtFront("test1");
        assertEquals("test1", deque.frontItem());
    }

    // ----------------------------------------------------------
    /**
     * test dequeueAtFront
     */
    public void testDequeueAtFront()
    {
        deque.enqueueAtFront("test1");
        deque.enqueueAtFront("test2");
        assertEquals("test2", deque.dequeueAtFront());
        assertEquals("test1", deque.dequeueAtFront());

    }

    // ----------------------------------------------------------
    /**
     * test enqueueAtRear
     */
    public void testEnqueueAtRear()
    {
        deque.enqueueAtRear("test1");
        assertEquals("test1", deque.rearItem());
    }

    // ----------------------------------------------------------
    /**
     * test dequeueAtRear.
     */
    public void testDequeueAtRear()
    {
        deque.enqueueAtRear("test1");
        deque.enqueueAtRear("test2");
        assertEquals("test2", deque.dequeueAtRear());
        assertEquals("test1", deque.dequeueAtRear());
    }

    // ----------------------------------------------------------
    /**
     * test frontItem
     */
    public void testFrontItem()
    {
        deque.enqueueAtFront("test1");
        assertEquals("test1", deque.frontItem());
    }

    // ----------------------------------------------------------
    /**
     * test rearItem
     */
    public void testRearItem()
    {
        deque.enqueueAtRear("test1");
        assertEquals("test1", deque.rearItem());
    }

    // ----------------------------------------------------------
    /**
     * test clear
     */
    public void testClear()
    {
        deque.enqueueAtFront("test1");
        deque.enqueueAtRear("test2");
        deque.clear();
        assertEquals(0, deque.size());
    }

    // ----------------------------------------------------------
    /**
     * test size
     */
    public void testSize()
    {
        assertEquals(0, deque.size());
        deque.enqueueAtFront("test1");
        assertEquals(1, deque.size());
    }

    // ----------------------------------------------------------
    /**
     * test toString
     */
    public void testToString() {
        deque.enqueueAtFront("test1");
        deque.enqueueAtRear("test2");
        assertEquals("[test1, test2]", deque.toString());
    }
}
