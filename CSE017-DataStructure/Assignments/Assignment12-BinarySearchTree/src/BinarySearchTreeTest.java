import student.TestCase;

// -------------------------------------------------------------------------
/**
 *  This is the test case for BinarySearchTree
 *
 *  @author Yang yi
 *  @version Nov 19, 2015
 */
public class BinarySearchTreeTest extends TestCase
{
    private BinarySearchTree<String> test1;

    /**
     * Create a method called setUp
     */
    public void setUp()
    {
        test1 = new BinarySearchTree<String>();
    }

    // ----------------------------------------------------------
    /**
     * test remove
     */
    public void testRemove()
    {
        BinarySearchTree<String> test2 = new BinarySearchTree<String>();
        test2.insert("11");
        test2.insert("111");
        test2.insert("1");
        test2.remove("11");
        assertNotNull(test2.find("111"));
        assertNull(test2.find("11"));

        BinarySearchTree<String> test3 = new BinarySearchTree<String>();
        test3.insert("11");
        test3.insert("111");
        test3.insert("1");
        test3.remove("1");
        test3.remove("111");
        assertNotNull(test3.find("11"));
        assertNull(test3.find("1"));
        assertNull(test3.find("111"));



        test1.insert("5");
        test1.insert("10");
        assertNotNull(test1.find("5"));
        test1.remove("5");
        assertNull(test1.find("5"));

        test1.remove("10");
        assertNull(test1.find("10"));

        Exception thrown = null;
        try
        {
            test1.remove("111");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof ItemNotFoundException);

    }

    // ----------------------------------------------------------
    /**
     * test insert
     */
    public void testInsert()
    {
        test1.insert("1111");
        test1.insert("11");
        test1.insert("11111");
        test1.insert("1");
        test1.insert("111111");
        test1.insert("111");

        Exception thrown = null;
        try
        {
            test1.insert("111");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertNotNull(thrown);
        assertTrue(thrown instanceof DuplicateItemException);
    }

    // ----------------------------------------------------------
    /**
     * test findMin
     */
    public void testFindMin()
    {
        assertNull(test1.findMin());
        test1.insert("11");
        test1.insert("1");
        test1.insert("111");
        assertEquals("1", test1.findMin());
    }

    // ----------------------------------------------------------
    /**
     * test findMax
     */
    public void testFindMax()
    {
        assertNull(test1.findMax());
        test1.insert("1");
        test1.insert("11");
        test1.insert("111");
        assertEquals("111", test1.findMax());
    }

    // ----------------------------------------------------------
    /**
     * test find
     */
    public void testFind()
    {
        assertNull(test1.find("1"));
        test1.insert("1");
        assertNotNull(test1.find("1"));
    }

    // ----------------------------------------------------------
    /**
     * test makeEmpty
     */
    public void testMakeEmpty()
    {
        test1.insert("1");
        assertFalse(test1.isEmpty());
        test1.makeEmpty();
        assertTrue(test1.isEmpty());
    }

    // ----------------------------------------------------------
    /**
     * test isEmpty
     */
    public void testIsEmpty()
    {
        assertTrue(test1.isEmpty());
        test1.insert("1");
        assertFalse(test1.isEmpty());
    }


}
