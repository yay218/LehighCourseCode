package cs2114;
//-------------------------------------------------------------------------
/**
 * Defines operations on a deque (a double-ended queue).  You can imagine a
 * deque as a sequence of items arranged in a line from left to right.
 * We can call the left end the "front" and the right end the "rear".
 * Operations are provided to insert or remove items from both ends.
 * <p>
 * In preconditions and postconditions, you will see see the following
 * shorthand used:
 * </p>
 * <pre>
 * [contents]        // Represents the sequence of items in the queue
 * [old-contents]    // Represents the sequence of items before a method
 * [new-contents]    // Represents the sequence of items after a method
 * [x]               // Represents a sequence containing the single item x
 * [x] * [y]         // Represents the concatenation of two sequences
 * []                // Represents an empty sequence
 * |[contents]|      // Represents the length of a sequence
 * result            // Return value of the method
 * </pre>
 *
 * @param <T> The type of elements contained in the deque.
 *
 * @author Stephen Edwards, Manuel A. Perez-Quinones
 * @version 2014.10.08
 */
public interface Deque<T>
{

    // ----------------------------------------------------------
    /**
     * Insert a new item at the front (the head) of the deque.
     * @param value the item to insert.
     * @postcondition [new-contents] = [value] * [old-contents]
     */
    public void enqueueAtFront(T value);


    // ----------------------------------------------------------
    /**
     * Remove the item at the front (the head) of the deque.
     * @return The item that was removed
     * @precondition |[old-contents]| > 0
     * @postcondition [old-contents] == [result] * [new-contents]
     */
    public T dequeueAtFront();

    // ----------------------------------------------------------
    /**
     * Insert a new item at the rear (the tail) of the deque.
     * @param value the item to insert.
     * @postcondition [new-contents] == [old-contents] * [value]
     */
    public void enqueueAtRear(T value);


    // ----------------------------------------------------------
    /**
     * Remove the item at the rear (the tail) of the deque.
     * @return The item that was removed
     * @precondition |[old-contents]| > 0
     * @postcondition [old-contents] = [new-contents] * [result]
     */
    public T dequeueAtRear();

    // ----------------------------------------------------------
    /**
     * Get the item at the front (the head) of the deque.
     * Does not alter the deque.
     * @return the item at the front of the deque.
     * @precondition |[contents]| > 0
     * @postcondition [new-contents] == [old-contents] and
     *     [contents] = [result] * [rest-of-items]
     */
    public T frontItem();


    // ----------------------------------------------------------
    /**
     * Get the item at the rear (the tail) of the deque.
     * Does not alter the deque.
     * @return the item at the rear of the deque.
     * @precondition |[contents]| > 0
     * @postcondition [new-contents] == [old-contents] and
     *     [contents] = [rest-of-items] * [result]
     */
    public T rearItem();


    // ----------------------------------------------------------
    /**
     * Get the number of items in this deque.
     * Does not alter the deque.
     * @return The number of items this deque contains.
     * @postcondition result = |[contents]|
     */
    public int size();


    // ----------------------------------------------------------
    /**
     * Empty the deque.
     * @postcondition [new-contents] = []
     */
    public void clear();


    // ----------------------------------------------------------
    /**
     * Returns a string representation of this deque.  A deque's string
     * representation is written as a comma-separated list of its
     * contents (in front-to-rear order) surrounded by square brackets,
     * like this:
     * <pre>
     * [52, 14, 12, 119, 73, 80, 35]
     * </pre>
     * <p>
     * An empty deque is simply <code>[]</code>.
     * </p>
     *
     * @return a string representation of the deque
     */
    public String toString();
}
