//-------------------------------------------------------------------------
/**
 * Defines operations on a stack collection.
 *
 * @param <Item> The type of elements contained in the stack.
 *
 * @author Mark Allen Weiss (with modifications by John Lewis, Stephen Edwards)
 * @version 2010.10.27
 */
public interface Stack<Item> extends Iterable<Item>
{
    // ----------------------------------------------------------
    /**
     * Insert a new item into the stack.
     * @param value the item to insert.
     */
    public void push(Item value);


    // ----------------------------------------------------------
    /**
     * Remove the most recently inserted item from the stack.
     * @precondition The stack is not empty.
     */
    public void pop();


    // ----------------------------------------------------------
    /**
     * Get the most recently inserted item in the stack.
     * Does not alter the stack.
     * @return the most recently inserted item in the stack.
     * @precondition The stack is not empty.
     */
    public Item top();


    // ----------------------------------------------------------
    /**
     * Return and remove the most recently inserted item
     * from the stack.
     * @return the most recently inserted item in the stack.
     * @precondition The stack is not empty.
     */
    public Item topAndPop();


    // ----------------------------------------------------------
    /**
     * Test if the stack is logically empty.
     * @return true if empty, false otherwise.
     */
    public int size();


    // ----------------------------------------------------------
    /**
     * Make the stack logically empty.
     */
    public void clear();
}
