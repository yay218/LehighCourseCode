//-------------------------------------------------------------------------
/**
 * The Bag interface defines the operations on a bag collection.
 * A bag is generic.  Use the Item type parameter to define what
 * kind of objects a given bag will hold.
 *
 * @param <T> The type of items this bag will hold.
 *
 * @author John Lewis, with modifications by Tony Allevato
 * @version 2010.09.22
 */
public interface Bag<T>
{
    // ----------------------------------------------------------
    /**
     * Adds the specified element to the bag.
     *
     * @param element item to be added
     * @precondition parameter element is not null
     */
    public void add(T element);


    // ----------------------------------------------------------
    /**
     * Removes and returns the specified element from the bag.  If multiple
     * copies of the same element appear in the bag, only one is removed.
     *
     * @param target item to be removed
     * @return the item removed or null if not found
     * @precondition parameter target is not null
     * @postcondition returned value x.equals(target)
     */
    public T remove(T target);


    // ----------------------------------------------------------
    /**
     * Removes and returns a random element from the bag.
     *
     * @return the element removed or null if the bag is empty
     */
    public T removeRandom();


    // ----------------------------------------------------------
    /**
     * Determines if the bag contains the specified element.
     *
     * @param target element to be found
     * @return true if target is in the collection, false otherwise
     * @precondition parameter target is not null
     */
    public boolean contains(T target);


    // ----------------------------------------------------------
    /**
     * Determines if the bag contains no elements.
     *
     * @return true if collection is empty, false otherwise
     */
    public boolean isEmpty();


    // ----------------------------------------------------------
    /**
     * Determines the number of elements currently in the bag.
     *
     * @return the number of elements in the bag
     */
    public int size();


    // ----------------------------------------------------------
    /**
     * Returns a string representation of this bag.  A bag's string
     * representation is written as a comma-separated list of its
     * contents (in some arbitrary order) surrounded by curly braces,
     * like this:
     * <pre>
     * {52, 14, 12, 119, 73, 80, 35}
     * </pre>
     * <p>
     * An empty bag is simply {}.
     * </p>
     *
     * @return a string representation of the bag and its contents
     */
    public String toString();
}