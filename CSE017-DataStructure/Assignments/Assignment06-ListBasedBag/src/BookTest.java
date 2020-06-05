import student.TestCase;

//-------------------------------------------------------------------------
/**
 * Tests for the {@link Book} class.
 *
 * @author  Stephen Edwards (authored class skeleton)
 * @author Yang Yi
 * @version 2015.10.07
 */
public class BookTest
    extends TestCase
{
    //~ Instance/static variables .............................................

    private Book book;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public BookTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Creates a brand new book as the test fixture for each test method.
     */
    public void setUp()
    {
        book = new Book(
            "Data Structures: Abstraction and Design Using Java",
            "Elliot B. Koffman and Paul A. T. Wolfgang",
            "978-0-470-12870-1");
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Book#Book(String, String, String)}.
     */
    public void testBook()
    {
        assertEquals(
            "Data Structures: Abstraction and Design Using Java",
            book.getTitle());
        assertEquals(
            "Elliot B. Koffman and Paul A. T. Wolfgang",
            book.getAuthor());
        assertEquals(
            "978-0-470-12870-1",
            book.getIsbn());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Book#setTitle(String)}.
     */
    public void testSetTitle()
    {
        book.setTitle("A Different Title");
        assertEquals("A Different Title", book.getTitle());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Book#setAuthor(String)}.
     */
    public void testSetAuthor()
    {
        book.setAuthor("Joe Hokie");
        assertEquals("Joe Hokie", book.getAuthor());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Book#setIsbn(String)}.
     */
    public void testSetIsbn()
    {
        book.setIsbn("1234567890");
        assertEquals("1234567890", book.getIsbn());
    }


    // ----------------------------------------------------------
    /**
     * Test method for {@link Book#toString()}.
     */
    public void testToString()
    {
        assertEquals(
            "Data Structures: Abstraction and Design Using Java, "
            + "Elliot B. Koffman and Paul A. T. Wolfgang, "
            + "978-0-470-12870-1",
            book.toString());
    }

    // ----------------------------------------------------------
    /**
     * Test method for equals() method
     */
    public void testEquals() {
        Object obj = new Object();
        assertEquals(book, book);
        assertFalse(book.equals(obj));
    }
}
