//-------------------------------------------------------------------------
/**
 * The Book class represents a simple book with a title (string),
 * author (string), and ISBN number (string).
 *
 * @author John Lewis (authored class skeleton)
 * @author Yang Yi
 * @version 2015.10.07
 */
public class Book
{
    //~ Instance/static variables .............................................

    /**
     * The book title.
     */
    protected String title;

    /**
     * The book author.
     */
    protected String author;

    /**
     * The book ISBN number.
     */
    protected String isbn;


    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Book object using the specified data.
     *
     * @param title  The book's title, which cannot be null
     * @param author The book's author, which cannot be null
     * @param isbn   The book's ISBN, which cannot be null

     * @precondition parameter title is not null
     *               and parameter author is not null
     *               and parameter isbn is not null
     */
    public Book(String title, String author, String isbn)
    {
        //assert title  != null : "A non-null title is required";
        //assert author != null : "A non-null author is required";
        //assert isbn   != null : "A non-null isbn is required";

        // Note the use of this.* to refer to fields, when the constructor's
        // parameters have the same names.
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }


    //~ Public methods ........................................................

    // ----------------------------------------------------------
    /**
     * Getter for the book title.
     *
     * @return the title of the book
     * @postcondition returned value is not null
     */
    public String getTitle()
    {
        return title;
    }


    // ----------------------------------------------------------
    /**
     * Setter for the book title.
     *
     * @param title the new title of the book
     * @precondition parameter title is not null
     */
    public void setTitle(String title)
    {
        //assert title != null : "A non-null title is required";
        this.title = title;
    }


    // ----------------------------------------------------------
    /**
     * Getter for the author name.
     *
     * @return the author of the book
     * @postcondition returned value is not null
     */
    public String getAuthor()
    {
        return author;
    }


    // ----------------------------------------------------------
    /**
     * Setter for the author name.
     *
     * @param author the new author of the book
     * @precondition parameter author is not null
     */
    public void setAuthor(String author)
    {
        //assert author != null : "A non-null author is required";
        this.author = author;
    }


    // ----------------------------------------------------------
    /**
     * Getter for the ISBN number.
     *
     * @return the ISBN of the book
     * @postcondition returned value is not null
     */
    public String getIsbn()
    {
        return isbn;
    }


    // ----------------------------------------------------------
    /**
     * Setter for the ISBN number.
     *
     * @param isbn the new isbn of the book
     * @precondition parameter isbn is not null
     */
    public void setIsbn(String isbn)
    {
        //assert isbn != null : "A non-null isbn is required";
        this.isbn = isbn;
    }


    // ----------------------------------------------------------
    /**
     * Returns a summary description of the book.
     *
     * @return a string representation of the book
     * @postcondition returned value is not null
     */
    public String toString()
    {
        return title + ", " + author + ", " + isbn;
    }

    // ----------------------------------------------------------
    /**
     * Create a method called equals
     *
     *@param anObject is Object
     * @return boolean type
     */
    public boolean equals(Object anObject) {
        if (anObject instanceof Book) {
            Book book = (Book)anObject;
            return book.getTitle().equals(getTitle());
        }
        return false;
    }
}